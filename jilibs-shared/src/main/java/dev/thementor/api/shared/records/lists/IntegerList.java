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

import dev.thementor.api.shared.records.IntegerPayload;

public record IntegerList(List<IntegerPayload> values)
{
    public static Codec<IntegerList> CODEC = IntegerPayload.LIST_CODEC.xmap(IntegerList::new, IntegerList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, IntegerList> STREAM_CODEC =
            IntegerPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(IntegerList::new, IntegerList::values);
    public static final IntegerList EMPTY = new IntegerList(List.of());
}