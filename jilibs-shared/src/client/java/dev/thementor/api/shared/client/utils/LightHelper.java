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

package dev.thementor.api.shared.client.utils;

import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

@SuppressWarnings("unused")
public class LightHelper
{
    public static int getLightLevel(Level world, BlockPos pos)
    {
        int block_level = world.getBrightness(LightLayer.BLOCK, pos);
        int sky_level = world.getBrightness(LightLayer.SKY, pos);

        return LightTexture.pack(block_level, sky_level);
    }
}