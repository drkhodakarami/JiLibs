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

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

/**
 * Provides utility methods for interacting with fake players in Minecraft.
 */
@SuppressWarnings({"unused", "SameParameterValue"})
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class FakePlayerHelper
{
    /**
     * Represents the result of an interaction with a fake player.
     */
    public record FakePlayerResult(InteractionResult actionResult, ItemStack returnStack) {}

    /**
     * Adds attributes to a fake player based on the item they are holding.
     *
     * @param player    The fake player.
     * @param itemStack The item stack the player is holding.
     * @param equipmentSlot The equipment slot of the item stack.
     */
    protected static void addAttributes(UsefulFakePlayer player, ItemStack itemStack, EquipmentSlot equipmentSlot)
    {
        AttributeMap container = player.getAttributes();
        if(!itemStack.isEmpty())
        {
            itemStack.forEachModifier(equipmentSlot, (attribute, modifier) ->
            {
                AttributeInstance attributeInstance = container.getInstance(attribute);
                if(attributeInstance != null)
                {
                    attributeInstance.removeModifier(modifier);
                    attributeInstance.addTransientModifier(modifier);
                }
            });
        }
    }

    /**
     * Removes attributes from a fake player based on the item they were holding.
     *
     * @param player    The fake player.
     * @param itemStack The item stack the player was holding.
     * @param equipmentSlot The equipment slot of the item stack.
     */
    protected static void removeAttributes(UsefulFakePlayer player, ItemStack itemStack, EquipmentSlot equipmentSlot)
    {
        AttributeMap container = player.getAttributes();
        if(!itemStack.isEmpty())
        {
            itemStack.forEachModifier(equipmentSlot, (attribute, modifier) ->
            {
                AttributeInstance attributeInstance = container.getInstance(attribute);
                if(attributeInstance != null)
                    attributeInstance.removeModifier(modifier);
            });
        }
    }

    /**
     * Sets up a fake player for use, placing them at the center of the specified direction and setting their equipment.
     *
     * @param player    The fake player to set up.
     * @param pos       The position of the tile entity being interacted with.
     * @param direction The direction from which the interaction is happening.
     * @param toHold    The item stack the player will be holding during the interaction.
     * @param sneaking  Whether the player should be sneaking.
     */
    public static void setupFakePlayerForUse(UsefulFakePlayer player, Vec3 pos, Direction direction, ItemStack toHold, boolean sneaking)
    {
        player.getInventory().getNonEquipmentItems().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.setXRot(xRot);
        player.setYRot(direction.toYRot());
        player.setYHeadRot(direction.toYRot());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.setPosRaw(pos.x + x, pos.y + y - player.getEyeY(), pos.z + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setShiftKeyDown(sneaking);
    }

    /**
     * Sets up a fake player for use, placing them at the center of the specified direction and setting their equipment.
     *
     * @param player    The fake player to set up.
     * @param pos       The position of the tile entity being interacted with.
     * @param direction The direction from which the interaction is happening.
     * @param toHold    The item stack the player will be holding during the interaction.
     * @param sneaking  Whether the player should be sneaking.
     */
    public static void setupFakePlayerForUse(UsefulFakePlayer player, BlockPos pos, Direction direction, ItemStack toHold, boolean sneaking)
    {
        player.getInventory().getNonEquipmentItems().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.absSnapRotationTo(direction.toYRot(), xRot);
        player.setYHeadRot(direction.toYRot());
        player.yHeadRotO = direction.toYRot();
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.absSnapTo(pos.getX() + x, pos.getY() + y - player.getEyeY(), pos.getZ() + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setShiftKeyDown(sneaking);
    }

    /**
     * Sets up a fake player for use, placing them at the center of the specified direction and setting their equipment.
     *
     * @param player    The fake player to set up.
     * @param pos       The position of the tile entity being interacted with.
     * @param direction The direction from which the interaction is happening.
     * @param toHold    The item stack the player will be holding during the interaction.
     * @param sneaking  Whether the player should be sneaking.
     */
    public static void setupFakePlayerForUse(UsefulFakePlayer player, BlockPos pos, Direction direction, Vec3 entityPosition, ItemStack toHold, boolean sneaking)
    {
        player.getInventory().getNonEquipmentItems().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.setXRot(xRot);
        player.setYRot(direction.toYRot());
        player.setYHeadRot(direction.toYRot());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getAxisDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.setPosRaw(pos.getX() + x, pos.getY() + y - player.getEyeY(), pos.getZ() + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setShiftKeyDown(sneaking);

        // Calculate the rotation angles to look at the target position
        Vec3 playerEyePos = new Vec3(player.getX(), player.getY() + player.getEyeY(), player.getZ());
        Vec3 toEntity = entityPosition.subtract(playerEyePos).normalize();
        float yaw = (float) Math.toDegrees(Math.atan2(toEntity.z, toEntity.x)) - 90.0F;
        float pitch = (float) Math.toDegrees(-Math.atan2(toEntity.y, Math.sqrt(toEntity.x * toEntity.x + toEntity.z * toEntity.z)));

        player.setYRot(yaw);
        player.setYHeadRot(yaw);  // Set both the body and head rotation to the calculated yaw
        player.setXRot(pitch);
    }

    /**
     * Cleans up a fake player after it has been used.
     *
     * @param player   The fake player.
     * @param oldStack The previous item stack held by the player.
     */
    public static void cleanupFakePlayerFromUse(UsefulFakePlayer player, ItemStack oldStack)
    {
        if (!oldStack.isEmpty())
            removeAttributes(player, oldStack, EquipmentSlot.MAINHAND);
        player.getInventory().getNonEquipmentItems().set(player.getInventory().getSelectedSlot(), ItemStack.EMPTY);
        if (!player.getInventory().isEmpty()) player.getInventory().dropAll(); //Handles bucket stacks, for example
        player.setShiftKeyDown(false);
        player.setReach(player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE));
    }

    /**
     * Simulates a right click interaction with an entity in the given direction.
     *
     * @param player    The fake player.
     * @param level     The level where the interaction occurs.
     * @param entity    The entity to interact with.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult clickEntityInDirection(UsefulFakePlayer player, Level level, LivingEntity entity, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceEntity(player, level, player.getReach());
        if (toUse == null) return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());

        if (clickType == 2) {
            ItemStack itemstack = player.getMainHandItem();
            if (itemstack.isEmpty()) {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            }
            if (!player.isUsingItem()) {
                player.startUsingItem(InteractionHand.MAIN_HAND);
            }
            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getTicksUsingItem();
            //System.out.println("Holding For: " + holdingFor);
            if (holdingFor >= maxHold) {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }
            return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }

        if (clickType == 0) { //RightClick
            if (processUseEntity(player, level, entity, toUse, InteractionType.INTERACT_AT))
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            else if (processUseEntity(player, level, entity, null, InteractionType.INTERACT))
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        } else { //Left Click
            if (processUseEntity(player, level, ((EntityHitResult) toUse).getEntity(), null, InteractionType.ATTACK))
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }

        return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
    }

    /**
     * Simulates a right or left click interaction with a block in the given direction.
     *
     * @param player    The fake player.
     * @param level     The level where the interaction occurs.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult clickBlockInDirection(UsefulFakePlayer player, Level level, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceBlock(player, level, player.getReach());

        ItemStack itemstack = player.getMainHandItem();
        if (clickType == 2) {
            if (itemstack.isEmpty())
            {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            }
            if (!player.isUsingItem())
                player.startUsingItem(InteractionHand.MAIN_HAND);

            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getTicksUsingItem();
            if (holdingFor >= maxHold)
            {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }
            return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }
        if (toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = level.getBlockState(blockpos);
            if (!state.isAir())
            {
                if (clickType == 0)
                {
                    InteractionResult type = player.gameMode.useItemOn(player, level, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult) toUse);
                    if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME)
                        return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
                else
                {
                    player.gameMode.handleBlockBreakAction(blockpos, ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK,
                                                                         ((BlockHitResult) toUse).getDirection(),
                                                                         player.level().getMaxY(), 0);
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
                }
            }
        }

        if (toUse.getType() == HitResult.Type.MISS) //Since we check for air before entering the method, there must be a block there, so try clicking it anyway
        {
            if (clickType == 0)
            {
                InteractionResult type = player.gameMode.useItemOn(player, level, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult) toUse);
                if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME)
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }
        }

        if (!itemstack.isEmpty())
        {
            InteractionResult type = player.gameMode.useItem(player, level, itemstack, InteractionHand.MAIN_HAND); //Uses the item by itself
            if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME)
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }
        return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
    }

    /**
     * Simulates a right click interaction with air in the given direction.
     *
     * @param player    The fake player.
     * @param level     The level where the interaction occurs.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult rightClickAirInDirection(UsefulFakePlayer player, Level level, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceBlock(player, level, player.getReach()); //Longer reach so it can connect with adjacent blocks to interact with them

        ItemStack itemstack = player.getMainHandItem();
        if (clickType == 2)
        {
            if (itemstack.isEmpty())
            {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
            }
            if (!player.isUsingItem())
                player.startUsingItem(InteractionHand.MAIN_HAND);

            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getTicksUsingItem();
            if (holdingFor >= maxHold)
            {
                player.releaseUsingItem();
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }
            return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }
        if (toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = level.getBlockState(blockpos);
            if (!state.isAir())
            {
                InteractionResult type = player.gameMode.useItemOn(player, level, itemstack, InteractionHand.MAIN_HAND, (BlockHitResult) toUse);
                if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME)
                    return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
            }
        }

        if (!itemstack.isEmpty())
        {
            InteractionResult type = player.gameMode.useItem(player, level, itemstack, InteractionHand.MAIN_HAND); //Uses the item by itself
            if (type == InteractionResult.SUCCESS || type == InteractionResult.CONSUME)
                return new FakePlayerResult(InteractionResult.SUCCESS, player.getMainHandItem());
        }
        return new FakePlayerResult(InteractionResult.FAIL, player.getMainHandItem());
    }

    /**
     * Simulates an attack with whatever the fake player is holding in the given direction.
     *
     * @param player      The fake player.
     * @param level       The level where the interaction occurs.
     * @param pos         The position of the tile entity.
     * @param side        The direction to attack in.
     * @param sourceState The state of the tile entity, so we don't click ourselves.
     * @return The remaining item stack after the attack.
     */
    public static ItemStack leftClickInDirection(UsefulFakePlayer player, Level level, BlockPos pos, Direction side, BlockState sourceState)
    {
        HitResult toUse = rayTrace(player, level, player.getReach());
        if (toUse == null)
            return player.getMainHandItem();

        if (toUse.getType() == HitResult.Type.ENTITY)
        {
            if (processUseEntity(player, level, ((EntityHitResult) toUse).getEntity(), null, InteractionType.ATTACK))
                return player.getMainHandItem();
        }
        else if (toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = level.getBlockState(blockpos);
            if (state != sourceState && !state.isAir())
            {
                player.gameMode.handleBlockBreakAction(blockpos, ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK,
                                                                     ((BlockHitResult) toUse).getDirection(),
                                                                     player.level().getMaxY(), 0);
                return player.getMainHandItem();
            }
        }

        if (toUse.getType() == HitResult.Type.MISS)
        {
            for (int i = 1; i <= 5; i++)
            {
                BlockState state = level.getBlockState(pos.relative(side, i));
                if (state != sourceState && !state.isAir())
                {
                    player.gameMode.handleBlockBreakAction(pos.relative(side, i),
                                                                         ServerboundPlayerActionPacket.Action.START_DESTROY_BLOCK,
                                                                         side.getOpposite(),
                                                                         player.level().getMaxY(), 0);
                    return player.getMainHandItem();
                }
            }
        }

        return player.getMainHandItem();
    }

    /**
     * Traces for an entity.
     *
     * @param player The fake player.
     * @param level  The level where the interaction occurs.
     * @return A ray trace result that will likely be of type entity, but may be type block, or null.
     */
    public static HitResult traceEntities(UsefulFakePlayer player, Vec3 base, Vec3 target, Level level)
    {
        Entity pointedEntity = null;
        HitResult result = null;
        Vec3 vec3d3 = null;
        AABB search = new AABB(base.x, base.y, base.z, target.x, target.y, target.z).inflate(.5, .5, .5);
        List<Entity> list = level.getEntitiesOfClass(Entity.class, search,
                                                     entity -> EntitySelector.NO_SPECTATORS.test(entity) && entity != null && entity.isPickable());
        double d2 = 5;

        for (Entity entity1 : list)
        {
            AABB aabb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
            Optional<Vec3> optVec = aabb.clip(base, target);

            if (aabb.contains(base))
            {
                if (d2 >= 0.0D)
                {
                    pointedEntity = entity1;
                    vec3d3 = optVec.orElse(base);
                    d2 = 0.0D;
                }
            }
            else if (optVec.isPresent())
            {
                double d3 = base.distanceTo(optVec.get());

                if (d3 < d2 || d2 == 0.0D)
                {
                    if (entity1.getRootVehicle() == player.getRootVehicle())// && !entity1.canRiderInteract()) {
                    {
                        if (d2 == 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = optVec.get();
                        }
                    }
                    else
                    {
                        pointedEntity = entity1;
                        vec3d3 = optVec.get();
                        d2 = d3;
                    }
                }
            }
        }

        if (pointedEntity != null && base.distanceTo(vec3d3) > 5)
        {
            pointedEntity = null;
            result = BlockHitResult.miss(vec3d3, null, BlockPos.containing(vec3d3));
        }

        if (pointedEntity != null)
            result = new EntityHitResult(pointedEntity, vec3d3);

        return result;
    }

    /**
     * Processes the use of an entity from the server side.
     *
     * @param player The fake player.
     * @param level  The level where the interaction occurs.
     * @param entity The entity to interact with.
     * @param result The actual ray trace result, only necessary if using {@link InteractionType#INTERACT_AT}
     * @param action The type of interaction to perform.
     * @return If the entity was used.
     */
    public static boolean processUseEntity(UsefulFakePlayer player, Level level, Entity entity, @Nullable HitResult result, InteractionType action)
    {
        if (entity != null)
        {
            if (player.distanceToSqr(entity) < 36)
            {
                if (action == InteractionType.INTERACT)
                    return player.interactOn(entity, InteractionHand.MAIN_HAND) == InteractionResult.SUCCESS;

                if (action == InteractionType.INTERACT_AT && result != null)
                    return entity.interactAt(player, result.getLocation(), InteractionHand.MAIN_HAND) == InteractionResult.SUCCESS;

                if (action == InteractionType.ATTACK)
                {
                    if (entity instanceof ItemEntity || entity instanceof ExperienceOrb || entity instanceof Arrow || entity == player)
                        return false;
                    player.attack(entity);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Performs a raytrace for what the fake player is looking at.
     *
     * @param player The fake player.
     * @param level  The level where the interaction occurs.
     * @return A ray trace result that could be of type block, entity, or miss.
     */
    public static HitResult rayTrace(UsefulFakePlayer player, Level level, double reachDist)
    {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);
        HitResult trace = level.clip(new ClipContext(base, target,
                                                     ClipContext.Block.OUTLINE,
                                                     ClipContext.Fluid.SOURCE_ONLY, player));
        HitResult traceEntity = traceEntities(player, base, target, level);
        HitResult toUse = trace;

        if (traceEntity != null) {
            double d1 = trace.getLocation().distanceTo(base);
            double d2 = traceEntity.getLocation().distanceTo(base);
            toUse = traceEntity.getType() == HitResult.Type.ENTITY && d1 > d2 ? traceEntity : trace;
        }

        return toUse;
    }

    /**
     * Performs a block raytrace for what the fake player is looking at.
     *
     * @param player The fake player.
     * @param level  The level where the interaction occurs.
     * @return A ray trace result that will be of type block or miss.
     */
    public static HitResult rayTraceBlock(UsefulFakePlayer player, Level level, double reachDist)
    {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);

        return level.clip(new ClipContext(base, target,
                                          ClipContext.Block.OUTLINE,
                                          ClipContext.Fluid.SOURCE_ONLY, player));
    }

    /**
     * Performs an entity raytrace for what the fake player is looking at.
     *
     * @param player The fake player.
     * @param level  The level where the interaction occurs.
     * @return A ray trace result that will be of type entity or miss.
     */
    public static HitResult rayTraceEntity(UsefulFakePlayer player, Level level, double reachDist)
    {
        Vec3 base = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        Vec3 look = player.getLookAngle();
        Vec3 target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);

        return traceEntities(player, base, target, level);
    }

    /**
     * Enumeration of interaction types.
     */
    public enum InteractionType {
        INTERACT,
        INTERACT_AT,
        ATTACK
    }
}