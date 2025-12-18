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

package dev.thementor.api.base.client;

import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

/**
 * Use Case:
 * <pre>
 * {@code
 * public class CustomBERenderer extends JiBlockEntityRenderer<CustomBE, CustomBERS>
 * {
 *      public CustomBERenderer(IBlockEntityRendererFactory.Context context)
 *      {
 *          //Pass the method reference to constructor of CustomBERS
 *          super(context, CustomBERS::new);
 *      }
 * }
 * }
 * </pre>
 * @param <T>
 * @param <U>
 */
@SuppressWarnings("unused")
public abstract class AbstractBaseBER<T extends BlockEntity, U extends BaseBERS> implements BlockEntityRenderer<T, U>
{
    private final ItemModelResolver itemModelManager;
    private final EntityRenderDispatcher entityRenderManager;
    protected Entity displayEntity;

    private final Supplier<U> renderStateFactory;

    public AbstractBaseBER(BlockEntityRendererProvider.Context context, Supplier<U> renderStateFactory)
    {
        itemModelManager = context.itemModelResolver();
        entityRenderManager = context.entityRenderer();

        this.renderStateFactory = renderStateFactory;
    }

    @Override
    public @NotNull U createRenderState()
    {
        return renderStateFactory.get();
    }

    @Override
    public void extractRenderState(T blockEntity, U state, float tickProgress, @NotNull Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay)
    {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.blockPos = blockEntity.getBlockPos();
        state.world = blockEntity.getLevel();

        handleItemstack(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        handleEntity(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
    }

    @Override
    public void submit(U state, @NotNull PoseStack matrices, @NotNull SubmitNodeCollector queue, @NotNull CameraRenderState cameraState)
    {
        if (shouldRenderItem())
            renderItemStack(matrices, queue, state.itemRenderState, cameraState);

        if (shouldRenderEntities() && displayEntity != null)
            renderDisplayEntities(matrices, queue, state.displayEntityRenderState, cameraState);
    }

    private void updateEntityManager(float tickProgress)
    {
        entityRenderManager.extractEntity(displayEntity, tickProgress);
    }

    private void updateItemModelManager(T blockEntity, U state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay)
    {
        ItemStack stack = getItemStack(blockEntity);
        if(stack != null && !stack.isEmpty())
            itemModelManager.updateForTopItem(state.itemRenderState, getItemStack(blockEntity), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    protected void handleItemstack(T blockEntity, U state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay)
    {
        if (shouldRenderItem())
            updateItemModelManager(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
    }

    protected void handleEntity(T blockEntity, U state, float tickProgress, Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay)
    {
        if (shouldRenderEntities())
        {
            if (displayEntity == null)
                displayEntity = getDisplayEntities(state.world);

            if (displayEntity != null)
                updateEntityManager(tickProgress);
        }
    }

    protected boolean shouldRenderItem()
    {
        return false;
    }

    protected boolean shouldRenderEntities()
    {
        return false;
    }

    protected ItemStack getItemStack(T blockEntity)
    {
        return null;
    }

    // new EndermanEntity(EntityType.ENDERMAN, world)
    protected Entity getDisplayEntities(Level world)
    {
        return null;
    }

    protected void renderDisplayEntities(PoseStack matrices, SubmitNodeCollector queue, EntityRenderState state, CameraRenderState cameraRenderState)
    {}

    protected void renderItemStack(PoseStack matrices, SubmitNodeCollector queue, ItemStackRenderState state, CameraRenderState cameraRenderState)
    {}
}