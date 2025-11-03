/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
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

package dev.thementor.api.shared.interfaces;

import dev.thementor.api.shared.annotations.*;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

/**
 * Represents a spreadable storage interface that can distribute its contents to neighboring blocks.
 *
 * @param <T> the type of the storage
 * @param <B> the type of the block entity or another storage entity
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
     * Simulates the insertion of a specified amount into the given storage without actually performing the operation.
     *
     * @param storage the storage to simulate insertion into
     * @param amount  the amount to simulate inserting
     * @param outer   the transaction context (if applicable)
     * @return the simulated insertion result
     */
    long simulateInsertion(T storage, long amount, Transaction outer);

    /**
     * Spreads the contents of the given storage to neighboring blocks.
     *
     * @param world   the world containing the block entity
     * @param pos     the position of the block entity
     * @param storage the storage to spread from
     */
    default void spread(World world, BlockPos pos, T storage)
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
    default void spread(World world, BlockPos pos, T storage, Set<BlockPos> exceptions)
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
    default void spread(World world, BlockPos pos, T storage, boolean equalAmount)
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
    void spread(World world, BlockPos pos, T storage, Set<BlockPos> exceptions, boolean equalAmount);
}