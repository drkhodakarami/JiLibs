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
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.thementor.api.shared.annotations.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a custom payload containing a fluid stack, including its variant and amount.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record FluidStackPayload(FluidVariant fluid, long amount) implements CustomPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Id<FluidStackPayload> ID = new Id<>(Identifier.of("jiralib", "fluid_stack_payload"));

    /**
     * An empty instance of FluidStackPayload.
     */
    public static final FluidStackPayload EMPTY = new FluidStackPayload(FluidVariant.blank(), 0);

    /**
     * The codec used to serialize and deserialize the FluidStackPayload.
     */
    public static final MapCodec<FluidStackPayload> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid").forGetter(FluidStackPayload::fluid),
            Codec.LONG.fieldOf("amount").forGetter(FluidStackPayload::amount)
    ).apply(inst, FluidStackPayload::new));

    /**
     * The packet codec used to send and receive the FluidStackPayload.
     */
    public static final PacketCodec<RegistryByteBuf, FluidStackPayload> PACKET_CODEC =
            PacketCodec.tuple(FluidVariant.PACKET_CODEC, FluidStackPayload::fluid,
                              PacketCodecs.LONG, FluidStackPayload::amount,
                              FluidStackPayload::new);

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

    /**
     * Checks if the fluid stack is empty (no fluid or amount 0).
     *
     * @return true if the fluid stack is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return this.amount == 0 || this.fluid.isBlank();
    }

    /**
     * Checks if this fluid stack matches another fluid stack.
     *
     * @param stack the fluid stack to compare with
     * @return true if the stacks match, false otherwise
     */
    public boolean matches(@Nullable FluidStackPayload stack)
    {
        if(this == EMPTY && stack == EMPTY)
            return true;
        return stack == null ? isEmpty() : this.fluid.equals(stack.fluid) && this.amount >= stack.amount();
    }

    /**
     * Creates a new fluid stack payload with the specified amount.
     *
     * @param amount the new amount of the fluid
     * @return the new FluidStackPayload instance
     */
    public FluidStackPayload withAmount(long amount)
    {
        return amount <= 0 ? EMPTY : new FluidStackPayload(this.fluid, amount);
    }

    /**
     * Tests if this fluid stack can be used in a recipe.
     *
     * @param stack the fluid stack to test against
     * @return true if the stacks match and this stack has enough fluid for the recipe, false otherwise
     */
    public boolean testForRecipe(FluidStackPayload stack)
    {
        return this.fluid.equals(stack.fluid()) && this.amount >= stack.amount();
    }

    /**
     * Tests if this fluid stack can be used in a recipe with the given SingleFluidStorage.
     *
     * @param fluidStorage the SingleFluidStorage to test against
     * @return true if the stacks match and there is enough fluid in the storage, false otherwise
     */
    public boolean testForRecipe(SingleFluidStorage fluidStorage)
    {
        if(fluidStorage == null)
            return false;

        return this.fluid.equals(fluidStorage.variant) && this.amount <= fluidStorage.amount;
    }

    /**
     * Tests if this fluid stack can be used in a recipe with the given SingleFluidStorage and minimum amount.
     *
     * @param fluidStorage the SingleFluidStorage to test against
     * @param amount the minimum amount of fluid required for the recipe
     * @return true if the stacks match, there is enough fluid in the storage, and it meets or exceeds the minimum amount, false otherwise
     */
    public boolean testForRecipe(SingleFluidStorage fluidStorage, int amount)
    {
        if(fluidStorage == null)
            return false;

        return this.fluid.equals(fluidStorage.variant) && this.amount <= fluidStorage.amount && this.amount >= amount;
    }

    /**
     * Creates a new FluidStackPayload from the given SingleFluidStorage.
     *
     * @param fluidStorage the SingleFluidStorage to convert
     * @return the new FluidStackPayload instance
     */
    public static FluidStackPayload of(SingleFluidStorage fluidStorage)
    {
        return new FluidStackPayload(fluidStorage.variant, fluidStorage.amount);
    }
}