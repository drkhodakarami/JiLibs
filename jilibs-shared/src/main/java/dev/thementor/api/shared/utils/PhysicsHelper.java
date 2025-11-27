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

import java.util.Optional;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
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
    public static BlockHitResult getHitResult(Player player)
    {
        var playerLook = new Vec3(player.getX(), player.getY() + player.getEyeY(), player.getZ());
        var lookVec = player.getEyePosition(1.0F);
        var reach = player.blockInteractionRange();
        var endLook = playerLook.add(lookVec.x * reach, lookVec.y * reach, lookVec.z * reach);
        return player.level()
                     .clip(new ClipContext(playerLook, endLook,
                                                 ClipContext.Block.COLLIDER,
                                             ClipContext.Fluid.NONE,
                                                    player));
    }

    /**
     * Gets the entity that is being looked at by a player within a specified maximum distance.
     *
     * @param player The player entity to get the entity look result for.
     * @param maxDistance The maximum distance to check for entities.
     * @return The entity being looked at, or null if no entity is found.
     */
    public static Entity getEntityLookedAt(Player player, double maxDistance)
    {
        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 lookVector = player.getViewVector(1.0F).scale(maxDistance);
        Vec3 endPosition = eyePosition.add(lookVector);

        // Perform ray trace for entities
        HitResult hitResult = player.level()
                                    .clip(new ClipContext(eyePosition, endPosition,
                                         ClipContext.Block.OUTLINE,
                                     ClipContext.Fluid.NONE,
                                            player));

        if (hitResult.getType() != HitResult.Type.MISS)
            endPosition = hitResult.getLocation();

        EntityHitResult entityHitResult = rayTraceEntities(player, eyePosition, endPosition,
                                                           player.getBoundingBox()
                                                                   .expandTowards(lookVector)
                                                                   .inflate(1.0D, 1.0D, 1.0D), maxDistance);

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
    private static EntityHitResult rayTraceEntities(Player player, Vec3 start, Vec3 end, AABB boundingBox, double maxDistance)
    {
        double closestDistance = maxDistance;
        Entity closestEntity = null;
        Vec3 hitLocation = null;

        for (Entity entity : player.level().getEntities(player, boundingBox, e -> e != player && e.isPickable()))
        {
            AABB entityBoundingBox = entity.getBoundingBox().inflate(entity.getPickRadius());
            Optional<Vec3> optHitVec = entityBoundingBox.clip(start, end);

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
                Vec3 hitVec = optHitVec.get();
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