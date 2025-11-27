package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.DoublePayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record DoubleList(List<DoublePayload> values)
{
    public static Codec<DoubleList> CODEC = DoublePayload.LIST_CODEC.xmap(DoubleList::new, DoubleList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, DoubleList> STREAM_CODEC =
            DoublePayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(DoubleList::new, DoubleList::values);
    public static final DoubleList EMPTY = new DoubleList(List.of());
}