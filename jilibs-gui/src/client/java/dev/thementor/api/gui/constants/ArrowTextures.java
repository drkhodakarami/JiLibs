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
public class ArrowTextures
{
    public static class Small
    {
        public static final TextureData ARROW_DOWN =
                new TextureData(0, 0, 0, 0, 9, 8, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/arrow_down.png"));
        public static final TextureData ARROW_LEFT =
                new TextureData(0, 0, 0, 0, 8, 9, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/arrow_left.png"));
        public static final TextureData ARROW_RIGHT =
                new TextureData(0, 0, 0, 0, 8, 9, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/arrow_right.png"));
        public static final TextureData ARROW_UP =
                new TextureData(0, 0, 0, 0, 9, 8, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/arrow_up.png"));
    }

    public static class HUD
    {
        public static final TextureData ARROW_DRAG =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/icon/arrow_drag.png"));
        public static final TextureData ARROW_RESIZE_TL_BR =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/icon/arrow_resize_tlbr.png"));
        public static final TextureData ARROW_RESIZE_TR_BL =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/icon/arrow_resize_trbl.png"));
        public static final TextureData ARROW_RESIZE_H =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/icon/arrow_resize_h.png"));
        public static final TextureData ARROW_RESIZE_V =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/icon/arrow_resize_v.png"));
    }
}