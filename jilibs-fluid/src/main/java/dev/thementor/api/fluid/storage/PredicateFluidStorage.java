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

package dev.thementor.api.fluid.storage;

import java.util.function.Predicate;

import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

@SuppressWarnings("unused")
public class PredicateFluidStorage extends SyncedFluidStorage
{
    private final Predicate<FluidVariant> canInsert;
    private final Predicate<FluidVariant> canExtract;

    public PredicateFluidStorage(BlockEntity blockEntity, long capacity, Predicate<FluidVariant> canInsert, Predicate<FluidVariant> canExtract)
    {
        super(blockEntity, capacity);
        this.canInsert = canInsert;
        this.canExtract = canExtract;
    }

    @Override
    public boolean canInsert(FluidVariant variant)
    {
        return this.canInsert.test(variant);
    }

    @Override
    public boolean canExtract(FluidVariant variant)
    {
        return this.canExtract.test(variant);
    }
}