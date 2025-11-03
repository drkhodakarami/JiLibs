/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package dev.thementor.api.shared.records;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.component.ComponentChanges;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Represents a custom payload containing stack data, including the count of items and component changes.
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record StackDataPayload(int count, ComponentChanges components) implements CustomPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Id<IntegerPayload> ID = new Id<>(Identifier.of("jiralib", "component_changes_stack_payload"));

    /**
     * The codec used to serialize and deserialize the StackDataPayload.
     */
    public static final Codec<StackDataPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.INT.fieldOf("count").forGetter(StackDataPayload::count),
            ComponentChanges.CODEC.fieldOf("components").forGetter(StackDataPayload::components)
    ).apply(inst, StackDataPayload::new));

    /**
     * The packet codec used to send and receive the StackDataPayload.
     */
    public static final PacketCodec<RegistryByteBuf, StackDataPayload> PACKET_CODEC =
            PacketCodec.tuple(PacketCodecs.INTEGER, StackDataPayload::count,
                              ComponentChanges.PACKET_CODEC, StackDataPayload::components,
                              StackDataPayload::new);

    /**
     * An empty instance of StackDataPayload with a count of 1 and no component changes.
     */
    public static StackDataPayload EMPTY = new StackDataPayload(1, ComponentChanges.EMPTY);

    /**
     * Creates a new {@link StackDataPayload} with the specified count and default component changes.
     *
     * @param count The count of items in the stack.
     * @return A new instance of StackDataPayload with the given count and default component changes.
     */
    public static StackDataPayload create(int count)
    {
        return new StackDataPayload(count, ComponentChanges.EMPTY);
    }

    /**
     * Creates a new {@link StackDataPayload} with the specified count and component changes.
     *
     * @param count      The count of items in the stack.
     * @param components The component changes associated with the stack.
     * @return A new instance of StackDataPayload with the given count and component changes.
     */
    public static StackDataPayload create(int count, ComponentChanges components)
    {
        return new StackDataPayload(count, components);
    }

    /**
     * Retrieves the unique identifier for this custom payload.
     *
     * @return the unique identifier
     */
    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}