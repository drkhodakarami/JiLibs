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

package dev.thementor.api.shared.enumerations;

/**
 * Enum representing different color modification modes.
 */
public enum ColorMode
{
    /**
     * Adds the modifier color to the original color.
     */
    ADDITION,

    /**
     * Multiplies the original color by the modifier color.
     */
    MULTIPLICATION,

    /**
     * Subtracts the modifier color from the original color.
     */
    SUBTRACTION,

    /**
     * Divides the original color by the modifier color.
     */
    DIVISION,

    /**
     * Replaces the original color with the modifier color.
     */
    REPLACE;

    /**
     * Modifies a color based on the provided mode and modifier color.
     *
     * @param color The original color to modify.
     * @param modifierColor The color used as a modifier.
     * @return The modified color.
     */
    public static int modifyColor(int color, int modifierColor, ColorMode mode)
    {
        float redModifier = (modifierColor >> 16 & 0xFF) / 255.0F;
        float greenModifier = (modifierColor >> 8 & 0xFF) / 255.0F;
        float blueModifier = (modifierColor & 0xFF) / 255.0F;
        float alphaModifier = (modifierColor >> 24 & 0xFF) / 255.0F;

        return modifyColor(color, redModifier, greenModifier, blueModifier, alphaModifier, mode);
    }

    /**
     * Modifies a color based on the provided mode and modifier color.
     *
     * @param color The original color to modify.
     * @param modifierColor The color used as a modifier.
     * @param mode The mode of color modification.
     * @return The modified color.
     */
    public static int modifyColor(int color, float redModifier, float greenModifier, float blueModifier, float alphaModifier, ColorMode mode) {
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;
        float a = (color >> 24 & 0xFF) / 255.0F;

        switch (mode) {
            case ADDITION:
                r += redModifier;
                g += greenModifier;
                b += blueModifier;
                a += alphaModifier;
                break;
            case MULTIPLICATION:
                r *= redModifier;
                g *= greenModifier;
                b *= blueModifier;
                a *= alphaModifier;
                break;
            case SUBTRACTION:
                r -= redModifier;
                g -= greenModifier;
                b -= blueModifier;
                a -= alphaModifier;
                break;
            case DIVISION:
                r /= redModifier;
                g /= greenModifier;
                b /= blueModifier;
                a /= alphaModifier;
                break;
            case REPLACE:
                r = redModifier;
                g = greenModifier;
                b = blueModifier;
                a = alphaModifier;
                break;
            default:
                throw new UnsupportedOperationException("Unexpected value: " + mode);
        }

        return ((int) (r * 255.0F) << 16) | ((int) (g * 255.0F) << 8) | (int) (b * 255.0F) | ((int) (a * 255.0F) << 24);
    }
}