package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.CoordinateDataPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record CoordinateDataList(List<CoordinateDataPayload> values)
{
    public static Codec<CoordinateDataList> CODEC = CoordinateDataPayload.LIST_CODEC.xmap(CoordinateDataList::new, CoordinateDataList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, CoordinateDataList> STREAM_CODEC =
            CoordinateDataPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                              .map(CoordinateDataList::new, CoordinateDataList::values);
    public static final CoordinateDataList EMPTY = new CoordinateDataList(List.of());
}