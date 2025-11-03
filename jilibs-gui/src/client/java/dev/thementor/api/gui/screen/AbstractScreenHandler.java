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

package dev.thementor.api.gui.screen;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.data.CachedBlockEntity;
import dev.thementor.api.shared.records.BlockPosPayload;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public abstract class AbstractScreenHandler <T extends BlockEntity> extends ScreenHandler
{
    protected final T blockEntity;
    protected final ScreenHandlerContext context;
    protected final World world;
    protected final Block block;
    protected final PlayerInventory playerInventory;
    protected final PlayerEntity player;

    public AbstractScreenHandler(ScreenHandlerType<?> type, int syncID, PlayerInventory playerInventory, BlockPosPayload payload, Class<T> blockEntityClass)
    {
        this(type, syncID, playerInventory, payload, new CachedBlockEntity<>(blockEntityClass));
    }

    public AbstractScreenHandler(ScreenHandlerType<?> type, int syncID, PlayerInventory playerInventory, BlockPosPayload payload, CachedBlockEntity<T> cachedBE)
    {
        this(type, syncID, playerInventory, cachedBE.apply(playerInventory, payload.pos()));
    }

    public AbstractScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, BlockPosPayload payload)
    {
        //noinspection unchecked
        this(type, syncId, playerInventory, (T) playerInventory.player.getEntityWorld().getBlockEntity(payload.pos()));
    }

    public AbstractScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, T blockEntity)
    {
        super(type, syncId);
        this.world = playerInventory.player.getEntityWorld();
        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(this.world, this.blockEntity.getPos());
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
        this.block = this.world.getBlockState(this.blockEntity.getPos()).getBlock();

        if (addPlayerInventory())
            addPlayerSlots(playerInventory, getPlayerInventoryX(), getPlayerInventoryY());
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex)
    {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasStack())
            return newStack;

        ItemStack stackInSlot = slot.getStack();
        newStack = stackInSlot.copy();
        if(slotIndex < getInventorySize())
        {
            if(!insertItem(stackInSlot, this.slots.size() - 9, this.slots.size(), true))
                if(!insertItem(stackInSlot, this.slots.size() - 36, this.slots.size() - 9, false))
                    return ItemStack.EMPTY;
        }
        else
        {
            if(!insertItem(stackInSlot, 0, getInventorySize(), false))
                return ItemStack.EMPTY;
        }

        if(stackInSlot.isEmpty())
            slot.setStack(ItemStack.EMPTY);
        else
            slot.markDirty();

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        boolean validBlock = false;
        for (Block block : getValidBlocks())
        {
            if(canUse(this.context, player, block))
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

    public PlayerEntity getPlayer()
    {
        return this.player;
    }


    protected abstract List<Block> getValidBlocks();
    protected abstract boolean addPlayerInventory();

    public abstract int getInventorySize();
    public abstract int getPlayerInventoryY();

    public int getPlayerInventoryX()
    {
        return 8;
    }
}