/*
 * Copyright (c) 2025 Alireza Khodakarami
 *
 * Licensed under the MIT, (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/license/mit
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.thementor.api.base.block;

import com.mojang.serialization.MapCodec;
import dev.thementor.api.shared.interfaces.IWrench;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import dev.thementor.api.base.blockentity.AbstractBaseBE;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.IBEScreen;
import dev.thementor.api.shared.interfaces.ITickProvider;
import dev.thementor.api.shared.properties.BlockProperties;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public abstract class AbstractBaseBlock extends Block implements EntityBlock, ITickProvider
{
    public static MapCodec<? extends AbstractBaseBlock> CODEC;

    protected BlockProperties<?> properties;

    public AbstractBaseBlock(Properties settings, BlockProperties<?> properties)
    {
        super(settings);
        this.properties = properties;

        StateDefinition.Builder<Block, @NotNull BlockState> builder = new StateDefinition.Builder<>(this);
        createBlockStateDefinition(builder);

         this.stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
        registerDefaultState(this.stateDefinition.any());

        BlockState state = this.stateDefinition.any();
        state = this.properties.getStateProperties().applyDefaults(state);
        if(state != null)
            registerDefaultState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, @NotNull BlockState> builder)
    {
        super.createBlockStateDefinition(builder);

        if (this.properties != null && this.properties.getStateProperties() != null)
            this.properties.getStateProperties().addToBuilder(builder);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(@NotNull BlockPlaceContext ctx)
    {
        BlockState state = super.getStateForPlacement(ctx);
        if(state == null)
            return null;

        if(this.properties.getStateProperties().containsProperty(BlockStateProperties.HORIZONTAL_FACING))
        {
            Direction facing = ctx.getHorizontalDirection();
            if(this.properties.isFacingOpposite())
                facing = facing.getOpposite();
            state = state.setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
        }
        else if(this.properties.getStateProperties().containsProperty(BlockStateProperties.FACING))
        {
            Direction facing = ctx.getNearestLookingDirection();
            if(this.properties.isFacingOpposite())
                facing = facing.getOpposite();
            state = state.setValue(BlockStateProperties.FACING, facing);
        }

        if(this.properties.getStateProperties().containsProperty(BlockStateProperties.AXIS))
        {
            state = state.setValue(BlockStateProperties.AXIS, ctx.getClickedFace().getAxis());
        }

        return state;
    }

    @Override
    protected @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation)
    {
        if(this.properties.getStateProperties().containsProperty(BlockStateProperties.HORIZONTAL_FACING))
            return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
        else if(this.properties.getStateProperties().containsProperty(BlockStateProperties.FACING))
            return state.setValue(BlockStateProperties.FACING, rotation.rotate(state.getValue(BlockStateProperties.FACING)));

        return super.rotate(state, rotation);
    }

    @Override
    protected @NotNull BlockState mirror(@NotNull BlockState state, @NotNull Mirror mirror)
    {
        if(this.properties.getStateProperties().containsProperty(BlockStateProperties.HORIZONTAL_FACING))
            return state.setValue(BlockStateProperties.HORIZONTAL_FACING, mirror.mirror(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
        else if(this.properties.getStateProperties().containsProperty(BlockStateProperties.FACING))
            return state.setValue(BlockStateProperties.FACING, mirror.mirror(state.getValue(BlockStateProperties.FACING)));

        return super.mirror(state, mirror);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    {
        return (this.properties.getBEType() != null && this.properties.getBEFactory() != null)
                ? this.properties.getBEFactory().create(pos, state)
                : null;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type)
    {
        if(this.properties.isTickable())
            return ITickProvider.super.getTicker(level);
        return fallbackTicker(level, state, type);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> fallbackTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return getVanillaTicker(level, state, type);
    }

    @Override
    protected boolean hasAnalogOutputSignal(@NotNull BlockState state)
    {
        return this.properties.hasComparatorOutput();
    }

    @Override
    protected int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Direction direction)
    {
        return this.properties.hasComparatorOutput()
               ? this.properties.getComparatorOutput().apply(state, world, pos, direction)
               : super.getAnalogOutputSignal(state, world, pos, direction);
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state)
    {
        return this.properties.getRenderShape();
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context)
    {
        return this.properties.getShapeFactory().create(state, blockGetter, pos, context);
    }

    @Override
    protected void affectNeighborsAfterRemoval(@NotNull BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, boolean moved)
    {
        Containers.updateNeighboursAfterDestroy(state, world, pos);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hit)
    {
        if (this.properties.hasGUI())
        {
            if(this.properties.getBEType() == null)
                throw new IllegalArgumentException("BlockEntityType supplier cannot be null or return null when you want a gui on block entity! Check your constructor's properties. Either remove the .addGui method call or add a block entity supplier!");
            if (!world.isClientSide())
            {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (player instanceof ServerPlayer sPlayer && blockEntity instanceof IBEScreen<?> blockEntityWithGui)
                {
                    sPlayer.openMenu(blockEntityWithGui);
                }
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull BlockState playerWillDestroy(Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Player player)
    {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if(blockEntity instanceof AbstractBaseBE<?> be)
        {
            if (!level.isClientSide())
            {
                ItemStack stack = new ItemStack(blockState.getBlock());
                stack.applyComponents(be.collectComponents());
                ItemEntity entity = new ItemEntity(level, blockPos.getX() + 0.5F, blockPos.getY() + 0.5F, blockPos.getZ() + 0.5F, stack);
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
            }
        }
        return super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader world, @NotNull BlockPos pos)
    {
        return this.properties.canExistAt().test(world, pos);
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state, @NotNull LevelReader world, @NotNull ScheduledTickAccess tickView, @NotNull BlockPos pos, @NotNull Direction direction, @NotNull BlockPos neighborPos, @NotNull BlockState neighborState, @NotNull RandomSource random)
    {
        return !state.canSurvive(world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec()
    {
        return CODEC;
    }

    @Override
    protected @NotNull InteractionResult useItemOn(ItemStack itemStack, @NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult)
    {
        if(itemStack.getItem() instanceof IWrench)
            return InteractionResult.PASS;

        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    /**
     * Example Method Body:
     * <pre>
     *     {@code
     *          return validateTicker(ModBlockEntities.SOME_BE,
     *                               (world1, pos, state1, blockEntity) ->
     *                               {
     *                                   blockEntity.tick(world1, pos, state1);
     *                               });
     *     }
     * </pre>
     */
    protected <T extends BlockEntity> BlockEntityTicker<T> getVanillaTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return EntityBlock.super.getTicker(world, state, type);
    }

    @SuppressWarnings("unchecked")
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> validateTicker(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    public boolean hasInventory()
    {
        if(this.properties.getBEType() == null && this.properties.hasInventory())
            throw new IllegalArgumentException("BlockEntityType supplier cannot be null or return null when you want an inventory on block entity! Check your constructor's properties. Either remove the .addInventory method call or add a block entity supplier!");
        return this.properties.hasInventory();
    }
}