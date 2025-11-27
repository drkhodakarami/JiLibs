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

import net.minecraft.client.renderer.Rect2i;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Utility class providing methods to determine if the mouse is over a specific area.
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

public class MouseHelper
{
    /**
     * Public constructor to enforce non-instantiation.
     */
    public MouseHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Checks if the mouse is over a specified rectangular area considering offsets.
     *
     * @param mouseX   The x-coordinate of the mouse.
     * @param mouseY   The y-coordinate of the mouse.
     * @param x        The x-coordinate of the rectangle's top-left corner.
     * @param y        The y-coordinate of the rectangle's top-left corner.
     * @param width    The width of the rectangle.
     * @param height   The height of the rectangle.
     * @param offsetX  The horizontal offset to adjust the mouse position by.
     * @param offsetY  The vertical offset to adjust the mouse position by.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height, int offsetX, int offsetY)
    {
        return mouseX >= x + offsetX &&
               mouseX <= x + offsetX + width &&
               mouseY >= y + offsetY &&
               mouseY <= y + offsetY + height;
    }

    /**
     * Checks if the mouse is over a specified rectangular area.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param x      The x-coordinate of the rectangle's top-left corner.
     * @param y      The y-coordinate of the rectangle's top-left corner.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y)
    {
        return isMouseOver(mouseX, mouseY, x, y, 16, 16, 0, 0);
    }

    /**
     * Checks if the mouse is over a specified rectangular area with uniform size.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param x      The x-coordinate of the rectangle's top-left corner.
     * @param y      The y-coordinate of the rectangle's top-left corner.
     * @param size   The width and height of the rectangle.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int size)
    {
        return isMouseOver(mouseX, mouseY, x, y, size, size, 0, 0);
    }

    /**
     * Checks if the mouse is over a specified rectangular area with uniform size.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param x      The x-coordinate of the rectangle's top-left corner.
     * @param y      The y-coordinate of the rectangle's top-left corner.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height)
    {
        return isMouseOver(mouseX, mouseY, x, y, width, height, 0, 0);
    }

    /**
     * Checks if the mouse is over a specified rectangular area.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param rect   The rectangle representing the area to check against.
     * @return true if the mouse is within the bounds of the area; false otherwise.
     */
    public static boolean isMouseOver(double mouseX, double mouseY, Rect2i rect)
    {
        return isMouseOver(mouseX, mouseY, rect.getX(), rect.getY(), rect.getWidth(), rect.getWidth());
    }
}