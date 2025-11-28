/*
 * Copyright (c) 2025 Alireza Khodakarami
 *
 * Licensed under the MIT, (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/license/mit
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.thementor.api.shared.records.lists;

import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import dev.thementor.api.shared.records.ConfiguredIngredient;

public record ConfiguredIngredientList(List<ConfiguredIngredient> values)
{
    public static Codec<ConfiguredIngredientList> CODEC = ConfiguredIngredient.LIST_CODEC.xmap(ConfiguredIngredientList::new, ConfiguredIngredientList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, ConfiguredIngredientList> STREAM_CODEC =
            ConfiguredIngredient.STREAM_CODEC.apply(ByteBufCodecs.list())
                                             .map(ConfiguredIngredientList::new, ConfiguredIngredientList::values);
    public static final ConfiguredIngredientList EMPTY = new ConfiguredIngredientList(List.of());
}