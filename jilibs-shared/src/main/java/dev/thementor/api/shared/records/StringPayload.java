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

package dev.thementor.api.shared.records;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import dev.thementor.api.shared.annotations.*;

import java.util.List;

/**
 * Represents a custom payload containing a string value.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public record StringPayload(String value) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<StringPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath("jilibs_shared", "string_payload"));

    /**
     * The codec used to serialize and deserialize the StringPayload.
     */
    public static final Codec<StringPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.fieldOf("value").forGetter(StringPayload::value)
    ).apply(inst, StringPayload::new));

    /**
     * The packet codec used to send and receive the StringPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, StringPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8, StringPayload::value, StringPayload::new);

    public static final Codec<List<StringPayload>> LIST_CODEC = CODEC.listOf();

    /**
     * Retrieves the unique identifier for this custom payload.
     *
     * @return the unique identifier
     */
    @Override
    public @NotNull Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}