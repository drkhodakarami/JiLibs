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

package dev.thementor.api.fluid.interfaces;

import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.fluid.utils.FluidHelper;

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