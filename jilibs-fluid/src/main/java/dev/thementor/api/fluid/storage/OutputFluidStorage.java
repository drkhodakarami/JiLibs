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

import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.world.level.material.Fluid;

public class OutputFluidStorage extends SyncedFluidStorage
{
    private final Fluid fluid;

    public OutputFluidStorage(BlockEntity blockEntity, long capacity, Fluid fluid)
    {
        super(blockEntity, capacity);
        this.fluid = fluid;
    }

    @Override
    public boolean canInsert(FluidVariant variant)
    {
        return false;
    }

    @Override
    public boolean canExtract(FluidVariant variant)
    {
        return variant.isOf(fluid);
    }
}