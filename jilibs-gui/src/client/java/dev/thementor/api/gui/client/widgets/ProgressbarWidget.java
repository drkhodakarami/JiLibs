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
import java.util.function.Supplier;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.resources.Identifier;

import dev.thementor.api.gui.client.constants.ProgressBarTextures;
import dev.thementor.api.gui.client.enumerations.WidgetDirection;
import dev.thementor.api.gui.client.enumerations.WidgetOrientation;
import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.gui.client.utils.MenuHelper;
import dev.thementor.api.shared.utils.BaseHelper;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ProgressbarWidget implements Renderable, LayoutElement
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
    public void render(@NotNull GuiGraphics context, int mouseX, int mouseY, float deltaTicks)
    {
        if(drawBackground && BaseHelper.validateIdentifier(backgroundTexture))
            MenuHelper.drawTexture(context, backgroundTexture,
                                    x + backgroundX, y + backgroundY,
                                   backgroundU, backgroundV,
                                   backgroundWidth, backgroundHeight,
                                   backgroundSizeWidth, backgroundSizeHeight);

        if(amountSupplier == null || capacitySupplier == null || amountSupplier.get() <= 0 || capacitySupplier.get() <= 0)
            return;

        if(BaseHelper.validateIdentifier(indicatorTexture))
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

            MenuHelper.drawTexture(context, indicatorTexture, fillX, fillY,
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
    public void visitWidgets(@NotNull Consumer<AbstractWidget> consumer){}

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

        public Builder hideBackground()
        {
            return this.shouldDrawBackground(false);
        }

        public Builder drawBackground()
        {
            return this.shouldDrawBackground(true);
        }

        public Builder shouldDrawBackground(boolean flag)
        {
            this.drawBackground = flag;
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
            return this.useArrowProgress(useSmallArrows, false);
        }

        public Builder useArrowProgress(boolean useSmallArrows, boolean drawBackground)
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
                                  .shouldDrawBackground(drawBackground)
                            : this.textures(ProgressBarTextures.Arrow.Short.Down.BACKGROUND, ProgressBarTextures.Arrow.Short.Down.PROGRESS)
                                  .shouldDrawBackground(drawBackground)
                    : useSmallArrows
                           ? this.textures(ProgressBarTextures.Arrow.Long.Up.BACKGROUND, ProgressBarTextures.Arrow.Long.Up.PROGRESS)
                                 .shouldDrawBackground(drawBackground)
                           : this.textures(ProgressBarTextures.Arrow.Short.Up.BACKGROUND, ProgressBarTextures.Arrow.Short.Up.PROGRESS)
                                 .shouldDrawBackground(drawBackground);
            }

            if(orientation == WidgetOrientation.HORIZONTAL)
            {
                return direction == WidgetDirection.LEFT_TO_RIGHT
                    ? useSmallArrows
                               ? this.textures(ProgressBarTextures.Arrow.Long.Right.BACKGROUND, ProgressBarTextures.Arrow.Long.Right.PROGRESS)
                                     .shouldDrawBackground(drawBackground)
                               : this.textures(ProgressBarTextures.Arrow.Short.Right.BACKGROUND, ProgressBarTextures.Arrow.Short.Right.PROGRESS)
                                     .shouldDrawBackground(drawBackground)
                    : useSmallArrows
                               ? this.textures(ProgressBarTextures.Arrow.Long.Left.BACKGROUND, ProgressBarTextures.Arrow.Long.Left.PROGRESS)
                                     .shouldDrawBackground(drawBackground)
                               : this.textures(ProgressBarTextures.Arrow.Short.Left.BACKGROUND, ProgressBarTextures.Arrow.Short.Left.PROGRESS)
                                     .shouldDrawBackground(drawBackground);
            }

            return this;
        }

        public Builder useBlazeProgress()
        {
            return this.useBlazeProgress(false, false);
        }

        public Builder useBlazeProgress(boolean drawBackground)
        {
            return this.useBlazeProgress(false, drawBackground);
        }

        public Builder useBlazeProgress(boolean isRightToLeft, boolean drawBackground)
        {
            return this.orientation(WidgetOrientation.HORIZONTAL)
                    .direction(isRightToLeft ? WidgetDirection.RIGHT_TO_LEFT : WidgetDirection.LEFT_TO_RIGHT)
                       .textures(ProgressBarTextures.BlazeFuel.BACKGROUND, ProgressBarTextures.BlazeFuel.PROGRESS)
                    .shouldDrawBackground(drawBackground);
        }

        public Builder useFireProgress()
        {
            return this.useFireProgress(false);
        }

        public Builder useFireProgress(boolean drawBackground)
        {
            return this.orientation(WidgetOrientation.VERTICAL)
                       .direction(WidgetDirection.BOTTOM_TO_TOP)
                       .textures(ProgressBarTextures.Fire.BACKGROUND, ProgressBarTextures.Fire.PROGRESS)
                    .shouldDrawBackground(drawBackground);
        }

        public Builder useTubeProgress(boolean useSmallTube)
        {
            return this.useTubeProgress(useSmallTube, false);
        }

        public Builder useTubeProgress(boolean useSmallTube, boolean drawBackground)
        {
            return useSmallTube
                    ? this.orientation(WidgetOrientation.VERTICAL)
                          .direction(WidgetDirection.TOP_TO_BOTTOM)
                          .textures(ProgressBarTextures.Tube.Short.BACKGROUND, ProgressBarTextures.Tube.Short.PROGRESS)
                           .shouldDrawBackground(drawBackground)
                    : this.orientation(WidgetOrientation.VERTICAL)
                          .direction(WidgetDirection.TOP_TO_BOTTOM)
                          .textures(ProgressBarTextures.Tube.Tall.BACKGROUND, ProgressBarTextures.Tube.Tall.PROGRESS)
                           .shouldDrawBackground(drawBackground);
        }

        public Builder useBubbleProgress()
        {
            return this.useBubbleProgress(false);
        }

        public Builder useBubbleProgress(boolean drawBackground)
        {
            if(orientation == WidgetOrientation.VERTICAL)
            {
                return direction == WidgetDirection.TOP_TO_BOTTOM
                         ? this.textures(ProgressBarTextures.Bubbles.Down.BACKGROUND, ProgressBarTextures.Bubbles.Down.PROGRESS)
                               .shouldDrawBackground(drawBackground)
                         : this.textures(ProgressBarTextures.Bubbles.Up.BACKGROUND, ProgressBarTextures.Bubbles.Up.PROGRESS)
                               .shouldDrawBackground(drawBackground);
            }

            if(orientation == WidgetOrientation.HORIZONTAL)
            {
                return direction == WidgetDirection.LEFT_TO_RIGHT
                         ? this.textures(ProgressBarTextures.Bubbles.Right.BACKGROUND, ProgressBarTextures.Bubbles.Right.PROGRESS)
                               .shouldDrawBackground(drawBackground)
                         : this.textures(ProgressBarTextures.Bubbles.Left.BACKGROUND, ProgressBarTextures.Bubbles.Left.PROGRESS)
                               .shouldDrawBackground(drawBackground);
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