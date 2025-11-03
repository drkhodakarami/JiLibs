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

package dev.thementor.api.shared.utils;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;
import net.minecraft.util.math.*;

/**
 * Provides utility methods for position-related operations in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class PosHelper
{
    /**
     * Private constructor to prevent instantiation.
     */
    public PosHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Retrieves an array of positions next to the given block position (up, down, east, west, north, south).
     *
     * @param pos The base block position.
     * @return An array of adjacent block positions.
     */
    public static BlockPos[] positionNextTo(BlockPos pos)
    {
        return new BlockPos[]{pos.up(), pos.down(), pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Retrieves an array of positions adjacent to the given block position (east, west, north, south).
     *
     * @param pos The base block position.
     * @return An array of adjacent block positions.
     */
    public static BlockPos[] positionSideTo(BlockPos pos)
    {
        return new BlockPos[]{pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Retrieves an array of positions adjacent to the given block position (down, east, west, north, south).
     *
     * @param pos The base block position.
     * @return An array of adjacent block positions.
     */
    public static BlockPos[] positionSideBottom(BlockPos pos)
    {
        return new BlockPos[]{pos.down(), pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Retrieves an array of positions adjacent to the given block position (up, east, west, north, south).
     *
     * @param pos The base block position.
     * @return An array of adjacent block positions.
     */
    public static BlockPos[] positionSideTop(BlockPos pos)
    {
        return new BlockPos[]{pos.up(), pos.east(), pos.west(), pos.north(), pos.south()};
    }

    /**
     * Retrieves an array of positions above and below the given block position (up, down).
     *
     * @param pos The base block position.
     * @return An array of adjacent block positions.
     */
    public static BlockPos[] positionTopBottom(BlockPos pos)
    {
        return new BlockPos[]{pos.up(), pos.down()};
    }

    /**
     * Determines the direction to the left of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction to the left.
     */
    public static Direction left(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.WEST;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
            case EAST -> Direction.NORTH;
            default -> facing;
        };
    }

    /**
     * Determines the direction to the right of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction to the right.
     */
    public static Direction right(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.EAST;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            case EAST -> Direction.SOUTH;
            default -> facing;
        };
    }

    /**
     * Determines the direction in front of the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction in front.
     */
    public static Direction front(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.SOUTH;
            case SOUTH -> Direction.NORTH;
            case WEST -> Direction.EAST;
            case EAST -> Direction.WEST;
            default -> facing;
        };
    }

    /**
     * Determines the direction behind the given facing direction.
     *
     * @param facing The current facing direction.
     * @return The direction behind.
     */
    public static Direction back(Direction facing)
    {
        return switch (facing)
        {
            case NORTH -> Direction.NORTH;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
            case EAST -> Direction.EAST;
            default -> facing;
        };
    }

    /**
     * Returns the block position offset by the direction to the left of the given facing direction.
     *
     * @param pos The base block position.
     * @param facing The current facing direction.
     * @return The offset block position.
     */
    public static BlockPos left(BlockPos pos, Direction facing)
    {
        return pos.offset(left(facing));
    }

    /**
     * Returns the block position offset by the direction to the right of the given facing direction.
     *
     * @param pos The base block position.
     * @param facing The current facing direction.
     * @return The offset block position.
     */
    public static BlockPos right(BlockPos pos, Direction facing)
    {
        return pos.offset(right(facing));
    }

    /**
     * Returns the block position offset by the direction in front of the given facing direction.
     *
     * @param pos The base block position.
     * @param facing The current facing direction.
     * @return The offset block position.
     */
    public static BlockPos front(BlockPos pos, Direction facing)
    {
        return pos.offset(front(facing));
    }

    /**
     * Returns the block position offset by the direction behind the given facing direction.
     *
     * @param pos The base block position.
     * @param facing The current facing direction.
     * @return The offset block position.
     */
    public static BlockPos back(BlockPos pos, Direction facing)
    {
        return pos.offset(back(facing));
    }

    /**
     * Returns the block position above the given block position.
     *
     * @param pos The base block position.
     * @return The block position above.
     */
    public static BlockPos top(BlockPos pos)
    {
        return pos.up();
    }

    /**
     * Returns the block position below the given block position.
     *
     * @param pos The base block position.
     * @return The block position below.
     */
    public static BlockPos bottom(BlockPos pos)
    {
        return pos.down();
    }

    /**
     * Calculates the Euclidean distance between two block positions.
     *
     * @param pos The destination block position.
     * @param origin The starting block position.
     * @return The distance between the two positions.
     */
    public static double getDistance(BlockPos pos, BlockPos origin)
    {
        return Math.sqrt(Math.pow(pos.getX() - origin.getX(), 2) + Math.pow(pos.getY() - origin.getY(), 2) + Math.pow(pos.getZ() - origin.getZ(), 2));
    }

    /**
     * Calculates the Euclidean distance between two integer coordinates in a 3D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param z1 The z-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param z2 The z-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(int x1, int y1, int z1 , int x2, int y2, int z2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    /**
     * Calculates the Euclidean distance between two integer coordinates in a 2D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(int x1, int y1 , int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Calculates the Euclidean distance between two float coordinates in a 3D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param z1 The z-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param z2 The z-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(float x1, float y1, float z1 , float x2, float y2, float z2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    /**
     * Calculates the Euclidean distance between two float coordinates in a 2D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(float x1, float y1 , float x2, float y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Calculates the Euclidean distance between two double coordinates in a 3D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param z1 The z-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @param z2 The z-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(double x1, double y1, double z1 , double x2, double y2, double z2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    /**
     * Calculates the Euclidean distance between two double coordinates in a 2D space.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The distance between the two points.
     */
    public static double getDistance(double x1, double y1 , double x2, double y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Calculates the Euclidean distance between two 2D points represented by {@link Vec2f}.
     *
     * @param point1 The first point.
     * @param point2 The second point.
     * @return The distance between the two points.
     */
    public static double getDistance(Vec2f point1, Vec2f point2)
    {
        return getDistance(point1.x, point1.y, point2.x, point2.y);
    }

    /**
     * Calculates the Euclidean distance between two 3D points represented by {@link Vec3i}.
     *
     * @param point1 The first point.
     * @param point2 The second point.
     * @return The distance between the two points.
     */
    public static double getDistance(Vec3i point1, Vec3i point2)
    {
        return getDistance(point1.getX(), point1.getY(), point1.getZ(), point2.getX(), point2.getY(), point2.getZ());
    }

    /**
     * Calculates the Euclidean distance between two 3D points represented by {@link Vec3d}.
     *
     * @param point1 The first point.
     * @param point2 The second point.
     * @return The distance between the two points.
     */
    public static double getDistance(Vec3d point1, Vec3d point2)
    {
        return getDistance(point1.getX(), point1.getY(), point1.getZ(), point2.getX(), point2.getY(), point2.getZ());
    }
}