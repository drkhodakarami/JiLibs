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

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a custom payload containing a float value.
 *
 * @param value the float value contained in this payload
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public record LongPayload(long value) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<@NotNull LongPayload> ID = new Type<>(Identifier.fromNamespaceAndPath("jilibs_shared", "long_payload"));

    /**
     * The codec used to serialize and deserialize the FloatPayload.
     */
    public static final Codec<LongPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.LONG.fieldOf("value").forGetter(LongPayload::value)
    ).apply(inst, LongPayload::new));

    /**
     * The packet codec used to send and receive the FloatPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, LongPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.LONG, LongPayload::value, LongPayload::new);

    public static final Codec<List<LongPayload>> LIST_CODEC = CODEC.listOf();

    /**
     * Retrieves the unique identifier for this custom payload.
     *
     * @return the unique identifier
     */
    @Override
    public @NotNull Type<? extends @NotNull CustomPacketPayload> type()
    {
        return ID;
    }
}