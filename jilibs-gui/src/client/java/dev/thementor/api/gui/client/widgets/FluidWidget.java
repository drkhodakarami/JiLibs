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

package dev.thementor.api.gui.client.widgets;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import dev.thementor.api.gui.client.constants.FluidContainerTextures;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.gui.client.utils.MenuHelper;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.client.utils.MouseHelper;
import dev.thementor.api.shared.utils.BaseHelper;
import dev.thementor.api.shared.utils.StringHelper;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class FluidWidget implements Renderable, LayoutElement
{
    private final SingleFluidStorage fluidStorage;

    private final int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
    private final int markerX, markerY, markerWidth, markerHeight;

    private final int backgroundU, backgroundV, markerU, markerV;

    private final int fluidX, fluidY, fluidWidth, fluidHeight;

    private int x, y;

    private final int backgroundSizeWidth, backgroundSizeHeight;
    private final int markerSizeWidth, markerSizeHeight;

    private final Supplier<BlockPos> posSupplier;

    private final ResourceLocation backgroundTexture, markerTexture;

    private final boolean drawBackground;

    public FluidWidget(SingleFluidStorage fluidStorage,
                       int x, int y, boolean drawBackground,
                       int backgroundX, int backgroundY, int backgroundWidth, int backgroundHeight,
                       int markerX, int markerY, int markerWidth, int markerHeight,
                       int fluidX, int fluidY, int fluidWidth, int fluidHeight,
                       int backgroundU, int backgroundV, int markerU, int markerV,
                       int backgroundSizeWidth, int backgroundSizeHeight,
                       int markerSizeWidth, int markerSizeHeight,
                       ResourceLocation backgroundTexture, ResourceLocation markerTexture,
                       Supplier<BlockPos> posSupplier)
    {
        this.fluidStorage = fluidStorage;
        this.backgroundX = backgroundX;
        this.backgroundY = backgroundY;
        this.backgroundWidth = backgroundWidth;
        this.backgroundHeight = backgroundHeight;
        this.markerX = markerX;
        this.markerY = markerY;
        this.markerWidth = markerWidth;
        this.markerHeight = markerHeight;
        this.posSupplier = posSupplier;
        this.backgroundTexture = backgroundTexture;
        this.markerTexture = markerTexture;
        this.backgroundU = backgroundU;
        this.backgroundV = backgroundV;
        this.markerU = markerU;
        this.markerV = markerV;
        this.fluidX = fluidX;
        this.fluidY = fluidY;
        this.fluidWidth = fluidWidth;
        this.fluidHeight = fluidHeight;
        this.backgroundSizeWidth = backgroundSizeWidth;
        this.backgroundSizeHeight = backgroundSizeHeight;
        this.markerSizeWidth = markerSizeWidth;
        this.markerSizeHeight = markerSizeHeight;
        this.x = x;
        this.y = y;
        this.drawBackground = drawBackground;
    }


    @ThanksTo(youtubeUsers = "TurtyWurty", discordUsers = "TurtyWurty")
    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks)
    {
        if(fluidStorage == null || fluidStorage.variant == null)
            return;

        // Render the fluid widget background
        if(drawBackground && BaseHelper.validateResource(backgroundTexture))
            MenuHelper.drawTexture(context, backgroundTexture,
                                    x + backgroundX, y + backgroundY,
                                   backgroundU, backgroundV,
                                   backgroundWidth, backgroundHeight,
                                   backgroundSizeWidth, backgroundSizeHeight);

        Fluid fluid = fluidStorage.variant.getFluid();
        long amount = fluidStorage.getAmount();
        long capacity = fluidStorage.getCapacity();
        int barHeight = Math.round((float) amount / capacity * fluidHeight);

        //region Fluid Render Handler Params
        FluidRenderHandler fluidRenderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
        if(fluidRenderHandler == null || amount <= 0)
        {
            MenuHelper.drawTexture(context, markerTexture,
                                   markerX, markerY,
                                   markerU, markerV,
                                   markerWidth, markerHeight,
                                   markerSizeWidth, markerSizeHeight);
            return;
        }

        BlockPos pos = posSupplier.get();
        FluidState fluidState = fluid.defaultFluidState();
        Level world = Minecraft.getInstance().level;

        TextureAtlasSprite stillTexture = fluidRenderHandler.getFluidSprites(world, pos, fluidState)[0];

        int tintColor = fluidRenderHandler.getFluidColor(world, pos, fluidState);

        float red = (tintColor >> 16 & 0xFF) / 255.0F;
        float green = (tintColor >> 8 & 0xFF) / 255.0F;
        float blue = (tintColor & 0xFF) / 255.0F;
        //endregion

        // Render the fluid widget fluid
        MenuHelper.renderTiledSprite(context, RenderPipelines.GUI_TEXTURED, stillTexture,
                                      x + fluidX, y + fluidY + fluidHeight - barHeight, fluidWidth, barHeight,
                                     ARGB.colorFromFloat(1.0F, red, green, blue));
        // Render the fluid widget marker
        if(BaseHelper.validateResource(markerTexture))
            MenuHelper.drawTexture(context, markerTexture,
                                    x + markerX, y + markerY,
                                   markerU, markerV,
                                   markerWidth, markerHeight,
                                   markerSizeWidth, markerSizeHeight);

        if(MouseHelper.isMouseOver(mouseX, mouseY, x + fluidX, y + fluidY, fluidWidth, fluidHeight))
            drawTooltip(context, mouseX, mouseY);
    }

    @Override
    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getWidth()
    {
        return backgroundWidth;
    }

    @Override
    public int getHeight()
    {
        return backgroundHeight;
    }

    @Override
    public void visitWidgets(Consumer<AbstractWidget> consumer) {}

    public SingleFluidStorage getFluidStorage()
    {
        return fluidStorage;
    }

    protected  void drawTooltip(GuiGraphics context, int mouseX, int mouseY)
    {
        Fluid fluid = fluidStorage.variant.getFluid();

        long amount = fluidStorage.getAmount();
        long capacity = fluidStorage.getCapacity();

        if(fluid == null || amount <= 0)
            return;

        Font textRenderer = Minecraft.getInstance().font;

        int amountInMillibuckets = (int) (((float) amount / FluidConstants.BUCKET) * 1000);
        int capacityInMillibuckets = (int) (((float) capacity / FluidConstants.BUCKET) * 1000);

        String amountText = StringHelper.formatted(amountInMillibuckets);
        String capacityText = StringHelper.formatted(capacityInMillibuckets);

        List<Component> texts = List.of(
                Component.translatable(fluid.defaultFluidState().createLegacyBlock().getBlock().getDescriptionId()),
                Component.literal((amountText + " / " + capacityText + " mB")));

        context.setComponentTooltipForNextFrame(textRenderer, texts, mouseX, mouseY);
    }

    public static class Builder
    {
        private SingleFluidStorage fluidStorage;

        private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
        private int markerX, markerY, markerWidth, markerHeight;

        private int backgroundU, backgroundV, markerU, markerV;

        private int fluidX, fluidY, fluidWidth, fluidHeight;

        private final int x, y;

        private int backgroundSizeWidth, backgroundSizeHeight;
        private int markerSizeWidth, markerSizeHeight;

        private Supplier<BlockPos> posSupplier = () -> null;

        private ResourceLocation backgroundTexture, markerTexture;

        private boolean drawBackground = false;

        public Builder(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public Builder fluidStorage(SingleFluidStorage fluidStorage)
        {
            this.fluidStorage = fluidStorage;
            return this;
        }

        public Builder backgroundTexture(TextureData data)
        {
            this.drawBackground = true;
            this.backgroundX = data.x();
            this.backgroundY = data.y();
            this.backgroundWidth = data.width();
            this.backgroundHeight = data.height();
            this.backgroundTexture = data.id();
            this.backgroundU = data.u();
            this.backgroundV = data.v();
            this.backgroundSizeWidth = data.textureWidth();
            this.backgroundSizeHeight = data.textureHeight();
            return this;
        }

        public Builder markerTexture(TextureData data)
        {
            this.markerX = data.x();
            this.markerY = data.y();
            this.markerWidth = data.width();
            this.markerHeight = data.height();
            this.markerTexture = data.id();
            this.markerU = data.u();
            this.markerV = data.v();
            this.markerSizeWidth = data.textureWidth();
            this.markerSizeHeight = data.textureHeight();
            return this;
        }

        public Builder textures(TextureData backgroundData, TextureData markerData)
        {
            return this.backgroundTexture(backgroundData).markerTexture(markerData);
        }

        public Builder backgroundRect(int x, int y, int width, int height)
        {
            this.backgroundX = x;
            this.backgroundY = y;
            this.backgroundWidth = width;
            this.backgroundHeight = height;
            return this;
        }

        public Builder markerRect(int x, int y, int width, int height)
        {
            this.markerX = x;
            this.markerY = y;
            this.markerWidth = width;
            this.markerHeight = height;
            return this;
        }

        public Builder fluidRect(int x, int y, int width, int height)
        {
            this.fluidX = x;
            this.fluidY = y;
            this.fluidWidth = width;
            this.fluidHeight = height;
            return this;
        }

        public Builder backgroundTexture(ResourceLocation texture, int u, int v, int width, int height)
        {
            this.drawBackground = true;
            this.backgroundTexture = texture;
            this.backgroundU = u;
            this.backgroundV = v;
            this.backgroundSizeWidth = width;
            this.backgroundSizeHeight = height;
            return this;
        }

        public Builder markerTexture(ResourceLocation texture, int u, int v, int width, int height)
        {
            this.markerTexture = texture;
            this.markerU = u;
            this.markerV = v;
            this.markerSizeWidth = width;
            this.markerSizeHeight = height;
            return this;
        }

        public Builder posSupplier(Supplier<BlockPos> posSupplier)
        {
            this.posSupplier = posSupplier;
            return this;
        }

        public Builder useDefaultValues(SingleFluidStorage fluidStorage, Supplier<BlockPos> posSupplier)
        {
            return this.fluidStorage(fluidStorage)
                    .posSupplier(posSupplier)
                    .markerTexture(FluidContainerTextures.Normal.MARKER_SMALL)
                    .fluidRect(FluidContainerTextures.Normal.FLUID_RECT.getX(),
                               FluidContainerTextures.Normal.FLUID_RECT.getY(),
                               FluidContainerTextures.Normal.FLUID_RECT.getWidth(),
                               FluidContainerTextures.Normal.FLUID_RECT.getHeight());
        }

        public FluidWidget build()
        {
            return new FluidWidget(fluidStorage,
                                   x, y, drawBackground,
                                   backgroundX, backgroundY, backgroundWidth, backgroundHeight,
                                   markerX, markerY, markerWidth, markerHeight,
                                   fluidX, fluidY, fluidWidth, fluidHeight,
                                   backgroundU, backgroundV, markerU, markerV,
                                   backgroundSizeWidth, backgroundSizeHeight,
                                   markerSizeWidth, markerSizeHeight,
                                   backgroundTexture, markerTexture, posSupplier);
        }
    }
}