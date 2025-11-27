package dev.thementor.api.fluid.interfaces;

import dev.thementor.api.fluid.utils.FluidHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Set;

public interface IFluidSpreader<T extends Storage<FluidVariant>>
{
    default long simulateInsertion(T storage, FluidVariant variant, Transaction outer)
    {
        return FluidHelper.simulateInsertion(storage, variant, outer);
    }

    default long simulateInsertion(T storage, FluidVariant variant)
    {
        return FluidHelper.simulateInsertion(storage, variant);
    }

    default long simulateExtraction(T storage, FluidVariant variant, Transaction outer)
    {
        return FluidHelper.simulateExtraction(storage, variant, outer);
    }

    default long simulateExtraction(T storage, FluidVariant variant)
    {
        return FluidHelper.simulateExtraction(storage, variant);
    }

    default void spread(BlockEntity blockEntity, T storage, boolean equalAmount, Set<BlockPos> blacklist)
    {
        FluidHelper.spread(blockEntity, storage, equalAmount, blacklist);
    }

    default void spread(BlockEntity blockEntity, T storage, Set<BlockPos> blacklist)
    {
        FluidHelper.spread(blockEntity, storage, blacklist);
    }

    default void spread(BlockEntity blockEntity, T storage, boolean equalAmount)
    {
        FluidHelper.spread(blockEntity, storage, equalAmount);
    }

    default void spread(BlockEntity blockEntity, Storage<FluidVariant> storage)
    {
        FluidHelper.spread(blockEntity, storage);
    }
}