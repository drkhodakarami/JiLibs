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

package dev.thementor.api.inventory.screen;

import dev.thementor.api.gui.screen.AbstractScreenHandler;
import dev.thementor.api.inventory.base.InventoryConnectorSH;
import dev.thementor.api.inventory.base.InventoryConnector;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.data.CachedBlockEntity;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.BlockPosPayload;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;

@SuppressWarnings({"unused", "unchecked", "DataFlowIssue"})
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public abstract class JInventorySH<T extends BlockEntity & IStorageProvider<InventoryStorage> & IStorageConnector<InventoryConnector<?>>> extends AbstractScreenHandler<T>
{
    protected final InventoryConnector<?> inventory;

    public JInventorySH(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, BlockPosPayload payload)
    {
        this(type, syncId, playerInventory, payload, (Class<T>) playerInventory.player.getEntityWorld().getBlockEntity(payload.pos()).getClass());
    }

    public JInventorySH(ScreenHandlerType<?> type, int syncID, PlayerInventory playerInventory, BlockPosPayload payload, Class<T> blockEntityClass)
    {
        this(type, syncID, playerInventory, payload, new CachedBlockEntity<>(blockEntityClass));
    }

    public JInventorySH(ScreenHandlerType<?> type, int syncID, PlayerInventory playerInventory, BlockPosPayload payload, CachedBlockEntity<T> cachedBE)
    {
        this(type, syncID, playerInventory,
             InventoryConnectorSH.copyOf(cachedBE.apply(playerInventory, payload.pos()).getConnector()),
             cachedBE.apply(playerInventory, payload.pos()));
    }

    public JInventorySH(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, InventoryConnector<?> inventory, T blockEntity)
    {
        super(type, syncId, playerInventory, blockEntity);
        this.inventory = inventory;
        this.inventory.checkSize(getInventorySize());
        this.addSlots();
        this.inventory.onOpen(playerInventory.player);
    }

    @Override
    public void onClosed(PlayerEntity player)
    {
        super.onClosed(player);
        this.inventory.onClose(player);
    }

    protected abstract void addSlots();

    @Override
    protected boolean addPlayerInventory()
    {
        return true;
    }
}