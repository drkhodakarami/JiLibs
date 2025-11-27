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

package dev.thementor.api.shared.client.interfaces;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

import dev.thementor.api.shared.annotations.*;

/**
 * Interface for rendering ingredients in a Minecraft mod.
 *
 * @author TurtyWurty, TheMentor
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface IIngredientRenderer<T>
{
    /**
     * Default render method that calls the main render method at position (0, 0).
     *
     * @param stack      The matrix stack to use for rendering.
     * @param ingredient The ingredient to render.
     */
    default void render(PoseStack stack, T ingredient)
    {
        render(stack, 0, 0, ingredient);
    }

    /**
     * Main render method that can be overridden to customize rendering logic.
     *
     * @param stack      The matrix stack to use for rendering.
     * @param x          The x-coordinate to render the ingredient at.
     * @param y          The y-coordinate to render the ingredient at.
     * @param ingredient The ingredient to render.
     */
    default void render(PoseStack stack, int x, int y, T ingredient)
    {}

    /**
     * Generates a tooltip for the given ingredient.
     *
     * @param ingredient        The ingredient to generate a tooltip for.
     * @param tooltipContext    The context for generating the tooltip.
     * @param modID             The ID of the mod that contains the ingredient.
     * @return A list of text elements representing the tooltip.
     */
    List<Component> tooltip(T ingredient, Item.TooltipContext tooltipContext, String modID);

    /**
     * Provides a default text renderer based on the current Minecraft client.
     *
     * @param client  The Minecraft client instance.
     * @param ingredient The ingredient to get the text renderer for.
     * @return The text renderer from the Minecraft client.
     */
    default Font fontRenderer(Minecraft client, T ingredient)
    {
        return client.font;
    }

    /**
     * Provides a default width for rendering ingredients.
     *
     * @return The default width of an ingredient.
     */
    default int width()
    {
        return 16;
    }

    /**
     * Provides a default height for rendering ingredients.
     *
     * @return The default height of an ingredient.
     */
    default int height()
    {
        return 16;
    }
}