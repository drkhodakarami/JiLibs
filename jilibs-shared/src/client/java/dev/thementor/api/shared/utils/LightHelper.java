package dev.thementor.api.shared.utils;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class LightHelper
{
    public static int getLightLevel(World world, BlockPos pos)
    {
        int block_level = world.getLightLevel(LightType.BLOCK, pos);
        int sky_level = world.getLightLevel(LightType.SKY, pos);

        return LightmapTextureManager.pack(block_level, sky_level);
    }
}