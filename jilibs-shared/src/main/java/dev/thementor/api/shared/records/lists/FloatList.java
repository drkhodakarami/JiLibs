package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.FloatPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record FloatList(List<FloatPayload> values)
{
    public static Codec<FloatList> CODEC = FloatPayload.LIST_CODEC.xmap(FloatList::new, FloatList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, FloatList> STREAM_CODEC =
            FloatPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(FloatList::new, FloatList::values);
    public static final FloatList EMPTY = new FloatList(List.of());
}