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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Provides utility methods for string formatting and numerical conversion.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20, TurtyWurty")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class StringHelper
{
    /**
     * The constant representing 20 as a BigDecimal for numerical operations.
     */
    private static final BigDecimal TWENTY = new BigDecimal(20);

    /**
     * Formats an integer value with commas.
     *
     * @param value The integer value to format.
     * @return A formatted string with thousands separators.
     */
    public static String formatted(int value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(value);
    }

    /**
     * Formats a float value with commas and two decimal places.
     *
     * @param value The float value to format.
     * @return A formatted string with thousands separators and two decimal places.
     */
    public static String formatted(float value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    /**
     * Formats a double value with commas and two decimal places.
     *
     * @param value The double value to format.
     * @return A formatted string with thousands separators and two decimal places.
     */
    public static String formatted(double value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    /**
     * Formats a long value with commas.
     *
     * @param value The long value to format.
     * @return A formatted string with thousands separators.
     */
    public static String formatted(long value)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }

    /**
     * Formats a count with an appropriate suffix (k, M, G, T, P, E) for large numbers.
     *
     * @param count The count to format.
     * @return A formatted string with a potential suffix and commas.
     */
    public static String withSuffix(int count)
    {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f%c",
                             count / Math.pow(1000, exp),
                             "kMGTPE".charAt(exp - 1));
    }

    /**
     * Formats a float with an appropriate suffix (k, M, G, T, P, E) for large numbers.
     *
     * @param value The float to format.
     * @return A formatted string with a potential suffix and two decimal places.
     */
    public static String withSuffix(float value)
    {
        if (value < 1000)
            return String.valueOf(value);

        int exp = (int) (Math.log(value) / Math.log(1000));
        return String.format("%.1f%c",
                             value / Math.pow(1000, exp),
                             "kMGTPE_____".charAt(exp - 1));
    }

    /**
     * Formats a double with an appropriate suffix (k, M, G, T, P, E) for large numbers.
     *
     * @param value The double to format.
     * @return A formatted string with a potential suffix and two decimal places.
     */
    public static String withSuffix(double value)
    {
        if (value < 1000)
            return String.valueOf(value);

        int exp = (int) (Math.log(value) / Math.log(1000));
        return String.format("%.1f%c",
                             value / Math.pow(1000, exp),
                             "kMGTPE_____".charAt(exp - 1));
    }

    /**
     * Formats a long with an appropriate suffix (k, M, G, T, P, E) for large numbers.
     *
     * @param value The long to format.
     * @return A formatted string with a potential suffix and commas.
     */
    public static String withSuffix(long value)
    {
        if (value < 1000)
            return String.valueOf(value);

        int exp = (int) (Math.log(value) / Math.log(1000));
        return String.format("%.1f%c",
                             value / Math.pow(1000, exp),
                             "kMGTPE_____".charAt(exp - 1));
    }

    /**
     * Converts ticks to seconds with a precision of one decimal place.
     *
     * @param ticks The number of ticks to convert.
     * @return A formatted string representing the equivalent time in seconds.
     */
    public static String ticksInSeconds(int ticks)
    {
        BigDecimal value = new BigDecimal(ticks);
        value = value.divide(TWENTY, 1, RoundingMode.HALF_UP);
        return value.toString();
    }

    @Developer("TurtyWurty")
    public static String snakeToTypeCase(String str)
    {
        StringBuilder result = new StringBuilder();
        boolean toUpperCase = true;
        for (char c : str.toCharArray()) {
            if (c == '_') {
                toUpperCase = true;
            } else {
                result.append(toUpperCase ? Character.toUpperCase(c) : c);
                toUpperCase = false;
            }
        }

        return result.toString();
    }
}