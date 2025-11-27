package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.IntegerPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record IntegerList(List<IntegerPayload> values)
{
    public static Codec<IntegerList> CODEC = IntegerPayload.LIST_CODEC.xmap(IntegerList::new, IntegerList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, IntegerList> STREAM_CODEC =
            IntegerPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(IntegerList::new, IntegerList::values);
    public static final IntegerList EMPTY = new IntegerList(List.of());
}