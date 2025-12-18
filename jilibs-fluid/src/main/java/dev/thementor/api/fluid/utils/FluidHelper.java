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

package dev.thementor.api.fluid.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.base.blockentity.AbstractBaseBE;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.records.FluidStackPayload;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class FluidHelper
{
    public static boolean isEmpty(SingleFluidStorage storage)
    {
        return storage.getAmount() == 0;
    }

    public static boolean isEmpty(SingleVariantStorage<@NotNull FluidVariant> storage)
    {
        return storage.getAmount() == 0;
    }

    public static boolean isEmpty(Storage<FluidVariant> storage)
    {
        for (StorageView<FluidVariant> view : storage)
            if (view.getAmount() > 0)
                return false;

        return true;
    }

    public static boolean handleStorageTransfer(Level world, BlockPos pos, SingleFluidStorage storage,
                                                Container inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    public static boolean handleStorageTransfer(Level world, BlockPos pos, SingleVariantStorage<@NotNull FluidVariant> storage,
                                                Container inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    public static boolean handleStorageTransfer(Level world, BlockPos pos, Storage<FluidVariant> storage,
                                                Container inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    private static boolean transferFromStorage(Level world, BlockPos pos, Storage<FluidVariant> storage, Container inputInventory, int inputSlot, boolean fullTransfer)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(inputInventory.getItem(inputSlot)).find(FluidStorage.ITEM);

        if (slotStorage == null)
            return false;

        for(StorageView<FluidVariant> storageView : storage)
        {
            FluidVariant variant = storageView.getResource();

            if (storageView.isResourceBlank() || storageView.getAmount() == 0)
                continue;

            for (StorageView<FluidVariant> view : slotStorage)
            {
                try (Transaction transaction = Transaction.openOuter())
                {
                    long storageTransfer = slotStorage.insert(variant, Long.MAX_VALUE, transaction);
                    long extracted = storage.extract(variant, storageTransfer, transaction);


                    if (storageTransfer == 0)
                        continue;

                    if (storageTransfer > 0)
                    {
                        if (fullTransfer && storageTransfer != extracted)
                            continue;

                        transaction.commit();
                        SoundEvent sound = FluidVariantAttributes.getFillSound(variant);
                        world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean transferToStorage(Level world, BlockPos pos, Storage<FluidVariant> storage, Container inputInventory, int inputSlot, boolean fullTransfer)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(inputInventory.getItem(inputSlot)).find(FluidStorage.ITEM);

        if(slotStorage == null)
            return false;

        for (StorageView<FluidVariant> view : slotStorage)
        {
            if(view.isResourceBlank() || view.getAmount() == 0)
                continue;

            FluidVariant variant = view.getResource();

            try(Transaction transaction = Transaction.openOuter())
            {
                long extracted = slotStorage.extract(variant, Long.MAX_VALUE, transaction);
                long storageTransfer = storage.insert(variant, extracted, transaction);

                if(storageTransfer == 0)
                    continue;

                if(storageTransfer > 0)
                {
                    if(fullTransfer && storageTransfer != extracted)
                        continue;

                    if(storageTransfer < extracted)
                        slotStorage.insert(variant, extracted - storageTransfer, transaction);

                    transaction.commit();
                    SoundEvent sound = FluidVariantAttributes.getEmptySound(variant);
                    world.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean interactWithBlock(Level world, BlockPos pos, Player player, InteractionHand hand)
    {
        Storage<FluidVariant> storage;
        for (MappedDirection direction : MappedDirection.values())
        {
            storage = FluidStorage.SIDED.find(world, pos, MappedDirection.toDirection(direction));

            if(storage != null)
                if(FluidStorageUtil.interactWithFluidStorage(storage, player, hand))
                    return true;
        }

        return false;
    }

    public static boolean isValidSlot(Container inventory, int slot, ItemStack itemStack)
    {
        // EMPTY BUCKET
        ItemStack slotStack = inventory.getItem(slot);

        if(itemStack.isEmpty())
            return false;

        if(slotStack.isEmpty())
            return true;

        if(!slotStack.is(itemStack.getItem()))
            return false;

        if(slotStack.getCount() + itemStack.getCount() > slotStack.getMaxStackSize())
            return false;

        Storage<FluidVariant> itemStorage = ContainerItemContext.withConstant(itemStack).find(FluidStorage.ITEM);
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(slotStack).find(FluidStorage.ITEM);

        boolean variantFlag = false;
        boolean itemFlag = false;

        if(itemStorage == null || slotStorage == null)
            return false;

        for(StorageView<FluidVariant> slotView : slotStorage)
        {
            if(slotView.isResourceBlank())
                continue;
            for(StorageView<FluidVariant> itemView : itemStorage)
            {
                if(itemView.isResourceBlank())
                    continue;

                if(slotView.getResource() == itemView.getResource())
                    variantFlag = true;

                if(slotView.getAmount() == itemView.getAmount())
                    itemFlag = true;
            }
        }
        return variantFlag && itemFlag;
    }

    public static boolean isEmptyBucket(Container inventory, int slot)
    {
        return inventory.getItem(slot).is(Items.BUCKET);
    }

    public static long simulateInsertion(Storage<FluidVariant> storage, FluidVariant variant, Transaction outer)
    {
        long amount;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.insert(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateInsertion(Storage<FluidVariant> storage, FluidVariant variant)
    {
        long amount;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.insert(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(Storage<FluidVariant> storage, FluidVariant variant, Transaction outer)
    {
        long amount;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.extract(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(Storage<FluidVariant> storage, FluidVariant variant)
    {
        long amount;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.extract(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static boolean sameFluidInStorage(Container inventory, int slot, Storage<FluidVariant> storage)
    {
        ItemStack slotStack = inventory.getItem(slot);

        if(slotStack.isEmpty())
            return false;

        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(slotStack).find(FluidStorage.ITEM);

        return sameFluidInStorage(slotStorage, storage);
    }

    public static boolean sameFluidInStorage(Storage<FluidVariant> storage1, Storage<FluidVariant> storage2)
    {
        if(storage1 == null || storage2 == null)
            return false;

        for(StorageView<FluidVariant> slotView : storage1)
        {
            if (slotView.isResourceBlank())
                continue;
            boolean found = false;
            for (StorageView<FluidVariant> storageView : storage2)
            {
                if (storageView.isResourceBlank())
                    continue;

                if(slotView.getResource() == storageView.getResource())
                    found = true;
            }
            if(!found)
                return false;
        }

        return true;
    }

    public static boolean isStorageFull(Storage<FluidVariant> storage)
    {
        if(storage == null)
            return true;

        for(StorageView<FluidVariant> slotView : storage)
        {
            if (slotView.isResourceBlank())
                return false;

            if(slotView.getAmount() < slotView.getCapacity())
                return false;
        }

        return true;
    }

    public static boolean canAccept(FluidStackPayload stack, Storage<FluidVariant> storage)
    {
        if(storage == null)
            return false;

        try(Transaction transaction = Transaction.openOuter())
        {
            long amount = storage.insert(stack.fluid(), Long.MAX_VALUE, transaction);
            return stack.amount() <= amount && amount > 0;
        }
    }

    public static Storage<FluidVariant> getStorage(Level world, BlockPos pos, Direction direction, Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos = pos.relative(direction);
        if(blacklist != null && blacklist.contains(adjacentPos))
            return null;

        return FluidStorage.SIDED.find(world, adjacentPos, direction.getOpposite());
    }

    public static List<Storage<FluidVariant>> getAllStorages(Level world, BlockPos pos, Set<BlockPos> blacklist)
    {
        List<Storage<FluidVariant>> storages = new ArrayList<>();
        for (Direction direction : Direction.values())
        {
            BlockPos adjacentPos = pos.relative(direction);
            if(blacklist != null && blacklist.contains(adjacentPos))
                continue;

            Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, adjacentPos, direction.getOpposite());

            if(storage != null)
                storages.add(storage);
        }

        return storages;
    }

    public static void spread(BlockEntity blockEntity, Storage<FluidVariant> storage, boolean equalAmount,Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos;

        List<Storage<FluidVariant>> adjacentStorages = getAllStorages(blockEntity.getLevel(), blockEntity.getBlockPos(), blacklist);

        if(adjacentStorages.isEmpty())
            return;

        for (StorageView<FluidVariant> storageView : storage)
        {
            FluidVariant variant = storageView.getResource();
            long currentAmount = storageView.getAmount();
            long totalInserted = 0;
            long totalExtractable = simulateExtraction(storage, variant);

            long finalAmount = equalAmount ? totalExtractable / adjacentStorages.size() : totalExtractable;

            for(Storage<FluidVariant> adjacentStorage : adjacentStorages)
            {
                var insertable = simulateInsertion(adjacentStorage, variant);
                if(insertable < finalAmount)
                    continue;

                try(Transaction transaction = Transaction.openOuter())
                {
                    long inserted = adjacentStorage.insert(variant, finalAmount, transaction);

                    if(inserted == finalAmount)
                        totalInserted += inserted;

                    transaction.commit();
                }
            }

            if(totalInserted > 0)
            {
                try(Transaction transaction = Transaction.openOuter())
                {
                    storage.extract(variant, totalInserted, transaction);
                    transaction.commit();
                }
            }

            if(currentAmount != storageView.getAmount())
            {
                if (blockEntity instanceof AbstractBaseBE<?> be)
                    be.update();
                else
                    blockEntity.setChanged();
            }
        }
    }

    public static void spread(BlockEntity blockEntity, Storage<FluidVariant> storage,Set<BlockPos> blacklist)
    {
        spread(blockEntity, storage, true, blacklist);
    }

    public static void spread(BlockEntity blockEntity, Storage<FluidVariant> storage, boolean equalAmount)
    {
        spread(blockEntity, storage, equalAmount, null);
    }

    public static void spread(BlockEntity blockEntity, Storage<FluidVariant> storage)
    {
        spread(blockEntity, storage, true, null);
    }
}