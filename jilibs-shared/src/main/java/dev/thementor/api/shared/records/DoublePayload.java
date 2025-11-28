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
 * Represents a custom payload containing a double value.
 *
 * @param value the double value contained in this payload
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public record DoublePayload(double value) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<DoublePayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath("jilibs_shared", "double_payload"));

    /**
     * The codec used to serialize and deserialize the DoublePayload.
     */
    public static final Codec<DoublePayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.DOUBLE.fieldOf("value").forGetter(DoublePayload::value)
    ).apply(inst, DoublePayload::new));

    /**
     * The packet codec used to send and receive the DoublePayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, DoublePayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.DOUBLE, DoublePayload::value, DoublePayload::new);

    public static final Codec<List<DoublePayload>> LIST_CODEC = CODEC.listOf();

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