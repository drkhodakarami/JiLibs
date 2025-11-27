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

package dev.thementor.api.fluid.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;

import dev.thementor.api.base.StorageConnector;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.FluidStackPayload;
import dev.thementor.api.shared.utils.DirectionHelper;

@SuppressWarnings("unused")
public class FluidConnector<T extends SingleFluidStorage> extends StorageConnector<T>
        implements IStorageConnector<FluidConnector<T>>, IStorageProvider<T>
{
    private final CombinedStorage<FluidVariant, T> combinedStorage = new CombinedStorage<>(this.storages);

    public CombinedStorage<FluidVariant, T> getCombinedStorage()
    {
        return combinedStorage;
    }

    public List<FluidStackPayload> getFluids()
    {
        List<FluidStackPayload> fluids = new ArrayList<>();
        this.storages.forEach(
                storage ->
                        storage.nonEmptyViews().forEach(
                                fluidContainer ->
                                        fluids.add(new FluidStackPayload(fluidContainer.getResource(), fluidContainer.getAmount()))));
        return fluids;
    }

    public void addStorage(BlockEntity blockEntity, int size, BiFunction<BlockEntity, Integer, T> factory)
    {
        addStorage(factory.apply(blockEntity, size));
    }

    public void addStorage(BlockEntity blockEntity, int size, MappedDirection side, BiFunction<BlockEntity, Integer, T> factory)
    {
        addStorage(factory.apply(blockEntity, size), side);
    }

    public void addStorage(BlockEntity blockEntity, int size, Direction side, BiFunction<BlockEntity, Integer, T> factory)
    {
        addStorage(factory.apply(blockEntity, size), side);
    }

    public long getAmount(MappedDirection direction)
    {
        return getStorage(direction).getAmount();
    }

    public long getCapacity(MappedDirection direction)
    {
        return getStorage(direction).getCapacity();
    }

    public FluidVariant getVariant(MappedDirection direction)
    {
        return getStorage(direction).getResource();
    }

    public long getAmount(int index)
    {
        return getStorage(index).getAmount();
    }

    public long getCapacity(int index)
    {
        return getStorage(index).getCapacity();
    }

    public FluidVariant getVariant(int index)
    {
        return getStorage(index).getResource();
    }

    @Override
    public FluidConnector<T> getConnector()
    {
        return this;
    }

    @Override
    public T getStorageProvider(MappedDirection direction, Direction facing)
    {

        Direction side = DirectionHelper.relativeDirection(MappedDirection.toDirection(direction), facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return getStorage(side);
        return null;
    }

    @Override
    public T getStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return getStorage(side);
        return null;
    }

    @Override
    public void saveAdditional(ValueOutput writeView)
    {
        ValueOutput.ValueOutputList list = writeView.childrenList("fluid" + BEKeys.HAS_FLUID);
        for (T storage : storages)
        {
            if(storage instanceof SingleFluidStorage singleFluidStorage)
            {
                ValueOutput compoundView = list.addChild();
                singleFluidStorage.writeData(compoundView);
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public void loadAdditional(ValueInput readView)
    {
        int index = 0;
        for (ValueInput view : readView.childrenListOrEmpty("fluid" + BEKeys.HAS_FLUID))
        {
            if(index >= storages.size())
                break;

            T storage = this.storages.get(index);

            if(storage instanceof SingleFluidStorage singleFluidStorage)
                singleFluidStorage.readData(view);
            else
                throw new UnsupportedOperationException("Unsupported storage type: " + storage.getClass().getName());

            index++;
        }
    }
}