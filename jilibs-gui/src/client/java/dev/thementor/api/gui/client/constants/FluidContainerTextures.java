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

import net.minecraft.client.renderer.Rect2i;

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
public class FluidContainerTextures
{
    public static class Normal
    {
        public static final TextureData BACKGROUND =
                new TextureData(0, 0, 0, 0, 18, 63, 32, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/fluid_storage_background.png"));
        public static final TextureData MARKER_BIG =
                new TextureData(1, 15, 0, 0, 16, 33, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/fluid_storage_marker_big.png"));
        public static final TextureData MARKER_SMALL =
                new TextureData(1, 6, 0, 0, 16, 51, 16, 64,
                                BaseHelper.id("jilibs_gui", "textures/gui/widget/fluid_storage_marker_small.png"));

        public static final Rect2i FLUID_RECT = new Rect2i(1, 1, 16, 62);
    }
}