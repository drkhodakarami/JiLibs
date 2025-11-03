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

import java.util.HashMap;
import java.util.Map;

/**
 * Manages a collection of fields associated with a BlockEntity.
 *
 * @param <B> the type of BlockEntity this collection belongs to
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class BlockEntityFields<B extends BlockEntity>
{
    /**
     * A map of fields, indexed by their names.
     */
    private final Map<String, BlockEntityField<?, B>> fields = new HashMap<>();

    /**
     * Adds a field to the collection with the specified name and default value.
     *
     * @param <T>       the type of the value stored in the field
     * @param name      the name of the field
     * @param defaultValue the default value for the field
     * @return the newly added BlockEntityField instance
     */
    public  <T> BlockEntityField<T, B> addField(String name, T defaultValue)
    {
        BlockEntityField<T, B> field = new BlockEntityField<>(defaultValue);
        this.fields.put(name, field);
        return field;
    }

    /**
     * Adds a field to the collection with the specified name, default value, and getter and setter methods.
     *
     * @param <T>       the type of the value stored in the field
     * @param name      the name of the field
     * @param defaultValue the default value for the field
     * @param getter    the method to retrieve the field's value from the BlockEntity
     * @param setter    the method to set the field's value on the BlockEntity
     * @return the newly added BlockEntityField instance
     */
    @SuppressWarnings("UnusedReturnValue")
    public <T> BlockEntityField<T, B> addField(String name, T defaultValue, IFieldGetter<T, B> getter, IFieldSetter<T, B> setter)
    {
        BlockEntityField<T, B> field = new BlockEntityField<>(defaultValue, getter, setter);
        this.fields.put(name, field);
        return field;
    }

    /**
     * Checks if a field with the specified name exists in the collection.
     *
     * @param name the name of the field to check
     * @return true if the field exists, false otherwise
     */
    public boolean containsField(String name)
    {
        return this.fields.containsKey(name);
    }

    //region GET FIELD
    /**
     * Retrieves a BlockEntityField instance by name and type.
     *
     * @param <T>       the type of the value stored in the field
     * @param name      the name of the field
     * @param type      the class type of the value
     * @return the retrieved BlockEntityField instance
     */
    public <T> BlockEntityField<T, B> getField(String name, Class<T> type)
    {
        try
        {
            if(!containsField(name))
                throw new IllegalArgumentException("Field does not exist: " + name);

            BlockEntityField<?, B> field = this.fields.get(name);
            if(field == null)
                throw new IllegalArgumentException("Field does not exist: " + name);

            if(type.isInstance(field.getValue()))
                //noinspection unchecked
                return (BlockEntityField<T, B>) field;
            else
                throw new IllegalArgumentException("Field with name '" + name + "' is not of type " + type.getSimpleName());
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Field with name '" + name + "' is not of type " + type.getSimpleName());
        }
    }

    /**
     * Retrieves the value of a field from the specified BlockEntity.
     *
     * @param <T>       the type of the value stored in the field
     * @param name      the name of the field
     * @param type      the class type of the value
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the value of the field
     */
    public <T> T getFieldValue(String name, Class<T> type, B blockEntity)
    {
        return getField(name, type).get(blockEntity);
    }

    /**
     * Retrieves an integer value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the integer value of the field
     */
    public int getFieldValueInt(String name, B blockEntity)
    {
        return getFieldValue(name, Integer.class, blockEntity);
    }

    /**
     * Retrieves a long value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the long value of the field
     */
    public long getFieldValueLong(String name, B blockEntity)
    {
        return getFieldValue(name, Long.class, blockEntity);
    }

    /**
     * Retrieves a float value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the float value of the field
     */
    public float getFieldValueFloat(String name, B blockEntity)
    {
        return getFieldValue(name, Float.class, blockEntity);
    }

    /**
     * Retrieves a double value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the double value of the field
     */
    public double getFieldValueDouble(String name, B blockEntity)
    {
        return getFieldValue(name, Double.class, blockEntity);
    }

    /**
     * Retrieves a boolean value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the boolean value of the field
     */
    public boolean getFieldValueBoolean(String name, B blockEntity)
    {
        return getFieldValue(name, Boolean.class, blockEntity);
    }

    /**
     * Retrieves a string value of a field from the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to retrieve the field's value from
     * @return the string value of the field
     */
    public String getFieldValueString(String name, B blockEntity)
    {
        return getFieldValue(name, String.class, blockEntity);
    }
    //endregion

    //region SET FIELD
    /**
     * Sets a value on a field in the specified BlockEntity.
     *
     * @param <T>       the type of the value stored in the field
     * @param name      the name of the field
     * @param type      the class type of the value
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new value for the field
     */
    public <T> void setFieldValue(String name, Class<T> type, B blockEntity, T value)
    {
        getField(name, type).set(blockEntity, value);
    }

    /**
     * Sets an integer value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new integer value for the field
     */
    public void setFieldValueInt(String name, B blockEntity, int value)
    {
        setFieldValue(name, Integer.class, blockEntity, value);
    }

    /**
     * Sets a long value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new long value for the field
     */
    public void setFieldValueLong(String name, B blockEntity, long value)
    {
        setFieldValue(name, Long.class, blockEntity, value);
    }

    /**
     * Sets a float value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new float value for the field
     */
    public void setFieldValueFloat(String name, B blockEntity, float value)
    {
        setFieldValue(name, Float.class, blockEntity, value);
    }

    /**
     * Sets a double value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new double value for the field
     */
    public void setFieldValueDouble(String name, B blockEntity, double value)
    {
        setFieldValue(name, Double.class, blockEntity, value);
    }

    /**
     * Sets a boolean value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new boolean value for the field
     */
    public void setFieldValueBoolean(String name, B blockEntity, boolean value)
    {
        setFieldValue(name, Boolean.class, blockEntity, value);
    }

    /**
     * Sets a string value on a field in the specified BlockEntity.
     *
     * @param name      the name of the field
     * @param blockEntity the BlockEntity to set the field's value on
     * @param value     the new string value for the field
     */
    public void setFieldValueString(String name, B blockEntity, String value)
    {
        setFieldValue(name, String.class, blockEntity, value);
    }
    //endregion
}