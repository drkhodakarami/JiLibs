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

package dev.thementor.api.shared.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.annotations.*;

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

public class CachedBlockEntity <T extends BlockEntity> implements BiFunction<Inventory, BlockPos, T>
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
    public T apply(Inventory playerInventory, BlockPos blockPos)
    {
        if(cache.containsKey(blockPos))
            return cache.get(blockPos);

        BlockEntity blockEntity = playerInventory.player.level().getBlockEntity(blockPos);
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