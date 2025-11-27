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

import java.util.function.BiConsumer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;

import dev.thementor.api.gui.client.constants.ToggleButtonTextures;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.gui.client.utils.MenuHelper;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ToggleButton extends Button
{
    private final BiConsumer<ToggleButton, Boolean> onPress;
    private final ResourceLocation onState;
    private final ResourceLocation offState;
    private final ResourceLocation onSelected;
    private final ResourceLocation offSelected;
    private final int onStateU, onStateV, offStateU, offStateV,
            onSelectedU, onSelectedV, offSelectedU, offSelectedV;

    private boolean toggled;

    protected ToggleButton(int x, int y, int width, int height, boolean defaultToggled,
                           ResourceLocation onState, ResourceLocation offState, ResourceLocation onSelected, ResourceLocation offSelected,
                           int onStateU, int onStateV,
                           int offStateU, int offStateV,
                           int onSelectedU, int onSelectedV,
                           int offSelectedU, int offSelectedV,
                           BiConsumer<ToggleButton, Boolean> onPress, CreateNarration narrationSupplier)
    {
        super(x, y, width, height, Component.empty(), $ -> {}, narrationSupplier);

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
    public void onPress(InputWithModifiers input)
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

    public ResourceLocation getOnState()
    {
        return this.onState;
    }

    public ResourceLocation getOffState()
    {
        return this.offState;
    }

    public ResourceLocation getOnStateSelected()
    {
        return this.onSelected;
    }

    public ResourceLocation getOffStateSelected()
    {
        return this.offSelected;
    }

    @Override
    protected void renderWidget(GuiGraphics context, int mouseX, int mouseY, float deltaTicks)
    {
        if(BaseHelper.validateResource(this.onSelected) &&
           BaseHelper.validateResource(this.onState) &&
           BaseHelper.validateResource(this.offSelected) &&
           BaseHelper.validateResource(this.offState))
                MenuHelper.drawTexture(
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
                    ARGB.white(this.alpha));
    }

    public static class Builder
    {
        private int x, y, width, height;
        private boolean defaultToggled;
        private  ResourceLocation onState;
        private  ResourceLocation offState;
        private  ResourceLocation onSelected;
        private  ResourceLocation offSelected;
        private int onStateU, onStateV, offStateU, offStateV,
                onSelectedU, onSelectedV, offSelectedU, offSelectedV;

        private BiConsumer<ToggleButton, Boolean> onPress = (button, flag) -> {};
        private CreateNarration narrationSupplier = textSupplier -> Component.empty();

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

        public Builder narrationSupplier(CreateNarration narrationSupplier)
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

        public Builder setOnState(ResourceLocation id)
        {
            this.onState = id;
            return this;
        }

        public Builder setOffState(ResourceLocation id)
        {
            this.offState = id;
            return this;
        }

        public Builder setOnSelected(ResourceLocation id)
        {
            this.onSelected = id;
            return this;
        }

        public Builder setOffSelected(ResourceLocation id)
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