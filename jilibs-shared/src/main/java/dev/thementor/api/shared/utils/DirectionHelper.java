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

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * Provides utility methods for working with directions in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20, TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class DirectionHelper
{
    /**
     * Determines the primary direction based on a vector.
     *
     * @param vec The vector to determine the direction from.
     * @return The primary direction.
     */
    @Developer("Direwolf20")
    public static Direction getPrimaryDirection(Vec3d vec)
    {
        double absX = Math.abs(vec.x);
        double absY = Math.abs(vec.y);
        double absZ = Math.abs(vec.z);

        // Determine the largest magnitude component
        if (absX > absY && absX > absZ)
            return vec.x > 0 ? Direction.EAST : Direction.WEST;
        if (absY > absX && absY > absZ)
            return vec.y > 0 ? Direction.UP : Direction.DOWN;
        return vec.z > 0 ? Direction.SOUTH : Direction.NORTH;
    }

    /**
     * Determines the facing direction for a player based on their yaw and pitch.
     *
     * @param player The player to determine the facing direction for.
     * @return The facing direction.
     */
    @Developer("Direwolf20")
    public static Direction getFacingDirection(PlayerEntity player)
    {
        float yaw = player.getYaw();
        float pitch = player.getPitch();

        // Convert yaw to horizontal direction
        Direction horizontalDirection = Direction.fromHorizontalDegrees(yaw);

        // Adjust for vertical direction if necessary (e.g., UP or DOWN)
        if (pitch < -45)
            return Direction.UP;
        if (pitch > 45)
            return Direction.DOWN;
        return horizontalDirection;
    }

    /**
     * Determines the relative direction based on a given direction and facing direction.
     *
     * @param direction The direction to convert.
     * @param facing The current facing direction.
     * @return The relative direction, or null if either parameter is null.
     */
    @Developer("The Mentor")
    public static Direction relativeDirection(@Nullable Direction direction, @Nullable Direction facing)
    {
        if(direction == null)
            return null;
        if(facing == null)
            return direction;
        if(direction.getAxis().isVertical())
            return direction;

        Direction relative = direction;

        // If looking straight up or down, and the input direction is vertical,
        // return the direction as is
        if (direction.getAxis().isVertical() && facing.getAxis().isVertical())
            return direction;

        // Handle vertical facings
        if (facing == Direction.UP)
            // When looking up, rotate once counterclockwise
            return direction.rotateYCounterclockwise();
        if (facing == Direction.DOWN)
            // When looking down, rotate once clockwise
            return direction.rotateYClockwise();

        // Calculate rotations based on facing direction
        switch (facing) {
            case SOUTH: // 180 degrees from north, need 2 rotations
                relative = relative.rotateYClockwise().rotateYClockwise();
                break;
            case EAST:  // 270 degrees from north, need 1 rotation counterclockwise
                relative = relative.rotateYCounterclockwise();
                break;
            case WEST:  // 90 degrees from north, need 1 rotation clockwise
                relative = relative.rotateYClockwise();
                break;
            case NORTH: // no rotation needed
            default:
                break;
        }

        return relative;
    }
}