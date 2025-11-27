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

package dev.thementor.api.inventory.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;

import dev.thementor.api.base.StorageConnector;
import dev.thementor.api.inventory.record.PredicateInventoryStorage;
import dev.thementor.api.inventory.storage.RecipeInventory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.utils.DirectionHelper;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class InventoryConnector<T extends SimpleContainer> extends StorageConnector<InventoryStorage>
    implements IStorageConnector<InventoryConnector<T>>, IStorageProvider<InventoryStorage>
{
    private final List<T> inventories = new ArrayList<>();
    private final List<Pair<MappedDirection, T>> sidedInventories = new ArrayList<>();

    private final CombinedStorage<ItemVariant, InventoryStorage> combinedStorage = new CombinedStorage<>(this.storages);

    //region ADD INVENTORY
    public void addStorage(@NotNull T inventory) {
        addStorage(inventory, MappedDirection.NONE);
    }

    public void addStorage(@NotNull T inventory, MappedDirection side)
    {
        this.inventories.add(inventory);
        this.sidedInventories.add(new Pair<>(side, inventory));
        var storage = InventoryStorage.of(inventory, MappedDirection.toDirection(side));
        addStorage(storage, side);
    }

    public void addStorage(@NotNull T inventory, Direction side)
    {
        addStorage(inventory, MappedDirection.fromDirection(side));
    }

    public void addStorage(@NotNull T inventory, Supplier<Boolean> canInsert, Supplier<Boolean> canExtract) {
        addStorage(inventory, MappedDirection.NONE, canInsert, canExtract);
    }

    public void addStorage(@NotNull T inventory, MappedDirection side, Supplier<Boolean> canInsert, Supplier<Boolean> canExtract)
    {
        this.inventories.add(inventory);
        this.sidedInventories.add(new Pair<>(side, inventory));
        var storage = PredicateInventoryStorage.of(InventoryStorage.of(inventory, MappedDirection.toDirection(side)), canInsert, canExtract);
        addStorage(storage, side);
    }

    public void addStorage(@NotNull T inventory, Direction side, Supplier<Boolean> canInsert, Supplier<Boolean> canExtract)
    {
        this.addStorage(inventory, MappedDirection.fromDirection(side), canInsert, canExtract);
    }

    public void addStorage(int size, Function<Integer, T> factory)
    {
        addStorage(factory.apply(size));
    }

    public void addStorage(int size, MappedDirection side, Function<Integer, T> factory)
    {
        addStorage(factory.apply(size), side);
    }

    public void addStorage(int size, Direction side, Function<Integer, T> factory)
    {
        addStorage(factory.apply(size), side);
    }

    public void addStorage(Function<ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(items));
    }

    public void addStorage(MappedDirection side, Function<ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(items), side);
    }

    //recipe, simple
    public void addStorage(Direction side, Function<ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(items), side);
    }

    //Used for synced, output, predicate
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

    public void addStorage(BlockEntity blockEntity, BiFunction<BlockEntity, ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(blockEntity, items));
    }

    public void addStorage(BlockEntity blockEntity, MappedDirection side, BiFunction<BlockEntity, ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(blockEntity, items), side);
    }

    public void addStorage(BlockEntity blockEntity, Direction side, BiFunction<BlockEntity, ItemStack[], T> factory, ItemStack... items)
    {
        addStorage(factory.apply(blockEntity, items), side);
    }
    //endregion

    //region ADD INSERT / EXTRACT ONLY
    public void addInsertOnlyInventory(@NotNull T inventory, MappedDirection side)
    {
        addInsertOnlyInventory(inventory, side, () -> true);
    }

    public void addInsertOnlyInventory(@NotNull T inventory, MappedDirection side, Supplier<Boolean> canInsert)
    {
        addStorage(inventory, side, canInsert, () -> false);
    }

    public void addExtractOnlyInventory(@NotNull T inventory, MappedDirection side)
    {
        addExtractOnlyInventory(inventory, side, () -> true);
    }

    public void addExtractOnlyInventory(@NotNull T inventory, MappedDirection side, Supplier<Boolean> canExtract)
    {
        addStorage(inventory, side, () -> false, canExtract);
    }

    public void addInsertOnlyInventory(@NotNull T inventory, Direction side)
    {
        addInsertOnlyInventory(inventory, side, () -> true);
    }

    public void addInsertOnlyInventory(@NotNull T inventory, Direction side, Supplier<Boolean> canInsert)
    {
        addStorage(inventory, side, canInsert, () -> false);
    }

    public void addExtractOnlyInventory(@NotNull T inventory, Direction side)
    {
        addExtractOnlyInventory(inventory, side, () -> true);
    }

    public void addExtractOnlyInventory(@NotNull T inventory, Direction side, Supplier<Boolean> canExtract)
    {
        addStorage(inventory, side, () -> false, canExtract);
    }
    //endregion

    public List<T> getInventories()
    {
        return inventories;
    }

    public CombinedStorage<ItemVariant, InventoryStorage> getCombinedStorage()
    {
        return combinedStorage;
    }

    //region GET INVENTORY
    public @Nullable T getInventory(int index)
    {
        return this.inventories.get(index);
    }

    public @Nullable T getInventory(MappedDirection side)
    {
        return this.inventories.get(this.storages.indexOf(getStorage(side)));
    }

    public @Nullable T getInventory(Direction side)
    {
        return this.inventories.get(this.storages.indexOf(getStorage(side)));
    }
    //endregion

    //region GET SLOT(S)
    public @Nullable SingleSlotStorage<ItemVariant> getSlot(int slotIndex, MappedDirection side)
    {
        return this.getStorage(side).getSlot(slotIndex);
    }

    public @Nullable SingleSlotStorage<ItemVariant> getSlot(int slotIndex, Direction side)
    {
        return this.getStorage(side).getSlot(slotIndex);
    }

    public @Nullable SingleSlotStorage<ItemVariant> getSlot(int slotIndex, int index)
    {
        return this.getStorage(index).getSlot(slotIndex);
    }

    public @Nullable List<SingleSlotStorage<ItemVariant>> getSlots(MappedDirection side)
    {
        return this.getStorage(side).getSlots();
    }

    public @Nullable List<SingleSlotStorage<ItemVariant>> getSlots(Direction side)
    {
        return this.getStorage(side).getSlots();
    }

    public @Nullable List<SingleSlotStorage<ItemVariant>> getSlots(int index)
    {
        return this.getStorage(index).getSlots();
    }
    //endregion

    public @NotNull List<ItemStack> getStacks()
    {
        List<ItemStack> stacks = new ArrayList<>();
        for (T inventory : this.inventories)
            for(int i = 0; i < inventory.getContainerSize(); i++)
                stacks.add(inventory.getItem(i));
        return stacks;
    }

    public void checkSize(int size)
    {
        int invSize = this.inventories.stream().map(Container::getContainerSize).reduce(0, Integer::sum);
        if( invSize != size)
            throw new IllegalArgumentException("Sie of inventories does not match the size provided: " + invSize + " => " + size);
    }

    public void onOpen(@NotNull Player player)
    {
        this.inventories.forEach(inventory -> inventory.startOpen(player));
    }

    public void removed(@NotNull Player player)
    {
        this.inventories.forEach(inventory -> inventory.stopOpen(player));
    }

    public void dropContent(@NotNull Level world, @NotNull BlockPos pos)
    {
        this.inventories.forEach(inventory -> Containers.dropContents(world, pos, inventory));
    }

    public RecipeInventory getRecipeInventory()
    {
        return new RecipeInventory(getStacks().toArray(new ItemStack[0]));
    }

    public List<Pair<MappedDirection, T>> getSidedInventories()
    {
        return this.sidedInventories;
    }

    @Override
    public InventoryConnector<T> getConnector()
    {
        return this;
    }

    @Override
    public InventoryStorage getStorageProvider(MappedDirection direction, Direction facing)
    {

        Direction side = DirectionHelper.relativeDirection(MappedDirection.toDirection(direction), facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return getStorage(side);
        return null;
    }

    @Override
    public InventoryStorage getStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        if(this.getSidedMap().containsKey(MappedDirection.fromDirection(side)))
            return getStorage(side);
        return null;
    }

    @Override
    public void saveAdditional(ValueOutput writeView)
    {
        ValueOutput.ValueOutputList listView = writeView.childrenList("inventory" + BEKeys.HAS_INVENTORY);
        for (T inventory : inventories)
        {
            ValueOutput compoundView = listView.addChild();
            ContainerHelper.saveAllItems(compoundView, inventory.getItems());
        }
    }

    @Override
    public void loadAdditional(ValueInput readView)
    {
        int index = 0;
        for (ValueInput view : readView.childrenListOrEmpty("inventory" + BEKeys.HAS_INVENTORY))
        {
            if(index >= inventories.size())
                break;
            ContainerHelper.loadAllItems(view, inventories.get(index).getItems());
            index++;
        }
    }
}