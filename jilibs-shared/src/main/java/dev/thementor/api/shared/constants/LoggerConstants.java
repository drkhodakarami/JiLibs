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
 * A class containing constants used for logger formatting.
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

public class LoggerConstants {

    /**
     * Constant to reset logger formatting.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Inner class containing constants for foreground colors.
     */
    public static class Foreground {
        /**
         * Black foreground color code.
         */
        public static final String BLACK = "\u001B[30m";

        /**
         * Red foreground color code.
         */
        public static final String RED = "\u001B[31m";

        /**
         * Green foreground color code.
         */
        public static final String GREEN = "\u001B[32m";

        /**
         * Yellow foreground color code.
         */
        public static final String YELLOW = "\u001B[33m";

        /**
         * Blue foreground color code.
         */
        public static final String BLUE = "\u001B[34m";

        /**
         * Magenta foreground color code.
         */
        public static final String MAGENTA = "\u001B[35m";

        /**
         * Cyan foreground color code.
         */
        public static final String CYAN = "\u001B[36m";

        /**
         * White foreground color code.
         */
        public static final String WHITE = "\u001B[37m";

        /**
         * Bright black foreground color code.
         */
        public static final String BRIGHT_BLACK = "\u001B[90m";

        /**
         * Bright red foreground color code.
         */
        public static final String BRIGHT_RED = "\u001B[91m";

        /**
         * Bright green foreground color code.
         */
        public static final String BRIGHT_GREEN = "\u001B[92m";

        /**
         * Bright yellow foreground color code.
         */
        public static final String BRIGHT_YELLOW = "\u001B[93m";

        /**
         * Bright blue foreground color code.
         */
        public static final String BRIGHT_BLUE = "\u001B[94m";

        /**
         * Bright magenta foreground color code.
         */
        public static final String BRIGHT_MAGENTA = "\u001B[95m";

        /**
         * Bright cyan foreground color code.
         */
        public static final String BRIGHT_CYAN = "\u001B[96m";
        /**
         * Bright white foreground color code.
         */
        public static final String BRIGHT_WHITE = "\u001B[97m";

        // Private constructor to prevent instantiation
        Foreground() {
            Exceptions.throwCtorAssertion();
        }
    }

    /**
     * Inner class containing constants for background colors.
     */
    public static class Background {
        /**
         * Black background color code.
         */
        public static final String BLACK = "\u001B[40m";

        /**
         * Red background color code.
         */
        public static final String RED = "\u001B[41m";

        /**
         * Green background color code.
         */
        public static final String GREEN = "\u001B[42m";

        /**
         * Yellow background color code.
         */
        public static final String YELLOW = "\u001B[43m";

        /**
         * Blue background color code.
         */
        public static final String BLUE = "\u001B[44m";

        /**
         * Magenta background color code.
         */
        public static final String MAGENTA = "\u001B[45m";

        /**
         * Cyan background color code.
         */
        public static final String CYAN = "\u001B[46m";

        /**
         * White background color code.
         */
        public static final String WHITE = "\u001B[47m";

        /**
         * Bright black background color code.
         */
        public static final String BRIGHT_BLACK = "\u001B[100m";

        /**
         * Bright red background color code.
         */
        public static final String BRIGHT_RED = "\u001B[101m";

        /**
         * Bright green background color code.
         */
        public static final String BRIGHT_GREEN = "\u001B[102m";

        /**
         * Bright yellow background color code.
         */
        public static final String BRIGHT_YELLOW = "\u001B[103m";

        /**
         * Bright blue background color code.
         */
        public static final String BRIGHT_BLUE = "\u001B[104m";

        /**
         * Bright magenta background color code.
         */
        public static final String BRIGHT_MAGENTA = "\u001B[105m";

        /**
         * Bright cyan background color code.
         */
        public static final String BRIGHT_CYAN = "\u001B[106m";

        /**
         * Bright white background color code.
         */
        public static final String BRIGHT_WHITE = "\u001B[107m";

        // Private constructor to prevent instantiation
        Background() {
            Exceptions.throwCtorAssertion();
        }
    }

    // Private constructor to prevent instantiation
    LoggerConstants() {
        Exceptions.throwCtorAssertion();
    }
}