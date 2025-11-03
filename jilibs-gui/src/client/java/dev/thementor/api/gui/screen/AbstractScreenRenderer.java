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

import dev.thementor.api.gui.constants.ContainerBaseTextures;
import dev.thementor.api.gui.utils.ScreenHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public abstract class AbstractScreenRenderer<B extends BlockEntity, T extends AbstractScreenHandler<B>> extends HandledScreen<T>
{
    public AbstractScreenRenderer(T handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    protected void init()
    {
        if(shouldDrawContainerName())
        {
            if(shouldContainNameCentered())
                this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(title)) / 2;
            else
                this.titleX = 8;
        }
        else
            this.titleX = 100_000;

        if(shouldDrawInventoryName())
        {
            if(shouldInventoryNameCentered())
                this.playerInventoryTitleX = (this.backgroundWidth - this.textRenderer.getWidth(playerInventoryTitle)) / 2;
            else
                this.playerInventoryTitleX = 8;

            this.playerInventoryTitleY = this.backgroundHeight - 94;
        }
        else
            this.playerInventoryTitleX = 100_000;

        this.backgroundWidth = getBackgroundWidth();
        this.backgroundHeight = getBackgroundHeight();

        super.init();
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY)
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

    protected void drawPlayerInventory(DrawContext context, float deltaTicks, int mouseX, int mouseY)
    {
        if(handler.addPlayerInventory())
            ScreenHelper.drawTexture(context, ContainerBaseTextures.PLAYER_INVENTORY.id(),
                                     this.x + handler.getPlayerInventoryX() - 1 + ContainerBaseTextures.PLAYER_INVENTORY.x(),
                                     this.y + handler.getPlayerInventoryY() - 1 + ContainerBaseTextures.PLAYER_INVENTORY.y(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.u(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.v(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.width(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.height(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.textureWidth(),
                                     ContainerBaseTextures.PLAYER_INVENTORY.textureHeight());
    }

    protected void drawMainBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY){}

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