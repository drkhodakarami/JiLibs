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

import java.util.function.Consumer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.layouts.LayoutElement;

import dev.thementor.api.gui.client.constants.IndicatorLightTextures;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.gui.client.utils.MenuHelper;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class AlarmLightWidget implements Renderable, LayoutElement
{
    private  final Identifier onState;
    private  final Identifier offState;

    private boolean isOn;

    private int x, y;

    private final int width, height, onStateU, onStateV, offStateU, offStateV;
    private final int offStateTextureWidth, offStateTextureHeight;
    private final int onStateTextureWidth, onStateTextureHeight;

    public AlarmLightWidget(int x, int y, int width, int height, boolean defaultOn,
                            Identifier onState, Identifier offState,
                            int onStateTextureWidth, int onStateTextureHeight,
                            int offStateTextureWidth, int offStateTextureHeight,
                            int onStateU, int onStateV, int offStateU, int offStateV)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onState = onState;
        this.offState = offState;
        this.onStateU = onStateU;
        this.onStateV = onStateV;
        this.offStateU = offStateU;
        this.offStateV = offStateV;
        this.isOn = defaultOn;
        this.onStateTextureWidth = onStateTextureWidth;
        this.onStateTextureHeight = onStateTextureHeight;
        this.offStateTextureWidth = offStateTextureWidth;
        this.offStateTextureHeight = offStateTextureHeight;
    }

    public void setOnState(boolean isOn)
    {
        this.isOn = isOn;
    }

    @Override
    public void render(@NotNull GuiGraphics context, int mouseX, int mouseY, float deltaTicks)
    {
        if(BaseHelper.validateIdentifier(offState))
            MenuHelper.drawTexture(context, offState,
                                   this.x, this.y, offStateU, offStateV, width, height,
                                   offStateTextureWidth, offStateTextureHeight);

        if(this.isOn && BaseHelper.validateIdentifier(onState))
            MenuHelper.drawTexture(context, onState,
                                   this.x, this.y, onStateU, onStateV, width, height,
                                   onStateTextureWidth, onStateTextureHeight);
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
        return this.x;
    }

    @Override
    public int getY()
    {
        return this.y;
    }

    @Override
    public int getWidth()
    {
        return this.width;
    }

    @Override
    public int getHeight()
    {
        return this.height;
    }

    @Override
    public void visitWidgets(@NotNull Consumer<AbstractWidget> consumer)
    {}

    public static class Builder
    {
        private  Identifier onState, offState;

        private boolean isOn;

        private final int x, y;
        private int width, height, onStateU, onStateV, offStateU, offStateV;
        private int offStateTextureWidth, offStateTextureHeight;
        private int onStateTextureWidth, onStateTextureHeight;

        public Builder(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Builder(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public Builder onStateUV(int u, int v)
        {
            this.onStateU = u;
            this.onStateV = v;
            return this;
        }

        public Builder onStateTextureSize(int width, int height)
        {
            this.onStateTextureWidth = width;
            this.onStateTextureHeight = height;
            return this;
        }

        public Builder offStateTextureSize(int width, int height)
        {
            this.offStateTextureWidth = width;
            this.offStateTextureHeight = height;
            return this;
        }

        public Builder offStateUV(int u, int v)
        {
            this.offStateU = u;
            this.offStateV = v;
            return this;
        }

        public Builder addDefaultSize()
        {
            this.width = 16;
            this.height = 16;
            this.onStateTextureSize(16, 16);
            this.offStateTextureSize(16, 16);
            return this;
        }

        public Builder width(int width)
        {
            this.width = width;
            return this;
        }

        public Builder height(int height)
        {
            this.height = height;
            return this;
        }

        public Builder onByDefault()
        {
            return this.onByDefault(true);
        }

        public Builder onByDefault(boolean flag)
        {
            this.isOn = flag;
            return this;
        }

        public Builder onState(Identifier id)
        {
            this.onState = id;
            return this;
        }

        public Builder offState(Identifier id)
        {
            this.offState = id;
            return this;
        }

        public Builder onState(TextureData texture)
        {
            return this.onState(texture.id())
                    .width(texture.width())
                    .height(texture.height())
                    .onStateTextureSize(texture.textureWidth(), texture.textureHeight())
                    .onStateUV(texture.u(), texture.v());
        }

        public Builder offState(TextureData texture)
        {
            return this.offState(texture.id())
                       .width(texture.width())
                       .height(texture.height())
                       .offStateTextureSize(texture.textureWidth(), texture.textureHeight())
                       .offStateUV(texture.u(), texture.v());
        }

        public Builder textures(TextureData onState, TextureData offState)
        {
            return this.onState(onState)
                    .offState(offState);
        }

        public Builder defaultBlack()
        {
            return textures(IndicatorLightTextures.Alarm.On.BLACK, IndicatorLightTextures.Alarm.Off.BLACK)
                    .onByDefault(false);
        }

        public Builder defaultBlue()
        {
            return textures(IndicatorLightTextures.Alarm.On.BLUE, IndicatorLightTextures.Alarm.Off.BLUE)
                    .onByDefault(false);
        }

        public Builder defaultBrown()
        {
            return textures(IndicatorLightTextures.Alarm.On.BROWN, IndicatorLightTextures.Alarm.Off.BROWN)
                    .onByDefault(false);
        }

        public Builder defaultCyan()
        {
            return textures(IndicatorLightTextures.Alarm.On.CYAN, IndicatorLightTextures.Alarm.Off.CYAN)
                    .onByDefault(false);
        }

        public Builder defaultGold()
        {
            return textures(IndicatorLightTextures.Alarm.On.GOLD, IndicatorLightTextures.Alarm.Off.GOLD)
                    .onByDefault(false);
        }

        public Builder defaultGray()
        {
            return textures(IndicatorLightTextures.Alarm.On.GRAY, IndicatorLightTextures.Alarm.Off.GRAY)
                    .onByDefault(false);
        }

        public Builder defaultGreen()
        {
            return textures(IndicatorLightTextures.Alarm.On.GREEN, IndicatorLightTextures.Alarm.Off.GREEN)
                    .onByDefault(false);
        }

        public Builder defaultLightBlue()
        {
            return textures(IndicatorLightTextures.Alarm.On.LIGHT_BLUE, IndicatorLightTextures.Alarm.Off.LIGHT_BLUE)
                    .onByDefault(false);
        }

        public Builder defaultLightGray()
        {
            return textures(IndicatorLightTextures.Alarm.On.LIGHT_GRAY, IndicatorLightTextures.Alarm.Off.LIGHT_GRAY)
                    .onByDefault(false);
        }

        public Builder defaultLime()
        {
            return textures(IndicatorLightTextures.Alarm.On.LIME, IndicatorLightTextures.Alarm.Off.LIME)
                    .onByDefault(false);
        }

        public Builder defaultMagenta()
        {
            return textures(IndicatorLightTextures.Alarm.On.MAGENTA, IndicatorLightTextures.Alarm.Off.MAGENTA)
                    .onByDefault(false);
        }

        public Builder defaultOlive()
        {
            return textures(IndicatorLightTextures.Alarm.On.OLIVE, IndicatorLightTextures.Alarm.Off.OLIVE)
                    .onByDefault(false);
        }

        public Builder defaultOrange()
        {
            return textures(IndicatorLightTextures.Alarm.On.ORANGE, IndicatorLightTextures.Alarm.Off.ORANGE)
                    .onByDefault(false);
        }

        public Builder defaultPink()
        {
            return textures(IndicatorLightTextures.Alarm.On.PINK, IndicatorLightTextures.Alarm.Off.PINK)
                    .onByDefault(false);
        }

        public Builder defaultPurple()
        {
            return textures(IndicatorLightTextures.Alarm.On.PURPLE, IndicatorLightTextures.Alarm.Off.PURPLE)
                    .onByDefault(false);
        }

        public Builder defaultRed()
        {
            return textures(IndicatorLightTextures.Alarm.On.RED, IndicatorLightTextures.Alarm.Off.RED)
                    .onByDefault(false);
        }

        public Builder defaultSilver()
        {
            return textures(IndicatorLightTextures.Alarm.On.SILVER, IndicatorLightTextures.Alarm.Off.SILVER)
                    .onByDefault(false);
        }

        public Builder defaultWhite()
        {
            return textures(IndicatorLightTextures.Alarm.On.WHITE, IndicatorLightTextures.Alarm.Off.WHITE)
                    .onByDefault(false);
        }

        public Builder defaultYellow()
        {
            return textures(IndicatorLightTextures.Alarm.On.YELLOW, IndicatorLightTextures.Alarm.Off.YELLOW)
                    .onByDefault(false);
        }

        public AlarmLightWidget build()
        {
            return new AlarmLightWidget(x, y, width, height, isOn,
                                        onState, offState,
                                        onStateTextureWidth, onStateTextureHeight,
                                        offStateTextureWidth, offStateTextureHeight,
                                        onStateU, onStateV, offStateU, offStateV);
        }
    }
}