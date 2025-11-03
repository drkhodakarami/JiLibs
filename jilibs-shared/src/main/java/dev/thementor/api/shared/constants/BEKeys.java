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

/**
 * A class containing constants representing various key names used in the backend.
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

public class BEKeys {
    /**
     * Key for the energy amount in a backend entity.
     */
    public static final String ENERGY_AMOUNT = ".be.energy.amount";

    /**
     * Key for the energy capacity in a backend entity.
     */
    public static final String ENERGY_CAPACITY = ".be.energy.capacity";

    /**
     * Key indicating whether a backend entity has energy.
     */
    public static final String HAS_ENERGY = ".be.has.energy";

    /**
     * Key for the fluid amount in a backend entity.
     */
    public static final String FLUID_AMOUNT = ".be.fluid.amount";

    /**
     * Key for the fluid capacity in a backend entity.
     */
    public static final String FLUID_CAPACITY = ".be.fluid.capacity";

    /**
     * Key indicating whether a backend entity has fluid.
     */
    public static final String HAS_FLUID = ".be.has.fluid";

    /**
     * Key for the progress amount in a backend entity.
     */
    public static final String PROGRESS_AMOUNT = ".be.progress.amount";

    /**
     * Key for the maximum progress value in a backend entity.
     */
    public static final String PROGRESS_MAX = ".be.progress.max";

    /**
     * Key for the cooldown amount in a backend entity.
     */
    public static final String COOLDOWN_AMOUNT = ".be.cooldown.amount";

    /**
     * Key for the maximum cooldown value in a backend entity.
     */
    public static final String COOLDOWN_MAX = ".be.cooldown.max";

    /**
     * Key for the burn amount in a backend entity.
     */
    public static final String BURN_AMOUNT = ".be.burn.amount";

    /**
     * Key for the maximum burn value in a backend entity.
     */
    public static final String BURN_MAX = ".be.burn.max";

    /**
     * Key indicating whether a backend entity has an inventory.
     */
    public static final String HAS_INVENTORY = ".be.has.inventory";

    /**
     * Key indicating whether a backend entity is dirty.
     */
    public static final String IS_DIRTY = "is.dirty";

    /**
     * Key indicating whether the client-side version of a backend entity is dirty.
     */
    public static final String IS_DIRTY_CLIENT = "is.dirty.client";

    /**
     * Key for the world information in a backend entity.
     */
    public static final String WORLD = "world";

    /**
     * Key for the position information in a backend entity.
     */
    public static final String POS = "pos";

    /**
     * Key for the cached state of a backend entity.
     */
    public static final String CACHED_STATE = "cached.state";

    /**
     * Key for the tick count in a backend entity.
     */
    public static final String TICKS = "ticks";

    /**
     * Key for the client-side tick count in a backend entity.
     */
    public static final String TICKS_CLIENT = "ticks.client";

    // Private constructor to prevent instantiation
    BEKeys() {
        Exceptions.throwCtorAssertion();
    }
}