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
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import dev.thementor.api.gui.client.constants.ContainerBaseTextures;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.shared.annotations.*;

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

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, float u, float v, int width, int height)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256);
    }

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, float u, float v, int width, int height, int color)
    {
        drawTexture(context, texture, x, y, u, v, width, height, 256, 256, color);
    }

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight)
    {
        drawTexture(context, texture, x, y, u, v, width, height, texWidth, texHeight, -1);
    }

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, float u, float v, int width, int height, int texWidth, int texHeight, int color)
    {
        context.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, texWidth, texHeight, color);
    }

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, int width, int height)
    {
        drawTexture(context, texture, x, y, width, height, -1);
    }

    public static void drawTexture(GuiGraphics context, ResourceLocation texture, int x, int y, int width, int height, int color)
    {
        context.blitSprite(RenderPipelines.GUI_TEXTURED, texture, x, y, width, height, color);
    }

    public static void renderTiledSprite(GuiGraphics context, RenderPipeline renderPipeline, TextureAtlasSprite sprite, int x, int y, int width, int height, int color)
    {
        int spriteWidth = 16;
        int spriteHeight = 16;

        int xCount = Mth.floor((float) width / spriteWidth);
        int yCount = Mth.floor((float) height / spriteHeight);
        int xRemainder = width % spriteWidth;
        int yRemainder = height % spriteHeight;

        ResourceLocation atlasId = sprite.atlasLocation();
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