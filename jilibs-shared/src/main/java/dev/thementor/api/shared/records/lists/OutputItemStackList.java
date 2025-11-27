package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.OutputItemStackPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record OutputItemStackList(List<OutputItemStackPayload> values)
{
    public static Codec<OutputItemStackList> CODEC = OutputItemStackPayload.LIST_CODEC.xmap(OutputItemStackList::new, OutputItemStackList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, OutputItemStackList> STREAM_CODEC =
            OutputItemStackPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(OutputItemStackList::new, OutputItemStackList::values);
    public static final OutputItemStackList EMPTY = new OutputItemStackList(List.of());
}