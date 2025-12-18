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
import org.jetbrains.annotations.Nullable;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import dev.thementor.api.shared.annotations.*;

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

public record FluidStackPayload(FluidVariant fluid, long amount) implements CustomPacketPayload
{
    /**
     * The unique identifier for this custom payload.
     */
    public static final Type<@NotNull FluidStackPayload> ID = new Type<>(Identifier.fromNamespaceAndPath("jilibs_shared", "fluid_stack_payload"));

    /**
     * An empty instance of FluidStackPayload.
     */
    public static final FluidStackPayload EMPTY = new FluidStackPayload(FluidVariant.blank(), 0);

    /**
     * The codec used to serialize and deserialize the FluidStackPayload.
     */
    public static final Codec<FluidStackPayload> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            FluidVariant.CODEC.fieldOf("fluid").forGetter(FluidStackPayload::fluid),
            Codec.LONG.fieldOf("amount").forGetter(FluidStackPayload::amount)
    ).apply(inst, FluidStackPayload::new));

    /**
     * The packet codec used to send and receive the FluidStackPayload.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, FluidStackPayload> STREAM_CODEC =
            StreamCodec.composite(FluidVariant.PACKET_CODEC, FluidStackPayload::fluid,
                              ByteBufCodecs.LONG, FluidStackPayload::amount,
                              FluidStackPayload::new);

    public static final Codec<List<FluidStackPayload>> LIST_CODEC = CODEC.listOf();

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