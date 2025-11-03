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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.Optional;

/**
 * Provides utility methods for physics-related operations in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class PhysicsHelper
{
    /**
     * Gets the block hit result for a player's current look direction.
     *
     * @param player The player entity to get the hit result for.
     * @return The block hit result.
     */
    public static BlockHitResult getHitResult(PlayerEntity player)
    {
        var playerLook = new Vec3d(player.getX(), player.getY() + player.getEyeY(), player.getZ());
        var lookVec = player.getCameraPosVec(1.0F);
        var reach = player.getBlockInteractionRange();
        var endLook = playerLook.add(lookVec.x * reach, lookVec.y * reach, lookVec.z * reach);
        return player.getEntityWorld()
                     .raycast(new RaycastContext(playerLook, endLook,
                                                 RaycastContext.ShapeType.COLLIDER,
                                             RaycastContext.FluidHandling.NONE,
                                                    player));
    }

    /**
     * Gets the entity that is being looked at by a player within a specified maximum distance.
     *
     * @param player The player entity to get the entity look result for.
     * @param maxDistance The maximum distance to check for entities.
     * @return The entity being looked at, or null if no entity is found.
     */
    public static Entity getEntityLookedAt(PlayerEntity player, double maxDistance)
    {
        Vec3d eyePosition = player.getCameraPosVec(1.0F);
        Vec3d lookVector = player.getRotationVec(1.0F).multiply(maxDistance);
        Vec3d endPosition = eyePosition.add(lookVector);

        // Perform ray trace for entities
        HitResult hitResult = player.getEntityWorld()
                                    .raycast(new RaycastContext(eyePosition, endPosition,
                                         RaycastContext.ShapeType.OUTLINE,
                                     RaycastContext.FluidHandling.NONE,
                                            player));

        if (hitResult.getType() != HitResult.Type.MISS)
            endPosition = hitResult.getPos();

        EntityHitResult entityHitResult = rayTraceEntities(player, eyePosition, endPosition,
                                                           player.getBoundingBox()
                                                                   .stretch(lookVector)
                                                                   .expand(1.0D, 1.0D, 1.0D), maxDistance);

        if (entityHitResult != null)
            return entityHitResult.getEntity();

        return null;
    }

    /**
     * Performs a ray trace for entities within the given bounds and returns the closest one.
     *
     * @param player The player entity performing the ray trace.
     * @param start The starting position of the ray.
     * @param end The ending position of the ray.
     * @param boundingBox The bounding box to search within.
     * @param maxDistance The maximum distance to check for entities.
     * @return The closest entity hit by the ray, or null if no entity is found.
     */
    private static EntityHitResult rayTraceEntities(PlayerEntity player, Vec3d start, Vec3d end, Box boundingBox, double maxDistance)
    {
        double closestDistance = maxDistance;
        Entity closestEntity = null;
        Vec3d hitLocation = null;

        for (Entity entity : player.getEntityWorld().getOtherEntities(player, boundingBox, e -> e != player && e.canHit()))
        {
            Box entityBoundingBox = entity.getBoundingBox().expand(entity.getTargetingMargin());
            Optional<Vec3d> optHitVec = entityBoundingBox.raycast(start, end);

            if (entityBoundingBox.contains(start))
            {
                if (closestDistance >= 0.0D)
                {
                    closestEntity = entity;
                    hitLocation = optHitVec.orElse(start);
                    closestDistance = 0.0D;
                }
            }
            else if (optHitVec.isPresent())
            {
                Vec3d hitVec = optHitVec.get();
                double distanceToHitVec = start.distanceTo(hitVec);

                if (distanceToHitVec < closestDistance || closestDistance == 0.0D)
                {
                    if (entity.getRootVehicle() == player.getRootVehicle())
                    {// && !entity.canRiderInteract()) {
                        if (closestDistance == 0.0D)
                        {
                            closestEntity = entity;
                            hitLocation = hitVec;
                        }
                    }
                    else
                    {
                        closestEntity = entity;
                        hitLocation = hitVec;
                        closestDistance = distanceToHitVec;
                    }
                }
            }
        }

        return closestEntity == null ? null : new EntityHitResult(closestEntity, hitLocation);
    }
}