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

package dev.thementor.api.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.core.Direction;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageHandler;
import dev.thementor.api.shared.interfaces.IViewSerializable;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
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