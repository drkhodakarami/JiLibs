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

package dev.thementor.api.shared.utils;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

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
    public static Direction primaryDirection(Vec3 vec)
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
    public static Direction facingDirection(Player player)
    {
        float yaw = player.getYRot();
        float pitch = player.getXRot();

        // Convert yaw to horizontal direction
        Direction horizontalDirection = Direction.fromYRot(yaw);

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
    @Developer("TheMentor")
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
            return direction.getCounterClockWise();
        if (facing == Direction.DOWN)
            // When looking down, rotate once clockwise
            return direction.getClockWise();

        // Calculate rotations based on facing direction
        switch (facing) {
            case SOUTH: // 180 degrees from north, need 2 rotations
                relative = relative.getClockWise().getClockWise();
                break;
            case EAST:  // 270 degrees from north, need 1 rotation counterclockwise
                relative = relative.getCounterClockWise();
                break;
            case WEST:  // 90 degrees from north, need 1 rotation clockwise
                relative = relative.getClockWise();
                break;
            case NORTH: // no rotation needed
            default:
                break;
        }

        return relative;
    }
}