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

package dev.thementor.api.shared.utils;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.util.math.Rect2i;

/**
 * Class representing an information area in the Minecraft client GUI.
 *
 * @author TheMentor
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class InfoArea extends DrawContext
{
    /**
     * The area of the information region.
     */
    protected final Rect2i area;

    /**
     * Constructs an InfoArea with default coordinates (0, 0, 0, 0).
     *
     * @param client The MinecraftClient instance.
     * @param state  The GuiRenderState for rendering.
     */
    public InfoArea(MinecraftClient client, GuiRenderState state)
    {
        super(client, state);
        this.area = new Rect2i(0, 0, 0, 0);
    }

    /**
     * Constructs an InfoArea with the specified coordinates.
     *
     * @param client The MinecraftClient instance.
     * @param state  The GuiRenderState for rendering.
     * @param area   The rectangle representing the area of the information region.
     */
    public InfoArea(MinecraftClient client, GuiRenderState state, Rect2i area)
    {
        super(client, state);
        this.area = area;
    }

    /**
     * Draws the content of the information area.
     *
     * @param context The DrawContext for drawing operations.
     */
    public void draw(DrawContext context)
    {}

    /**
     * Checks if the mouse is over the specified area.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public boolean isMouseOver(double mouseX, double mouseY)
    {
        return MouseHelper.isMouseOver(mouseX, mouseY, this.area);
    }
}