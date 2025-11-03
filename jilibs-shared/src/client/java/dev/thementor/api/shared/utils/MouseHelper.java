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

import dev.thementor.api.shared.exceptions.Exceptions;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.client.util.math.Rect2i;

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