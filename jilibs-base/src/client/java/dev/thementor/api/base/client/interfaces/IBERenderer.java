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

package dev.thementor.api.base.client.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import org.joml.Matrix4f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import dev.thementor.api.shared.annotations.*;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IBERenderer<T extends BlockEntity>
{
    default void renderTag(T blockEntity, String inputText,
                           BlockEntityRendererProvider.Context context, PoseStack matrices, MultiBufferSource vertexConsumer,
                           Vec3 vec3d, float tickData, int light, int overlay)
    {
        Component text = Component.literal(inputText);

        matrices.pushPose();
        {
            matrices.translate(vec3d.x, vec3d.y, vec3d.z);
            matrices.mulPose(Minecraft.getInstance().gameRenderer.getMainCamera().rotation());
            matrices.scale(0.025f, -0.025f, 0.025f);
            Matrix4f matrix4f = matrices.last().pose();
            Font textRenderer = context.font();
            float f = -textRenderer.width(text) / 2.0f;
            int j = (int) (Minecraft.getInstance().options.getBackgroundOpacity(0.25f) * 255f) << 24;
            textRenderer.drawInBatch(text, f, 1, -2130706433, false, matrix4f, vertexConsumer,
                              Font.DisplayMode.SEE_THROUGH, j, light);
            textRenderer.drawInBatch(text, f, 1, CommonColors.WHITE, false, matrix4f, vertexConsumer,
                              Font.DisplayMode.NORMAL, 0, LightTexture.lightCoordsWithEmission(light, 2));
        }
        matrices.popPose();
    }
}