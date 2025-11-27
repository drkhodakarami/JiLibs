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

import com.mojang.authlib.GameProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.portal.TeleportTransition;

import net.fabricmc.fabric.api.entity.FakePlayer;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

/**
 * Extends the Fabric API's FakePlayer to provide additional useful methods and behavior.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class UsefulFakePlayer extends FakePlayer
{
    /**
     * The reach distance of the fake player.
     */
    private double reach;

    /**
     * Private constructor to prevent instantiation.
     *
     * @param world The server level in which the fake player exists.
     * @param profile The game profile for the fake player.
     */
    protected UsefulFakePlayer(ServerLevel world, GameProfile profile)
    {
        super(world, profile);
    }

    /**
     * Overrides the base cooldown progress to prevent the attack strength from always being 0.03 due to not ticking.
     *
     * @param baseTime The base time for the cooldown progress calculation.
     * @return A fixed value of 1.
     */
    @Override
    public float getAttackStrengthScale(float baseTime)
    {
        return 1; // Prevent the attack strength from always being 0.03 due to not ticking.
    }

    /**
     * Overrides the item cooldown manager to prevent item cool downs due to the player not ticking.
     *
     * @return A new ItemCooldownManager instance.
     */
    @Override
    public @NotNull ItemCooldowns getCooldowns()
    {
        return new ItemCooldowns(); //Prevent item cool downs due to player not ticking
    }

    /**
     * Overrides the method to prevent them being targeted by mobs or other entities.
     *
     * @return Always returns false.
     */
    @Override
    public boolean canBeSeenByAnyone()
    {
        return false; //Prevent them being targeted by mobs?
    }

    /**
     * Simulates updating the item stack usage for the fake player.
     *
     * @param stack The item stack to update.
     */
    public void fakeUpdateUsingItem(ItemStack stack)
    {
        this.updateUsingItem(stack);
    }

    /**
     * Overrides the teleport method to create a new fake player at the target teleport location.
     *
     * @param teleportTarget The teleport target containing the new position and level.
     * @return The new fake player created at the target location.
     */
    @Override
    public @Nullable ServerPlayer teleport(TeleportTransition teleportTarget)
    {
        return createPlayer(teleportTarget.newLevel(), this.getGameProfile());
    }

    /**
     * Gets the reach distance of the fake player.
     *
     * @return The reach distance.
     */
    public double getReach() {
        return reach;
    }

    /**
     * Sets the reach distance of the fake player.
     *
     * @param reach The new reach distance to set.
     */
    public void setReach(double reach)
    {
        this.reach = reach;
    }

    /**
     * Creates a new instance of UsefulFakePlayer with the given server level and game profile.
     *
     * @param world The server level in which the fake player exists.
     * @param profile The game profile for the fake player.
     * @return A new instance of UsefulFakePlayer.
     */
    public static UsefulFakePlayer createPlayer(ServerLevel world, GameProfile profile)
    {
        return new UsefulFakePlayer(world, profile);
    }

    /**
     * Creates a new instance of UsefulFakePlayer with the default game profile from the Fabric API's FakePlayer.
     *
     * @param level The server level in which the fake player exists.
     * @return A new instance of UsefulFakePlayer.
     */
    public static UsefulFakePlayer createPlayer(ServerLevel level)
    {
        return new UsefulFakePlayer(level, FakePlayer.get(level).getGameProfile());
    }
}