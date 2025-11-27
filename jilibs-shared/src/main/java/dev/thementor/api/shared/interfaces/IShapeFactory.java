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

package dev.thementor.api.shared.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a factory for creating voxel shapes.
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface IShapeFactory
{
    /**
     * Creates a new voxel shape based on the given block state, world context, position, and shape context.
     *
     * @param state  the block state of the block
     * @param world  the block view containing the block
     * @param pos    the position of the block in the world
     * @param context the shape context for determining visibility
     * @return the created voxel shape
     */
    VoxelShape create(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context);
}