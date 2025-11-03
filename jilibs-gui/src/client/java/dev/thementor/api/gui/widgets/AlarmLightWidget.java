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

package dev.thementor.api.gui.widgets;

import dev.thementor.api.gui.constants.IndicatorLightTextures;
import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.gui.utils.ScreenHelper;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class AlarmLightWidget implements Drawable, Widget
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
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks)
    {
        if(BaseHelper.validIdentifier(offState))
            ScreenHelper.drawTexture(context, offState,
                                     this.x, this.y, offStateU, offStateV, width, height,
                                     offStateTextureWidth, offStateTextureHeight);

        if(this.isOn && BaseHelper.validIdentifier(onState))
            ScreenHelper.drawTexture(context, onState,
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
    public void forEachChild(Consumer<ClickableWidget> consumer)
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