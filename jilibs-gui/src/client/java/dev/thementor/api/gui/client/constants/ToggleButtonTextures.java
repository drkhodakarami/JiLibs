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
public class ToggleButtonTextures
{
    public static class Normal
    {
        public static class Off
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_switch_off.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_switch_off.png"));
        }

        public static class On
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_switch_on.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_switch_on.png"));
        }
    }

    public static class Colored
    {
        public static class Off
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_switch_green_on.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_switch_green_on.png"));
        }

        public static class On
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_switch_red_off.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 32, 16, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_switch_red_off.png"));
        }
    }

    public static class Small
    {
        public static class Off
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 19, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_button_green_on.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 19, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_button_green_on.png"));
        }

        public static class On
        {
            public static final TextureData NORMAL =
                    new TextureData(0, 0, 0, 0, 19, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/toggle_button_red_off.png"));
            public static final TextureData HOVERED =
                    new TextureData(0, 0, 0, 0, 19, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/selected_toggle_button_red_off.png"));
        }
    }
}