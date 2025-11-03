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
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Represents an interface for entities that can be ticked.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface ITick
{
    /**
     * Called on the server side to perform the entity's tick logic.
     */
    void tick();

    /**
     * Called on the client side to perform any client-specific tick logic (default implementation does nothing).
     */
    default void tickClient(){}

    /**
     * Creates a ticker for a given world that will call the appropriate tick method based on whether it is the server or client.
     *
     * @param <T> the type of the block entity
     * @param world the world containing the block entity
     * @return a BlockEntityTicker that will handle ticking the entity
     */
    static <T extends BlockEntity> BlockEntityTicker<T> createTicker(World world)
    {
        return !world.isClient()
               ? (pworld, pos, state, entity) -> ((ITick) entity).tick()
               : (pworld, pos, state, entity) -> ((ITick) entity).tickClient();
    }

    /**
     * Retrieves the block state of the entity's position in the world.
     *
     * @param world the world containing the entity
     * @param pos   the position of the entity
     * @return the block state at the given position, or null if the world is null
     */
    default BlockState getState(World world, BlockPos pos)
    {
        return world != null ? world.getBlockState(pos) : null;
    }
}