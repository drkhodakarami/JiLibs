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

import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a spreadable storage interface that can distribute its contents to neighboring blocks.
 *
 * @param <T> the type of the storage
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface ISpread<T>
{
    /**
     * Spreads the contents of the given storage to neighboring blocks.
     *
     * @param world   the world containing the block entity
     * @param pos     the position of the block entity
     * @param storage the storage to spread from
     */
    default void spread(Level world, BlockPos pos, T storage)
    {
        spread(world, pos, storage, null);
    }

    /**
     * Spreads the contents of the given storage to neighboring blocks, excluding specified positions.
     *
     * @param world       the world containing the block entity
     * @param pos         the position of the block entity
     * @param storage     the storage to spread from
     * @param exceptions  a set of block positions to exclude from spreading
     */
    default void spread(Level world, BlockPos pos, T storage, Set<BlockPos> exceptions)
    {
        spread(world, pos, storage, exceptions, true);
    }

    /**
     * Spreads the contents of the given storage to neighboring blocks with an option to use equal amounts.
     *
     * @param world       the world containing the block entity
     * @param pos         the position of the block entity
     * @param storage     the storage to spread from
     * @param equalAmount whether to distribute the contents equally among neighbors
     */
    default void spread(Level world, BlockPos pos, T storage, boolean equalAmount)
    {
        spread(world, pos, storage, null, equalAmount);
    }

    /**
     * Spreads the contents of the given storage to neighboring blocks, excluding specified positions and with an option to use equal amounts.
     *
     * @param world       the world containing the block entity
     * @param pos         the position of the block entity
     * @param storage     the storage to spread from
     * @param exceptions  a set of block positions to exclude from spreading
     * @param equalAmount whether to distribute the contents equally among neighbors
     */
    void spread(Level world, BlockPos pos, T storage, Set<BlockPos> exceptions, boolean equalAmount);
}