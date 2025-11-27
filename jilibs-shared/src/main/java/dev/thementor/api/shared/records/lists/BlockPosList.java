package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.BlockPosPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record BlockPosList(List<BlockPosPayload> values)
{
    public static Codec<BlockPosList> CODEC = BlockPosPayload.LIST_CODEC.xmap(BlockPosList::new, BlockPosList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, BlockPosList> STREAM_CODEC =
            BlockPosPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                        .map(BlockPosList::new, BlockPosList::values);
    public static final BlockPosList EMPTY = new BlockPosList(List.of());
}