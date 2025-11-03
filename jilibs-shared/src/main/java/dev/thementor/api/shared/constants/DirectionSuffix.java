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
 * A class containing constants representing direction suffixes.
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

public class DirectionSuffix {

    /**
     * Constant representing the north direction suffix.
     */
    public static final String NORTH = ".north";

    /**
     * Constant representing the south direction suffix.
     */
    public static final String SOUTH = ".south";

    /**
     * Constant representing the west direction suffix.
     */
    public static final String WEST = ".west";

    /**
     * Constant representing the east direction suffix.
     */
    public static final String EAST = ".east";

    /**
     * Constant representing the top direction suffix.
     */
    public static final String TOP = ".top";

    /**
     * Constant representing the bottom direction suffix.
     */
    public static final String BOTTOM = ".bottom";

    /**
     * Constant representing no direction suffix.
     */
    public static final String NO_DIRECTION = ".no.direction";

    // Private constructor to prevent instantiation
    DirectionSuffix() {
        Exceptions.throwCtorAssertion();
    }
}