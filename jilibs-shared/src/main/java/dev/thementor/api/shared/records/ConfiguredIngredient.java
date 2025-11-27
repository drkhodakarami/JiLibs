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

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.SlotDisplay;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.MathHelper;

/**
 * Represents a configured ingredient, combining an item list and stack data payload.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public record ConfiguredIngredient(HolderSet<Item> entries, StackDataPayload stackData)
{
    /**
     * The codec used to serialize and deserialize the ConfiguredIngredient.
     */
    public static final Codec<ConfiguredIngredient> CODEC = Codec.lazyInitialized(() -> RecordCodecBuilder.create(
            inst -> inst.group(
                    Ingredient.NON_AIR_HOLDER_SET_CODEC.fieldOf("ingredients").forGetter(ConfiguredIngredient::entries),
                    StackDataPayload.CODEC.optionalFieldOf("data", StackDataPayload.EMPTY).forGetter(ConfiguredIngredient::stackData)
            ).apply(inst, ConfiguredIngredient::new)
    ));

    /**
     * The packet codec used to send and receive the ConfiguredIngredient.
     */
    public static final StreamCodec<RegistryFriendlyByteBuf, ConfiguredIngredient> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.ITEM), ConfiguredIngredient::entries,
            StackDataPayload.STREAM_CODEC, ConfiguredIngredient::stackData,
            ConfiguredIngredient::new
    );

    public static final Codec<List<ConfiguredIngredient>> LIST_CODEC = CODEC.listOf();

    /**
     * An empty instance of ConfiguredIngredient.
     */
    public static final ConfiguredIngredient EMPTY = new ConfiguredIngredient(HolderSet.direct(), StackDataPayload.EMPTY);

    /**
     * Creates a new ConfiguredIngredient with the given items and default stack data (count 1).
     *
     * @param entries the items to include in the ingredient
     */
    public ConfiguredIngredient(HolderSet<Item> entries)
    {
        this(entries, StackDataPayload.create(1));
    }

    /**
     * Creates a new ConfiguredIngredient with the given items and specified count.
     *
     * @param entries the items to include in the ingredient
     * @param count   the count of each item
     */
    public ConfiguredIngredient(HolderSet<Item> entries, int count)
    {
        this(entries, StackDataPayload.create(count));
    }

    /**
     * Creates a new ConfiguredIngredient with the given items and default stack data (count 1).
     *
     * @param items the items to include in the ingredient
     */
    @SuppressWarnings("deprecation")
    public ConfiguredIngredient(int count, Item... items)
    {
        this(HolderSet.direct(Arrays.stream(items).map(Item::builtInRegistryHolder).toList()), StackDataPayload.create(count));
    }

    /**
     * Creates a new ConfiguredIngredient with the given items and default stack data (count 1).
     *
     * @param items the items to include in the ingredient
     */
    @SuppressWarnings("deprecation")
    public ConfiguredIngredient(Item... items)
    {
        this(HolderSet.direct(Arrays.stream(items).map(Item::builtInRegistryHolder).toList()), StackDataPayload.create(1));
    }

    /**
     * Creates a new ConfiguredIngredient with the given item and default stack data (count 1).
     *
     * @param count the count of the item
     * @param item  the item to include in the ingredient
     */
    @SuppressWarnings("deprecation")
    public ConfiguredIngredient(int count, Item item)
    {
        this(HolderSet.direct(Arrays.stream(new Item[]{item}).map(Item::builtInRegistryHolder).toList()), StackDataPayload.create(count));
    }

    /**
     * Creates a new ConfiguredIngredient with the given item and default stack data (count 1).
     *
     * @param item the item to include in the ingredient
     */
    @SuppressWarnings("deprecation")
    public ConfiguredIngredient(Item item)
    {
        this(HolderSet.direct(Arrays.stream(new Item[]{item}).map(Item::builtInRegistryHolder).toList()), StackDataPayload.create(1));
    }

    /**
     * Creates a new ConfiguredIngredient with the given items and specified count and components.
     *
     * @param entries   the items to include in the ingredient
     * @param count     the count of each item
     * @param components the component changes for the stack data
     */
    public ConfiguredIngredient(HolderSet<Item> entries, int count, DataComponentPatch components)
    {
        this(entries, StackDataPayload.create(count, components));
    }

    /**
     * Retrieves a list of ItemStacks that match the configured ingredients.
     *
     * @return a list of matching ItemStacks
     */
    public List<ItemStack> getMatchingStacks()
    {
        return this.entries.stream().map(item -> new ItemStack(item, this.stackData.count(), this.stackData.components())).toList();
    }

    /**
     * Tests if the given stack can be used in a recipe, considering count and components.
     *
     * @param stack the stack to test
     * @return true if the stack matches the ingredient, false otherwise
     */
    public boolean testForRecipe(ItemStack stack)
    {
        return test(stack, MathHelper.countLessThanOrEquals(stack.getCount()));
    }

    /**
     * Tests if the given stack can be used in a recipe, ignoring components.
     *
     * @param stack the stack to test
     * @return true if the stack matches the ingredient, false otherwise
     */
    public boolean testForRecipeIgnoreComponents(ItemStack stack)
    {
        return this.entries.stream().anyMatch(item ->
                stack.getItem() == item.value() &&
                this.stackData.count() >= stack.getCount());
    }

    /**
     * Tests if the given stack can be used in a recipe, considering count and components.
     *
     * @param stack       the stack to test
     * @param matchCount  whether to match the count of the ingredient
     * @param matchComponents whether to match the component changes of the ingredient
     * @return true if the stack matches the ingredient, false otherwise
     */
    public boolean test(ItemStack stack, boolean matchCount, boolean matchComponents)
    {
        return this.entries.stream().anyMatch(item ->
                stack.getItem() == item.value() &&
                (!matchCount || stack.getCount() == this.stackData.count()) &&
                (!matchComponents || this.stackData.components().equals(stack.getComponentsPatch())));
    }

    /**
     * Tests if the given stack can be used in a recipe, considering count and components.
     *
     * @param stack the stack to test
     * @return true if the stack matches the ingredient, false otherwise
     */
    public boolean test(ItemStack stack)
    {
        return test(stack, true, true);
    }

    /**
     * Tests if the given stack can be used in a recipe, considering count.
     *
     * @param stack       the stack to test
     * @param countPredicate a predicate for matching the count of the ingredient
     * @return true if the stack matches the ingredient, false otherwise
     */
    public boolean test(ItemStack stack, Predicate<Integer> countPredicate)
    {
        return this.entries.stream().anyMatch(item ->
                  stack.getItem() == item.value() &&
                  countPredicate.test(stackData.count()) &&
                  this.stackData.components().equals(stack.getComponentsPatch()));
    }

    /**
     * Creates a display for the configured ingredient.
     *
     * @return the SlotDisplay instance
     */
    public SlotDisplay toDisplay()
    {
        if(isEmpty())
            return SlotDisplay.Empty.INSTANCE;

        return new SlotDisplay.Composite(
                getMatchingStacks().stream()
                        .map(SlotDisplay.ItemStackSlotDisplay::new)
                        .map(SlotDisplay.class::cast)
                        .toList()
        );
    }

    /**
     * Checks if the configured ingredient is empty.
     *
     * @return true if the ingredient is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return this == EMPTY;
    }
}