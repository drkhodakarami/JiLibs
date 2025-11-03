package dev.thementor.api.base;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JiBlockEntityRS extends BlockEntityRenderState
{
    public BlockPos position;
    public World world;

    public ItemRenderState itemRenderState = new ItemRenderState();
    public EntityRenderState displayEntityRenderState = new EntityRenderState();
}