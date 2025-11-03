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

package dev.thementor.api.shared.enumerations;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;

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