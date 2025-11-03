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

import dev.thementor.api.gui.enumerations.WidgetDirection;
import dev.thementor.api.gui.enumerations.WidgetOrientation;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.MouseHelper;
import dev.thementor.api.shared.utils.StringHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ColorIndicatorWidget implements Drawable, Widget
{
    private final int width, height, color;
    private final WidgetOrientation orientation;
    private final WidgetDirection direction;
    private final Supplier<Long> amountSupplier, capacitySupplier;
    private final String suffix;

    private int x, y;

    public ColorIndicatorWidget(int x, int y, int width, int height,
                                String suffix, int color,
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
        this.color = color;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks)
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

        context.fill(fillX, fillY, fillX + fillWidth, fillY + fillHeight, color);

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
    public void forEachChild(Consumer<ClickableWidget> consumer){}

    protected  void drawTooltip(DrawContext context, int mouseX, int mouseY)
    {
        long amount = amountSupplier.get();
        long capacity = capacitySupplier.get();

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        String amountText = StringHelper.formatted(amount);
        String capacityText = StringHelper.formatted(capacity);

        String temp = !Objects.equals(suffix, "")
                      ? amountText + " / " + capacityText + " " + suffix
                      : amountText + " / " + capacityText;

        List<Text> texts = List.of(Text.literal(temp));

        context.drawTooltip(textRenderer, texts, mouseX, mouseY);
    }

    public static class Builder
    {
        private int x, y, color, width, height;
        private WidgetOrientation orientation;
        private WidgetDirection direction;
        private Supplier<Long> amountSupplier, capacitySupplier;
        private String suffix;

        public Builder(){}

        public Builder suffix (String suffix)
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

        public Builder color(int color)
        {
            this.color = color;
            return this;
        }

        public Builder color(int red, int green, int blue)
        {
            this.color = ColorHelper.getArgb(red, green, blue);
            return this;
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

        public ColorIndicatorWidget build()
        {
            return new ColorIndicatorWidget(x, y, width, height,
                                            suffix, color,
                                            orientation, direction,
                                            amountSupplier, capacitySupplier);
        }
    }
}