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

package dev.thementor.api.inventory.record;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import com.google.common.collect.MapMaker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

import dev.thementor.api.inventory.base.PredicateKey;
import dev.thementor.api.shared.annotations.*;

@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
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
    public @UnmodifiableView @NotNull List<SingleSlotStorage<ItemVariant>> getSlots()
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
    public long insert(@NotNull ItemVariant resource, long maxAmount, @NotNull TransactionContext transactionContext)
    {
        return storage.insert(resource, maxAmount, transactionContext);
    }

    @Override
    public long extract(@NotNull ItemVariant resource, long maxAmount, @NotNull TransactionContext transactionContext)
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
    public @NotNull SingleSlotStorage<ItemVariant> getSlot(int slotIndex)
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
    public @NotNull Iterator<StorageView<ItemVariant>> nonEmptyIterator()
    {
        return storage.nonEmptyIterator();
    }

    @Override
    public @NotNull Iterable<StorageView<ItemVariant>> nonEmptyViews()
    {
        return storage.nonEmptyViews();
    }

    @Override
    public long getVersion()
    {
        return storage.getVersion();
    }
}