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

package dev.thementor.api.gui.client.constants;

import dev.thementor.api.gui.client.records.TextureData;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

@SuppressWarnings("unused")
@Developer("The Mentor")
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
                                BaseHelper.id("jilibs_gui", "textures/gui/container/arrow_down.png"));
        public static final TextureData ARROW_LEFT =
                new TextureData(0, 0, 0, 0, 8, 9, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/arrow_left.png"));
        public static final TextureData ARROW_RIGHT =
                new TextureData(0, 0, 0, 0, 8, 9, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/arrow_right.png"));
        public static final TextureData ARROW_UP =
                new TextureData(0, 0, 0, 0, 9, 8, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/arrow_up.png"));
    }

    public static class HUD
    {
        public static final TextureData ARROW_DRAG =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/arrow_drag.png"));
        public static final TextureData ARROW_RESIZE_TL_BR =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/arrow_resize_tlbr.png"));
        public static final TextureData ARROW_RESIZE_TR_BL =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/arrow_resize_trbl.png"));
        public static final TextureData ARROW_RESIZE_H =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/arrow_resize_h.png"));
        public static final TextureData ARROW_RESIZE_V =
                new TextureData(0, 0, 0, 0, 32, 32, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/icon/arrow_resize_v.png"));
    }
}