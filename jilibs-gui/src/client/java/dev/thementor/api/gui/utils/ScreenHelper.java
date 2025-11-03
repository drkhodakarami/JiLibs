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

package dev.thementor.api.gui.utils;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.thementor.api.gui.constants.ContainerBaseTextures;
import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-23")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class ScreenHelper
{
    public static void drawTexture(DrawContext context, TextureData texture, int x, int y)
    {
        drawTexture(context, texture.id(), x + texture.x(), y + texture.y(), texture.u(), texture.v(), texture.textureWidth(), texture.height(), texture.textureWidth(), texture.textureHeight());
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, float u, float v, int width, int height)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256);
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, float u, float v, int width, int height, int color)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256, color);
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight)
    {
        drawTexture(context, texture, x, y, u, v, width, height, texWidth, texHeight, -1);
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight, int color)
    {
        context.drawTexture(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, texWidth, texHeight, color);
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, int width, int height)
    {
        drawTexture(context, texture, x, y, width, height, -1);
    }

    public static void drawTexture(DrawContext context, Identifier texture, int x, int y, int width, int height, int color)
    {
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, texture, x, y, width, height, color);
    }

    public static void renderTiledSprite(DrawContext context, RenderPipeline renderPipeline, Sprite sprite, int x, int y, int width, int height, int color)
    {
        int spriteWidth = 16;
        int spriteHeight = 16;

        int xCount = MathHelper.floor((float) width / spriteWidth);
        int yCount = MathHelper.floor((float) height / spriteHeight);
        int xRemainder = width % spriteWidth;
        int yRemainder = height % spriteHeight;

        Identifier atlasId = sprite.getAtlasId();
        float minU = sprite.getMinU();
        float minV = sprite.getMinV();

        for (int i = 0; i < xCount; i++)
        {
            for (int j = 0; j < yCount; j++)
            {
                int x1 = x + i * spriteWidth;
                int y1 = y + j * spriteHeight;
                int x2 = x1 + spriteWidth;
                int y2 = y1 + spriteHeight;
                float maxU = sprite.getMaxU();
                float maxV = sprite.getMaxV();

                context.drawTexturedQuad(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }

            if (yRemainder > 0)
            {
                int x1 = x + i * spriteWidth;
                int y1 = y + yCount * spriteHeight;
                int x2 = x1 + spriteWidth;
                int y2 = y1 + yRemainder;
                float maxU = sprite.getMaxU();
                float maxV = minV + (sprite.getMaxV() - sprite.getMinV()) * ((float) yRemainder / spriteHeight);
                context.drawTexturedQuad(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
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
                float maxU = minU + (sprite.getMaxU() - sprite.getMinU()) * ((float) xRemainder / spriteWidth);
                float maxV = sprite.getMaxV();
                context.drawTexturedQuad(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }

            if (yRemainder > 0)
            {
                int x1 = x + xCount * spriteWidth;
                int y1 = y + yCount * spriteHeight;
                int x2 = x1 + xRemainder;
                int y2 = y1 + yRemainder;
                float maxU = minU + (sprite.getMaxU() - sprite.getMinU()) * ((float) xRemainder / spriteWidth);
                float maxV = minV + (sprite.getMaxV() - sprite.getMinV()) * ((float) yRemainder / spriteHeight);
               context.drawTexturedQuad(renderPipeline, atlasId, x1, x2, y1, y2, minU, maxU, minV, maxV, color);
            }
        }
    }

    public static void drawPlayerInventory(DrawContext context, int x, int y)
    {
        drawTexture(context, ContainerBaseTextures.PLAYER_INVENTORY.id(),
                    x, y,
                    ContainerBaseTextures.PLAYER_INVENTORY.u(), ContainerBaseTextures.PLAYER_INVENTORY.v(),
                    ContainerBaseTextures.PLAYER_INVENTORY.width(), ContainerBaseTextures.PLAYER_INVENTORY.height(),
                    ContainerBaseTextures.PLAYER_INVENTORY.textureWidth(),
                    ContainerBaseTextures.PLAYER_INVENTORY.textureHeight());
    }
}