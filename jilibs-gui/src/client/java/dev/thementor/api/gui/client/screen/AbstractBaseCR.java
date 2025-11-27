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

package dev.thementor.api.gui.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.gui.client.constants.ContainerBaseTextures;
import dev.thementor.api.gui.client.utils.MenuHelper;
import dev.thementor.api.gui.screen.AbstractBaseCH;

public abstract class AbstractBaseCR<B extends BlockEntity, T extends AbstractBaseCH<B>> extends AbstractContainerScreen<T>
{
    public AbstractBaseCR(T handler, Inventory inventory, Component title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        if(shouldDrawContainerName())
        {
            if(shouldContainNameCentered())
                this.titleLabelX = (this.imageWidth - this.font.width(title)) / 2;
            else
                this.titleLabelX = 8;
        }
        else
            this.titleLabelX = 100_000;

        if(shouldDrawInventoryName())
        {
            if(shouldInventoryNameCentered())
                this.inventoryLabelX = (this.imageWidth - this.font.width(playerInventoryTitle)) / 2;
            else
                this.inventoryLabelX = 8;

            this.inventoryLabelY = this.imageHeight - 94;
        }
        else
            this.inventoryLabelX = 100_000;

        this.imageWidth = getBackgroundWidth();
        this.imageHeight = getBackgroundHeight();

        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics context, float deltaTicks, int mouseX, int mouseY)
    {
        if(shouldDrawBackground())
            drawMainBackground(context, deltaTicks, mouseX, mouseY);
        if(shouldDrawPlayerInventory())
            drawPlayerInventory(context, deltaTicks, mouseX, mouseY);
    }

    protected boolean shouldDrawPlayerInventory()
    {
        return false;
    }

    protected boolean shouldDrawBackground()
    {
        return true;
    }

    protected void drawPlayerInventory(GuiGraphics context, float deltaTicks, int mouseX, int mouseY)
    {
        if(menu.addPlayerInventory())
            MenuHelper.drawTexture(context, ContainerBaseTextures.PLAYER_INVENTORY.id(),
                                     this.leftPos + menu.getPlayerInventoryX() - 1 + ContainerBaseTextures.PLAYER_INVENTORY.x(),
                                     this.topPos + menu.getPlayerInventoryY() - 1 + ContainerBaseTextures.PLAYER_INVENTORY.y(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.u(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.v(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.width(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.height(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.textureWidth(),
                                   ContainerBaseTextures.PLAYER_INVENTORY.textureHeight());
    }

    protected void drawMainBackground(GuiGraphics context, float deltaTicks, int mouseX, int mouseY){}

    protected boolean shouldInventoryNameCentered()
    {
        return false;
    }

    protected boolean shouldDrawInventoryName()
    {
        return false;
    }

    protected boolean shouldContainNameCentered()
    {
        return true;
    }

    protected boolean shouldDrawContainerName()
    {
        return true;
    }

    protected abstract int getBackgroundHeight();
    protected abstract int getBackgroundWidth();
}