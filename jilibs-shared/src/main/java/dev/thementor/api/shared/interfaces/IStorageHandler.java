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

import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.util.math.Direction;

import java.util.List;
import java.util.Map;

/**
 * Represents an interface for managing storage entities.
 *
 * @param <T> the type of the storage entity
 */
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IStorageHandler<T>
{
    /**
     * Adds a storage entity to this handler.
     *
     * @param storage the storage entity to add
     */
    void addStorage(T storage);

    /**
     * Adds a storage entity to this handler in a specific direction using {@link MappedDirection}.
     *
     * @param storage  the storage entity to add
     * @param direction the direction of the connection
     */
    void addStorage(T storage, MappedDirection direction);

    /**
     * Adds a storage entity to this handler in a specific direction.
     *
     * @param storage  the storage entity to add
     * @param direction the direction of the connection
     */
    void addStorage(T storage, Direction direction);

    /**
     * Retrieves all storage entities managed by this handler.
     *
     * @return a list of all storage entities
     */
    List<T> getStorages();

    /**
     * Retrieves a map of storage entities indexed by their {@link MappedDirection}.
     *
     * @return a map of storage entities
     */
    Map<MappedDirection, T> getSidedMap();

    /**
     * Retrieves the storage entity associated with a specific side using {@link MappedDirection}.
     *
     * @param side the side to retrieve the storage for
     * @return the storage entity associated with the given side
     */
    T getStorage(MappedDirection side);

    /**
     * Retrieves the storage entity associated with a specific direction.
     *
     * @param side the direction of the connection
     * @return the storage entity associated with the given direction
     */
    T getStorage(Direction side);

    /**
     * Retrieves the storage entity at a specified index in the list of managed storages.
     *
     * @param index the index of the storage to retrieve
     * @return the storage entity at the given index
     */
    T getStorage(int index);
}