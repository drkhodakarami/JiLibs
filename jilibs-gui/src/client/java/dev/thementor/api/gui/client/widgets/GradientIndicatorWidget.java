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
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;

import dev.thementor.api.gui.client.enumerations.WidgetDirection;
import dev.thementor.api.gui.client.enumerations.WidgetOrientation;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.client.utils.MouseHelper;
import dev.thementor.api.shared.utils.StringHelper;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class GradientIndicatorWidget implements Renderable, LayoutElement
{
    private final int width, height, color1, color2;
    private final WidgetOrientation orientation;
    private final WidgetDirection direction;
    private final Supplier<Long> amountSupplier, capacitySupplier;
    private final String suffix;

    private int x, y;

    public GradientIndicatorWidget(int x, int y, int width, int height,
                                   String suffix, int color1, int color2,
                                   WidgetOrientation orientation, WidgetDirection direction,
                                   Supplier<Long> amountSupplier, Supplier<Long> capacitySupplier)
    {
        this.width = width;
        this.height = height;
        this.orientation = orientation;
        this.direction = direction;
        this.amountSupplier = amountSupplier;
        this.capacitySupplier = capacitySupplier;
        this.suffix = suffix;
        this.x = x;
        this.y = y;
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks)
    {
        if(amountSupplier == null || capacitySupplier == null || amountSupplier.get() <= 0 || capacitySupplier.get() <= 0)
            return;

        long amount = amountSupplier.get();
        long capacity = capacitySupplier.get();

        float percentage = (float) amount / capacity;

        int fillX, fillY, fillWidth, fillHeight;

        if(orientation == WidgetOrientation.VERTICAL)
        {
            fillHeight = Math.round(percentage * height);
            fillX = x;
            fillY = direction == WidgetDirection.BOTTOM_TO_TOP ? y + height - fillHeight : y;
            fillWidth = width;
        }
        else
        {
            fillWidth = Math.round(percentage * width);
            fillX = direction == WidgetDirection.RIGHT_TO_LEFT ? x + width - fillWidth : x;
            fillY = y;
            fillHeight = height;
        }

        int colorTop = ARGB.lerp(percentage, color1, color2);

        context.fillGradient(fillX, fillY, fillX + fillWidth, fillY + fillHeight, colorTop, color1);

        if(MouseHelper.isMouseOver(mouseX, mouseY, x, y, width, height))
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
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public void visitWidgets(Consumer<AbstractWidget> consumer){}

    protected  void drawTooltip(GuiGraphics context, int mouseX, int mouseY)
    {
        long amount = amountSupplier.get();
        long capacity = capacitySupplier.get();

        Font textRenderer = Minecraft.getInstance().font;

        String amountText = StringHelper.formatted(amount);
        String capacityText = StringHelper.formatted(capacity);

        String temp = !Objects.equals(suffix, "")
                      ? amountText + " / " + capacityText + " " + suffix
                      : amountText + " / " + capacityText;

        List<Component> texts = List.of(Component.literal(temp));

        context.setComponentTooltipForNextFrame(textRenderer, texts, mouseX, mouseY);
    }

    public static class Builder
    {
        private int x, y, color1, color2, width, height;
        private WidgetOrientation orientation;
        private WidgetDirection direction;
        private Supplier<Long> amountSupplier, capacitySupplier;
        private String suffix = "";

        public Builder()
        {
        }

        public Builder suffix(String suffix)
        {
            this.suffix = suffix;
            return this;
        }

        public Builder fillRect(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder colors(int colorA, int colorB)
        {
            return this.colorA(colorA).colorB(colorB);
        }

        public Builder colorA(int color)
        {
            this.color1 = color;
            return this;
        }

        public Builder colorB(int color)
        {
            this.color2 = color;
            return this;
        }

        public Builder colorA(int red, int green, int blue)
        {
            this.color1 = ARGB.color(red, green, blue);
            return this;
        }

        public Builder colorB(int red, int green, int blue)
        {
            this.color2 = ARGB.color(red, green, blue);
            return this;
        }

        public Builder colors(int redA, int greenA, int blueA, int redB, int greenB, int blueB)
        {
            return this.colorA(redA, greenA, blueA).colorB(redB, greenB, blueB);
        }

        public Builder amountSupplier(Supplier<Long> amountSupplier)
        {
            this.amountSupplier = amountSupplier;
            return this;
        }

        public Builder capacitySupplier(Supplier<Long> capacitySupplier)
        {
            this.capacitySupplier = capacitySupplier;
            return this;
        }

        public Builder orientation(WidgetOrientation orientation)
        {
            this.orientation = orientation;
            return this;
        }

        public Builder direction(WidgetDirection direction)
        {
            this.direction = direction;
            return this;
        }

        public GradientIndicatorWidget build()
        {
            return new GradientIndicatorWidget(x, y, width, height,
                                               suffix, color1, color2,
                                               orientation, direction,
                                               amountSupplier, capacitySupplier);
        }
    }
}