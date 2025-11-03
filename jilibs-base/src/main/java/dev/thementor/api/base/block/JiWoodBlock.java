package dev.thementor.api.base.block;

import dev.thementor.api.base.interfaces.ITickedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class JiWoodBlock extends PillarBlock implements ITickedBlock
{
    public final boolean isStripped;

    public JiWoodBlock(Settings settings, boolean isStripped)
    {
        super(settings);
        this.isStripped = isStripped;

        setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.Y).with(TICK_LEVEL, 0));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return withRandomTick(ctx, super.getPlacementState(ctx), this, 0, 20);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(TICK_LEVEL);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        super.randomTick(state, world, pos, random);
        tickBlock(state, world, pos, random);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state)
    {
        return this.isStripped && state.get(TICK_LEVEL) > 0;
    }
}