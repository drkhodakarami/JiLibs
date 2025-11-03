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

package dev.thementor.api.fluid.storage;

import dev.thementor.api.base.blockentity.JiBlockEntity;
import dev.thementor.api.shared.interfaces.ISyncable;
import dev.thementor.api.shared.records.FluidStackPayload;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

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
        if(this.isDirty && this.blockEntity != null && this.blockEntity.hasWorld() && !this.blockEntity.getWorld().isClient())
        {
            this.isDirty = false;
            if(this.blockEntity instanceof JiBlockEntity<?> be)
                be.update();
            else
                this.blockEntity.markDirty();
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
    public JiBlockEntity<?> getJiBlockEntity()
    {
        if(this.blockEntity instanceof JiBlockEntity<?> be)
            return be;
        return  null;
    }
}