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
public class ContainerBaseTextures
{
    public static class Background
    {
        public static int MAX_VANILLA_WITH = 276;
        public static int MAX_VANILLA_HEIGHT = 220;

        public static class Slice
        {
            public static final TextureData TOP_LEFT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.identifier("jigui", "textures/gui/container/slice/background_top_left.png"));
            public static final TextureData TOP_RIGHT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.identifier("jigui", "textures/gui/container/slice/background_top_right.png"));
            public static final TextureData BOTTOM_LEFT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.identifier("jigui", "textures/gui/container/slice/background_bottom_left.png"));
            public static final TextureData BOTTOM_RIGHT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.identifier("jigui", "textures/gui/container/slice/background_bottom_right.png"));
        }

        public static class Generic
        {
            public static final TextureData G_174_220 =
                    new TextureData(0, 0, 0, 0, 174, 220, 256, 256,
                                    BaseHelper.identifier("jigui", "textures/gui/container/generic/174_220_blank.png"));
            public static final TextureData G_176_133 =
                    new TextureData(0, 0, 0, 0, 176, 133, 256, 256,
                                    BaseHelper.identifier("jigui", "textures/gui/container/generic/176_133_blank.png"));
            public static final TextureData G_176_166 =
                    new TextureData(0, 0, 0, 0, 176, 166, 256, 256,
                                    BaseHelper.identifier("jigui", "textures/gui/container/generic/176_166_blank.png"));
            public static final TextureData G_230_219 =
                    new TextureData(0, 0, 0, 0, 230, 219, 256, 256,
                                    BaseHelper.identifier("jigui", "textures/gui/container/generic/230_219_blank.png"));
            public static final TextureData G_276_166 =
                    new TextureData(0, 0, 0, 0, 276, 166, 256, 256,
                                    BaseHelper.identifier("jigui", "textures/gui/container/generic/276_166_blank.png"));
        }
    }

    public static final TextureData PLAYER_INVENTORY =
            new TextureData(0, 0, 0, 0, 162, 76, 256, 128,
                            BaseHelper.identifier("jigui", "textures/gui/container/player_inventory_slots.png"));
}