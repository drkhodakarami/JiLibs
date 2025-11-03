package dev.thementor.api.base.interfaces;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public interface ITickedBlock
{
    String TICK_PROPERTY_NAME = "tick_level";
    IntProperty TICK_LEVEL = IntProperty.of(TICK_PROPERTY_NAME, 0, 20);

    default BlockState withRandomTick(ItemPlacementContext ctx, BlockState superState, Block block, int propertyMin, int propertyMax)
    {
        BlockState state = superState == null ? block.getDefaultState() : superState;
        return state.with(TICK_LEVEL, ctx.getWorld().random.nextBetween(propertyMin + 1, propertyMax));
    }

    default void tickBlock(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if(random.nextInt(10) == 0)
            world.setBlockState(pos, state.with(TICK_LEVEL, state.get(TICK_LEVEL) - 1));
    }
}