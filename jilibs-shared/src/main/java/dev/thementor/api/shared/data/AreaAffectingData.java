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

package dev.thementor.api.shared.data;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import java.util.Objects;

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
    public Box area;

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
        xOffset = facing.getVector().getX();
        yOffset = facing.getVector().getY();
        zOffset = facing.getVector().getZ();
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