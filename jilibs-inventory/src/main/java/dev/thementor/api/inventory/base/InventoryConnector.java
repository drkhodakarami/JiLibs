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

package dev.thementor.api.inventory.base;

import dev.thementor.api.base.StorageConnector;
import dev.thementor.api.inventory.record.PredicateInventoryStorage;
import dev.thementor.api.inventory.storage.RecipeInventory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.utils.DirectionHelper;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class InventoryConnector<T extends SimpleInventory> extends StorageConnector<InventoryStorage>
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
            for(int i = 0; i < inventory.size(); i++)
                stacks.add(inventory.getStack(i));
        return stacks;
    }

    public void checkSize(int size)
    {
        int invSize = this.inventories.stream().map(Inventory::size).reduce(0, Integer::sum);
        if( invSize != size)
            throw new IllegalArgumentException("Sie of inventories does not match the size provided: " + invSize + " => " + size);
    }

    public void onOpen(@NotNull PlayerEntity player)
    {
        this.inventories.forEach(inventory -> inventory.onOpen(player));
    }

    public void onClose(@NotNull PlayerEntity player)
    {
        this.inventories.forEach(inventory -> inventory.onClose(player));
    }

    public void dropContent(@NotNull World world, @NotNull BlockPos pos)
    {
        this.inventories.forEach(inventory -> ItemScatterer.spawn(world, pos, inventory));
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
    public void writeData(WriteView writeView)
    {
        WriteView.ListView listView = writeView.getList("inventory" + BEKeys.HAS_INVENTORY);
        for (T inventory : inventories)
        {
            WriteView compoundView = listView.add();
            Inventories.writeData(compoundView, inventory.getHeldStacks());
        }
    }

    @Override
    public void readData(ReadView readView)
    {
        int index = 0;
        for (ReadView view : readView.getListReadView("inventory" + BEKeys.HAS_INVENTORY))
        {
            if(index >= inventories.size())
                break;
            Inventories.readData(view, inventories.get(index).getHeldStacks());
            index++;
        }
    }
}