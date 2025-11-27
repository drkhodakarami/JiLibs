package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.StackDataPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record StackDataList(List<StackDataPayload> values)
{
    public static Codec<StackDataList> CODEC = StackDataPayload.LIST_CODEC.xmap(StackDataList::new, StackDataList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, StackDataList> STREAM_CODEC =
            StackDataPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(StackDataList::new, StackDataList::values);
    public static final StackDataList EMPTY = new StackDataList(List.of());
}