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