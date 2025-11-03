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

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

import java.util.Random;
import java.util.function.Predicate;

/**
 * Provides utility methods for mathematical operations.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class MathHelper
{
    /**
     * The random number generator used by the utility methods.
     */
    private static final Random rand = new Random();

    /**
     * Private constructor to prevent instantiation.
     */
    public MathHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Generates a random double between the specified minimum and maximum values.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random double within the specified range.
     */
    public static double nextDouble(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    /**
     * Generates a random integer between the specified minimum and maximum values.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random integer within the specified range.
     */
    public static int nextInt(int min, int max) {
        return min + (max - min) * rand.nextInt();
    }

    /**
     * Generates a random float between the specified minimum and maximum values.
     *
     * @param min The minimum value (inclusive).
     * @param max The maximum value (exclusive).
     * @return A random float within the specified range.
     */
    public static float nextFloat(float min, float max) {
        return min + (max - min) * rand.nextFloat();
    }

    /**
     * Creates a predicate that checks if an integer is equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count equals the given value.
     */
    public static Predicate<Integer> countEquals(int value)
    {
        return count -> count == value;
    }

    /**
     * Creates a predicate that checks if an integer is less than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than the given value.
     */
    public static Predicate<Integer> countLessThan(int value)
    {
        return count -> count < value;
    }

    /**
     * Creates a predicate that checks if an integer is less than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than or equal to the given value.
     */
    public static Predicate<Integer> countLessThanOrEquals(int value)
    {
        return count -> count <= value;
    }

    /**
     * Creates a predicate that checks if an integer is greater than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than the given value.
     */
    public static Predicate<Integer> countGreaterThan(int value)
    {
        return count -> count > value;
    }

    /**
     * Creates a predicate that checks if an integer is greater than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than or equal to the given value.
     */
    public static Predicate<Integer> countGreaterThanOrEquals(int value)
    {
        return count -> count >= value;
    }

    /**
     * Creates a predicate that checks if a float is equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count equals the given value.
     */
    public static Predicate<Float> countEquals(float value)
    {
        return count -> count == value;
    }

    /**
     * Creates a predicate that checks if a float is less than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than the given value.
     */
    public static Predicate<Float> countLessThan(float value)
    {
        return count -> count < value;
    }

    /**
     * Creates a predicate that checks if a float is less than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than or equal to the given value.
     */
    public static Predicate<Float> countLessThanOrEquals(float value)
    {
        return count -> count <= value;
    }

    /**
     * Creates a predicate that checks if a float is greater than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than the given value.
     */
    public static Predicate<Float> countGreaterThan(float value)
    {
        return count -> count > value;
    }

    /**
     * Creates a predicate that checks if a float is greater than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than or equal to the given value.
     */
    public static Predicate<Float> countGreaterThanOrEquals(float value)
    {
        return count -> count >= value;
    }

    /**
     * Creates a predicate that checks if a double is equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count equals the given value.
     */
    public static Predicate<Double> countEquals(double value)
    {
        return count -> count == value;
    }

    /**
     * Creates a predicate that checks if a double is less than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than the given value.
     */
    public static Predicate<Double> countLessThan(double value)
    {
        return count -> count < value;
    }

    /**
     * Creates a predicate that checks if a double is less than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is less than or equal to the given value.
     */
    public static Predicate<Double> countLessThanOrEquals(double value)
    {
        return count -> count <= value;
    }

    /**
     * Creates a predicate that checks if a double is greater than the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than the given value.
     */
    public static Predicate<Double> countGreaterThan(double value)
    {
        return count -> count > value;
    }

    /**
     * Creates a predicate that checks if a double is greater than or equal to the given value.
     *
     * @param value The value to compare against.
     * @return A predicate that returns true if the count is greater than or equal to the given value.
     */
    public static Predicate<Double> countGreaterThanOrEquals(double value)
    {
        return count -> count >= value;
    }

    /**
     * Calculates the reverse chance for a given value.
     *
     * @param value The value to calculate the reverse chance for.
     * @return The reverse chance as a float between 0 and 1.
     */
    public static float getReverseChance(int value)
    {
        return (100 - value) / 100f;
    }

    /**
     * Checks if a random chance is successful based on the given value.
     *
     * @param value The value to determine the chance for.
     * @return True if the random chance is successful, false otherwise.
     */
    public static boolean getChance(int value)
    {
        return rand.nextFloat() >= getReverseChance(value);
    }
}