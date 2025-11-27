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

package dev.thementor.api.shared.properties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

import dev.thementor.api.shared.annotations.*;

/**
 * Manages state properties for a block, providing methods to add, retrieve, and apply these properties.
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@SuppressWarnings("unused")
public class StateProperties
{
    /**
     * A concurrent map to store state properties by their names.
     */
    private final Map<String, StateProperty<?>> properties = new ConcurrentHashMap<>();

    //region FACING AND AXIS
    /**
     * Adds a horizontal facing property to the block state with a default value of NORTH.
     */
    public void addHorizontalFacing()
    {
        addProperty(new StateProperty<>(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    /**
     * Adds a general facing property to the block state with a default value of NORTH.
     */
    public void addFacing()
    {
        addProperty(new StateProperty<>(BlockStateProperties.FACING, Direction.NORTH));
    }

    /**
     * Adds an axis property to the block state with a default value of Y.
     */
    public void addAxis()
    {
        addProperty(new StateProperty<>(BlockStateProperties.AXIS, Direction.Axis.Y));
    }
    //endregion

    //region BOOLEAN
    /**
     * Adds an enabled property to the block state with a default value of true.
     */
    public void addEnabled()
    {
        addProperty(new StateProperty<>(BlockStateProperties.ENABLED, true));
    }

    /**
     * Adds a locked property to the block state with a default value of false.
     */
    public void addLocked()
    {
        addProperty(new StateProperty<>(BlockStateProperties.LOCKED, false));
    }

    /**
     * Adds a powered property to the block state with a default value of false.
     */
    public void addPowered()
    {
        addProperty(new StateProperty<>(BlockStateProperties.POWERED, false));
    }

    /**
     * Adds a lit property to the block state with a default value of false.
     */
    public void addLit()
    {
        addProperty(new StateProperty<>(BlockStateProperties.LIT, false));
    }

    /**
     * Adds an unstable property to the block state with a default value of false.
     */
    public void addUnstable()
    {
        addProperty(new StateProperty<>(BlockStateProperties.UNSTABLE, false));
    }
    //endregion

    /**
     * Adds a waterloggable property to the block state with a default value of false.
     */
    public void addWaterlogged()
    {
        addProperty(new StateProperty<>(BlockStateProperties.WATERLOGGED, false));
    }

    /**
     * Adds a custom state property to the block state.
     *
     * @param <T>       the type of the state property
     * @param property  the state property to add
     */
    public <T extends Comparable<T>> void addProperty(StateProperty<T> property)
    {
        if (this.properties.containsKey(property.delegate().getName()))
            throw new IllegalArgumentException("Property with name: " + property.delegate().getName() + " already exists!");

        this.properties.put(property.delegate().getName(), property);
    }

    /**
     * Retrieves a state property by its name and type.
     *
     * @param <T>       the type of the state property
     * @param name      the name of the state property
     * @param type      the class type of the value
     * @return the retrieved state property
     */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> StateProperty<T> getProperty(String name, Class<T> type)
    {
        StateProperty<?> stateProperty = this.properties.get(name);
        if (stateProperty == null)
            throw new IllegalArgumentException("Property with name: " + name + " does not exist!");

        if (!type.isInstance(stateProperty.delegate().getValueClass()))
            throw new IllegalArgumentException("Property with name: " + name + " is not of the correct type: " + type.getSimpleName());

        try
        {
            return (StateProperty<T>) stateProperty;
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("Property with name: " + name + " is not of the correct type: " + type.getSimpleName());
        }
    }

    /**
     * Retrieves a state property by its delegate Property.
     *
     * @param property the delegate Property
     * @return the retrieved state property if it exists; null otherwise
     */
    public <T extends Comparable<T>> Property<T> getProperty(Property<T> property)
    {
        String name = property.getName();
        return containsProperty(name) ? getProperty(name, property.getValueClass()).delegate() : null;
    }

    /**
     * Checks if a state property with the specified name exists.
     *
     * @param name the name of the state property
     * @return true if the property exists, false otherwise
     */
    public boolean containsProperty(String name)
    {
        return this.properties.containsKey(name);
    }

    /**
     * Checks if a state property with the specified delegate Property exists.
     *
     * @param property the delegate Property
     * @return true if the property exists, false otherwise
     */
    public boolean containsProperty(Property<?> property)
    {
        return containsProperty(property.getName());
    }

    /**
     * Sets the default value for a state property by its name and type.
     *
     * @param <T>       the type of the state property
     * @param name      the name of the state property
     * @param value     the default value for the state property
     */
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void setDefaultValue(String name, T value)
    {
        StateProperty<T> stateProperty = getProperty(name, (Class<T>) value.getClass());
        stateProperty.setDefaultValue(value);
    }

    /**
     * Applies the default values to a given block state.
     *
     * @param state the block state to apply defaults to
     * @return the modified block state with default values applied
     */
    private <T extends Comparable<T>> BlockState applyDefault(BlockState state, StateProperty<T> property)
    {
        return state.setValue(property.delegate(), property.defaultValue());
    }

    /**
     * Applies default values to a given block state if it is not null.
     *
     * @param state the block state to apply defaults to
     * @return the modified block state with default values applied, or null if the input state was null
     */
    public @Nullable BlockState applyDefaults(@Nullable BlockState state)
    {
        if(state == null)
            return null;

        for (StateProperty<?> property : this.properties.values())
            state = applyDefault(state, property);

        return state;
    }

    /**
     * Adds all managed state properties to a builder.
     *
     * @param builder the StateManager.Builder to add the properties to
     */
    public void addToBuilder(StateDefinition.Builder<Block, BlockState> builder)
    {
        for (StateProperty<?> property : this.properties.values())
            builder.add(property.delegate());
    }
}