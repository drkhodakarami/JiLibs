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

package dev.thementor.api.shared.constants;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * A class containing constants for Minecraft tooltip formatting.
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

public class TooltipConstants {

    /**
     * Constant for black color code.
     */
    public static final String BLACK = "§0";

    /**
     * Constant for dark blue color code.
     */
    public static final String DARK_BLUE = "§1";

    /**
     * Constant for dark green color code.
     */
    public static final String DARK_GREEN = "§2";

    /**
     * Constant for dark aqua color code.
     */
    public static final String DARK_AQUA = "§3";

    /**
     * Constant for dark red color code.
     */
    public static final String DARK_RED = "§4";

    /**
     * Constant for dark purple color code.
     */
    public static final String DARK_PURPLE = "§5";

    /**
     * Constant for gold color code.
     */
    public static final String GOLD = "§6";

    /**
     * Constant for gray color code.
     */
    public static final String GRAY = "§7";

    /**
     * Constant for dark gray color code.
     */
    public static final String DARK_GRAY = "§8";

    /**
     * Constant for blue color code.
     */
    public static final String BLUE = "§9";

    /**
     * Constant for green color code.
     */
    public static final String GREEN = "§a";

    /**
     * Constant for aqua color code.
     */
    public static final String AQUA = "§b";

    /**
     * Constant for red color code.
     */
    public static final String RED = "§c";

    /**
     * Constant for light purple color code.
     */
    public static final String LIGHT_PURPLE = "§d";

    /**
     * Constant for yellow color code.
     */
    public static final String YELLOW = "§e";

    /**
     * Constant for white color code.
     */
    public static final String WHITE = "§f";

    /**
     * Constant for obfuscated style.
     */
    public static final String STYLE_OBFUSCATED = "§k";

    /**
     * Constant for bold style.
     */
    public static final String STYLE_BOLD = "§l";

    /**
     * Constant for strike-through style.
     */
    public static final String STYLE_STRIKE = "§m";

    /**
     * Constant for underline style.
     */
    public static final String STYLE_UNDERLINE = "§n";

    /**
     * Constant for italic style.
     */
    public static final String STYLE_ITALIC = "§o";

    /**
     * Constant to reset formatting.
     */
    public static final String RESET = "§r";

    // Private constructor to prevent instantiation
    TooltipConstants() {
        Exceptions.throwCtorAssertion();
    }
}