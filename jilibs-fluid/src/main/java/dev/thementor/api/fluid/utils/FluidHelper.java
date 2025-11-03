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

package dev.thementor.api.fluid.utils;

import dev.thementor.api.base.blockentity.JiBlockEntity;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.records.FluidStackPayload;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FluidHelper
{
    public static boolean isEmpty(SingleFluidStorage storage)
    {
        return storage.getAmount() == 0;
    }

    public static boolean isEmpty(SingleVariantStorage<FluidVariant> storage)
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

    public static boolean handleStorageTransfer(World world, BlockPos pos, SingleFluidStorage storage,
                                                Inventory inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    public static boolean handleStorageTransfer(World world, BlockPos pos, SingleVariantStorage<FluidVariant> storage,
                                                Inventory inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    public static boolean handleStorageTransfer(World world, BlockPos pos, Storage<FluidVariant> storage,
                                                Inventory inputInventory,
                                                int inputSlot,
                                                boolean fullTransfer)
    {
        if(transferToStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer))
            return true;
        return transferFromStorage(world, pos, storage, inputInventory, inputSlot, fullTransfer);
    }

    private static boolean transferFromStorage(World world, BlockPos pos, Storage<FluidVariant> storage, Inventory inputInventory, int inputSlot, boolean fullTransfer)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(inputInventory.getStack(inputSlot)).find(FluidStorage.ITEM);

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
                        world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean transferToStorage(World world, BlockPos pos, Storage<FluidVariant> storage, Inventory inputInventory, int inputSlot, boolean fullTransfer)
    {
        Storage<FluidVariant> slotStorage = ContainerItemContext.withConstant(inputInventory.getStack(inputSlot)).find(FluidStorage.ITEM);

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
                    world.playSound(null, pos, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean interactWithBlock(World world, BlockPos pos, PlayerEntity player, Hand hand)
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

    public static boolean isValidSlot(Inventory inventory, int slot, ItemStack itemStack)
    {
        // EMPTY BUCKET
        ItemStack slotStack = inventory.getStack(slot);

        if(itemStack.isEmpty())
            return false;

        if(slotStack.isEmpty())
            return true;

        if(!slotStack.isOf(itemStack.getItem()))
            return false;

        if(slotStack.getCount() + itemStack.getCount() > slotStack.getMaxCount())
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

    public static boolean isEmptyBucket(Inventory inventory, int slot)
    {
        return inventory.getStack(slot).isOf(Items.BUCKET);
    }

    public static long simulateInsertion(Storage<FluidVariant> storage, FluidVariant variant, Transaction outer)
    {
        long amount = 0;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.insert(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateInsertion(Storage<FluidVariant> storage, FluidVariant variant)
    {
        long amount = 0;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.insert(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(Storage<FluidVariant> storage, FluidVariant variant, Transaction outer)
    {
        long amount = 0;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.extract(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(Storage<FluidVariant> storage, FluidVariant variant)
    {
        long amount = 0;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.extract(variant, Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static boolean sameFluidInStorage(Inventory inventory, int slot, Storage<FluidVariant> storage)
    {
        ItemStack slotStack = inventory.getStack(slot);

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

    public static Storage<FluidVariant> getStorage(World world, BlockPos pos, Direction direction, Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos = pos.offset(direction);
        if(blacklist != null && blacklist.contains(adjacentPos))
            return null;

        return FluidStorage.SIDED.find(world, adjacentPos, direction.getOpposite());
    }

    public static List<Storage<FluidVariant>> getAllStorages(World world, BlockPos pos, Set<BlockPos> blacklist)
    {
        List<Storage<FluidVariant>> storages = new ArrayList<>();
        for (Direction direction : Direction.values())
        {
            BlockPos adjacentPos = pos.offset(direction);
            if(blacklist != null && blacklist.contains(adjacentPos))
                continue;

            Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, adjacentPos, direction.getOpposite());

            if(storage != null)
                storages.add(storage);
        }

        return storages;
    }

    public void spread(BlockEntity blockEntity, Storage<FluidVariant> storage, boolean equalAmount,Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos;

        List<Storage<FluidVariant>> adjacentStorages = getAllStorages(blockEntity.getWorld(), blockEntity.getPos(), blacklist);

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
                try(Transaction transaction = Transaction.openOuter())
                {
                    long inserted = adjacentStorage.insert(variant, finalAmount, transaction);
                    if(inserted == finalAmount)
                    {
                        transaction.commit();
                        totalInserted += inserted;
                    }
                    else
                        transaction.abort();
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
                if (blockEntity instanceof JiBlockEntity<?> be)
                    be.update();
                else
                    blockEntity.markDirty();
            }
        }
    }

    public void spread(BlockEntity blockEntity, Storage<FluidVariant> storage,Set<BlockPos> blacklist)
    {
        spread(blockEntity, storage, true, blacklist);
    }

    public void spread(BlockEntity blockEntity, Storage<FluidVariant> storage, boolean equalAmount)
    {
        spread(blockEntity, storage, equalAmount, null);
    }

    public void spread(BlockEntity blockEntity, Storage<FluidVariant> storage)
    {
        spread(blockEntity, storage, true, null);
    }
}