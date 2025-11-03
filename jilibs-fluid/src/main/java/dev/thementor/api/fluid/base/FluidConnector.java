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

package dev.thementor.api.fluid.base;

import dev.thementor.api.base.StorageConnector;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.FluidStackPayload;
import dev.thementor.api.shared.utils.DirectionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

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
        this.storages.forEach(storage -> {
            storage.nonEmptyViews().forEach(fluidContainer -> {
                fluids.add(new FluidStackPayload(fluidContainer.getResource(), fluidContainer.getAmount()));
            });
        });
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
    public void writeData(WriteView writeView)
    {
        WriteView.ListView list = writeView.getList("fluid" + BEKeys.HAS_FLUID);
        for (T storage : storages)
        {
            if(storage instanceof SingleFluidStorage singleFluidStorage)
            {
                /*WriteView fluidView = list.add();
                fluidView.putLong("Amount", singleFluidStorage.getAmount());
                DataResult<NbtElement> encoded = FluidVariant.CODEC.encode(singleFluidStorage.getResource(), NbtOps.INSTANCE, new NbtCompound());

                if(encoded.result().isPresent())
                {
                    NbtElement element = encoded.result().get();

                    if(element instanceof NbtCompound compound)
                        fluidView.put("fluid", NbtCompound.CODEC, compound);
                    else
                        throw new IllegalArgumentException("Encoded Fluid Variant is not an NbtCompound");
                }
                else
                    throw new IllegalArgumentException("Fluid Variant encoding failed : " + encoded.error());*/

                WriteView compoundView = list.add();
                singleFluidStorage.writeData(compoundView);
            }
        }
    }

    @Override
    public void readData(ReadView readView)
    {
        int index = 0;
        for (ReadView view : readView.getListReadView("fluid" + BEKeys.HAS_FLUID))
        {
            if(index >= storages.size())
                break;

            T storage = this.storages.get(index);

            if(storage instanceof SingleFluidStorage singleFluidStorage)
            {
                /*singleFluidStorage.amount = view.getLong("Amount", 0L);

                Optional<NbtCompound> optionalVariantCompound = view.read("Fluid", NbtCompound.CODEC);

                optionalVariantCompound.ifPresent(nbt ->
                                                          singleFluidStorage.variant =
                                                                  FluidVariant.CODEC.decode(NbtOps.INSTANCE, nbt)
                                                                                    .map(Pair::getFirst)
                                                                                    .getOrThrow());*/
                singleFluidStorage.readData(view);
            }
            else
                //noinspection DataFlowIssue
                throw new UnsupportedOperationException("Unsupported storage type: " + storage.getClass().getName());

            index++;
        }
    }
}