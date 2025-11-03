/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package dev.thementor.api.base.block;

import com.mojang.serialization.MapCodec;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.IBEScreen;
import dev.thementor.api.shared.interfaces.ITick;
import dev.thementor.api.shared.properties.BlockProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public abstract class JiBlock extends Block implements BlockEntityProvider
{
    public static MapCodec<? extends JiBlock> CODEC;

    protected BlockProperties<?> properties;

    public JiBlock(Settings settings, BlockProperties<?> properties)
    {
        super(settings);
        this.properties = properties;

        StateManager.Builder<Block, BlockState> builder = new StateManager.Builder<>(this);
        appendProperties(builder);

         this.stateManager = builder.build(Block::getDefaultState, BlockState::new);
        setDefaultState(this.stateManager.getDefaultState());

        BlockState state = this.stateManager.getDefaultState();
        state = this.properties.getStateProperties().applyDefaults(state);
        setDefaultState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);

        if (this.properties != null && this.properties.getStateProperties() != null)
            this.properties.getStateProperties().addToBuilder(builder);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockState state = super.getPlacementState(ctx);
        if(state == null)
            return null;

        if(this.properties.getStateProperties().containsProperty(Properties.HORIZONTAL_FACING))
        {
            Direction facing = ctx.getHorizontalPlayerFacing();
            if(this.properties.isFacingOpposite())
                facing = facing.getOpposite();
            state = state.with(Properties.HORIZONTAL_FACING, facing);
        }
        else if(this.properties.getStateProperties().containsProperty(Properties.FACING))
        {
            Direction facing = ctx.getPlayerLookDirection();
            if(this.properties.isFacingOpposite())
                facing = facing.getOpposite();
            state = state.with(Properties.FACING, facing);
        }

        if(this.properties.getStateProperties().containsProperty(Properties.AXIS))
        {
            state = state.with(Properties.AXIS, ctx.getSide().getAxis());
        }

        return state;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation)
    {
        if(this.properties.getStateProperties().containsProperty(Properties.HORIZONTAL_FACING))
            return state.with(Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING)));
        else if(this.properties.getStateProperties().containsProperty(Properties.FACING))
            return state.with(Properties.FACING, rotation.rotate(state.get(Properties.FACING)));

        return super.rotate(state, rotation);
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror)
    {
        if(this.properties.getStateProperties().containsProperty(Properties.HORIZONTAL_FACING))
            return state.with(Properties.HORIZONTAL_FACING, mirror.apply(state.get(Properties.HORIZONTAL_FACING)));
        else if(this.properties.getStateProperties().containsProperty(Properties.FACING))
            return state.with(Properties.FACING, mirror.apply(state.get(Properties.FACING)));

        return super.mirror(state, mirror);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return (this.properties.getBEType() != null && this.properties.getBEFactory() != null)
                ? this.properties.getBEFactory().create(pos, state)
                : null;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        if(this.properties.getBEType() == null && this.properties.isTickable())
            throw new IllegalArgumentException("BlockEntityType supplier cannot be null or return null when you want a ticking block entity! Check your constructor's properties. Either remove the .tick method call or add a block entity supplier!");
        if(this.properties.getBEType() != null && this.properties.isTickable())
        {
            if(this.properties.getBEType() instanceof ITick)
                ITick.createTicker(world);
            else
                getBETicker(world, state, type);
        }
        return null;
    }

    @SuppressWarnings("UnusedReturnValue")
    protected <T extends BlockEntity> BlockEntityTicker<T> getBETicker(World world, BlockState state, BlockEntityType<T> type)
    {
        //EXAMPLE USAGE FOR VANILLA BLOCKS WITHOUT THE INTERFACE IMPLEMENTATION
        /*return validateTicker(this.properties.blockEntityProperties().getBlockEntityType(),
                              (world1, pos, state1, blockEntity) ->
                              {
                                  blockEntity.tick(world1, pos, state1);
                              });*/
        return BlockEntityProvider.super.getTicker(world, state, type);
    }

    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> validateTicker(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        //noinspection unchecked
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state)
    {
        return this.properties.hasComparatorOutput();
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos, Direction direction)
    {
        return this.properties.hasComparatorOutput()
               ? this.properties.getComparatorOutput().apply(state, world, pos, direction)
               : super.getComparatorOutput(state, world, pos, direction);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state)
    {
        return this.properties.getRenderType();
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        return this.properties.getShapeFactory().create(state, world, pos, context);
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved)
    {
        ItemScatterer.onStateReplaced(state, world, pos);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (this.properties.hasGUI())
        {
            if(this.properties.getBEType() == null)
                throw new IllegalArgumentException("BlockEntityType supplier cannot be null or return null when you want a gui on block entity! Check your constructor's properties. Either remove the .addGui method call or add a block entity supplier!");
            if (!world.isClient())
            {
                BlockEntity blockEntity = world.getBlockEntity(pos);
                if (player instanceof ServerPlayerEntity sPlayer && blockEntity instanceof IBEScreen<?> blockEntityWithGui)
                {
                    sPlayer.openHandledScreen(blockEntityWithGui);
                }
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        return this.properties.canExistAt().test(world, pos);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random)
    {
        return !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected MapCodec<? extends Block> getCodec()
    {
        return CODEC;
    }

    public boolean hasInventory()
    {
        if(this.properties.getBEType() == null && this.properties.hasInventory())
            throw new IllegalArgumentException("BlockEntityType supplier cannot be null or return null when you want an inventory on block entity! Check your constructor's properties. Either remove the .addInventory method call or add a block entity supplier!");
        return this.properties.hasInventory();
    }
}