package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.ItemStackPayload;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record ItemStackList(List<ItemStackPayload> values)
{
    public static Codec<ItemStackList> CODEC = ItemStackPayload.LIST_CODEC.xmap(ItemStackList::new, ItemStackList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, ItemStackList> STREAM_CODEC =
            ItemStackPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(ItemStackList::new, ItemStackList::values);
    public static final ItemStackList EMPTY = new ItemStackList(List.of());
}