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

package dev.thementor.api.shared.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.renderer.Rect2i;

import dev.thementor.api.shared.annotations.*;

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

public class InfoArea extends GuiGraphics
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
    public InfoArea(Minecraft client, GuiRenderState state, int mouseX, int mouseY)
    {
        super(client, state, mouseX, mouseY);
        this.area = new Rect2i(0, 0, 0, 0);
    }

    /**
     * Constructs an InfoArea with the specified coordinates.
     *
     * @param client The MinecraftClient instance.
     * @param state  The GuiRenderState for rendering.
     * @param area   The rectangle representing the area of the information region.
     */
    public InfoArea(Minecraft client, GuiRenderState state, int mouseX, int mouseY, Rect2i area)
    {
        super(client, state, mouseX, mouseY);
        this.area = area;
    }

    /**
     * Draws the content of the information area.
     *
     * @param context The DrawContext for drawing operations.
     */
    public void draw(GuiGraphics context)
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