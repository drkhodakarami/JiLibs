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

import dev.thementor.api.gui.constants.ToggleButtonTextures;
import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.gui.utils.ScreenHelper;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ToggleButton extends ButtonWidget
{
    private final BiConsumer<ToggleButton, Boolean> onPress;
    private final Identifier onState;
    private final Identifier offState;
    private final Identifier onSelected;
    private final Identifier offSelected;
    private final int onStateU, onStateV, offStateU, offStateV,
            onSelectedU, onSelectedV, offSelectedU, offSelectedV;

    private boolean toggled;

    protected ToggleButton(int x, int y, int width, int height, boolean defaultToggled,
                           Identifier onState, Identifier offState, Identifier onSelected, Identifier offSelected,
                           int onStateU, int onStateV,
                           int offStateU, int offStateV,
                           int onSelectedU, int onSelectedV,
                           int offSelectedU, int offSelectedV,
                           BiConsumer<ToggleButton, Boolean> onPress, NarrationSupplier narrationSupplier)
    {
        super(x, y, width, height, Text.empty(), $ -> {}, narrationSupplier);

        this.onPress = onPress;
        this.toggled = defaultToggled;
        this.onState = onState;
        this.offState = offState;
        this.onSelected = onSelected;
        this.offSelected = offSelected;
        this.onStateU = onStateU;
        this.onStateV = onStateV;
        this.offStateU = offStateU;
        this.offStateV = offStateV;

        this.onSelectedU = onSelectedU;
        this.onSelectedV = onSelectedV;
        this.offSelectedU = offSelectedU;
        this.offSelectedV = offSelectedV;
    }

    @Override
    public void onPress(AbstractInput input)
    {
        toggle();
        this.onPress.accept(this, this.toggled);
    }

    public void toggle()
    {
        this.toggled = !this.toggled;
    }

    public void setToggled(boolean flag)
    {
        this.toggled = flag;
    }

    public boolean getToggled()
    {
        return this.toggled;
    }

    public Identifier getOnState()
    {
        return this.onState;
    }

    public Identifier getOffState()
    {
        return this.offState;
    }

    public Identifier getOnStateSelected()
    {
        return this.onSelected;
    }

    public Identifier getOffStateSelected()
    {
        return this.offSelected;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks)
    {
        if(BaseHelper.validIdentifier(this.onSelected) &&
           BaseHelper.validIdentifier(this.onState) &&
           BaseHelper.validIdentifier(this.offSelected) &&
           BaseHelper.validIdentifier(this.offState))
                ScreenHelper.drawTexture(
                           context,
                this.toggled
                            ? (isHovered() ? this.onSelected : this.onState)
                            : (isHovered() ? this.offSelected : this.offState),
                        getX(),
                        getY(),
                       this.toggled
                            ? (isHovered() ? this.onSelectedU : this.onStateU)
                            : (isHovered() ? this.offSelectedU : this.offStateU),
                       this.toggled
                           ? (isHovered() ? this.onSelectedV : this.onStateV)
                           : (isHovered() ? this.offSelectedV : this.offStateV),
                            getWidth(),
                            getHeight(),
                 getWidth(),
                getHeight(),
                    ColorHelper.getWhite(this.alpha));
    }

    public static class Builder
    {
        private int x, y, width, height;
        private boolean defaultToggled;
        private  Identifier onState;
        private  Identifier offState;
        private  Identifier onSelected;
        private  Identifier offSelected;
        private int onStateU, onStateV, offStateU, offStateV,
                onSelectedU, onSelectedV, offSelectedU, offSelectedV;

        private BiConsumer<ToggleButton, Boolean> onPress = (button, flag) -> {};
        private NarrationSupplier narrationSupplier = textSupplier -> Text.empty();

        public Builder()
        {}

        public Builder(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public Builder(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Builder addDefaultSize()
        {
            this.width = 32 ;
            this.height = 16;
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

        public Builder onStateUV(int u, int v)
        {
            this.onStateU = u;
            this.onStateV = v;
            return this;
        }

        public Builder offStateUV(int u, int v)
        {
            this.onStateU = u;
            this.onStateV = v;
            return this;
        }

        public Builder onSelectedUV(int u, int v)
        {
            this.onSelectedU = u;
            this.onSelectedV = v;
            return this;
        }

        public Builder offSelectedUV(int u, int v)
        {
            this.onSelectedU = u;
            this.onSelectedV = v;
            return this;
        }

        public Builder onPress(BiConsumer<ToggleButton, Boolean> onPress)
        {
            this.onPress = onPress;
            return this;
        }

        public Builder narrationSupplier(NarrationSupplier narrationSupplier)
        {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        public Builder toggleByDefault()
        {
            this.defaultToggled = true;
            return this;
        }

        public Builder toggledByDefault(boolean flag)
        {
            this.defaultToggled = flag;
            return this;
        }

        public Builder setOnState(Identifier id)
        {
            this.onState = id;
            return this;
        }

        public Builder setOffState(Identifier id)
        {
            this.offState = id;
            return this;
        }

        public Builder setOnSelected(Identifier id)
        {
            this.onSelected = id;
            return this;
        }

        public Builder setOffSelected(Identifier id)
        {
            this.offSelected = id;
            return this;
        }

        public Builder setOnState(TextureData texture)
        {
            return this.width(texture.width())
                    .height(texture.height())
                    .onStateUV(texture.u(), texture.v())
                    .setOnState(texture.id());
        }

        public Builder setOffState(TextureData texture)
        {
            return this.width(texture.width())
                       .height(texture.height())
                       .offStateUV(texture.u(), texture.v())
                       .setOffState(texture.id());
        }

        public Builder setOnSelected(TextureData texture)
        {
            return this.width(texture.width())
                       .height(texture.height())
                       .onSelectedUV(texture.u(), texture.v())
                       .setOnSelected(texture.id());
        }

        public Builder setOffSelected(TextureData texture)
        {
            return this.width(texture.width())
                       .height(texture.height())
                       .offSelectedUV(texture.u(), texture.v())
                       .setOffSelected(texture.id());
        }

        public Builder setDefaultTextures()
        {
            return this.setOnState(ToggleButtonTextures.Normal.On.NORMAL.id())
                       .setOffState(ToggleButtonTextures.Normal.Off.NORMAL.id())
                       .setOnSelected(ToggleButtonTextures.Normal.On.HOVERED.id())
                       .setOffSelected(ToggleButtonTextures.Normal.Off.HOVERED.id());
        }

        public Builder setColoredTextures()
        {
            return this.setOnState(ToggleButtonTextures.Colored.On.NORMAL.id())
                       .setOffState(ToggleButtonTextures.Colored.Off.NORMAL.id())
                       .setOnSelected(ToggleButtonTextures.Colored.On.HOVERED.id())
                       .setOffSelected(ToggleButtonTextures.Colored.Off.HOVERED.id());
        }

        public Builder setSmallTextures()
        {
            return this.setOnState(ToggleButtonTextures.Small.On.NORMAL.id())
                       .setOffState(ToggleButtonTextures.Small.Off.NORMAL.id())
                       .setOnSelected(ToggleButtonTextures.Small.On.HOVERED.id())
                       .setOffSelected(ToggleButtonTextures.Small.Off.HOVERED.id());
        }

        public ToggleButton build()
        {
            return new ToggleButton(x, y, width, height, defaultToggled,
                                    onState, offState, onSelected, offSelected,
                                    onStateU, onStateV,
                                    offStateU, offStateV,
                                    onSelectedU, onSelectedV,
                                    offSelectedU, offSelectedV,
                                    onPress, narrationSupplier);
        }
    }
}