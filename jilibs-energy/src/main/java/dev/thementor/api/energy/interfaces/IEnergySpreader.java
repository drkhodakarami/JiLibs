package dev.thementor.api.energy.interfaces;

import dev.thementor.api.energy.utils.EnergyHelper;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import team.reborn.energy.api.EnergyStorage;

import java.util.Set;

public interface IEnergySpreader<T extends EnergyStorage>
{
    default long simulateInsertion(T storage, Transaction outer)
    {
        return EnergyHelper.simulateInsertion(storage, outer);
    }

    default long simulateInsertion(T storage)
    {
        return EnergyHelper.simulateInsertion(storage);
    }

    default long simulateExtraction(T storage, Transaction outer)
    {
        return EnergyHelper.simulateExtraction(storage, outer);
    }

    default long simulateExtraction(T storage)
    {
        return EnergyHelper.simulateExtraction(storage);
    }

    default void spread(BlockEntity blockEntity, T storage, boolean equalAmount, Set<BlockPos> blacklist)
    {
        EnergyHelper.spread(blockEntity, storage, equalAmount, blacklist);
    }

    default void spread(BlockEntity blockEntity, T storage, Set<BlockPos> blacklist)
    {
        EnergyHelper.spread(blockEntity, storage, blacklist);
    }

    default void spread(BlockEntity blockEntity, T storage, boolean equalAmount)
    {
        EnergyHelper.spread(blockEntity, storage, equalAmount);
    }

    default void spread(BlockEntity blockEntity, T storage)
    {
        EnergyHelper.spread(blockEntity, storage);
    }
}