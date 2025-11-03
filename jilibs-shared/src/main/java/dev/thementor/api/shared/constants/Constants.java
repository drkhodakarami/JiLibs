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

package dev.thementor.api.shared.constants;

import dev.thementor.api.shared.exceptions.Exceptions;
import dev.thementor.api.shared.annotations.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.util.math.Direction;

/**
 * A class containing various constants used throughout the project.
 *
 * @author TheMentor
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class Constants {

    /**
     * Inner class containing constants related to inventory settings.
     */
    public static class Inventory {
        /**
         * Default output size for an inventory.
         */
        public static int DEFAULT_OUTPUT_SIZE = 1;

        /**
         * Default output direction for an inventory.
         */
        public static Direction DEFAULT_OUTPUT_DIRECTION = Direction.DOWN;
    }

    /**
     * Inner class containing constants related to fluid settings.
     */
    public static class Fluid {
        /**
         * Inner class containing constants related to fluid container capacities.
         */
        public static class ContainerCapacity {
            /**
             * Inner class containing constants related to fluid capacities based on size.
             */
            public static class SizeBased {
                /**
                 * Default fluid capacity in buckets.
                 */
                public static long DEFAULT = FluidConstants.BUCKET * 20;

                /**
                 * Small fluid capacity in buckets.
                 */
                public static long SMALL = FluidConstants.BUCKET * 10;

                /**
                 * Medium fluid capacity in buckets.
                 */
                public static long MEDIUM = FluidConstants.BUCKET * 50;

                /**
                 * Large fluid capacity in buckets.
                 */
                public static long LARGE = FluidConstants.BUCKET * 100;
                /**
                 * XLarge fluid capacity in buckets.
                 */
                public static long XLARGE = FluidConstants.BUCKET * 250;
            }

            /**
             * Inner class containing constants related to fluid capacities based on material.
             */
            public static class MaterialBased {
                /**
                 * Wood-based fluid capacity in buckets.
                 */
                public static long WOOD = FluidConstants.BUCKET * 4;

                /**
                 * Stone-based fluid capacity in buckets.
                 */
                public static long STONE = FluidConstants.BUCKET * 16;

                /**
                 * Copper-based fluid capacity in buckets.
                 */
                public static long COPPER = FluidConstants.BUCKET * 40;

                /**
                 * Iron-based fluid capacity in buckets.
                 */
                public static long IRON = FluidConstants.BUCKET * 80;

                /**
                 * Gold-based fluid capacity in buckets.
                 */
                public static long GOLD = FluidConstants.BUCKET * 120;

                /**
                 * Obsidian-based fluid capacity in buckets.
                 */
                public static long OBSIDIAN = FluidConstants.BUCKET * 160;

                /**
                 * Diamond-based fluid capacity in buckets.
                 */
                public static long DIAMOND = FluidConstants.BUCKET * 200;

                /**
                 * Emerald-based fluid capacity in buckets.
                 */
                public static long EMERALD = FluidConstants.BUCKET * 280;

                /**
                 * Star-based fluid capacity in buckets.
                 */
                public static long STAR = FluidConstants.BUCKET * 360;
                /**
                 * Netherite-based fluid capacity in buckets.
                 */
                public static long NETHERITE = FluidConstants.BUCKET * 500;

                /**
                 * End-based fluid capacity in buckets.
                 */
                public static long END = FluidConstants.BUCKET * 800;
            }
        }

        /**
         * Conversion factor from buckets to milli-buckets.
         */
        public static long MILLI_BUCKET = FluidConstants.BUCKET / 1000;
    }

    /**
     * Inner class containing constants related to energy settings.
     */
    public static class Energy
    {
        /**
         * Default energy capacity in the system.
         */
        public static int DEFAULT_CAPACITY = 100_000;

        /**
         * Maximum amount of energy that can be inserted into the system per operation.
         */
        public static int DEFAULT_MAX_INSERT = 1000;

        /**
         * Maximum amount of energy that can be extracted from the system per operation.
         */
        public static int DEFAULT_MAX_EXTRACT = 1000;

        /**
         * Normal amount of energy to insert into the system per operation.
         */
        public static int DEFAULT_NORMAL_INSERT = 100;

        /**
         * Normal amount of energy to extract from the system per operation.
         */
        public static int DEFAULT_NORMAL_EXTRACT = 100;
    }

    // Private constructor to prevent instantiation
    public Constants()
    {
        Exceptions.throwCtorAssertion();
    }
}