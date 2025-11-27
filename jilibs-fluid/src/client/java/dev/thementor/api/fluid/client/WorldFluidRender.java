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

package dev.thementor.api.fluid.client;

/*import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import dev.thementor.api.shared.enumerations.ColorMode;*/

@SuppressWarnings("unused")
public class WorldFluidRender
{
/*    public static void render(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                       Level world, BlockPos pos,
                       float x1, float y1, float z1,
                       float x2, float maxHeight, float z2)
    {
        render(storage, vertexConsumers, matrices, light, overlay, world, pos, x1, y1, z1, x2, maxHeight, z2, 0xFFFFFFFF, ColorMode.MULTIPLICATION, false);
    }

    public static void render(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                       Level world, BlockPos pos,
                       float x1, float y1, float z1,
                       float x2, float maxHeight, float z2,
                       int color, ColorMode colorMode)
    {
        render(storage, vertexConsumers, matrices, light, overlay, world, pos, x1, y1, z1, x2, maxHeight, z2, color, colorMode, false);
    }

    public static void render(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                       Level world, BlockPos pos,
                       float x1, float y1, float z1,
                       float x2, float maxHeight, float z2, boolean debug)
    {
        render(storage, vertexConsumers, matrices, light, overlay, world, pos, x1, y1, z1, x2, maxHeight, z2, 0xFFFFFFFF, ColorMode.MULTIPLICATION, debug);
    }

    public static void render(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                       Level world, BlockPos pos,
                       float x1, float y1, float z1,
                       float x2, float maxHeight, float z2,
                       int color, ColorMode colorMode, boolean debug)
    {
        if (storage == null || storage.isResourceBlank() || storage.amount <= 0)
            return;

        FluidVariant fluidVariant = storage.getResource();
        long amount = storage.amount;
        long capacity = storage.getCapacity();
        float fillPercentage = (float) amount / capacity;

        if(debug)
            fillPercentage = (float) (Math.sin(world.getGameTime() / 64.0) * 0.5 + 0.5);

        int fluidColor = FluidVariantRendering.getColor(fluidVariant, world, pos);
        fluidColor = ColorMode.modifyColor(fluidColor, color, colorMode);

        TextureAtlasSprite stillSprite = FluidVariantRendering.getSprite(fluidVariant);
        if(stillSprite == null)
            return;

        RenderType renderLayer = RenderType.itemEntityTranslucentCull(stillSprite.atlasLocation());
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        float y2 = ((fillPercentage * maxHeight) / 16f) + y1;

        matrices.pushPose();

            //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

            if (!FluidVariantAttributes.isLighterThanAir(fluidVariant))
                matrices.mulPose(Axis.XP.rotationDegrees(180));

            PoseStack.Pose entry = matrices.last();

            int blockLight = (light >> 4) & 0xF;
            int luminosity = Math.max(blockLight, FluidVariantAttributes.getLuminance(fluidVariant));
            light = (light & 0xF00000) | (luminosity << 4);

            // Front (XY plane, z constant)
            drawTiledXYQuad(vertexConsumer, entry,
                            x1, y1, z1 + 0.001F,
                            x2, y2, z1 + 0.001F,
                            stillSprite, fluidColor, light, overlay, 0.0F, 1.0F, -1.0F);

            // Back (XY plane, z constant)
            drawReversedTiledXYQuad(vertexConsumer, entry,
                                    x1, y1, z2 - 0.001F,
                                    x2, y2, z2 - 0.001F,
                                    stillSprite, fluidColor, light, overlay, 0.0F, 1.0F, 1.0F);

            // Left (YZ plane, x constant)
            drawReversedTiledYZQuad(vertexConsumer, entry,
                                    x1 + 0.001F, y1, z1,
                                    y2, z2,
                                    stillSprite, fluidColor, light, overlay, 1.0F, 1.0F, 0.0F);

            // Right (YZ plane, x constant)
            drawTiledYZQuad(vertexConsumer, entry,
                            x2 - 0.001F, y1, z1,
                            y2, z2,
                            stillSprite, fluidColor, light, overlay, -1.0F, 1.0F, 0.0F);

            if (fillPercentage < 1.0F)
                drawTiledTopQuad(vertexConsumer, entry, x1, y2, z1 + 0.001F, x2, z2 - 0.001F, stillSprite, fluidColor, light, overlay);

        matrices.popPose();
    }

    public static void renderTopFaceOnly(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                                  Level world, BlockPos pos,
                                  float x1, float y, float z1,
                                  float x2, float z2)
    {
        renderTopFaceOnly(storage, vertexConsumers, matrices, light, overlay, world, pos, x1, y, z1, x2, z2, 0xFFFFFFFF, ColorMode.MULTIPLICATION);
    }

    public static void renderTopFaceOnly(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                                  Level world, BlockPos pos,
                                  float x1, float y, float z1,
                                  float x2, float z2,
                                  int color, ColorMode colorMode)
    {
        if(storage == null || storage.isResourceBlank() || storage.amount <= 0)
            return;

        FluidVariant fluidVariant = storage.getResource();

        if(fluidVariant == null)
            return;

        int fluidColor = FluidVariantRendering.getColor(fluidVariant, world, pos);
        fluidColor = ColorMode.modifyColor(fluidColor, color, colorMode);

        TextureAtlasSprite stillSprite = FluidVariantRendering.getSprite(fluidVariant);
        if(stillSprite == null)
            return;

        RenderType renderLayer = RenderType.itemEntityTranslucentCull(stillSprite.atlasLocation());
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        matrices.pushPose();

            //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

            if (!FluidVariantAttributes.isLighterThanAir(fluidVariant))
                matrices.mulPose(Axis.XP.rotationDegrees(180));

            PoseStack.Pose entry = matrices.last();

            int blockLight = (light >> 4) & 0xF;
            int luminosity = Math.max(blockLight, FluidVariantAttributes.getLuminance(fluidVariant));
            light = (light & 0xF00000) | (luminosity << 4);

            drawTiledTopQuad(vertexConsumer, entry, x1, y, z1 + 0.001F, x2, z2 - 0.001F, stillSprite, fluidColor, light, overlay);

        matrices.popPose();
    }

    public static void drawTiledTopQuad(VertexConsumer vertexConsumer,
                                        PoseStack.Pose entry,
                                        float x1, float y, float z1,
                                        float x2, float z2,
                                        TextureAtlasSprite sprite,
                                        int color, int light, int overlay)
    {
        //float tileSize = 1.0f; // Maximum tile size in world space
        int tileCountX = Math.max(1, Math.round((x2 - x1)));// / tileSize));
        int tileCountZ = Math.max(1, Math.round((z2 - z1)));// / tileSize));

        float tileWidth = (x2 - x1) / tileCountX;
        float tileDepth = (z2 - z1) / tileCountZ;

        float u0 = sprite.getU0();
        float v0 = sprite.getV0();
        float u1 = sprite.getU1();
        float v1 = sprite.getV1();

        float tileUSize = (u1 - u0);
        float tileVSize = (v1 - v0);

        for (int i = 0; i < tileCountX; i++) {
            for (int j = 0; j < tileCountZ; j++) {
                float xStart = x1 + i * tileWidth;
                float xEnd = xStart + tileWidth;
                float zStart = z1 + j * tileDepth;
                float zEnd = zStart + tileDepth;

                float uEnd = u0 + tileUSize;
                float vEnd = v0 + tileVSize;

                vertexConsumer.addVertex(entry, xStart, y, zStart)
                              .setColor(color)
                              .setUv(u0, v0)
                              .setLight(light)
                              .setOverlay(overlay)
                              .setNormal(0.0F, 1.0F, 0.0F);

                vertexConsumer.addVertex(entry, xStart, y, zEnd)
                              .setColor(color)
                              .setUv(u0, vEnd)
                              .setLight(light)
                              .setOverlay(overlay)
                              .setNormal(0.0F, 1.0F, 0.0F);

                vertexConsumer.addVertex(entry, xEnd, y, zEnd)
                              .setColor(color)
                              .setUv(uEnd, vEnd)
                              .setLight(light)
                              .setOverlay(overlay)
                              .setNormal(0.0F, 1.0F, 0.0F);

                vertexConsumer.addVertex(entry, xEnd, y, zStart)
                              .setColor(color)
                              .setUv(uEnd, v0)
                              .setLight(light)
                              .setOverlay(overlay)
                              .setNormal(entry, 0.0F, 1.0F, 0.0F);
            }
        }
    }

    public static void drawTiledXYQuadOnly(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                                    Level world, BlockPos pos,
                                    float x1, float y1, float z1,
                                    float x2, float y2, float z2)
    {
        drawTiledXYQuadOnly(storage, vertexConsumers, matrices, light, overlay, world, pos, x1, y1, z1, x2, y2, z2, 0xFFFFFFFF, ColorMode.MULTIPLICATION);
    }

    public static void drawTiledXYQuadOnly(@Nullable SingleFluidStorage storage, MultiBufferSource vertexConsumers, PoseStack matrices, int light, int overlay,
                                    Level world, BlockPos pos,
                                    float x1, float y1, float z1,
                                    float x2, float y2, float z2,
                                    int color, ColorMode colorMode)
    {
        if(storage == null || storage.isResourceBlank() || storage.amount <= 0)
            return;

        FluidVariant fluidVariant = storage.getResource();

        if (fluidVariant == null)
            return;

        int fluidColor = FluidVariantRendering.getColor(fluidVariant, world, pos);
        fluidColor = ColorMode.modifyColor(fluidColor, color, colorMode);

        TextureAtlasSprite stillSprite = FluidVariantRendering.getSprite(fluidVariant);
        if(stillSprite == null)
            return;

        RenderType renderLayer = RenderType.itemEntityTranslucentCull(stillSprite.atlasLocation());
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        matrices.pushPose();

            //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

            if (!FluidVariantAttributes.isLighterThanAir(fluidVariant))
                matrices.mulPose(Axis.XP.rotationDegrees(180));

            PoseStack.Pose entry = matrices.last();

            int blockLight = (light >> 4) & 0xF;
            int luminosity = Math.max(blockLight, FluidVariantAttributes.getLuminance(fluidVariant));
            light = (light & 0xF00000) | (luminosity << 4);

            drawTiledXYQuad(vertexConsumer, entry, x1, y1, z1, x2, y2, z2, stillSprite, fluidColor, light, overlay, 0.0F, 1.0F, -1.0F);

        matrices.popPose();
    }

    public static void drawTiledXYQuad(VertexConsumer vertexConsumer,
                                       PoseStack.Pose entry,
                                       float x1, float y1, float z1,
                                       float x2, float y2, float z2,
                                       TextureAtlasSprite sprite,
                                       int color,
                                       int light, int overlay,
                                       float nx, float ny, float nz)
    {
        float tileSize = 1.0f;
        int fullTilesX = (int) ((x2 - x1));// / tileSize);
        int fullTilesY = (int) ((y2 - y1));// / tileSize);
        float leftoverX = (x2 - x1) - (fullTilesX);// * tileSize);
        float leftoverY = (y2 - y1) - (fullTilesY);// * tileSize);

        // Draw full tiles
        for (int i = 0; i < fullTilesX; i++) {
            for (int j = 0; j < fullTilesY; j++) {
                float xStart = x1 + i;// * tileSize;
                float xEnd = xStart + tileSize;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU1();
                float v1 = sprite.getV1();
                drawQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in x
        if (leftoverX > 0) {
            for (int j = 0; j < fullTilesY; j++) {
                float xStart = x1 + fullTilesX;// * tileSize;
                float xEnd = xStart + leftoverX;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverX);
                float v1 = sprite.getV(tileSize);
                drawQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in y
        if (leftoverY > 0) {
            for (int i = 0; i < fullTilesX; i++) {
                float xStart = x1 + i;// * tileSize;
                float xEnd = xStart + tileSize;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(tileSize);
                float v1 = sprite.getV(leftoverY);
                drawQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }

            // Draw the corner leftover tile if both leftoverX and leftoverY > 0
            if (leftoverX > 0) {
                float xStart = x1 + fullTilesX;// * tileSize;
                float xEnd = xStart + leftoverX;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverX);
                float v1 = sprite.getV(leftoverY);
                drawQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }
    }

    public static void drawTiledYZQuad(VertexConsumer vertexConsumer,
                                       PoseStack.Pose entry,
                                       float x, float y1, float z1,
                                       float y2, float z2,
                                       TextureAtlasSprite sprite,
                                       int color,
                                       int light, int overlay,
                                       float nx, float ny, float nz)
    {
        float tileSize = 1.0f;
        int fullTilesZ = (int) ((z2 - z1));// / tileSize);
        int fullTilesY = (int) ((y2 - y1));// / tileSize);
        float leftoverZ = (z2 - z1) - (fullTilesZ);// * tileSize);
        float leftoverY = (y2 - y1) - (fullTilesY);// * tileSize);

        // Draw full tiles
        for (int i = 0; i < fullTilesZ; i++) {
            for (int j = 0; j < fullTilesY; j++) {
                float zStart = z1 + i;// * tileSize;
                float zEnd = zStart + tileSize;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU1();
                float v1 = sprite.getV1();
                drawQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in z
        if (leftoverZ > 0) {
            for (int j = 0; j < fullTilesY; j++) {
                float zStart = z1 + fullTilesZ;// * tileSize;
                float zEnd = zStart + leftoverZ;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverZ);
                float v1 = sprite.getV(tileSize);
                drawQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in y
        if (leftoverY > 0) {
            for (int i = 0; i < fullTilesZ; i++) {
                float zStart = z1 + i;// * tileSize;
                float zEnd = zStart + tileSize;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(tileSize);
                float v1 = sprite.getV(leftoverY);
                drawQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }

            // Draw the corner leftover tile if both leftoverZ and leftoverY > 0
            if (leftoverZ > 0) {
                float zStart = z1 + fullTilesZ;// * tileSize;
                float zEnd = zStart + leftoverZ;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverZ);
                float v1 = sprite.getV(leftoverY);
                drawQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }
    }

    private static void drawQuad(VertexConsumer vertexConsumer,
                                 PoseStack.Pose entry,
                                 float x1, float y1, float z1,
                                 float x2, float y2, float z2,
                                 float minU, float minV,
                                 float maxU, float maxV,
                                 int color,
                                 int light, int overlay,
                                 float normalX, float normalY, float normalZ)
    {
        vertexConsumer.addVertex(entry, x1, y1, z1)
                      .setColor(color)
                      .setUv(minU, minV)
                      .setLight(light)
                      .setOverlay(overlay)
                      .setNormal(normalX, normalY, normalZ);

        vertexConsumer.addVertex(entry, x1, y2, z1)
                      .setColor(color)
                      .setUv(minU, maxV)
                      .setLight(light)
                      .setOverlay(overlay)
                      .setNormal(normalX, normalY, normalZ);

        vertexConsumer.addVertex(entry, x2, y2, z2)
                      .setColor(color)
                      .setUv(maxU, maxV)
                      .setLight(light)
                      .setOverlay(overlay)
                      .setNormal(normalX, normalY, normalZ);

        vertexConsumer.addVertex(entry, x2, y1, z2)
                      .setColor(color)
                      .setUv(maxU, minV)
                      .setLight(light)
                      .setOverlay(overlay)
                      .setNormal(normalX, normalY, normalZ);
    }

    private static void drawReversedQuad(VertexConsumer vertexConsumer,
                                         PoseStack.Pose entry,
                                         float x1, float y1, float z1,
                                         float x2, float y2, float z2,
                                         float minU, float minV,
                                         float maxU, float maxV,
                                         int color,
                                         int light, int overlay,
                                         float normalX, float normalY, float normalZ)
    {
        // Vertex 4: (x2, y1, z2) with (maxU, minV)
        vertexConsumer.addVertex(entry, x2, y1, z2).setColor(color).setUv(maxU, minV).setLight(light).setOverlay(overlay).setNormal(normalX, normalY, normalZ);
        // Vertex 3: (x2, y2, z2) with (maxU, maxV)
        vertexConsumer.addVertex(entry, x2, y2, z2).setColor(color).setUv(maxU, maxV).setLight(light).setOverlay(overlay).setNormal(normalX, normalY, normalZ);
        // Vertex 2: (x1, y2, z1) with (minU, maxV)
        vertexConsumer.addVertex(entry, x1, y2, z1).setColor(color).setUv(minU, maxV).setLight(light).setOverlay(overlay).setNormal(normalX, normalY, normalZ);
        // Vertex 1: (x1, y1, z1) with (minU, minV)
        vertexConsumer.addVertex(entry, x1, y1, z1).setColor(color).setUv(minU, minV).setLight(light).setOverlay(overlay).setNormal(normalX, normalY, normalZ);
    }

    private static void drawReversedTiledXYQuad(VertexConsumer vertexConsumer,
                                                PoseStack.Pose entry,
                                                float x1, float y1, float z1,
                                                float x2, float y2, float z2,
                                                TextureAtlasSprite sprite,
                                                int color,
                                                int light, int overlay,
                                                float nx, float ny, float nz) {
        float tileSize = 1.0f;
        int fullTilesX = (int) ((x2 - x1));// / tileSize);
        int fullTilesY = (int) ((y2 - y1));// / tileSize);
        float leftoverX = (x2 - x1) - (fullTilesX);// * tileSize);
        float leftoverY = (y2 - y1) - (fullTilesY);// * tileSize);

        // Draw full tiles
        for (int i = 0; i < fullTilesX; i++) {
            for (int j = 0; j < fullTilesY; j++) {
                float xStart = x1 + i;// * tileSize;
                float xEnd = xStart + tileSize;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU1();
                float v1 = sprite.getV1();
                drawReversedQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in x
        if (leftoverX > 0) {
            for (int j = 0; j < fullTilesY; j++) {
                float xStart = x1 + fullTilesX;// * tileSize;
                float xEnd = xStart + leftoverX;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverX);
                float v1 = sprite.getV(tileSize);
                drawReversedQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in y
        if (leftoverY > 0) {
            for (int i = 0; i < fullTilesX; i++) {
                float xStart = x1 + i;// * tileSize;
                float xEnd = xStart + tileSize;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(tileSize);
                float v1 = sprite.getV(leftoverY);
                drawReversedQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }

            // Draw the corner leftover tile if both leftoverX and leftoverY > 0
            if (leftoverX > 0) {
                float xStart = x1 + fullTilesX;// * tileSize;
                float xEnd = xStart + leftoverX;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverX);
                float v1 = sprite.getV(leftoverY);
                drawReversedQuad(vertexConsumer, entry, xStart, yStart, z1, xEnd, yEnd, z2, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }
    }

    private static void drawReversedTiledYZQuad(VertexConsumer vertexConsumer,
                                                PoseStack.Pose entry,
                                                float x, float y1, float z1,
                                                float y2, float z2,
                                                TextureAtlasSprite sprite,
                                                int color,
                                                int light, int overlay,
                                                float nx, float ny, float nz) {
        float tileSize = 1.0f;
        int fullTilesZ = (int) ((z2 - z1));// / tileSize);
        int fullTilesY = (int) ((y2 - y1));// / tileSize);
        float leftoverZ = (z2 - z1) - (fullTilesZ);// * tileSize);
        float leftoverY = (y2 - y1) - (fullTilesY);// * tileSize);

        // Draw full tiles
        for (int i = 0; i < fullTilesZ; i++) {
            for (int j = 0; j < fullTilesY; j++) {
                float zStart = z1 + i;// * tileSize;
                float zEnd = zStart + tileSize;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU1();
                float v1 = sprite.getV1();
                drawReversedQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in z
        if (leftoverZ > 0) {
            for (int j = 0; j < fullTilesY; j++) {
                float zStart = z1 + fullTilesZ;// * tileSize;
                float zEnd = zStart + leftoverZ;
                float yStart = y1 + j;// * tileSize;
                float yEnd = yStart + tileSize;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverZ);
                float v1 = sprite.getV(tileSize);
                drawReversedQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }

        // Draw leftover tiles in y
        if (leftoverY > 0) {
            for (int i = 0; i < fullTilesZ; i++) {
                float zStart = z1 + i;// * tileSize;
                float zEnd = zStart + tileSize;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(tileSize);
                float v1 = sprite.getV(leftoverY);
                drawReversedQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }

            // Draw the corner leftover tile if both leftoverZ and leftoverY > 0
            if (leftoverZ > 0) {
                float zStart = z1 + fullTilesZ;// * tileSize;
                float zEnd = zStart + leftoverZ;
                float yStart = y1 + fullTilesY;// * tileSize;
                float yEnd = yStart + leftoverY;
                float u0 = sprite.getU0();
                float v0 = sprite.getV0();
                float u1 = sprite.getU(leftoverZ);
                float v1 = sprite.getV(leftoverY);
                drawReversedQuad(vertexConsumer, entry, x, yStart, zStart, x, yEnd, zEnd, u0, v0, u1, v1, color, light, overlay, nx, ny, nz);
            }
        }
    }*/
}