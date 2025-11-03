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

package dev.thementor.api.inventory.record;

import com.google.common.collect.MapMaker;
import dev.thementor.api.inventory.base.PredicateKey;
import dev.thementor.api.shared.annotations.*;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@Experimental
@SuppressWarnings("NonExtendableApiUsage")
public record PredicateInventoryStorage(InventoryStorage storage, Supplier<Boolean> canInsert, Supplier<Boolean> canExtract) implements InventoryStorage
{
    private static final ConcurrentMap<InventoryStorage, ConcurrentMap<PredicateKey, PredicateInventoryStorage>> CACHE = new MapMaker().weakKeys().makeMap();

    public static PredicateInventoryStorage of(InventoryStorage storage, Supplier<Boolean> canInsert, Supplier<Boolean> canExtract)
    {
        ConcurrentMap<PredicateKey, PredicateInventoryStorage> inventoryCache = CACHE.computeIfAbsent(storage, k -> new MapMaker().makeMap());
        PredicateKey key = new PredicateKey(canInsert, canExtract);
        return inventoryCache.computeIfAbsent(key, k -> new PredicateInventoryStorage(storage, canInsert, canExtract));
    }

    @Override
    public @UnmodifiableView List<SingleSlotStorage<ItemVariant>> getSlots()
    {
        return this.storage.getSlots();
    }

    @Override
    public boolean supportsInsertion()
    {
        return this.storage.supportsInsertion() && this.canInsert.get();
    }

    @Override
    public boolean supportsExtraction()
    {
        return this.storage.supportsExtraction() && this.canExtract.get();
    }

    @Override
    public long insert(ItemVariant resource, long maxAmount, TransactionContext transactionContext)
    {
        return storage.insert(resource, maxAmount, transactionContext);
    }

    @Override
    public long extract(ItemVariant resource, long maxAmount, TransactionContext transactionContext)
    {
        return storage.extract(resource, maxAmount, transactionContext);
    }

    @Override
    public @NotNull Iterator<StorageView<ItemVariant>> iterator()
    {
        return storage.iterator();
    }

    @Override
    public int getSlotCount()
    {
        return storage.getSlotCount();
    }

    @Override
    public SingleSlotStorage<ItemVariant> getSlot(int slotIndex)
    {
        return storage.getSlot(slotIndex);
    }

    @SuppressWarnings("EqualsDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj)
    {
        return storage.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return storage.hashCode();
    }

    @Override
    public @NotNull String toString()
    {
        return "PredicateInventoryStorage[%s]".formatted(storage.toString());
    }

    @Override
    public Iterator<StorageView<ItemVariant>> nonEmptyIterator()
    {
        return storage.nonEmptyIterator();
    }

    @Override
    public Iterable<StorageView<ItemVariant>> nonEmptyViews()
    {
        return storage.nonEmptyViews();
    }

    @Override
    public long getVersion()
    {
        return storage.getVersion();
    }
}