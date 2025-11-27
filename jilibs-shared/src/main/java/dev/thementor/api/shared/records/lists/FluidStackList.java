package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.FluidStackPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record FluidStackList(List<FluidStackPayload> values)
{
    public static Codec<FluidStackList> CODEC = FluidStackPayload.LIST_CODEC.xmap(FluidStackList::new, FluidStackList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, FluidStackList> STREAM_CODEC =
            FluidStackPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(FluidStackList::new, FluidStackList::values);
    public static final FluidStackList EMPTY = new FluidStackList(List.of());
}