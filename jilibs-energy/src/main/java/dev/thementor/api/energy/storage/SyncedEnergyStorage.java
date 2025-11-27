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

package dev.thementor.api.energy.storage;

import team.reborn.energy.api.base.SimpleEnergyStorage;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.base.blockentity.AbstractBaseBE;
import dev.thementor.api.shared.interfaces.ISyncable;
import dev.thementor.api.shared.interfaces.IUpdatable;

@SuppressWarnings("unused")
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

        if(blockEntity instanceof IUpdatable updatable)
            updatable.update();
        else
            blockEntity.setChanged();
    }

    @Override
    public void sync()
    {
        //noinspection DataFlowIssue
        if(this.isDirty && blockEntity != null && this.blockEntity.hasLevel() && !this.blockEntity.getLevel().isClientSide())
        {
            this.isDirty = false;
            if(blockEntity instanceof AbstractBaseBE<?> be)
                be.update();
            else
                blockEntity.setChanged();
        }
    }

    public BlockEntity getBlockEntity()
    {
        return blockEntity;
    }
}