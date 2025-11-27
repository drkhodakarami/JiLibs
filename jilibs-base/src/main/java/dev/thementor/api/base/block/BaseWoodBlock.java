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

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import dev.thementor.api.shared.interfaces.ITickedBlock;

public class BaseWoodBlock extends RotatedPillarBlock implements ITickedBlock
{
    public final boolean isStripped;

    public BaseWoodBlock(Properties settings, boolean isStripped)
    {
        super(settings);
        this.isStripped = isStripped;

        registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.Y).setValue(TICK_LEVEL, 0));
    }

    @Override
    public @NotNull BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        return withRandomTick(ctx, super.getStateForPlacement(ctx), this, 0, 20);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(TICK_LEVEL);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        super.randomTick(state, world, pos, random);
        tickBlock(state, world, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state)
    {
        return this.isStripped && state.getValue(TICK_LEVEL) > 0;
    }
}