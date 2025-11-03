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

import com.mojang.authlib.GameProfile;
import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.TeleportTarget;
import org.jetbrains.annotations.Nullable;

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
     * @param world The server world in which the fake player exists.
     * @param profile The game profile for the fake player.
     */
    protected UsefulFakePlayer(ServerWorld world, GameProfile profile)
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
    public float getAttackCooldownProgress(float baseTime)
    {
        return 1; // Prevent the attack strength from always being 0.03 due to not ticking.
    }

    /**
     * Overrides the item cooldown manager to prevent item cool downs due to the player not ticking.
     *
     * @return A new ItemCooldownManager instance.
     */
    @Override
    public ItemCooldownManager getItemCooldownManager()
    {
        return new ItemCooldownManager(); //Prevent item cool downs due to player not ticking
    }

    /**
     * Overrides the method to prevent them being targeted by mobs or other entities.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isPartOfGame()
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
        this.tickItemStackUsage(stack);
    }

    /**
     * Overrides the teleport method to create a new fake player at the target teleport location.
     *
     * @param teleportTarget The teleport target containing the new position and world.
     * @return The new fake player created at the target location.
     */
    @Override
    public @Nullable ServerPlayerEntity teleportTo(TeleportTarget teleportTarget)
    {
        return createPlayer(teleportTarget.world(), this.getGameProfile());
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
     * Creates a new instance of UsefulFakePlayer with the given server world and game profile.
     *
     * @param world The server world in which the fake player exists.
     * @param profile The game profile for the fake player.
     * @return A new instance of UsefulFakePlayer.
     */
    public static UsefulFakePlayer createPlayer(ServerWorld world, GameProfile profile)
    {
        return new UsefulFakePlayer(world, profile);
    }

    /**
     * Creates a new instance of UsefulFakePlayer with the default game profile from the Fabric API's FakePlayer.
     *
     * @param world The server world in which the fake player exists.
     * @return A new instance of UsefulFakePlayer.
     */
    public static UsefulFakePlayer createPlayer(ServerWorld world)
    {
        return new UsefulFakePlayer(world, FakePlayer.get(world).getGameProfile());
    }
}