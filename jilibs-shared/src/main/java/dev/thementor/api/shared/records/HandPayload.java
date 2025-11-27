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
import java.util.Objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a custom payload containing a player hand (e.g., main hand, off hand).
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record HandPayload(InteractionHand hand) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<HandPayload> ID = new Type<>(ResourceLocation.fromNamespaceAndPath("jilibs_shared", "hand_payload"));

    /**
     * The codec used to serialize and deserialize the HandPayload.
     */
    public static final Codec<HandPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.xmap(str -> Objects.requireNonNull(InteractionHand.valueOf(str)), InteractionHand::name)
                        .fieldOf("hand").forGetter(HandPayload::hand)
    ).apply(inst, HandPayload::new));

    /**
     * The packet codec used to send and receive the HandPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, HandPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.STRING_UTF8,
                              payload -> payload.hand().name(),
                              handStr -> new HandPayload(InteractionHand.valueOf(handStr)));

    public static final Codec<List<HandPayload>> LIST_CODEC = CODEC.listOf();

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