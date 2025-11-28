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
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

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

public record FloatPayload(float value) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<FloatPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath("jilibs_shared", "float_payload"));

    /**
     * The codec used to serialize and deserialize the FloatPayload.
     */
    public static final Codec<FloatPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.FLOAT.fieldOf("value").forGetter(FloatPayload::value)
    ).apply(inst, FloatPayload::new));

    /**
     * The packet codec used to send and receive the FloatPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, FloatPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.FLOAT, FloatPayload::value, FloatPayload::new);

    public static final Codec<List<FloatPayload>> LIST_CODEC = CODEC.listOf();

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