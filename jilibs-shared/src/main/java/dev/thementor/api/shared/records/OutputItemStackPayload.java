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

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.network.ExtraPacketCodecs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.floatprovider.FloatProviderType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.IntProviderType;
import net.minecraft.util.math.random.Random;

import java.util.stream.IntStream;

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

public record OutputItemStackPayload(Item output, IntProvider count, FloatProvider chance) implements CustomPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Id<BlockPosPayload> ID = new Id<>(Identifier.of("jiralib", "output_item_stack_payload"));

    /**
     * The default chance value (1.0f).
     */
    public static final ConstantFloatProvider DEFAULT_CHANCE = ConstantFloatProvider.create(1.0f);

    /**
     * An empty instance of OutputItemStackPayload.
     */
    public static final OutputItemStackPayload EMPTY = new OutputItemStackPayload(ItemStack.EMPTY);

    /**
     * The codec used to serialize and deserialize the OutputItemStackPayload.
     */
    public static final MapCodec<OutputItemStackPayload> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Registries.ITEM.getCodec().fieldOf("output").forGetter(OutputItemStackPayload::output),
            IntProvider.VALUE_CODEC.fieldOf("count").forGetter(OutputItemStackPayload::count),
            FloatProvider.VALUE_CODEC.fieldOf("chance").forGetter(OutputItemStackPayload::chance)
    ).apply(inst, OutputItemStackPayload::new));

    /**
     * The packet codec used to send and receive the OutputItemStackPayload.
     */
    public static final PacketCodec<RegistryByteBuf, OutputItemStackPayload> PACKET_CODEC =
            PacketCodec.ofStatic(OutputItemStackPayload::encode, OutputItemStackPayload::decode);

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
        this(output, ConstantIntProvider.create(count), ConstantFloatProvider.create(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack}.
     *
     * @param stack The {@link ItemStack} to be converted into the payload.
     * @throws IllegalArgumentException if the stack is null or its item is null
     */
    public OutputItemStackPayload(ItemStack stack)
    {
        this(stack.getItem(), ConstantIntProvider.create(stack.getCount()), DEFAULT_CHANCE);
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
        this(output, count, ConstantFloatProvider.create(chance));
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
        this(output, ConstantIntProvider.create(count), chance);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and count.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param count  The count of items, cannot be zero or negative.
     */
    public OutputItemStackPayload(Item output, int count)
    {
        this(output, ConstantIntProvider.create(count), DEFAULT_CHANCE);
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
        this(output, ConstantIntProvider.create(1), ConstantFloatProvider.create(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item and chance provider.
     *
     * @param output The item to be included in the payload, cannot be null.
     * @param chance The chance provider for the item appearing, cannot be null.
     */
    public OutputItemStackPayload(Item output, FloatProvider chance)
    {
        this(output, ConstantIntProvider.create(1), chance);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} with the given item.
     *
     * @param output The item to be included in the payload, cannot be null.
     */
    public OutputItemStackPayload(Item output)
    {
        this(output, ConstantIntProvider.create(1), DEFAULT_CHANCE);
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack} and chance.
     *
     * @param stack  The {@link ItemStack} to be converted into the payload, cannot be null or empty.
     * @param chance The chance of the item appearing, cannot be null or less than 0.
     */
    public OutputItemStackPayload(ItemStack stack, float chance)
    {
        this(stack.getItem(), ConstantIntProvider.create(stack.getCount()), ConstantFloatProvider.create(chance));
    }

    /**
     * Constructs an {@link OutputItemStackPayload} from a given {@link ItemStack} and chance provider.
     *
     * @param stack  The {@link ItemStack} to be converted into the payload, cannot be null or empty.
     * @param chance The chance provider for the item appearing, cannot be null.
     */
    public OutputItemStackPayload(ItemStack stack, FloatProvider chance)
    {
        this(stack.getItem(), ConstantIntProvider.create(stack.getCount()), chance);
    }

    /**
     * Represents a payload for an output item stack with configurable properties.
     */
    public ItemStack createStack(Random random)
    {
        return this.chance.get(random) < random.nextFloat()
               ? ItemStack.EMPTY
               : new ItemStack(this.output, this.count.get(random));
    }

    /**
     * Converts this payload to a display representation of multiple item stacks.
     *
     * @return A {@link SlotDisplay} containing multiple item stacks.
     */
    public SlotDisplay toDisplay()
    {
        return new SlotDisplay.CompositeSlotDisplay(
                IntStream.range(this.count.getMin(), this.count.getMax() + 1)
                        .mapToObj(c -> new ItemStack(this.output, c))
                        .map(SlotDisplay.StackSlotDisplay::new)
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
    private static void encode(RegistryByteBuf buf, OutputItemStackPayload stackPayload)
    {
        buf.writeRegistryKey(Registries.ITEM.getKey(stackPayload.output()).orElseThrow());

        Registries.INT_PROVIDER_TYPE.getKey(stackPayload.count().getType()).ifPresent(buf::writeRegistryKey);
        ExtraPacketCodecs.encode(buf, stackPayload.count());

        Registries.FLOAT_PROVIDER_TYPE.getKey(stackPayload.chance().getType()).ifPresent(buf::writeRegistryKey);
        ExtraPacketCodecs.encode(buf, stackPayload.chance());
    }

    /**
     * Decodes a {@link OutputItemStackPayload} from a byte buffer.
     *
     * @param buf The registry byte buffer to decode the payload from.
     * @return The decoded {@link OutputItemStackPayload}.
     */
    private static OutputItemStackPayload decode(RegistryByteBuf buf)
    {
        Item item = Registries.ITEM.get(buf.readRegistryKey(RegistryKeys.ITEM));

        RegistryKey<IntProviderType<?>> countType = buf.readRegistryKey(RegistryKeys.INT_PROVIDER_TYPE);
        IntProviderType<?> countTypeInstance = Registries.INT_PROVIDER_TYPE.get(countType);
        IntProvider count = ExtraPacketCodecs.decode(buf, countTypeInstance);

        RegistryKey<FloatProviderType<?>> chanceType = buf.readRegistryKey(RegistryKeys.FLOAT_PROVIDER_TYPE);
        FloatProviderType<?> chanceTypeInstance = Registries.FLOAT_PROVIDER_TYPE.get(chanceType);
        FloatProvider chance = ExtraPacketCodecs.decode(buf, chanceTypeInstance);

        return new OutputItemStackPayload(item, count, chance);
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