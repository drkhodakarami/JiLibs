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
import java.util.stream.IntStream;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviderType;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.network.ExtraPacketCodecs;

/**
 * Represents a custom payload containing output information for an item stack, including the item, count provider, and chance.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record OutputItemStackPayload(Item output, IntProvider count, FloatProvider chance) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<@NotNull BlockPosPayload> ID = new Type<>(Identifier.fromNamespaceAndPath("jilibs_shared", "output_item_stack_payload"));

    /**
     * The default chance value (1.0f).
     */
    public static final ConstantFloat DEFAULT_CHANCE = ConstantFloat.of(1.0f);

    /**
     * An empty instance of OutputItemStackPayload.
     */
    public static final OutputItemStackPayload EMPTY = new OutputItemStackPayload(ItemStack.EMPTY);

    /**
     * The codec used to serialize and deserialize the OutputItemStackPayload.
     */
    public static final Codec<OutputItemStackPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("output").forGetter(OutputItemStackPayload::output),
            IntProvider.CODEC.fieldOf("count").forGetter(OutputItemStackPayload::count),
            FloatProvider.CODEC.fieldOf("chance").forGetter(OutputItemStackPayload::chance)
    ).apply(inst, OutputItemStackPayload::new));

    /**
     * The packet codec used to send and receive the OutputItemStackPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, OutputItemStackPayload> STREAM_CODEC =
            StreamCodec.of(OutputItemStackPayload::encode, OutputItemStackPayload::decode);

    public static final Codec<List<OutputItemStackPayload>> LIST_CODEC = CODEC.listOf();

    /**
     * Constructs a new {@link OutputItemStackPayload} and performs necessary validations.
     *
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public OutputItemStackPayload
    {
        if(output == null)
            throw new IllegalArgumentException("Item can't be null");
        if(count == null)
            throw new IllegalArgumentException("Count can't be null");
        if(chance == null)
            throw new IllegalArgumentException("Chance can't be null");
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the specified item, count, and chance.
     *
     * @param output The item to be included in the payload.
     * @param count  The count of items.
     * @param chance The chance of the item being generated.
     */
    public OutputItemStackPayload(Item output, int count, float chance)
    {
        this(output, ConstantInt.of(count), ConstantFloat.of(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack}.
     *
     * @param stack The {@link ItemStack} to be converted into the payload.
     * @throws IllegalArgumentException if the stack is null or its item is null
     */
    public OutputItemStackPayload(ItemStack stack)
    {
        this(stack.getItem(), ConstantInt.of(stack.getCount()), DEFAULT_CHANCE);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and count.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param count  The count provider for the items, cannot be null or provide zero or negative values.
     * @param chance The chance of the item appearing, cannot be null or less than 0.
     */
    public OutputItemStackPayload(Item output, IntProvider count, float chance)
    {
        this(output, count, ConstantFloat.of(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and count.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param count  The count of items, cannot be zero or negative.
     * @param chance The chance provider for the item appearing, cannot be null.
     */
    public OutputItemStackPayload(Item output, int count, FloatProvider chance)
    {
        this(output, ConstantInt.of(count), chance);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and count.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param count  The count of items, cannot be zero or negative.
     */
    public OutputItemStackPayload(Item output, int count)
    {
        this(output, ConstantInt.of(count), DEFAULT_CHANCE);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and count provider.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param count  The count provider for the items, cannot be null or provide zero or negative values.
     */
    public OutputItemStackPayload(Item output, IntProvider count)
    {
        this(output, count, DEFAULT_CHANCE);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and chance.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param chance The chance of the item appearing, cannot be null or less than 0.
     */
    public OutputItemStackPayload(Item output, float chance)
    {
        this(output, ConstantInt.of(1), ConstantFloat.of(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and chance provider.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param chance The chance provider for the item appearing, cannot be null.
     */
    public OutputItemStackPayload(Item output, FloatProvider chance)
    {
        this(output, ConstantInt.of(1), chance);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item.
     *
     * @param output The item to be included in the payload, cannot be null.
     */
    public OutputItemStackPayload(Item output)
    {
        this(output, ConstantInt.of(1), DEFAULT_CHANCE);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack} and chance.
     *
     * @param stack  The {@link ItemStack} to be converted into the payload, cannot be null or empty.
     * @param chance The chance of the item appearing, cannot be null or less than 0.
     */
    public OutputItemStackPayload(ItemStack stack, float chance)
    {
        this(stack.getItem(), ConstantInt.of(stack.getCount()), ConstantFloat.of(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack} and chance provider.
     *
     * @param stack  The {@link ItemStack} to be converted into the payload, cannot be null or empty.
     * @param chance The chance provider for the item appearing, cannot be null.
     */
    public OutputItemStackPayload(ItemStack stack, FloatProvider chance)
    {
        this(stack.getItem(), ConstantInt.of(stack.getCount()), chance);
    }

    /**
     * Represents a payload for an output item stack with configurable properties.
     */
    public ItemStack createStack(RandomSource random)
    {
        return this.chance.sample(random) < random.nextFloat()
               ? ItemStack.EMPTY
               : new ItemStack(this.output, this.count.sample(random));
    }

    /**
     * Converts this payload to a display representation of multiple item stacks.
     *
     * @return A {@link SlotDisplay} containing multiple item stacks.
     */
    public SlotDisplay toDisplay()
    {
        return new SlotDisplay.Composite(
                IntStream.range(this.count.getMinValue(), this.count.getMaxValue() + 1)
                        .mapToObj(c -> new ItemStack(this.output, c))
                        .map(SlotDisplay.ItemStackSlotDisplay::new)
                        .map(SlotDisplay.class::cast)
                        .toList()
        );
    }

    /**
     * Encodes the {@link OutputItemStackPayload} into a byte buffer.
     *
     * @param buf The registry byte buffer to encode the payload into.
     * @param stackPayload The payload to encode.
     */
    private static void encode(RegistryFriendlyByteBuf buf, OutputItemStackPayload stackPayload)
    {
        buf.writeResourceKey(BuiltInRegistries.ITEM.getResourceKey(stackPayload.output()).orElseThrow());

        BuiltInRegistries.INT_PROVIDER_TYPE.getResourceKey(stackPayload.count().getType()).ifPresent(buf::writeResourceKey);
        ExtraPacketCodecs.encode(buf, stackPayload.count());

        BuiltInRegistries.FLOAT_PROVIDER_TYPE.getResourceKey(stackPayload.chance().getType()).ifPresent(buf::writeResourceKey);
        ExtraPacketCodecs.encode(buf, stackPayload.chance());
    }

    /**
     * Decodes a {@link OutputItemStackPayload} from a byte buffer.
     *
     * @param buf The registry byte buffer to decode the payload from.
     * @return The decoded {@link OutputItemStackPayload}.
     */
    private static OutputItemStackPayload decode(RegistryFriendlyByteBuf buf)
    {
        Item item = BuiltInRegistries.ITEM.getValue(buf.readResourceKey(Registries.ITEM));

        ResourceKey<IntProviderType<?>> countType = buf.readResourceKey(Registries.INT_PROVIDER_TYPE);
        IntProviderType<?> countTypeInstance = BuiltInRegistries.INT_PROVIDER_TYPE.getValue(countType);
        IntProvider count = ExtraPacketCodecs.decode(buf, countTypeInstance);

        ResourceKey<FloatProviderType<?>> chanceType = buf.readResourceKey(Registries.FLOAT_PROVIDER_TYPE);
        FloatProviderType<?> chanceTypeInstance = BuiltInRegistries.FLOAT_PROVIDER_TYPE.getValue(chanceType);
        FloatProvider chance = ExtraPacketCodecs.decode(buf, chanceTypeInstance);

        return new OutputItemStackPayload(item, count, chance);
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