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

package dev.thementor.api.ticklogic.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/**
 * A blackboard used to store and retrieve data in a key-value format.
 */
public class Blackboard
{
    /**
     * The data stored in the blackboard.
     */
    private final Map<String, Object> data = new HashMap<>();

    /**
     * Constructs a new Blackboard instance.
     */
    public Blackboard()
    {}

    /**
     * Puts a value in the blackboard with a given key and type.
     *
     * @param key   the key for the value
     * @param value the value to store
     * @throws IllegalArgumentException if the value's type is not supported
     */
    public void put(String key, Object value)
    {
        data.put(key, value);
    }

    /**
     * Retrieves a value from the blackboard with a given key and type.
     *
     * @param key   the key for the value
     * @param type  the class of the expected type
     * @param <T>   the type of the expected value
     * @return the value if found, otherwise null
     */
    @Nullable
    public <T> T get(String key, Class<T> type)
    {
        Object entry = data.get(key);

        if(entry == null)
            return null;

        if(!type.isInstance(entry))
            return null;

        return type.cast(entry);
    }

    /**
     * Retrieves an integer value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the integer value, or 0 if not found
     */
    public int getInt(String key)
    {
        var value = get(key, Integer.class);
        return value == null ? 0 : value;
    }

    /**
     * Retrieves a float value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the float value, or 0.0f if not found
     */
    public float getFloat(String key)
    {
        var value = get(key, Float.class);
        return value == null ? 0.0f : value;
    }

    /**
     * Retrieves a double value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the double value, or 0 if not found
     */
    public double getDouble(String key)
    {
        var value = get(key, Double.class);
        return value == null ? 0 : value;
    }

    /**
     * Retrieves a long value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the long value, or 0 if not found
     */
    public long getLong(String key)
    {
        var value = get(key, Long.class);
        return value == null ? 0 : value;
    }

    /**
     * Retrieves a boolean value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the boolean value, or false if not found
     */
    public boolean getBoolean(String key)
    {
        var value = get(key, Boolean.class);
        return value != null && value;
    }

    /**
     * Retrieves a string value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the string value, or an empty string if not found
     */
    public String getString(String key)
    {
        var value = get(key, String.class);
        return value == null ? "" : value;
    }

    /**
     * Retrieves a BlockPos value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the BlockPos value, or null if not found
     */
    @Nullable
    public BlockPos getBlockPos(String key)
    {
        return get(key, BlockPos.class);
    }

    /**
     * Retrieves a Vec3d value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the Vec3d value, or Vec3d.ZERO if not found
     */
    public Vec3 getVec3(String key)
    {
        var value = get(key, Vec3.class);
        return value == null ? Vec3.ZERO : value;
    }

    /**
     * Retrieves a Vec3i value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the Vec3i value, or Vec3i.ZERO if not found
     */
    public Vec3i getVec3i(String key)
    {
        var value = get(key, Vec3i.class);
        return value == null ? Vec3i.ZERO : value;
    }

    /**
     * Retrieves a Vec2f value from the blackboard with a given key.
     *
     * @param key   the key for the value
     * @return the Vec2f value, or Vec2f.ZERO if not found
     */
    public Vec2 getVec2(String key)
    {
        var value = get(key, Vec2.class);
        return value == null ? Vec2.ZERO : value;
    }

    /**
     * Removes a value from the blackboard with a given key.
     *
     * @param key the key for the value to remove
     */
    public void remove(String key)
    {
        data.remove(key);
    }

    /**
     * Clears all values from the blackboard.
     */
    public void clear()
    {
        data.clear();
    }

    /**
     * Returns an unmodifiable view of the blackboard's data.
     *
     * @return the data map
     */
    public Map<String, Object> getData()
    {
        return Collections.unmodifiableMap(data);
    }

    /**
     * Returns a string representation of the blackboard, showing its contents.
     *
     * @return a string representing the blackboard
     */
    @Override
    public String toString()
    {
        return "Blackboard {" + data + " }";
    }

    /**
     * Converts a map to a Blackboard instance.
     *
     * @param map the map representation of the blackboard
     * @return the Blackboard instance
     */
    public static Blackboard fromMap(Map<String, Object> map)
    {
        Blackboard board = new Blackboard();
        board.data.putAll(map);
        return board;
    }
}