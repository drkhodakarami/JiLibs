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

package dev.thementor.api.gui.constants;

import dev.thementor.api.gui.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public class IndicatorBarTextures
{
    public static final TextureData BACKGROUND =
            new TextureData(0, 0, 0, 0, 16, 64, 16, 64,
                            BaseHelper.identifier("jigui", "textures/gui/widget/bar_dark_background.png"));

    public static class Foreground
    {
        public static final TextureData CENTRAL_BORDER =
                new TextureData(5, 0, 0, 0, 6, 64, 8, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_dark_central_border.png"));
        public static final TextureData CENTRAL_LINE =
                new TextureData(7, 1, 0, 0, 2, 62, 2, 62,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_dark_central_line.png"));
    }

    public static class Progress
    {
        public static final TextureData BLUE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_blue.png"));
        public static final TextureData CYAN =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_cyan.png"));
        public static final TextureData GRAY =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_gray.png"));
        public static final TextureData GREEN =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_green.png"));
        public static final TextureData LIGHT_BLUE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_light_blue.png"));
        public static final TextureData LIGHT_GRAY =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_light_gray.png"));
        public static final TextureData LIME =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_lime.png"));
        public static final TextureData MAGENTA =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_magenta.png"));
        public static final TextureData ORANGE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_orange.png"));
        public static final TextureData PINK =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_pink.png"));
        public static final TextureData PURPLE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_purple.png"));
        public static final TextureData RED =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_red.png"));
        public static final TextureData WHITE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_white.png"));
        public static final TextureData YELLOW =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/bar_yellow.png"));
    }
}