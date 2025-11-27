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
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/slice/background_top_left.png"));
            public static final TextureData TOP_RIGHT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/slice/background_top_right.png"));
            public static final TextureData BOTTOM_LEFT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/slice/background_bottom_left.png"));
            public static final TextureData BOTTOM_RIGHT =
                    new TextureData(0, 0, 0, 0, 4, 4, 4, 4,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/slice/background_bottom_right.png"));
        }

        public static class Generic
        {
            public static final TextureData G_174_220 =
                    new TextureData(0, 0, 0, 0, 174, 220, 256, 256,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/generic/174_220_blank.png"));
            public static final TextureData G_176_133 =
                    new TextureData(0, 0, 0, 0, 176, 133, 256, 256,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/generic/176_133_blank.png"));
            public static final TextureData G_176_166 =
                    new TextureData(0, 0, 0, 0, 176, 166, 256, 256,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/generic/176_166_blank.png"));
            public static final TextureData G_230_219 =
                    new TextureData(0, 0, 0, 0, 230, 219, 256, 256,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/generic/230_219_blank.png"));
            public static final TextureData G_276_166 =
                    new TextureData(0, 0, 0, 0, 276, 166, 256, 256,
                                    BaseHelper.id("jilibs_gui", "textures/gui/container/generic/276_166_blank.png"));
        }
    }

    public static final TextureData PLAYER_INVENTORY =
            new TextureData(0, 0, 0, 0, 162, 76, 256, 128,
                            BaseHelper.id("jilibs_gui", "textures/gui/container/player_inventory_slots.png"));
}