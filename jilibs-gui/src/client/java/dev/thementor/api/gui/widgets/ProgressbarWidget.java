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

import dev.thementor.api.gui.constants.ProgressBarTextures;
import dev.thementor.api.gui.enumerations.WidgetDirection;
import dev.thementor.api.gui.enumerations.WidgetOrientation;
import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.gui.utils.ScreenHelper;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ProgressbarWidget implements Drawable, Widget
{
    private final int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
    private final int indicatorX, indicatorY, indicatorWidth, indicatorHeight;

    private final int backgroundU, backgroundV, indicatorU, indicatorV;

    private int x, y;

    private final int backgroundSizeWidth, backgroundSizeHeight;
    private final int indicatorSizeWidth, indicatorSizeHeight;

    private final WidgetOrientation orientation;
    private final WidgetDirection direction;
    private final Supplier<Long> amountSupplier, capacitySupplier;

    private final Identifier backgroundTexture, indicatorTexture;

    private final boolean drawBackground;

    public ProgressbarWidget(int x, int y,
                             WidgetDirection direction, WidgetOrientation orientation,
                             boolean drawBackground,
                             int backgroundX, int backgroundY, int backgroundWidth, int backgroundHeight,
                             int backgroundU, int backgroundV,
                             int backgroundSizeWidth, int backgroundSizeHeight,
                             int indicatorX, int indicatorY, int indicatorWidth, int indicatorHeight,
                             int indicatorU, int indicatorV,
                             int indicatorSizeWidth, int indicatorSizeHeight,
                             Identifier backgroundTexture, Identifier indicatorTexture,
                             Supplier<Long> amountSupplier, Supplier<Long> capacitySupplier)
    {
        this.direction = direction;
        this.backgroundX = backgroundX;
        this.backgroundY = backgroundY;
        this.backgroundWidth = backgroundWidth;
        this.backgroundHeight = backgroundHeight;
        this.indicatorX = indicatorX;
        this.indicatorY = indicatorY;
        this.indicatorWidth = indicatorWidth;
        this.indicatorHeight = indicatorHeight;
        this.backgroundU = backgroundU;
        this.backgroundV = backgroundV;
        this.indicatorU = indicatorU;
        this.indicatorV = indicatorV;
        this.x = x;
        this.y = y;
        this.backgroundSizeWidth = backgroundSizeWidth;
        this.backgroundSizeHeight = backgroundSizeHeight;
        this.indicatorSizeWidth = indicatorSizeWidth;
        this.indicatorSizeHeight = indicatorSizeHeight;
        this.orientation = orientation;
        this.amountSupplier = amountSupplier;
        this.capacitySupplier = capacitySupplier;
        this.backgroundTexture = backgroundTexture;
        this.indicatorTexture = indicatorTexture;
        this.drawBackground = drawBackground;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks)
    {
        if(drawBackground && BaseHelper.validIdentifier(backgroundTexture))
            ScreenHelper.drawTexture(context, backgroundTexture,
                                    x + backgroundX, y + backgroundY,
                                     backgroundU, backgroundV,
                                     backgroundWidth, backgroundHeight,
                                     backgroundSizeWidth, backgroundSizeHeight);

        if(amountSupplier == null || capacitySupplier == null || amountSupplier.get() <= 0 || capacitySupplier.get() <= 0)
            return;

        if(BaseHelper.validIdentifier(indicatorTexture))
        {
            long amount = amountSupplier.get();
            long capacity = capacitySupplier.get();

            float percentage = (float) amount / capacity;

            int fillX, fillY, fillWidth, fillHeight, fillU, fillV;

            if (orientation == WidgetOrientation.VERTICAL)
            {
                fillHeight = Math.round(percentage * indicatorHeight);
                fillX = x + indicatorX;
                fillY = direction == WidgetDirection.BOTTOM_TO_TOP ? y + indicatorY + indicatorHeight - fillHeight : y + indicatorY;
                fillU = indicatorU;
                fillV = direction == WidgetDirection.BOTTOM_TO_TOP ? indicatorV + indicatorHeight - fillHeight : indicatorV;
                fillWidth = indicatorWidth;
            }
            else
            {
                fillWidth = Math.round(percentage * indicatorWidth);
                fillX = direction == WidgetDirection.RIGHT_TO_LEFT ? x + indicatorX + indicatorWidth - fillWidth : x + indicatorX;
                fillY = y + indicatorY;
                fillU = direction == WidgetDirection.RIGHT_TO_LEFT ? indicatorU + indicatorWidth - fillWidth : indicatorU;
                fillV = indicatorV;
                fillHeight = indicatorHeight;
            }

            ScreenHelper.drawTexture(context, indicatorTexture, fillX, fillY,
                                     fillU, fillV,
                                     fillWidth, fillHeight,
                                     indicatorSizeWidth, indicatorSizeHeight);
        }
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
    public void forEachChild(Consumer<ClickableWidget> consumer){}

    @SuppressWarnings("unused")
    public static class Builder
    {
        private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
        private int indicatorX, indicatorY, indicatorWidth, indicatorHeight;

        private int backgroundU, backgroundV, indicatorU, indicatorV;

        private final int x, y;

        private int backgroundSizeWidth, backgroundSizeHeight;
        private int indicatorSizeWidth, indicatorSizeHeight;

        private WidgetOrientation orientation;
        private WidgetDirection direction;
        private Supplier<Long> amountSupplier, capacitySupplier;

        private Identifier backgroundTexture, indicatorTexture;

        private boolean drawBackground = false;

        public Builder(int x, int y)
        {
            this.x = x;
            this.y = y;
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

        public Builder indicatorTexture(TextureData data)
        {
            this.indicatorX = data.x();
            this.indicatorY = data.y();
            this.indicatorWidth = data.width();
            this.indicatorHeight = data.height();
            this.indicatorTexture = data.id();
            this.indicatorU = data.u();
            this.indicatorV = data.v();
            this.indicatorSizeWidth = data.textureWidth();
            this.indicatorSizeHeight = data.textureHeight();
            return this;
        }

        public Builder textures(TextureData backgroundData, TextureData indicatorData)
        {
            return this.backgroundTexture(backgroundData).indicatorTexture(indicatorData);
        }

        public Builder backgroundRect(int x, int y, int width, int height)
        {
            this.backgroundX = x;
            this.backgroundY = y;
            this.backgroundWidth = width;
            this.backgroundHeight = height;
            return this;
        }

        public Builder indicatorRect(int x, int y, int width, int height)
        {
            this.indicatorX = x;
            this.indicatorY = y;
            this.indicatorWidth = width;
            this.indicatorHeight = height;
            return this;
        }

        public Builder backgroundTexture(Identifier texture, int u, int v, int width, int height)
        {
            this.drawBackground = true;
            this.backgroundTexture = texture;
            this.backgroundU = u;
            this.backgroundV = v;
            this.backgroundSizeWidth = width;
            this.backgroundSizeHeight = height;
            return this;
        }

        public Builder indicatorTexture(Identifier texture, int u, int v, int width, int height)
        {
            this.indicatorTexture = texture;
            this.indicatorU = u;
            this.indicatorV = v;
            this.indicatorSizeWidth = width;
            this.indicatorSizeHeight = height;
            return this;
        }

        public Builder useArrowProgress(boolean useSmallArrows)
        {
            if(orientation == null)
                throw new IllegalArgumentException("You should set orientation before arrow progress");
            if(direction == null)
                throw new IllegalArgumentException("You should set direction before arrow progress");

            if(orientation == WidgetOrientation.VERTICAL)
            {
                return direction == WidgetDirection.TOP_TO_BOTTOM
                    ? useSmallArrows
                            ? this.textures(ProgressBarTextures.Arrow.Long.Down.BACKGROUND, ProgressBarTextures.Arrow.Long.Down.PROGRESS)
                            : this.textures(ProgressBarTextures.Arrow.Short.Down.BACKGROUND, ProgressBarTextures.Arrow.Short.Down.PROGRESS)
                    : useSmallArrows
                           ? this.textures(ProgressBarTextures.Arrow.Long.Up.BACKGROUND, ProgressBarTextures.Arrow.Long.Up.PROGRESS)
                           : this.textures(ProgressBarTextures.Arrow.Short.Up.BACKGROUND, ProgressBarTextures.Arrow.Short.Up.PROGRESS);
            }

            if(orientation == WidgetOrientation.HORIZONTAL)
            {
                return direction == WidgetDirection.LEFT_TO_RIGHT
                    ? useSmallArrows
                               ? this.textures(ProgressBarTextures.Arrow.Long.Right.BACKGROUND, ProgressBarTextures.Arrow.Long.Right.PROGRESS)
                               : this.textures(ProgressBarTextures.Arrow.Short.Right.BACKGROUND, ProgressBarTextures.Arrow.Short.Right.PROGRESS)
                    : useSmallArrows
                               ? this.textures(ProgressBarTextures.Arrow.Long.Left.BACKGROUND, ProgressBarTextures.Arrow.Long.Left.PROGRESS)
                               : this.textures(ProgressBarTextures.Arrow.Short.Left.BACKGROUND, ProgressBarTextures.Arrow.Short.Left.PROGRESS);
            }

            return this;
        }

        public Builder useBlazeProgress()
        {
            return this.useBlazeProgress(false);
        }

        public Builder useBlazeProgress(boolean isRigthToLeft)
        {
            return this.orientation(WidgetOrientation.HORIZONTAL)
                    .direction(isRigthToLeft ? WidgetDirection.RIGHT_TO_LEFT : WidgetDirection.LEFT_TO_RIGHT)
                       .textures(ProgressBarTextures.BlazeFuel.BACKGROUND, ProgressBarTextures.BlazeFuel.PROGRESS);
        }

        public Builder useFireProgress()
        {
            return this.orientation(WidgetOrientation.VERTICAL)
                       .direction(WidgetDirection.BOTTOM_TO_TOP)
                       .textures(ProgressBarTextures.Fire.BACKGROUND, ProgressBarTextures.Fire.PROGRESS);
        }

        public Builder useTubeProgress(boolean useSmallTube)
        {
            return useSmallTube
                    ? this.orientation(WidgetOrientation.VERTICAL)
                          .direction(WidgetDirection.TOP_TO_BOTTOM)
                          .textures(ProgressBarTextures.Tube.Short.BACKGROUND, ProgressBarTextures.Tube.Short.PROGRESS)
                    : this.orientation(WidgetOrientation.VERTICAL)
                          .direction(WidgetDirection.TOP_TO_BOTTOM)
                          .textures(ProgressBarTextures.Tube.Tall.BACKGROUND, ProgressBarTextures.Tube.Tall.PROGRESS);
        }

        public Builder useBubbleProgress()
        {
            if(orientation == WidgetOrientation.VERTICAL)
            {
                return direction == WidgetDirection.TOP_TO_BOTTOM
                         ? this.textures(ProgressBarTextures.Bubbles.Down.BACKGROUND, ProgressBarTextures.Bubbles.Down.PROGRESS)
                         : this.textures(ProgressBarTextures.Bubbles.Up.BACKGROUND, ProgressBarTextures.Bubbles.Up.PROGRESS);
            }

            if(orientation == WidgetOrientation.HORIZONTAL)
            {
                return direction == WidgetDirection.LEFT_TO_RIGHT
                         ? this.textures(ProgressBarTextures.Bubbles.Right.BACKGROUND, ProgressBarTextures.Bubbles.Right.PROGRESS)
                         : this.textures(ProgressBarTextures.Bubbles.Left.BACKGROUND, ProgressBarTextures.Bubbles.Left.PROGRESS);
            }

            return this;
        }

        public ProgressbarWidget build()
        {
            return new ProgressbarWidget(x, y,
                                         direction, orientation,
                                         drawBackground,
                                         backgroundX, backgroundY, backgroundWidth, backgroundHeight,
                                         backgroundU, backgroundV,
                                         backgroundSizeWidth, backgroundSizeHeight,
                                         indicatorX, indicatorY, indicatorWidth, indicatorHeight,
                                         indicatorU, indicatorV,
                                         indicatorSizeWidth, indicatorSizeHeight,
                                         backgroundTexture, indicatorTexture,
                                         amountSupplier, capacitySupplier);
        }
    }
}