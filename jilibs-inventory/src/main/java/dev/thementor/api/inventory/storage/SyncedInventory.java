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

package dev.thementor.api.inventory.storage;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.base.blockentity.AbstractBaseBE;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.ISyncable;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class SyncedInventory extends RecipeInventory implements ISyncable
{
    private final BlockEntity blockEntity;
    private boolean isDirty = false;

    public SyncedInventory(BlockEntity blockEntity, int size)
    {
        super(size);
        this.blockEntity = blockEntity;
    }

    public SyncedInventory(BlockEntity blockEntity, ItemStack... items)
    {
        super(items);
        this.blockEntity = blockEntity;
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
    public void setChanged()
    {
        super.setChanged();
        this.isDirty = true;
    }

    public BlockEntity getBlockEntity()
    {
        return this.blockEntity;
    }

    @Nullable
    public AbstractBaseBE<?> getJiBlockEntity()
    {
        if(this.blockEntity instanceof AbstractBaseBE<?> be)
            return be;
        return  null;
    }
}