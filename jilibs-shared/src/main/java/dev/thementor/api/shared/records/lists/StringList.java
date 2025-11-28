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

import dev.thementor.api.shared.records.StringPayload;

public record StringList(List<StringPayload> values)
{
    public static Codec<StringList> CODEC = StringPayload.LIST_CODEC.xmap(StringList::new, StringList::values);
    public static StreamCodec<RegistryFriendlyByteBuf, StringList> STREAM_CODEC =
            StringPayload.STREAM_CODEC.apply(ByteBufCodecs.list())
                                          .map(StringList::new, StringList::values);
    public static final StringList EMPTY = new StringList(List.of());
}