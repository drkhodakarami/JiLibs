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

package dev.thementor.api.energy.base;

import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import net.minecraft.core.Direction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.base.StorageConnector;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.utils.DirectionHelper;

@SuppressWarnings("unused")
public class EnergyConnector<T extends EnergyStorage> extends StorageConnector<EnergyStorage>
        implements IStorageConnector<EnergyConnector<T>>, IStorageProvider<T>
{
    @Override
    public EnergyConnector<T> getConnector()
    {
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable T getStorageProvider(MappedDirection direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(MappedDirection.toDirection(direction), facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return (T) getStorage(side);
        return null;
    }

    @SuppressWarnings("unchecked")
    public T getStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return (T) getStorage(side);
        return null;
    }

    public long getAmount(MappedDirection direction)
    {
        return getStorage(direction).getAmount();
    }

    public long getAmount(Direction direction)
    {
        return getStorage(direction).getAmount();
    }

    public long getAmount(int index)
    {
        return getStorage(index).getAmount();
    }

    public long getCapacity(MappedDirection direction)
    {
        return getStorage(direction).getCapacity();
    }

    public long getCapacity(Direction direction)
    {
        return getStorage(direction).getCapacity();
    }

    public long getCapacity(int index)
    {
        return getStorage(index).getCapacity();
    }

    @Override
    public void saveAdditional(ValueOutput writeView)
    {
        ValueOutput.ValueOutputList list = writeView.childrenList("energy" + BEKeys.HAS_ENERGY);
        for(EnergyStorage storage : storages)
        {
            ValueOutput energyView = list.addChild();
            energyView.putLong("Amount", storage.getAmount());
        }
    }

    @Override
    public void loadAdditional(ValueInput readView)
    {
        int index = 0;
        for (ValueInput view : readView.childrenListOrEmpty("energy" + BEKeys.HAS_ENERGY))
        {
            if(index >= storages.size())
                break;

            var storage = this.storages.get(index);

            if(storage instanceof SimpleEnergyStorage simpleEnergyStorage)
                simpleEnergyStorage.amount = view.getLongOr("Amount", 0L);
            else
            {
                try(Transaction transaction = Transaction.openOuter())
                {
                    long amount = view.getLongOr("Amount", 0L);
                    long current = storage.getAmount();
                    if(current < amount)
                        storage.insert(amount - current, transaction);
                    else
                        storage.extract(current - amount, transaction);
                    transaction.commit();
                }
            }

            index++;
        }
    }
}