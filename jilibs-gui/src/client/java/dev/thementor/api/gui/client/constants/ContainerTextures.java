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
public class ContainerTextures
{
    public static class Connection
    {
        public static final TextureData INPUT_3 =
                new TextureData(0, 0, 0, 0, 30, 25, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/connection_three_input.png"));
        public static final TextureData OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 30, 25, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/connection_three_result.png"));
        public static final TextureData TUBE_TO_IO =
                new TextureData(0, 0, 0, 0, 25, 29, 32, 32,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/connection_tube_to_io.png"));
    }

    public static class ContainerMode
    {
        public static final TextureData EXTRACT =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/container_mode_extract.png"));
        public static final TextureData FILL =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/container_mode_fill.png"));
        public static final TextureData IO =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/container_mode_io.png"));
        public static final TextureData LOCKED =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/container_mode_locked.png"));
    }

    public static class IO
    {
        public static final TextureData INPUT_1_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 38, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/one_input_two_output.png"));
        public static final TextureData INPUT_1_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/one_input_three_output.png"));

        public static final TextureData INPUT_2_OUTPUT_1 =
                new TextureData(0, 0, 0, 0, 64, 38, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/two_input_one_output.png"));
        public static final TextureData INPUT_2_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/two_input_two_output.png"));
        public static final TextureData INPUT_2_OUTPUT_2_CENTERED =
                new TextureData(0, 0, 0, 0, 64, 58, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/two_input_central_two_output.png"));
        public static final TextureData INPUT_2_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/two_input_three_output.png"));

        public static final TextureData INPUT_3_OUTPUT_1 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/three_input_one_output.png"));
        public static final TextureData INPUT_3_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/three_input_two_output.png"));
        public static final TextureData INPUT_3_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 58, 64, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/three_input_three_output.png"));
    }

    public static class Lock
    {
        public static final TextureData CLOSE =
                new TextureData(0, 0, 0, 0, 10, 14, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/locked.png"));
        public static final TextureData OPEN =
                new TextureData(0, 0, 0, 0, 15, 14, 16, 16,
                                BaseHelper.id("jilibs_gui", "textures/gui/container/unlocked.png"));
    }
}