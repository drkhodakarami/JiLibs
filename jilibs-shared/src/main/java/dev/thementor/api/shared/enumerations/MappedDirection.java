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

package dev.thementor.api.shared.enumerations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.util.Util;
import net.minecraft.core.Direction;

import dev.thementor.api.shared.annotations.*;

/**
 * Enum representing mapped directions.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public enum MappedDirection
{
    /**
     * Represents the direction down.
     */
    DOWN,

    /**
     * Represents the direction up.
     */
    UP,

    /**
     * Represents the direction north.
     */
    NORTH,

    /**
     * Represents the direction south.
     */
    SOUTH,

    /**
     * Represents the direction west.
     */
    WEST,

    /**
     * Represents the direction east.
     */
    EAST,

    /**
     * Represents no specific direction.
     */
    NONE;

    /**
     * A bi-directional map between Minecraft Direction and MappedDirection for easy conversion.
     */
    static final BiMap<Direction, MappedDirection> biMap =
            Util.make(HashBiMap.create(),
                      map ->
                      {
                          map.put(null, MappedDirection.NONE);
                          map.put(Direction.DOWN, MappedDirection.DOWN);
                          map.put(Direction.UP, MappedDirection.UP);
                          map.put(Direction.NORTH, MappedDirection.NORTH);
                          map.put(Direction.SOUTH, MappedDirection.SOUTH);
                          map.put(Direction.EAST, MappedDirection.EAST);
                          map.put(Direction.WEST, MappedDirection.WEST);
                      });

    /**
     * Converts a Minecraft Direction to its corresponding MappedDirection.
     *
     * @param direction The Minecraft Direction to convert.
     * @return The corresponding MappedDirection.
     */
    public static MappedDirection fromDirection(Direction direction)
    {
        return biMap.get(direction);
    }

    /**
     * Converts an MappedDirection to its corresponding Minecraft Direction.
     *
     * @param direction The MappedDirection to convert.
     * @return The corresponding Minecraft Direction.
     */
    public static Direction toDirection(MappedDirection direction)
    {
        return biMap.inverse().get(direction);
    }
}