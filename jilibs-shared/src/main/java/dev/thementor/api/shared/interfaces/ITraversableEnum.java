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

package dev.thementor.api.shared.interfaces;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents an interface for traversable enums, allowing easy navigation between enum constants.
 *
 * @param <T> the type of the enum
 */
@SuppressWarnings({"unchecked", "unused"})
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface ITraversableEnum<T extends Enum<T>>
{
    /**
     * Retrieves the next enum constant in a circular fashion after the current one.
     *
     * @return the next enum constant
     */
    default T next()
    {
        T[] values = ((Class<T>) ((T) this).getClass()).getEnumConstants();
        int nextOrdinal = (((T) this).ordinal() + 1) % values.length;
        return values[nextOrdinal];
    }

    /**
     * Retrieves the previous enum constant in a circular fashion before the current one.
     *
     * @return the previous enum constant
     */
    default T previous()
    {
        T[] values = ((Class<T>) ((T) this).getClass()).getEnumConstants();
        int nextOrdinal = (((T) this).ordinal() - 1 + values.length) % values.length;
        return values[nextOrdinal];
    }
}