package dev.thementor.api.shared.records.lists;

import com.mojang.serialization.Codec;
import dev.thementor.api.shared.records.ConfiguredIngredient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record ConfiguredIngredientList(List<ConfiguredIngredient> values)
{
    public static Codec<ConfiguredIngredientList> CODEC = ConfiguredIngredient.LIST_CODEC.xmap(ConfiguredIngredientList::new, ConfiguredIngredientList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, ConfiguredIngredientList> STREAM_CODEC =
            ConfiguredIngredient.STREAM_CODEC.apply(ByteBufCodecs.list())
                                             .map(ConfiguredIngredientList::new, ConfiguredIngredientList::values);
    public static final ConfiguredIngredientList EMPTY = new ConfiguredIngredientList(List.of());
}