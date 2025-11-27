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

package dev.thementor.api.shared.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("unused")
public interface IWrench
{
    /**
     * This method is called when the player right-clicks a block with the wrench.
     * This method should be override in the wrench item implementations
     * This is the primary interaction point for configuration.
     *
     * @param context The context from using the item on a block method
     * @return true if the interaction consumed the action (e.g., it rotated a machine and should stop further processing).
     */
    default boolean onWrenchRightClickBlock(UseOnContext context)
    {
        return false;
    }

    /**
     * This method is called when the player right-clicks a block entity
     * This method should be override in the wrench item implementations.
     * The check to see if the block contains a block entity or not is responsibility of impl
     * with the wrench, allowing for specialized configuration of entities like pipes, machines, etc.
     *
     * @param user The player holding the wrench.
     * @param stack The item stack representing the wrench itself.
     * @param entity The entity that used the wrench on
     * @param hand The hand holding the wrench
     * @return true if the interaction consumed the action.
     */
    default boolean onWrenchRightClickBlockEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand)
    {
        return false;
    }

    /**
     * Determines the wrench's current mode or tool setting.
     * This is useful for wrenches that have multiple functions (e.g., "Rotate," "Configure," "Harvest").
     *
     * @param stack The wrench item stack.
     * @return An integer representing the current mode (e.g., 0=Rotate, 1=Configure).
     */
    default int getWrenchMode(ItemStack stack)
    {
        return -1;
    }

    /**
     * Cycles the wrench's mode to the next available setting.
     * This is typically called on an alternate action, like Shift + Right-Click.
     *
     * @param stack The wrench item stack to modify.
     * @param player The player performing the action (optional, can be null).
     * @return true if the mode was successfully changed.
     */
    default boolean cycleWrenchMode(ItemStack stack, Player player)
    {
        return false;
    }

    /**
     * Attempts to instantly break the block at the given position, consuming durability.
     * This simulates a wrench set to a dedicated 'harvest' or 'break' mode.
     *
     * @param level The level (dimension) where the action occurs.
     * @param pos The position of the block to break.
     * @param state The state of the block being broken.
     * @param player The player initiating the break (can be null if broken by other means).
     * @param stack The wrench item stack being used.
     * @return true if the block was successfully broken and removed from the world.
     */
    default boolean wrenchBreakBlock(Level level, BlockPos pos, BlockState state, Player player, ItemStack stack)
    {
        if(level.isClientSide())
            return false;

        return level.destroyBlock(pos, true, player);
    }
}