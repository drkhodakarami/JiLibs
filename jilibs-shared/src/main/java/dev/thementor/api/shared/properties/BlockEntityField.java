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

import dev.thementor.api.shared.interfaces.IFieldGetter;
import dev.thementor.api.shared.interfaces.IFieldSetter;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a single field in a BlockEntity, including its value and optional getter and setter methods.
 *
 * @param <T> the type of the value stored in the field
 * @param <B> the type of BlockEntity this field belongs to
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class BlockEntityField<T, B extends BlockEntity>
{
    /**
     * The value stored in the field.
     */
    private final T value;

    /**
     * The optional getter method for the field value.
     */
    private final @Nullable IFieldGetter<T, B> getter;

    /**
     * The optional setter method for the field value.
     */
    private final @Nullable IFieldSetter<T, B> setter;

    //region CTOR
    /**
     * Constructs a new BlockEntityField with the specified value and both getter and setter methods.
     *
     * @param value  the value of the field
     * @param getter the method to retrieve the field's value from the BlockEntity
     * @param setter the method to set the field's value on the BlockEntity
     */
    public BlockEntityField(T value, @Nullable IFieldGetter<T, B> getter, @Nullable IFieldSetter<T, B> setter)
    {
        this.value = value;
        this.getter = getter;
        this.setter = setter;
    }

    /**
     * Constructs a new BlockEntityField with the specified value and only the getter method.
     *
     * @param value  the value of the field
     * @param getter the method to retrieve the field's value from the BlockEntity
     */
    public BlockEntityField(T value, @Nullable IFieldGetter<T, B> getter)
    {
        this(value, getter, null);
    }

    /**
     * Constructs a new BlockEntityField with the specified value and only the setter method.
     *
     * @param value  the value of the field
     * @param setter the method to set the field's value on the BlockEntity
     */
    public BlockEntityField(T value, @Nullable IFieldSetter<T, B> setter)
    {
        this(value, null, setter);
    }

    /**
     * Constructs a new BlockEntityField with the specified value and no getter or setter methods.
     *
     * @param value the value of the field
     */
    public BlockEntityField(T value)
    {
        this(value, null, null);
    }
    //endregion

    /**
     * Retrieves the stored value of the field.
     *
     * @return the value of the field
     */
    public T getValue()
    {
        return this.value;
    }

    /**
     * Retrieves the optional getter method for the field value.
     *
     * @return the getter method if it exists; null otherwise
     */
    public @Nullable IFieldGetter<T, B> getGetter()
    {
        return this.getter;
    }

    /**
     * Retrieves the optional setter method for the field value.
     *
     * @return the setter method if it exists; null otherwise
     */
    public @Nullable IFieldSetter<T, B> getSetter()
    {
        return this.setter;
    }

    /**
     * Retrieves the value of the field from the specified BlockEntity using an optional getter method.
     *
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the value of the field
     */
    public T get(B blockEntity)
    {
        return this.getter != null ? this.getter.get(blockEntity) : this.value;
    }

    /**
     * Sets the value of the field on the specified BlockEntity using an optional setter method.
     *
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value       the new value for the field
     */
    public void set(B blockEntity, T value)
    {
        if(this.setter != null)
            this.setter.set(blockEntity, value);
    }
}