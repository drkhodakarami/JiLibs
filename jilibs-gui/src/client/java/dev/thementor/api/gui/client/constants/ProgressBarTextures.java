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
public class ProgressBarTextures
{
    public static class BlazeFuel
    {
        public static final TextureData BACKGROUND =
                new TextureData(0, 0, 0, 0, 20, 6, 32, 8,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/blaze_fuel_background.png"));
        public static final TextureData PROGRESS =
                new TextureData(1, 1, 0, 0, 18, 4, 32, 4,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/blaze_fuel_progress.png"));
    }

    public static class Bubbles
    {
        public static class Up
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 10, 27, 16, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_background_up.png"));
            public static final TextureData PROGRESS =
                    new TextureData(0, 0, 0, 0, 11, 28, 16, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_white_up.png"));
        }

        public static class Down
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 10, 27, 16, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_background_down.png"));
            public static final TextureData PROGRESS =
                    new TextureData(0, 0, 0, 0, 11, 28, 16, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_white_down.png"));
        }

        public static class Left
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 27, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_background_left.png"));
            public static final TextureData PROGRESS =
                    new TextureData(0, 0, 0, 0, 28, 11, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_white_left.png"));
        }

        public static class Right
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 27, 10, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_background_right.png"));
            public static final TextureData PROGRESS =
                    new TextureData(-1, 0, 0, 0, 28, 11, 32, 16,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/bubbles_progress_white_right.png"));
        }
    }

    public static class Fire
    {
        public static final TextureData BACKGROUND =
                new TextureData(0, 0, 0, 0, 13, 13, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/fire_progress_off.png"));
        public static final TextureData PROGRESS =
                new TextureData(-1, 0, 0, 0, 13, 13, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/fire_progress_on.png"));
    }

    public static class Arrow
    {
        public static class Short
        {
            public static class Up
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 15, 22, 16, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_up_short_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 16, 22, 16, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_up_short_on.png"));
            }

            public static class Down
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 15, 22, 16, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_down_short_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(-1, 0, 0, 0, 16, 22, 16, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_down_short_on.png"));
            }

            public static class Left
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 22, 15, 32, 16,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_left_short_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 22, 16, 32, 16,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_left_short_on.png"));
            }

            public static class Right
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 22, 16, 32, 16,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_right_short_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 22, 15, 32, 16,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_right_short_on.png"));
            }
        }

        public static class Long
        {
            public static class Up
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 7, 26, 8, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_up_long_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 8, 26, 8, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_up_long_on.png"));
            }

            public static class Down
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 7, 26, 8, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_down_long_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 8, 26, 8, 32,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_down_long_on.png"));
            }

            public static class Left
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 26, 7, 32, 8,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_left_long_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 26, 8, 32, 8,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_left_long_on.png"));
            }

            public static class Right
            {
                public static final TextureData BACKGROUND =
                        new TextureData(0, 0, 0, 0, 26, 7, 32, 8,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_right_long_off.png"));
                public static final TextureData PROGRESS =
                        new TextureData(0, 0, 0, 0, 26, 8, 32, 8,
                                        BaseHelper.id("jilibs_gui", "textures/gui/widget/progress_arrow_right_long_on.png"));
            }
        }
    }

    public static class Tube
    {
        public static class Short
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 27, 20, 32, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/short_tube_background.png"));
            public static final TextureData PROGRESS =
                    new TextureData(1, 0, 0, 0, 25, 20, 32, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/short_tube_full.png"));
        }

        public static class Tall
        {
            public static final TextureData BACKGROUND =
                    new TextureData(0, 0, 0, 0, 27, 29, 32, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/tall_tube_background.png"));
            public static final TextureData PROGRESS =
                    new TextureData(1, 0, 0, 0, 25, 29, 32, 32,
                                    BaseHelper.id("jilibs_gui", "textures/gui/widget/tall_tube_full.png"));
        }
    }
}