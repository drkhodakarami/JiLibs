package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.HandPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record HandList(List<HandPayload> values)
{
    public static Codec<HandList> CODEC = HandPayload.LIST_CODEC.xmap(HandList::new, HandList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, HandList> STREAM_CODEC =
            HandPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(HandList::new, HandList::values);
    public static final HandList EMPTY = new HandList(List.of());
}