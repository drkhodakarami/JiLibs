package dev.thementor.api.base;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

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
public class JiBlockEntityRenderer<T extends BlockEntity, U extends JiBlockEntityRS> implements BlockEntityRenderer<T, U>
{
    private final ItemModelManager itemModelManager;
    private final EntityRenderManager entityRenderManager;
    protected Entity displayEntity;

    private final Supplier<U> renderStateFactory;

    public JiBlockEntityRenderer(BlockEntityRendererFactory.Context context, Supplier<U> renderStateFactory)
    {
        itemModelManager = context.itemModelManager();
        entityRenderManager = context.entityRenderDispatcher();

        this.renderStateFactory = renderStateFactory;
    }

    @Override
    public U createRenderState()
    {
        return renderStateFactory.get();
    }

    @Override
    public void updateRenderState(T blockEntity, U state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay)
    {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.position = blockEntity.getPos();
        state.world = blockEntity.getWorld();

        handleItemstack(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        handleEntity(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
    }

    @Override
    public void render(U state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState)
    {
        if (shouldRenderItem())
            renderItemStack(matrices, queue, state.itemRenderState, cameraState);

        if (shouldRenderEntities() && displayEntity != null)
            renderDisplayEntities(matrices, queue, state.displayEntityRenderState, cameraState);
    }

    private void updateEntityManager(float tickProgress)
    {
        entityRenderManager.getAndUpdateRenderState(displayEntity, tickProgress);
    }

    private void updateItemModelManager(T blockEntity, U state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay)
    {
        ItemStack stack = getItemStack(blockEntity);
        if(stack != null && !stack.isEmpty())
            itemModelManager.clearAndUpdate(state.itemRenderState, getItemStack(blockEntity), ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
    }

    protected void handleItemstack(T blockEntity, U state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay)
    {
        if (shouldRenderItem())
            updateItemModelManager(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
    }

    protected void handleEntity(T blockEntity, U state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay)
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
    protected Entity getDisplayEntities(World world)
    {
        return null;
    }

    protected void renderDisplayEntities(MatrixStack matrices, OrderedRenderCommandQueue queue, EntityRenderState state, CameraRenderState cameraRenderState)
    {}

    protected void renderItemStack(MatrixStack matrices, OrderedRenderCommandQueue queue, ItemRenderState state, CameraRenderState cameraRenderState)
    {}
}