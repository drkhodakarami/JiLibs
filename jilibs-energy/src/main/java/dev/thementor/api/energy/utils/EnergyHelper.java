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

package dev.thementor.api.energy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import team.reborn.energy.api.EnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.base.blockentity.AbstractBaseBE;

@SuppressWarnings("unused")
public class EnergyHelper
{
    public static long simulateInsertion(EnergyStorage storage, Transaction outer)
    {
        long amount;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.insert(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateInsertion(EnergyStorage storage)
    {
        long amount;
        try(Transaction transaction = Transaction.openOuter())
        {
            amount = storage.insert(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(EnergyStorage storage, Transaction outer)
    {
        long amount;
        try(Transaction transaction = outer.openNested())
        {
            amount = storage.extract(Long.MAX_VALUE, transaction);
            transaction.abort();
        }
        return amount;
    }

    public static long simulateExtraction(EnergyStorage storage)
    {
        long amount;
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

    public static EnergyStorage getStorage(Level world, BlockPos pos, Direction direction, Set<BlockPos> blacklist)
    {
        BlockPos adjacentPos = pos.relative(direction);
        if(blacklist != null && blacklist.contains(adjacentPos))
            return null;
        return EnergyStorage.SIDED.find(world, pos, direction.getOpposite());
    }

    public static List<EnergyStorage> getAllStorages(Level world, BlockPos pos, Set<BlockPos> blacklist)
    {
        List<EnergyStorage> storages = new ArrayList<>();
        for (Direction direction : Direction.values())
        {
            BlockPos adjacentPos = pos.relative(direction);
            if(blacklist != null && blacklist.contains(adjacentPos))
                continue;
            EnergyStorage storage = EnergyStorage.SIDED.find(world, adjacentPos, direction.getOpposite());

            if(storage != null)
                storages.add(storage);
        }
        return storages;
    }

    public static void spread(BlockEntity blockEntity, EnergyStorage storage, boolean equalAmount, Set<BlockPos> blacklist)
    {
        List<EnergyStorage> storages = getAllStorages(blockEntity.getLevel(), blockEntity.getBlockPos(), blacklist);

        if(storages.isEmpty())
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
                }
            }

            transaction.commit();

            if(current != storage.getAmount())
            {
                if (blockEntity instanceof AbstractBaseBE<?> be)
                    be.update();
                else
                    blockEntity.setChanged();
            }
        }
    }

    public static void spread(BlockEntity blockEntity, EnergyStorage storage, Set<BlockPos> blacklist)
    {
        spread(blockEntity, storage, true, blacklist);
    }

    public static void spread(BlockEntity blockEntity, EnergyStorage storage, boolean equalAmount)
    {
        spread(blockEntity, storage, equalAmount, null);
    }

    public static void spread(BlockEntity blockEntity, EnergyStorage storage)
    {
        spread(blockEntity, storage, true, null);
    }
}