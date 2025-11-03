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

package dev.thementor.api.energy.storage;

import dev.thementor.api.base.blockentity.JiBlockEntity;
import dev.thementor.api.shared.interfaces.ISyncable;
import net.minecraft.block.entity.BlockEntity;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class SyncedEnergyStorage extends SimpleEnergyStorage implements ISyncable
{
    private final BlockEntity blockEntity;
    private boolean isDirty = false;

    public SyncedEnergyStorage(BlockEntity blockEntity, long capacity, long maxInsert, long maxExtract)
    {
        super(capacity, maxInsert, maxExtract);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void onFinalCommit()
    {
        super.onFinalCommit();
        this.isDirty = true;
    }

    @Override
    public void sync()
    {
        //noinspection DataFlowIssue
        if(this.isDirty && blockEntity != null && this.blockEntity.hasWorld() && !this.blockEntity.getWorld().isClient())
        {
            this.isDirty = false;
            if(blockEntity instanceof JiBlockEntity<?> be)
                be.update();
            else
                blockEntity.markDirty();
        }
    }

    public BlockEntity getBlockEntity()
    {
        return blockEntity;
    }
}