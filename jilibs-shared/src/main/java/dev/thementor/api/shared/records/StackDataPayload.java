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

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a custom payload containing stack data, including the count of items and component changes.
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record StackDataPayload(int count, DataComponentPatch components) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<@NotNull IntegerPayload> ID = new Type<>(Identifier.fromNamespaceAndPath("jilibs_shared", "component_changes_stack_payload"));

    /**
     * The codec used to serialize and deserialize the StackDataPayload.
     */
    public static final Codec<StackDataPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("count").forGetter(StackDataPayload::count),
            DataComponentPatch.CODEC.fieldOf("components").forGetter(StackDataPayload::components)
    ).apply(inst, StackDataPayload::new));

    /**
     * The packet codec used to send and receive the StackDataPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, StackDataPayload> STREAM_CODEC =
            StreamCodec.composite(ByteBufCodecs.INT, StackDataPayload::count,
                              DataComponentPatch.STREAM_CODEC, StackDataPayload::components,
                              StackDataPayload::new);

    public static final Codec<List<StackDataPayload>> LIST_CODEC = CODEC.listOf();

    /**
     * An empty instance of StackDataPayload with a count of 1 and no component changes.
     */
    public static StackDataPayload EMPTY = new StackDataPayload(1, DataComponentPatch.EMPTY);

    /**
     * Creates a new {@link StackDataPayload} with the specified count and default component changes.
     *
     * @param count The count of items in the stack.
     * @return A new instance of StackDataPayload with the given count and default component changes.
     */
    public static StackDataPayload create(int count)
    {
        return new StackDataPayload(count, DataComponentPatch.EMPTY);
    }

    /**
     * Creates a new {@link StackDataPayload} with the specified count and component changes.
     *
     * @param count      The count of items in the stack.
     * @param components The component changes associated with the stack.
     * @return A new instance of StackDataPayload with the given count and component changes.
     */
    public static StackDataPayload create(int count, DataComponentPatch components)
    {
        return new StackDataPayload(count, components);
    }

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