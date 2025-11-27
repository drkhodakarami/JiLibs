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

package dev.thementor.api.shared.data;

import java.util.Objects;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

/**
 * A class representing data for area-affecting abilities or mechanics.
 *
 * @author Direwolf20
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class AreaAffectingData
{
    /**
     * The radius in the X dimension.
     */
    public double xRadius = 0;

    /**
     * The radius in the Y dimension.
     */
    public double yRadius = 0;

    /**
     * The radius in the Z dimension.
     */
    public double zRadius = 0;

    /**
     * The offset along the X dimension.
     */
    public int xOffset = 0;

    /**
     * The offset along the Y dimension.
     */
    public int yOffset = 1;

    /**
     * The offset along the Z dimension.
     */
    public int zOffset = 0;

    /**
     * Whether to render the area.
     */
    public boolean renderArea = false;

    /**
     * The bounding box representing the affected area.
     */
    public AABB area;

    /**
     * Default constructor.
     */
    public AreaAffectingData() {
        // Default constructor
    }

    /**
     * Constructor with direction parameter.
     *
     * @param facing The direction the effect is facing.
     */
    public AreaAffectingData(Direction facing) {
        xOffset = facing.getUnitVec3i().getX();
        yOffset = facing.getUnitVec3i().getY();
        zOffset = facing.getUnitVec3i().getZ();
    }

    /**
     * Constructor with all parameters.
     *
     * @param xRadius The radius in the X dimension.
     * @param yRadius The radius in the Y dimension.
     * @param zRadius The radius in the Z dimension.
     * @param xOffset The offset along the X dimension.
     * @param yOffset The offset along the Y dimension.
     * @param zOffset The offset along the Z dimension.
     */
    public AreaAffectingData(double xRadius, double yRadius, double zRadius, int xOffset, int yOffset, int zOffset) {
        this.xRadius = xRadius;
        this.yRadius = yRadius;
        this.zRadius = zRadius;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
    }

    /**
     * Checks if the given object is equal to this {@link AreaAffectingData} instance.
     *
     * @param o The object to compare with this instance.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        AreaAffectingData that = (AreaAffectingData) o;
        return xRadius == that.xRadius &&
               yRadius == that.yRadius &&
               zRadius == that.zRadius &&
               xOffset == that.xOffset &&
               yOffset == that.yOffset &&
               zOffset == that.zOffset &&
               renderArea == that.renderArea;
    }

    /**
     * Calculates the hash code for this {@link AreaAffectingData} instance.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xRadius, yRadius, zRadius, xOffset, yOffset, zOffset, renderArea);
    }
}