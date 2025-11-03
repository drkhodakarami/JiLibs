/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
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

package dev.thementor.api.base;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageHandler;
import dev.thementor.api.shared.interfaces.IViewSerializable;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public abstract class StorageConnector<T> implements IViewSerializable, IStorageHandler<T>
{
    protected final List<T> storages = new ArrayList<>(MappedDirection.values().length);
    protected final Map<MappedDirection, T> sidedMap = new HashMap<>(MappedDirection.values().length);

    public void addStorage(T storage)
    {
        addStorage(storage, MappedDirection.NONE);
    }

    public void addStorage(T storage, MappedDirection direction)
    {
        this.storages.add(storage);
        this.sidedMap.put(direction, storage);
    }

    public void addStorage(T storage, Direction direction)
    {
        this.storages.add(storage);
        this.sidedMap.put(MappedDirection.fromDirection(direction), storage);
    }

    public List<T> getStorages()
    {
        return this.storages;
    }

    public Map<MappedDirection, T> getSidedMap()
    {
        return this.sidedMap;
    }

    public T getStorage(MappedDirection side)
    {
        return this.sidedMap.get(side);
    }

    public T getStorage(Direction side)
    {
        return this.sidedMap.get(MappedDirection.fromDirection(side));
    }

    public T getStorage(int index)
    {
        return this.storages.get(index);
    }
}