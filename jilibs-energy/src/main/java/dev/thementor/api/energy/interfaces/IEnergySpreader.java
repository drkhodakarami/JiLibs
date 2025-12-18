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

package dev.thementor.api.energy.interfaces;

import java.util.Set;

import team.reborn.energy.api.EnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.energy.utils.EnergyHelper;

@SuppressWarnings("unused")
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