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

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import dev.thementor.api.base.blockentity.AbstractBaseBE;
import dev.thementor.api.shared.interfaces.ISyncable;
import dev.thementor.api.shared.interfaces.IUpdatable;
import dev.thementor.api.shared.records.FluidStackPayload;

@SuppressWarnings("unused")
public class SyncedFluidStorage extends SingleFluidStorage implements ISyncable
{
    private final BlockEntity blockEntity;
    private final long capacity;

    private boolean isDirty = false;

    public SyncedFluidStorage(BlockEntity blockEntity, long capacity)
    {
        this.blockEntity = blockEntity;
        this.capacity = capacity;
    }

    @Override
    public void sync()
    {
        //noinspection DataFlowIssue
        if(this.isDirty && this.blockEntity != null && this.blockEntity.hasLevel() && !this.blockEntity.getLevel().isClientSide())
        {
            this.isDirty = false;
            if(this.blockEntity instanceof AbstractBaseBE<?> be)
                be.update();
            else
                this.blockEntity.setChanged();
        }
    }

    @Override
    protected long getCapacity(FluidVariant variant)
    {
        return this.capacity;
    }

    @Override
    protected void onFinalCommit()
    {
        super.onFinalCommit();
        this.isDirty = true;

        if(blockEntity instanceof IUpdatable updatable)
            updatable.update();
        else
            blockEntity.setChanged();
    }

    @Override
    public boolean canInsert(FluidVariant variant)
    {
        return super.canInsert(variant);
    }

    @Override
    public boolean canExtract(FluidVariant variant)
    {
        return super.canExtract(variant);
    }

    public boolean canInsert(FluidStackPayload fluidStack)
    {
        return (this.variant == fluidStack.fluid() || this.variant.isBlank()) && fluidStack.amount() <= this.capacity - this.amount;
    }

    public boolean canExtract(FluidStackPayload fluidStack)
    {
        return this.variant == fluidStack.fluid() && fluidStack.amount() <= this.amount;
    }

    public void markDirty()
    {
        this.isDirty = true;
    }

    public BlockEntity getBlockEntity()
    {
        return blockEntity;
    }

    @Nullable
    public AbstractBaseBE<?> getJiBlockEntity()
    {
        if(this.blockEntity instanceof AbstractBaseBE<?> be)
            return be;
        return  null;
    }
}