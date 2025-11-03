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

package dev.thementor.api.energy.utils;

import dev.thementor.api.base.blockentity.JiBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnergyHelper
{
    public static long simulateInsertion(EnergyStorage storage, Transaction outer)
    {
        long amount = 0;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.insert(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateInsertion(EnergyStorage storage)
    {
        long amount = 0;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.insert(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(EnergyStorage storage, Transaction outer)
    {
        long amount = 0;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.extract(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(EnergyStorage storage)
    {
        long amount = 0;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.extract(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static boolean isEmpty(EnergyStorage storage)
    {
        return storage.getAmount() == 0;
    }

    public static boolean isStorageFull(EnergyStorage storage)
    {
        return storage.getAmount() >= storage.getCapacity();
    }

    public static boolean canAccept(EnergyStorage storage, int amount)
    {
        return storage.getAmount() + amount <= storage.getCapacity();
    }

    public static EnergyStorage getStorage(World world, BlockPos pos, Direction direction, Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos = pos.offset(direction);
        if(blacklist != null && blacklist.contains(adjacentPos))
            return null;
        return EnergyStorage.SIDED.find(world, pos, direction.getOpposite());
    }

    public static List<EnergyStorage> getAllStorages(World world, BlockPos pos, Set<BlockPos> blacklist)
    {
        List<EnergyStorage> storages = new ArrayList<>();
        for (Direction direction : Direction.values())
        {
            BlockPos adjacentPos = pos.offset(direction);
            if(blacklist != null && blacklist.contains(adjacentPos))
                return null;
            EnergyStorage storage = EnergyStorage.SIDED.find(world, pos, direction.getOpposite());

            if(storage != null)
                storages.add(storage);
        }
        return storages;
    }

    public void spread(BlockEntity blockEntity, EnergyStorage storage, boolean equalAmount, Set<BlockPos> blacklist)
    {
        List<EnergyStorage> storages = getAllStorages(blockEntity.getWorld(), blockEntity.getPos(), blacklist);

        if(storages == null || storages.isEmpty())
            return;

        try(Transaction transaction = Transaction.openOuter())
        {
            long current = storage.getAmount();
            long extractable = simulateExtraction(storage);
            long totalInserted = 0;
            long finalAmount = equalAmount ? extractable / storages.size() : extractable;

            for (EnergyStorage adjacentStorage : storages)
            {
                long inserted = adjacentStorage.insert(finalAmount, transaction);
                totalInserted += inserted;
            }

            if(totalInserted > 0)
            {
                try(Transaction inner = transaction.openNested())
                {
                    storage.extract(totalInserted, inner);
                    transaction.commit();
                }
            }

            if(current != storage.getAmount())
            {
                if (blockEntity instanceof JiBlockEntity<?> be)
                    be.update();
                else
                    blockEntity.markDirty();
            }
        }
    }

    public void spread(BlockEntity blockEntity, EnergyStorage storage, Set<BlockPos> blacklist)
    {
        spread(blockEntity, storage, true, blacklist);
    }

    public void spread(BlockEntity blockEntity, EnergyStorage storage, boolean equalAmount)
    {
        spread(blockEntity, storage, equalAmount, null);
    }

    public void spread(BlockEntity blockEntity, EnergyStorage storage)
    {
        spread(blockEntity, storage, true, null);
    }
}