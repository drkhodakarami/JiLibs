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
public class ContainerTextures
{
    public static class Connection
    {
        public static final TextureData INPUT_3 =
                new TextureData(0, 0, 0, 0, 30, 25, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/container/connection_three_input.png"));
        public static final TextureData OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 30, 25, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/container/connection_three_result.png"));
        public static final TextureData TUBE_TO_IO =
                new TextureData(0, 0, 0, 0, 25, 29, 32, 32,
                                BaseHelper.identifier("jigui", "textures/gui/container/connection_tube_to_io.png"));
    }

    public static class ContainerMode
    {
        public static final TextureData EXTRACT =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/container_mode_extract.png"));
        public static final TextureData FILL =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/container_mode_fill.png"));
        public static final TextureData IO =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/container_mode_io.png"));
        public static final TextureData LOCKED =
                new TextureData(0, 0, 0, 0, 16, 16, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/container_mode_locked.png"));
    }

    public static class IO
    {
        public static final TextureData INPUT_1_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 38, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/one_input_two_output.png"));
        public static final TextureData INPUT_1_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/one_input_three_output.png"));

        public static final TextureData INPUT_2_OUTPUT_1 =
                new TextureData(0, 0, 0, 0, 64, 38, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/two_input_one_output.png"));
        public static final TextureData INPUT_2_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/two_input_two_output.png"));
        public static final TextureData INPUT_2_OUTPUT_2_CENTERED =
                new TextureData(0, 0, 0, 0, 64, 58, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/two_input_central_two_output.png"));
        public static final TextureData INPUT_2_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/two_input_three_output.png"));

        public static final TextureData INPUT_3_OUTPUT_1 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/three_input_one_output.png"));
        public static final TextureData INPUT_3_OUTPUT_2 =
                new TextureData(0, 0, 0, 0, 64, 45, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/three_input_two_output.png"));
        public static final TextureData INPUT_3_OUTPUT_3 =
                new TextureData(0, 0, 0, 0, 64, 58, 64, 64,
                                BaseHelper.identifier("jigui", "textures/gui/container/three_input_three_output.png"));
    }

    public static class Lock
    {
        public static final TextureData CLOSE =
                new TextureData(0, 0, 0, 0, 10, 14, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/locked.png"));
        public static final TextureData OPEN =
                new TextureData(0, 0, 0, 0, 15, 14, 16, 16,
                                BaseHelper.identifier("jigui", "textures/gui/container/unlocked.png"));
    }
}