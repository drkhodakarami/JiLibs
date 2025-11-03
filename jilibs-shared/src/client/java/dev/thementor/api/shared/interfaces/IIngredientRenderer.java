/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
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

package dev.thementor.api.shared.interfaces;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import java.util.List;

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
    default void render(MatrixStack stack, T ingredient)
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
    default void render(MatrixStack stack, int x, int y, T ingredient)
    {}

    /**
     * Generates a tooltip for the given ingredient.
     *
     * @param ingredient        The ingredient to generate a tooltip for.
     * @param tooltipContext    The context for generating the tooltip.
     * @param modID             The ID of the mod that contains the ingredient.
     * @return A list of text elements representing the tooltip.
     */
    List<Text> tooltip(T ingredient, Item.TooltipContext tooltipContext, String modID);

    /**
     * Provides a default text renderer based on the current Minecraft client.
     *
     * @param client  The Minecraft client instance.
     * @param ingredient The ingredient to get the text renderer for.
     * @return The text renderer from the Minecraft client.
     */
    default TextRenderer fontRenderer(MinecraftClient client, T ingredient)
    {
        return client.textRenderer;
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