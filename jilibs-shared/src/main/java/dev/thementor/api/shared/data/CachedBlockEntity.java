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

package dev.thementor.api.shared.data;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Provides a cached block entity for specific types.
 *
 * @param <T> The type of {@link BlockEntity} that the cache will hold.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class CachedBlockEntity <T extends BlockEntity> implements BiFunction<PlayerInventory, BlockPos, T>
{
    /**
     * The class representing the type of block entity to cache.
     */
    private final Class<T> beClass;

    /**
     * A map to store cached block entities, keyed by their positions.
     */
    private final Map<BlockPos, T> cache = new HashMap<>();

    /**
     * Creates a new instance of CachedBlockEntity for a specific block entity type.
     *
     * @param beClass The class representing the type of block entity to cache.
     */
    public CachedBlockEntity(Class<T> beClass)
    {
        this.beClass = beClass;
    }

    /**
     * Retrieves a cached block entity at a specified position. If the block entity is not in the cache,
     * it will create and cache one.
     *
     * @param playerInventory The player's inventory containing the world information.
     * @param blockPos The position of the block entity.
     * @return A cached or newly created block entity at the specified position.
     * @throws IllegalArgumentException if the block entity at the given position is not an instance of T.
     */
    @Override
    public T apply(PlayerInventory playerInventory, BlockPos blockPos)
    {
        if(cache.containsKey(blockPos))
            return cache.get(blockPos);

        BlockEntity blockEntity = playerInventory.player.getEntityWorld().getBlockEntity(blockPos);
        if(beClass.isInstance(blockEntity))
        {
            T castedBE = beClass.cast(blockEntity);
            cache.put(blockPos, castedBE);
            return castedBE;
        }
        else
            throw new IllegalArgumentException("Block entity at " + blockPos + " is not of type " + beClass.getSimpleName());
    }
}