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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import dev.thementor.api.shared.annotations.*;


/**
 * Represents a factory for creating block entity instances.
 *
 * @param <T> the type of the block entity
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@FunctionalInterface
public interface IBEFactory<T extends BlockEntity>
{
    /**
     * Creates a new block entity instance at the given position and with the specified block state.
     *
     * @param pos  the position where the block entity should be created
     * @param state the block state of the block
     * @return a new block entity instance
     */
    T create(BlockPos pos, BlockState state);
}