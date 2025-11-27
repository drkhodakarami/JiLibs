package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.StringPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record StringList(List<StringPayload> values)
{
    public static Codec<StringList> CODEC = StringPayload.LIST_CODEC.xmap(StringList::new, StringList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, StringList> STREAM_CODEC =
            StringPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(StringList::new, StringList::values);
    public static final StringList EMPTY = new StringList(List.of());
}