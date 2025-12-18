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

package dev.thementor.api.shared.enumerations;

/**
 * Enum representing different color modification modes.
 */
@SuppressWarnings("unused")
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
     * @param redModifier The red color used as a modifier.
     * @param greenModifier The green color used as a modifier.
     * @param blueModifier The blue color used as a modifier.
     * @param alphaModifier The color alpha used as a modifier.
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