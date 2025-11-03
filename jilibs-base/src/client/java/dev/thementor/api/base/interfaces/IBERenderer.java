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

package dev.thementor.api.base.interfaces;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IBERenderer<T extends BlockEntity>
{
    default void renderTag(T blockEntity, String inputText,
                           BlockEntityRendererFactory.Context context, MatrixStack matrices, VertexConsumerProvider vertexConsumer,
                           Vec3d vec3d, float tickData, int light, int overlay)
    {
        Text text = Text.literal(inputText);

        matrices.push();
        {
            matrices.translate(vec3d.x, vec3d.y, vec3d.z);
            matrices.multiply(MinecraftClient.getInstance().gameRenderer.getCamera().getRotation());
            matrices.scale(0.025f, -0.025f, 0.025f);
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            TextRenderer textRenderer = context.textRenderer();
            float f = -textRenderer.getWidth(text) / 2.0f;
            int j = (int) (MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f) * 255f) << 24;
            textRenderer.draw(text, f, 1, -2130706433, false, matrix4f, vertexConsumer,
                              TextRenderer.TextLayerType.SEE_THROUGH, j, light);
            textRenderer.draw(text, f, 1, Colors.WHITE, false, matrix4f, vertexConsumer,
                              TextRenderer.TextLayerType.NORMAL, 0, LightmapTextureManager.applyEmission(light, 2));
        }
        matrices.pop();
    }
}