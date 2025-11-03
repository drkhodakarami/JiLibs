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
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

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
    public record FakePlayerResult(ActionResult actionResult, ItemStack returnStack) {}

    /**
     * Adds attributes to a fake player based on the item they are holding.
     *
     * @param player    The fake player.
     * @param itemStack The item stack the player is holding.
     * @param equipmentSlot The equipment slot of the item stack.
     */
    protected static void addAttributes(UsefulFakePlayer player, ItemStack itemStack, EquipmentSlot equipmentSlot)
    {
        AttributeContainer container = player.getAttributes();
        if(!itemStack.isEmpty())
        {
            itemStack.applyAttributeModifiers(equipmentSlot, (attribute, modifier) ->
            {
                EntityAttributeInstance attributeInstance = container.getCustomInstance(attribute);
                if(attributeInstance != null)
                {
                    attributeInstance.removeModifier(modifier);
                    attributeInstance.addTemporaryModifier(modifier);
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
        AttributeContainer container = player.getAttributes();
        if(!itemStack.isEmpty())
        {
            itemStack.applyAttributeModifiers(equipmentSlot, (attribute, modifier) ->
            {
                EntityAttributeInstance attributeInstance = container.getCustomInstance(attribute);
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
    public static void setupFakePlayerForUse(UsefulFakePlayer player, Vec3d pos, Direction direction, ItemStack toHold, boolean sneaking)
    {
        player.getInventory().getMainStacks().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.setPitch(xRot);
        player.setYaw(direction.getPositiveHorizontalDegrees());
        player.setHeadYaw(direction.getPositiveHorizontalDegrees());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.setPos(pos.x + x, pos.y + y - player.getEyeY(), pos.z + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setSneaking(sneaking);
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
        player.getInventory().getMainStacks().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.setAngles(direction.getPositiveHorizontalDegrees(), xRot);
        player.setHeadYaw(direction.getPositiveHorizontalDegrees());
        player.lastHeadYaw = direction.getPositiveHorizontalDegrees();
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.updatePosition(pos.getX() + x, pos.getY() + y - player.getEyeY(), pos.getZ() + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setSneaking(sneaking);
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
    public static void setupFakePlayerForUse(UsefulFakePlayer player, BlockPos pos, Direction direction, Vec3d entityPosition, ItemStack toHold, boolean sneaking)
    {
        player.getInventory().getMainStacks().set(player.getInventory().getSelectedSlot(), toHold);
        float xRot = direction == Direction.DOWN ? 90 : direction == Direction.UP ? -90 : 0;
        player.setPitch(xRot);
        player.setYaw(direction.getPositiveHorizontalDegrees());
        player.setHeadYaw(direction.getPositiveHorizontalDegrees());
        Direction.Axis a = direction.getAxis();
        Direction.AxisDirection ad = direction.getDirection();
        double x = a == Direction.Axis.X ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double y = a == Direction.Axis.Y ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        double z = a == Direction.Axis.Z ? ad == Direction.AxisDirection.NEGATIVE ? 0.95 : 0.05 : 0.5;
        player.setPos(pos.getX() + x, pos.getY() + y - player.getEyeY(), pos.getZ() + z);
        if (!toHold.isEmpty())
            addAttributes(player, toHold, EquipmentSlot.MAINHAND);
        player.setSneaking(sneaking);

        // Calculate the rotation angles to look at the target position
        Vec3d playerEyePos = new Vec3d(player.getX(), player.getY() + player.getEyeY(), player.getZ());
        Vec3d toEntity = entityPosition.subtract(playerEyePos).normalize();
        float yaw = (float) Math.toDegrees(Math.atan2(toEntity.z, toEntity.x)) - 90.0F;
        float pitch = (float) Math.toDegrees(-Math.atan2(toEntity.y, Math.sqrt(toEntity.x * toEntity.x + toEntity.z * toEntity.z)));

        player.setYaw(yaw);
        player.setHeadYaw(yaw);  // Set both the body and head rotation to the calculated yaw
        player.setPitch(pitch);
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
        player.getInventory().getMainStacks().set(player.getInventory().getSelectedSlot(), ItemStack.EMPTY);
        if (!player.getInventory().isEmpty()) player.getInventory().dropAll(); //Handles bucket stacks, for example
        player.setSneaking(false);
        player.setReach(player.getAttributeValue(EntityAttributes.BLOCK_INTERACTION_RANGE));
    }

    /**
     * Simulates a right click interaction with an entity in the given direction.
     *
     * @param player    The fake player.
     * @param world     The world where the interaction occurs.
     * @param entity    The entity to interact with.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult clickEntityInDirection(UsefulFakePlayer player, World world, LivingEntity entity, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceEntity(player, world, player.getReach());
        if (toUse == null) return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());

        if (clickType == 2) {
            ItemStack itemstack = player.getMainHandStack();
            if (itemstack.isEmpty()) {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
            }
            if (!player.isUsingItem()) {
                player.setCurrentHand(Hand.MAIN_HAND);
            }
            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getItemUseTime();
            //System.out.println("Holding For: " + holdingFor);
            if (holdingFor >= maxHold) {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            }
            return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }

        if (clickType == 0) { //RightClick
            if (processUseEntity(player, world, entity, toUse, InteractionType.INTERACT_AT))
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            else if (processUseEntity(player, world, entity, null, InteractionType.INTERACT))
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        } else { //Left Click
            if (processUseEntity(player, world, ((EntityHitResult) toUse).getEntity(), null, InteractionType.ATTACK))
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }

        return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
    }

    /**
     * Simulates a right or left click interaction with a block in the given direction.
     *
     * @param player    The fake player.
     * @param world     The world where the interaction occurs.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult clickBlockInDirection(UsefulFakePlayer player, World world, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceBlock(player, world, player.getReach());
        if (toUse == null) return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());

        ItemStack itemstack = player.getMainHandStack();
        if (clickType == 2) {
            if (itemstack.isEmpty())
            {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
            }
            if (!player.isUsingItem())
                player.setCurrentHand(Hand.MAIN_HAND);

            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getItemUseTime();
            if (holdingFor >= maxHold)
            {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            }
            return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }
        if (toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = world.getBlockState(blockpos);
            if (!state.isAir())
            {
                if (clickType == 0)
                {
                    ActionResult type = player.interactionManager.interactBlock(player, world, itemstack, Hand.MAIN_HAND, (BlockHitResult) toUse);
                    if (type == ActionResult.SUCCESS || type == ActionResult.CONSUME)
                        return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
                }
                else
                {
                    player.interactionManager.processBlockBreakingAction(blockpos, PlayerActionC2SPacket.Action.START_DESTROY_BLOCK,
                                                                         ((BlockHitResult) toUse).getSide(),
                                                                         player.getEntityWorld().getTopYInclusive(), 0);
                    return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
                }
            }
        }

        if (toUse.getType() == HitResult.Type.MISS) //Since we check for air before entering the method, there must be a block there, so try clicking it anyway
        {
            if (clickType == 0)
            {
                ActionResult type = player.interactionManager.interactBlock(player, world, itemstack, Hand.MAIN_HAND, (BlockHitResult) toUse);
                if (type == ActionResult.SUCCESS || type == ActionResult.CONSUME)
                    return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            }
        }

        if (!itemstack.isEmpty())
        {
            ActionResult type = player.interactionManager.interactItem(player, world, itemstack, Hand.MAIN_HAND); //Uses the item by itself
            if (type == ActionResult.SUCCESS || type == ActionResult.CONSUME)
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }
        return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
    }

    /**
     * Simulates a right click interaction with air in the given direction.
     *
     * @param player    The fake player.
     * @param world     The world where the interaction occurs.
     * @param clickType The type of interaction (right-click or left-click).
     * @param maxHold   The maximum time in ticks the item should be held before stopping.
     * @return The result of the interaction and the remaining item stack.
     */
    public static FakePlayerResult rightClickAirInDirection(UsefulFakePlayer player, World world, int clickType, int maxHold)
    {
        HitResult toUse = rayTraceBlock(player, world, player.getReach()); //Longer reach so it can connect with adjacent blocks to interact with them
        if (toUse == null) new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());

        ItemStack itemstack = player.getMainHandStack();
        if (clickType == 2)
        {
            if (itemstack.isEmpty())
            {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
            }
            if (!player.isUsingItem())
                player.setCurrentHand(Hand.MAIN_HAND);

            player.fakeUpdateUsingItem(itemstack);
            int holdingFor = player.getItemUseTime();
            if (holdingFor >= maxHold)
            {
                player.stopUsingItem();
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            }
            return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }
        if (toUse != null && toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = world.getBlockState(blockpos);
            if (!state.isAir())
            {
                ActionResult type = player.interactionManager.interactBlock(player, world, itemstack, Hand.MAIN_HAND, (BlockHitResult) toUse);
                if (type == ActionResult.SUCCESS || type == ActionResult.CONSUME)
                    return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
            }
        }

        if (!itemstack.isEmpty())
        {
            ActionResult type = player.interactionManager.interactItem(player, world, itemstack, Hand.MAIN_HAND); //Uses the item by itself
            if (type == ActionResult.SUCCESS || type == ActionResult.CONSUME)
                return new FakePlayerResult(ActionResult.SUCCESS, player.getMainHandStack());
        }
        return new FakePlayerResult(ActionResult.FAIL, player.getMainHandStack());
    }

    /**
     * Simulates an attack with whatever the fake player is holding in the given direction.
     *
     * @param player      The fake player.
     * @param world       The world where the interaction occurs.
     * @param pos         The position of the tile entity.
     * @param side        The direction to attack in.
     * @param sourceState The state of the tile entity, so we don't click ourselves.
     * @return The remaining item stack after the attack.
     */
    public static ItemStack leftClickInDirection(UsefulFakePlayer player, World world, BlockPos pos, Direction side, BlockState sourceState)
    {
        HitResult toUse = rayTrace(player, world, player.getReach());
        if (toUse == null)
            return player.getMainHandStack();

        if (toUse.getType() == HitResult.Type.ENTITY)
        {
            if (processUseEntity(player, world, ((EntityHitResult) toUse).getEntity(), null, InteractionType.ATTACK))
                return player.getMainHandStack();
        }
        else if (toUse.getType() == HitResult.Type.BLOCK)
        {
            BlockPos blockpos = ((BlockHitResult) toUse).getBlockPos();
            BlockState state = world.getBlockState(blockpos);
            if (state != sourceState && !state.isAir())
            {
                player.interactionManager.processBlockBreakingAction(blockpos, PlayerActionC2SPacket.Action.START_DESTROY_BLOCK,
                                                                     ((BlockHitResult) toUse).getSide(),
                                                                     player.getEntityWorld().getTopYInclusive(), 0);
                return player.getMainHandStack();
            }
        }

        if (toUse.getType() == HitResult.Type.MISS)
        {
            for (int i = 1; i <= 5; i++)
            {
                BlockState state = world.getBlockState(pos.offset(side, i));
                if (state != sourceState && !state.isAir())
                {
                    player.interactionManager.processBlockBreakingAction(pos.offset(side, i),
                                                                         PlayerActionC2SPacket.Action.START_DESTROY_BLOCK,
                                                                         side.getOpposite(),
                                                                         player.getEntityWorld().getTopYInclusive(), 0);
                    return player.getMainHandStack();
                }
            }
        }

        return player.getMainHandStack();
    }

    /**
     * Traces for an entity.
     *
     * @param player The fake player.
     * @param world  The world where the interaction occurs.
     * @return A ray trace result that will likely be of type entity, but may be type block, or null.
     */
    public static HitResult traceEntities(UsefulFakePlayer player, Vec3d base, Vec3d target, World world)
    {
        Entity pointedEntity = null;
        HitResult result = null;
        Vec3d vec3d3 = null;
        Box search = new Box(base.x, base.y, base.z, target.x, target.y, target.z).expand(.5, .5, .5);
        List<Entity> list = world.getEntitiesByClass(Entity.class, search,
                                                     entity -> EntityPredicates.EXCEPT_SPECTATOR.test(entity) && entity != null && entity.canHit());
        double d2 = 5;

        for (Entity entity1 : list)
        {
            Box aabb = entity1.getBoundingBox().expand(entity1.getTargetingMargin());
            Optional<Vec3d> optVec = aabb.raycast(base, target);

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
            result = BlockHitResult.createMissed(vec3d3, null, BlockPos.ofFloored(vec3d3));
        }

        if (pointedEntity != null)
            result = new EntityHitResult(pointedEntity, vec3d3);

        return result;
    }

    /**
     * Processes the use of an entity from the server side.
     *
     * @param player The fake player.
     * @param world  The world where the interaction occurs.
     * @param entity The entity to interact with.
     * @param result The actual ray trace result, only necessary if using {@link InteractionType#INTERACT_AT}
     * @param action The type of interaction to perform.
     * @return If the entity was used.
     */
    public static boolean processUseEntity(UsefulFakePlayer player, World world, Entity entity, @Nullable HitResult result, InteractionType action)
    {
        if (entity != null)
        {
            if (player.squaredDistanceTo(entity) < 36)
            {
                if (action == InteractionType.INTERACT)
                    return player.interact(entity, Hand.MAIN_HAND) == ActionResult.SUCCESS;

                if (action == InteractionType.INTERACT_AT && result != null)
                    return entity.interactAt(player, result.getPos(), Hand.MAIN_HAND) == ActionResult.SUCCESS;

                if (action == InteractionType.ATTACK)
                {
                    if (entity instanceof ItemEntity || entity instanceof ExperienceOrbEntity || entity instanceof ArrowEntity || entity == player)
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
     * @param world  The world where the interaction occurs.
     * @return A ray trace result that could be of type block, entity, or miss.
     */
    public static HitResult rayTrace(UsefulFakePlayer player, World world, double reachDist)
    {
        Vec3d base = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d look = player.getRotationVector();
        Vec3d target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);
        HitResult trace = world.raycast(new RaycastContext(base, target,
                                                           RaycastContext.ShapeType.OUTLINE,
                                                           RaycastContext.FluidHandling.SOURCE_ONLY, player));
        HitResult traceEntity = traceEntities(player, base, target, world);
        HitResult toUse = trace == null ? traceEntity : trace;

        if (trace != null && traceEntity != null) {
            double d1 = trace.getPos().distanceTo(base);
            double d2 = traceEntity.getPos().distanceTo(base);
            toUse = traceEntity.getType() == HitResult.Type.ENTITY && d1 > d2 ? traceEntity : trace;
        }

        return toUse;
    }

    /**
     * Performs a block raytrace for what the fake player is looking at.
     *
     * @param player The fake player.
     * @param world  The world where the interaction occurs.
     * @return A ray trace result that will be of type block or miss.
     */
    public static HitResult rayTraceBlock(UsefulFakePlayer player, World world, double reachDist)
    {
        Vec3d base = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d look = player.getRotationVector();
        Vec3d target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);

        return world.raycast(new RaycastContext(base, target,
                                                RaycastContext.ShapeType.OUTLINE,
                                                RaycastContext.FluidHandling.SOURCE_ONLY, player));
    }

    /**
     * Performs an entity raytrace for what the fake player is looking at.
     *
     * @param player The fake player.
     * @param world  The world where the interaction occurs.
     * @return A ray trace result that will be of type entity or miss.
     */
    public static HitResult rayTraceEntity(UsefulFakePlayer player, World world, double reachDist)
    {
        Vec3d base = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d look = player.getRotationVector();
        Vec3d target = base.add(look.x * reachDist, look.y * reachDist, look.z * reachDist);

        return traceEntities(player, base, target, world);
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