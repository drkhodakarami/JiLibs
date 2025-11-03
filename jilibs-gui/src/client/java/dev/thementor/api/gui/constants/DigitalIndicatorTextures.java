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

@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public class DigitalIndicatorTextures
{
    public static class Normal
    {
        public static class Background
        {
            public static final TextureData DARK =
                    new TextureData(0, 0, 0, 0, 16, 64, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_background_dark.png"));
            public static final TextureData RED =
                    new TextureData(0, 0, 0, 0, 16, 64, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_background_red.png"));
        }

        public static class Progress
        {
            public static final TextureData CYAN =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_cyan.png"));
            public static final TextureData DARK_GREEN =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_dark_green.png"));
            public static final TextureData GRADIANT =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_gradiant.png"));
            public static final TextureData LIME =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_lime.png"));
            public static final TextureData RED =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_red.png"));
            public static final TextureData YELLOW =
                    new TextureData(1, 1, 0, 0, 14, 62, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/main_bar_yellow.png"));
        }
    }

    public static class Small
    {
        public static final TextureData BACKGROUND =
                new TextureData(0, 0, 0, 0, 11, 41, 16, 64,
                                BaseHelper.identifier("jigui", "textures/gui/widget/small_energy_dark_background.png"));

        public static class Progress
        {
            public static final TextureData BLUE =
                    new TextureData(0, 1, 0, 0, 11, 39, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/small_energy_dark_blue.png"));
            public static final TextureData RED =
                    new TextureData(0, 1, 0, 0, 11, 39, 16, 64,
                                    BaseHelper.identifier("jigui", "textures/gui/widget/small_energy_dark_red.png"));
        }
    }
}