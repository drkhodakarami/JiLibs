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

package dev.thementor.api.shared.properties;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.state.property.Property;

/**
 * Represents a state property of a block, including its delegate Property and default value.
 *
 * @param <T> the type of the value stored in the state property
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class StateProperty<T extends Comparable<T>>
{
    /**
     * The delegate Property of the state property.
     */
    private final Property<T> delegate;

    /**
     * The default value for the state property.
     */
    private T defaultValue;

    /**
     * Constructs a new instance of StateProperty with the specified delegate Property and default value.
     *
     * @param delegate  the delegate Property
     * @param defaultValue the default value for the state property
     */
    public StateProperty(Property<T> delegate, T defaultValue)
    {
        this.delegate = delegate;
        this.defaultValue = defaultValue;
    }

    /**
     * Retrieves the delegate Property of the state property.
     *
     * @return the delegate Property
     */
    public Property<T> delegate()
    {
        return this.delegate;
    }

    /**
     * Retrieves the default value for the state property.
     *
     * @return the default value
     */
    public T defaultValue()
    {
        return this.defaultValue;
    }

    /**
     * Sets a new default value for the state property.
     *
     * @param defaultValue the new default value
     */
    public void setDefaultValue(T defaultValue)
    {
        this.defaultValue = defaultValue;
    }
}