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

import net.minecraft.world.entity.player.Player;

import dev.thementor.api.shared.annotations.*;

/**
 * Provides utility methods for experience (XP) calculations and management in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class XpHelper
{
    /**
     * Calculates the total experience required to reach a given level.
     *
     * @param level The target level for which to calculate the experience requirement.
     * @return The total experience required to reach the specified level.
     */
    public static int totalXpForLevel(int level)
    {
        if(level <= 16)
            return level * level + 6 * level;
        if(level <= 31)
            return (int) (2.5 * level * level - 40.5 * level + 360);
        long totalXp = (long) (4.5 * level * level - 162.5 * level + 2220);
        return (int) Math.min(totalXp, Integer.MAX_VALUE);
    }

    /**
     * Calculates the player's level based on their total experience.
     *
     * @param totalXp The total experience points of the player.
     * @return The calculated player level.
     */
    public static int levelFromTotalXp(int totalXp)
    {
        // Check in the level ranges where experience requirements change
        if(totalXp < totalXpForLevel(16))
            // Level range 0-15
            return (int) Math.floor((-6 + Math.sqrt(36 + 4 * totalXp)) / 2);
        if(totalXp < totalXpForLevel(31))
            // Level range 16-30
            return (int) Math.floor((40.5 + Math.sqrt(1_640.25 - 10.0 * (360 - totalXp))) / 5.0);
        // Level range 31+
        return (int) Math.floor((162.5 + Math.sqrt(26_406.25 - 18.0 * (2220 - totalXp))) / 9.0);
    }

    /**
     * Removes a specified number of levels from the player, ensuring they don't lose more than available.
     *
     * @param player The player entity to remove levels from.
     * @param levelsToRemove The number of levels to remove.
     * @return The total experience points removed.
     */
    public static int removeLevels(Player player, int levelsToRemove)
    {
        int currentTotalExp = playerTotalXp(player);
        int targetLevel = Math.max(0, player.experienceLevel - levelsToRemove);

        // Calculate how much exp is required to be at the target level
        int targetTotalExp = totalXpForLevel(targetLevel);
        int expToRemove = currentTotalExp - targetTotalExp;

        player.giveExperiencePoints(-levelsToRemove);
        return expToRemove;
    }

    /**
     * Calculates the experience required to go from one level to the next.
     *
     * @param level The current level.
     * @return The experience required for the next level.
     */
    public static int xpForNextLevel(int level)
    {
        if (level >= 30)
            return 112 + (level - 30) * 9;
        return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
    }

    /**
     * Calculates the total experience points a player currently has, considering their current level and progress.
     *
     * @param player The player entity to calculate experience for.
     * @return The total experience points of the player.
     */
    public static int playerTotalXp(Player player)
    {
        int exp = totalXpForLevel(player.experienceLevel);
        long totalXp = (long) exp + (long) Math.round(player.experienceProgress * player.getXpNeededForNextLevel());
        return (int) Math.min(totalXp, Integer.MAX_VALUE);
    }

    /**
     * Calculates the experience needed to reach the next level.
     *
     * @param player The player entity to calculate for.
     * @return The experience needed to reach the next level.
     */
    public static int xpNeededForNextLevel(Player player)
    {
        return player.getXpNeededForNextLevel() - (int) (player.experienceProgress * player.getXpNeededForNextLevel());
    }

    /**
     * Calculates the progress towards the next level as a fraction (float) based on total experience points.
     *
     * @param totalExp The total experience points of the player.
     * @return The fractional progress to the next level.
     */
    public static float progressToNextLevel(int totalExp)
    {
        int level = levelFromTotalXp(totalExp);  // Get the number of full levels
        int expForCurrentLevel = totalXpForLevel(level);  // Total exp required to reach this level
        int expForNextLevel = xpForNextLevel(level);  // Exp needed for the next level

        // Remaining experience after subtracting full levels
        int expAfterFullLevels = totalExp - expForCurrentLevel;

        // Calculate the fractional progress (as a float between 0.0 and 1.0)
        return (float) expAfterFullLevels / (float) expForNextLevel;
    }

    /**
     * Removes experience points from the player, ensuring they don't lose more than available.
     *
     * @param player The player entity to remove experience from.
     * @param pointsToRemove The number of experience points to remove.
     * @return The total experience points removed.
     */
    public static int removePoints(Player player, int pointsToRemove)
    {
        int currentTotalExp = playerTotalXp(player);
        int expToRemove = Math.min(currentTotalExp, pointsToRemove);
        player.giveExperiencePoints(-expToRemove);
        return expToRemove;  // Amount of exp removed
    }
}