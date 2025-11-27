package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.LongPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record LongList(List<LongPayload> values)
{
    public static Codec<LongList> CODEC = LongPayload.LIST_CODEC.xmap(LongList::new, LongList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, LongList> STREAM_CODEC =
            LongPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(LongList::new, LongList::values);
    public static final LongList EMPTY = new LongList(List.of());
}