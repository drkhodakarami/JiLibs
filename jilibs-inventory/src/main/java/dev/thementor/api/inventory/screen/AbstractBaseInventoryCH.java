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

package dev.thementor.api.inventory.screen;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;

import dev.thementor.api.gui.screen.AbstractBaseCH;
import dev.thementor.api.inventory.base.InventoryConnector;
import dev.thementor.api.inventory.base.InventoryConnectorCH;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.data.CachedBlockEntity;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.BlockPosPayload;

@SuppressWarnings({"unused", "unchecked", "DataFlowIssue"})
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public abstract class AbstractBaseInventoryCH<T extends BlockEntity & IStorageProvider<InventoryStorage> & IStorageConnector<InventoryConnector<?>>> extends AbstractBaseCH<T>
{
    protected final InventoryConnector<?> inventory;

    public AbstractBaseInventoryCH(MenuType<?> type, int syncId, Inventory playerInventory, BlockPosPayload payload)
    {
        this(type, syncId, playerInventory, payload, (Class<T>) playerInventory.player.level().getBlockEntity(payload.pos()).getClass());
    }

    public AbstractBaseInventoryCH(MenuType<?> type, int syncID, Inventory playerInventory, BlockPosPayload payload, Class<T> blockEntityClass)
    {
        this(type, syncID, playerInventory, payload, new CachedBlockEntity<>(blockEntityClass));
    }

    public AbstractBaseInventoryCH(MenuType<?> type, int syncID, Inventory playerInventory, BlockPosPayload payload, CachedBlockEntity<T> cachedBE)
    {
        this(type, syncID, playerInventory,
             InventoryConnectorCH.copyOf(cachedBE.apply(playerInventory, payload.pos()).getConnector()),
             cachedBE.apply(playerInventory, payload.pos()));
    }

    public AbstractBaseInventoryCH(MenuType<?> type, int syncId, Inventory playerInventory, InventoryConnector<?> inventory, T blockEntity)
    {
        super(type, syncId, playerInventory, blockEntity);
        this.inventory = inventory;
        this.inventory.checkSize(getInventorySize());
        this.addSlots();
        this.inventory.onOpen(playerInventory.player);
    }

    @Override
    public void removed(Player player)
    {
        super.removed(player);
        this.inventory.removed(player);
    }

    protected abstract void addSlots();

    @Override
    public boolean addPlayerInventory()
    {
        return true;
    }
}