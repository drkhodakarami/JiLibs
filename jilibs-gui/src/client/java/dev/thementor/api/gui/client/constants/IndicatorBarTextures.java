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
public class IndicatorBarTextures
{
    public static final TextureData BACKGROUND =
            new TextureData(0, 0, 0, 0, 16, 64, 16, 64,
                            BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_dark_background.png"));

    public static class Foreground
    {
        public static final TextureData CENTRAL_BORDER =
                new TextureData(5, 0, 0, 0, 6, 64, 8, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_dark_central_border.png"));
        public static final TextureData CENTRAL_LINE =
                new TextureData(7, 1, 0, 0, 2, 62, 2, 62,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_dark_central_line.png"));
    }

    public static class Progress
    {
        public static final TextureData BLUE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_blue.png"));
        public static final TextureData CYAN =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_cyan.png"));
        public static final TextureData GRAY =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_gray.png"));
        public static final TextureData GREEN =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_green.png"));
        public static final TextureData LIGHT_BLUE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_light_blue.png"));
        public static final TextureData LIGHT_GRAY =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_light_gray.png"));
        public static final TextureData LIME =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_lime.png"));
        public static final TextureData MAGENTA =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_magenta.png"));
        public static final TextureData ORANGE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_orange.png"));
        public static final TextureData PINK =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_pink.png"));
        public static final TextureData PURPLE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_purple.png"));
        public static final TextureData RED =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_red.png"));
        public static final TextureData WHITE =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_white.png"));
        public static final TextureData YELLOW =
                new TextureData(3, 1, 0, 0, 10, 62, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/bar_yellow.png"));
    }
}