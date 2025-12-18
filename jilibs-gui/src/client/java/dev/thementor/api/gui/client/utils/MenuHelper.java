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

package dev.thementor.api.gui.client.utils;

import com.mojang.blaze3d.pipeline.RenderPipeline;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

import dev.thementor.api.gui.client.constants.ContainerBaseTextures;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import org.w3c.dom.css.Rect;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-23")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class MenuHelper
{
    public static void drawTexture(GuiGraphics context, TextureData texture, int x, int y)
    {
        drawTexture(context, texture.id(), x + texture.x(), y + texture.y(), texture.u(), texture.v(), texture.width(), texture.height(), texture.textureWidth(), texture.textureHeight());
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, float u, float v, int width, int height)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256);
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, float u, float v, int width, int height, int color)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256, color);
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight)
    {
        drawTexture(context, texture, x, y, u, v, width, height, texWidth, texHeight, -1);
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight, int color)
    {
        context.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, texWidth, texHeight, color);
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, int width, int height)
    {
        drawTexture(context, texture, x, y, width, height, -1);
    }

    public static void drawTexture(GuiGraphics context, Identifier texture, int x, int y, int width, int height, int color)
    {
        context.blitSprite(RenderPipelines.GUI_TEXTURED, texture, x, y, width, height, color);
    }

    public static void drawNineSliced(GuiGraphics context, TextureData texture, int x, int y, int panelWidth, int panelHeight, int slice)
    {
        drawNineSliced(context, texture.id(), x + texture.x(), y + texture.y(), panelWidth, panelHeight, texture.u(), texture.v(), texture.width(), texture.height(), slice, texture.textureWidth(), texture.textureHeight(), -1);
    }

    public static void drawNineSliced(GuiGraphics context, TextureData texture, int x, int y, int panelWidth, int panelHeight, int slice, int color)
    {
        drawNineSliced(context, texture.id(), x + texture.x(), y + texture.y(), panelWidth, panelHeight, texture.u(), texture.v(), texture.width(), texture.height(), slice, texture.textureWidth(), texture.textureHeight(), color);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int slice)
    {
        drawNineSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, slice, 256, 256, -1);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int slice, int texWidth, int texHeight)
    {
        drawNineSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, slice, texWidth, texHeight, -1);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int slice, int texWidth, int texHeight)
    {
        drawNineSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, slice, texWidth, texHeight, -1);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int slice, int color)
    {
        drawNineSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, slice, 256, 256, color);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int slice, int color)
    {
        drawNineSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, slice, 256, 256, color);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, int x, int y,  int panelWidth, int panelHeight, float u, float v, int width, int height, int slice, int texWidth, int texHeight, int color)
    {
        drawNineAdvanceSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, slice, slice, slice, slice, texWidth, texHeight, color);
    }

    public static void drawNineSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int slice, int texWidth, int texHeight, int color)
    {
        drawNineAdvanceSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, slice, slice, slice, slice, texWidth, texHeight, color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, TextureData texture, int x, int y, int panelWidth, int panelHeight, int topSlice, int bottomSlice, int leftSlice, int rightSlice)
    {
        drawNineAdvanceSliced(context, texture.id(), x + texture.x(), y + texture.y(), panelWidth, panelHeight, texture.u(), texture.v(), texture.width(), texture.height(), topSlice, bottomSlice, leftSlice, rightSlice, texture.textureWidth(), texture.textureHeight(), -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, TextureData texture, Rect2i panel, int topSlice, int bottomSlice, int leftSlice, int rightSlice)
    {
        drawNineAdvanceSliced(context, texture.id(), panel.getX() + texture.x(), panel.getY() + texture.y(), panel.getWidth(), panel.getHeight(), texture.u(), texture.v(), texture.width(), texture.height(), topSlice, bottomSlice, leftSlice, rightSlice, texture.textureWidth(), texture.textureHeight(),
                              -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, TextureData texture, int x, int y, int panelWidth, int panelHeight, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int color)
    {
        drawNineAdvanceSliced(context, texture.id(), x + texture.x(), y + texture.y(), panelWidth, panelHeight, texture.u(), texture.v(), texture.width(), texture.height(), topSlice, bottomSlice, leftSlice, rightSlice, texture.textureWidth(), texture.textureHeight(), color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, TextureData texture, Rect2i panel, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int color)
    {
        drawNineAdvanceSliced(context, texture.id(), panel.getX() + texture.x(), panel.getY() + texture.y(), panel.getWidth(), panel.getHeight(), texture.u(), texture.v(), texture.width(), texture.height(), topSlice, bottomSlice, leftSlice, rightSlice, texture.textureWidth(), texture.textureHeight(), color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice)
    {
        drawNineAdvanceSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, 256, 256, -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice)
    {
        drawNineAdvanceSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, 256, 256, -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int texWidth, int texHeight)
    {
        drawNineAdvanceSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, texWidth, texHeight, -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int texWidth, int texHeight)
    {
        drawNineAdvanceSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, texWidth, texHeight, -1);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int color)
    {
        drawNineAdvanceSliced(context, texture, x, y, panelWidth, panelHeight, u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, 256, 256, color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int color)
    {
        drawNineAdvanceSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, 256, 256, color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, Rect2i panel, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int texWidth, int texHeight, int color)
    {
        drawNineAdvanceSliced(context, texture, panel.getX(), panel.getY(), panel.getWidth(), panel.getHeight(), u, v, width, height, topSlice, bottomSlice, leftSlice, rightSlice, texWidth, texHeight, color);
    }

    public static void drawNineAdvanceSliced(GuiGraphics context, Identifier texture, int x, int y, int panelWidth, int panelHeight, float u, float v, int width, int height, int topSlice, int bottomSlice, int leftSlice, int rightSlice, int texWidth, int texHeight, int color)
    {
        // --- Calculate sizes ---
        // These are the actual pixel widths/heights of the tiles we will use.
        int centerTexWidth = width - leftSlice - rightSlice;
        int centerTexHeight = height - topSlice - bottomSlice;

        // 2. Calculate the space available for each section of the panel on screen
        int centerPanelWidth = panelWidth - leftSlice - rightSlice;
        int centerPanelHeight  = panelHeight - topSlice - bottomSlice;

        // --- 1. CORNER DRAWING (Drawn exactly once) ---
        // Top-Left Corner (Screen: x, y | Texture: u, v)
        drawTexture(context, texture,
                    x, y,
                    u, v,
                    leftSlice, topSlice,
                    texWidth, texHeight, color);
        // Top-Right Corner: Draw size = rightSlice x topSlice
        drawTexture(context, texture,
                    x + panelWidth - rightSlice, y,
                    u + width - rightSlice, v,
                    rightSlice, topSlice,
                    texWidth, texHeight, color);
        // Bottom-Left Corner: Draw size = leftSlice x bottomSlice
        drawTexture(context, texture,
                    x, y + panelHeight - bottomSlice,
                    u, v + height - bottomSlice,
                    leftSlice, bottomSlice,
                    texWidth, texHeight, color);
        // Bottom-Right Corner: Draw size = rightSlice x bottomSlice
        drawTexture(context, texture,
                    x + panelWidth - rightSlice, y + panelHeight - bottomSlice,
                    u + width - rightSlice, v + height - bottomSlice,
                    rightSlice, bottomSlice,
                    texWidth, texHeight, color);

        // --- 2. EDGE DRAWING (Tiled Horizontally) ---
        for (int tx = 0; tx < centerPanelWidth; tx += centerTexWidth)
        {
            int segmentWidth = Math.min(centerTexWidth, centerPanelWidth - tx);
            // Top Edge: Draw height = topSlice
            drawTexture(context, texture,
                        x + leftSlice + tx, y,
                        u + leftSlice, v,
                        segmentWidth, topSlice,
                        texWidth, texHeight, color);
            // Bottom Edge: Draw height = bottomSlice
            drawTexture(context, texture,
                        x + leftSlice + tx, y + panelHeight - bottomSlice,
                        u + leftSlice, v + height - bottomSlice,
                        segmentWidth, bottomSlice,
                        texWidth, texHeight, color);
        }

        // --- 3. EDGE DRAWING (Tiled Vertically) ---
        for (int ty = 0; ty < centerPanelHeight; ty += centerTexHeight)
        {
            int segmentHeight = Math.min(centerTexHeight, centerPanelHeight - ty);
            // Left Edge: Draw width = leftSlice
            drawTexture(context, texture,
                        x, y + topSlice + ty,
                        u, v + topSlice,
                        leftSlice, segmentHeight,
                        texWidth, texHeight, color);
            // Right Edge: Draw width = rightSlice
            drawTexture(context, texture,
                        x + panelWidth - rightSlice, y + topSlice + ty,
                        u + width - rightSlice, v + topSlice,
                        rightSlice, segmentHeight,
                        texWidth, texHeight, color);
        }

        // --- 4. CENTER DRAWING (Tiled Both Ways) ---
        for (int tx = 0; tx < centerPanelWidth; tx += centerTexWidth)
        {
            int segmentHeight = Math.min(centerTexWidth, centerPanelWidth - tx);
            for (int ty = 0; ty < centerPanelHeight; ty += centerTexHeight)
            {
                int segmentWidth = Math.min(centerTexHeight, centerPanelHeight - ty);
                drawTexture(context, texture,
                            x + leftSlice + tx, y + topSlice + ty,
                            u + leftSlice, v + topSlice,
                            segmentWidth, segmentHeight,
                            texWidth, texHeight, color);
            }
        }
    }

    public static void renderTiledSprite(GuiGraphics context, RenderPipeline renderPipeline, TextureAtlasSprite sprite, int x, int y, int width, int height, int color)
    {
        int spriteWidth = 16;
        int spriteHeight = 16;

        int xCount = Mth.floor((float) width / spriteWidth);
        int yCount = Mth.floor((float) height / spriteHeight);
        int xRemainder = width % spriteWidth;
        int yRemainder = height % spriteHeight;

        Identifier atlasId = sprite.atlasLocation();
        float minU = sprite.getU0();
        float minV = sprite.getV0();

        for (int i = 0; i < xCount; i++)
        {
            for (int j = 0; j < yCount; j++)
            {
                int x1 = x + i * spriteWidth;
                int y1 = y + j * spriteHeight;
                int x2 = x1 + spriteWidth;
                int y2 = y1 + spriteHeight;
                float maxU = sprite.getU1();
                float maxV = sprite.getV1();

                context.innerBlit(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }

            if (yRemainder > 0)
            {
                int x1 = x + i * spriteWidth;
                int y1 = y + yCount * spriteHeight;
                int x2 = x1 + spriteWidth;
                int y2 = y1 + yRemainder;
                float maxU = sprite.getU1();
                float maxV = minV + (sprite.getV1() - sprite.getV0()) * ((float) yRemainder / spriteHeight);
                context.innerBlit(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }
        }

        if (xRemainder > 0)
        {
            for (int j = 0; j < yCount; j++)
            {
                int x1 = x + xCount * spriteWidth;
                int y1 = y + j * spriteHeight;
                int x2 = x1 + xRemainder;
                int y2 = y1 + spriteHeight;
                float maxU = minU + (sprite.getU1() - sprite.getU0()) * ((float) xRemainder / spriteWidth);
                float maxV = sprite.getV1();
                context.innerBlit(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }

            if (yRemainder > 0)
            {
                int x1 = x + xCount * spriteWidth;
                int y1 = y + yCount * spriteHeight;
                int x2 = x1 + xRemainder;
                int y2 = y1 + yRemainder;
                float maxU = minU + (sprite.getU1() - sprite.getU0()) * ((float) xRemainder / spriteWidth);
                float maxV = minV + (sprite.getV1() - sprite.getV0()) * ((float) yRemainder / spriteHeight);
               context.innerBlit(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }
        }
    }

    public static void drawPlayerInventory(GuiGraphics context, int x, int y)
    {
        drawTexture(context, ContainerBaseTextures.PLAYER_INVENTORY.id(),
                    x, y,
                    ContainerBaseTextures.PLAYER_INVENTORY.u(), ContainerBaseTextures.PLAYER_INVENTORY.v(),
                    ContainerBaseTextures.PLAYER_INVENTORY.width(), ContainerBaseTextures.PLAYER_INVENTORY.height(),
                    ContainerBaseTextures.PLAYER_INVENTORY.textureWidth(),
                    ContainerBaseTextures.PLAYER_INVENTORY.textureHeight());
    }
}