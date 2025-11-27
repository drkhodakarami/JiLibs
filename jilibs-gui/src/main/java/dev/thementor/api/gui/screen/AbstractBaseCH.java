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

package dev.thementor.api.gui.screen;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.data.CachedBlockEntity;
import dev.thementor.api.shared.records.BlockPosPayload;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public abstract class AbstractBaseCH<T extends BlockEntity> extends AbstractContainerMenu
{
    protected final T blockEntity;
    protected final ContainerLevelAccess context;
    protected final Level world;
    protected final Block block;
    protected final Inventory playerInventory;
    protected final Player player;

    public AbstractBaseCH(MenuType<?> type, int syncID, Inventory playerInventory, BlockPosPayload payload, Class<T> blockEntityClass)
    {
        this(type, syncID, playerInventory, payload, new CachedBlockEntity<>(blockEntityClass));
    }

    public AbstractBaseCH(MenuType<?> type, int syncID, Inventory playerInventory, BlockPosPayload payload, CachedBlockEntity<T> cachedBE)
    {
        this(type, syncID, playerInventory, cachedBE.apply(playerInventory, payload.pos()));
    }

    @SuppressWarnings("unchecked")
    public AbstractBaseCH(MenuType<?> type, int syncId, Inventory playerInventory, BlockPosPayload payload)
    {
        this(type, syncId, playerInventory, (T) playerInventory.player.level().getBlockEntity(payload.pos()));
    }

    public AbstractBaseCH(MenuType<?> type, int syncId, Inventory playerInventory, T blockEntity)
    {
        super(type, syncId);
        this.world = playerInventory.player.level();
        this.blockEntity = blockEntity;
        this.context = ContainerLevelAccess.create(this.world, this.blockEntity.getBlockPos());
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
        this.block = this.world.getBlockState(this.blockEntity.getBlockPos()).getBlock();

        if (addPlayerInventory())
            addStandardInventorySlots(playerInventory, getPlayerInventoryX(), getPlayerInventoryY());
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int slotIndex)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem())
            return newStack;

        ItemStack stackInSlot = slot.getItem();
        newStack = stackInSlot.copy();
        if(slotIndex < getInventorySize())
        {
            if(!moveItemStackTo(stackInSlot, this.slots.size() - 9, this.slots.size(), true))
                if(!moveItemStackTo(stackInSlot, this.slots.size() - 36, this.slots.size() - 9, false))
                    return ItemStack.EMPTY;
        }
        else
        {
            if(!moveItemStackTo(stackInSlot, 0, getInventorySize(), false))
                return ItemStack.EMPTY;
        }

        if(stackInSlot.isEmpty())
            slot.setByPlayer(ItemStack.EMPTY);
        else
            slot.setChanged();

        return newStack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        boolean validBlock = false;
        for (Block block : getValidBlocks())
        {
            if(stillValid(this.context, player, block))
            {
                validBlock = true;
                break;
            }
        }
        return validBlock;
    }

    public T getBlockEntity()
    {
        return this.blockEntity;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    protected abstract List<Block> getValidBlocks();

    public abstract boolean addPlayerInventory();
    public abstract int getInventorySize();
    public abstract int getPlayerInventoryY();

    public int getPlayerInventoryX()
    {
        return 8;
    }
}