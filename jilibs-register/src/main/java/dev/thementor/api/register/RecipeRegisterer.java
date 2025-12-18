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

package dev.thementor.api.register;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.*;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Registers custom recipes and recipe serializers for Minecraft.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class RecipeRegisterer
{
    /**
     * The mod ID used for registering recipes and serializers.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiRecipeRegister with the specified mod ID.
     *
     * @param modId the mod ID
     */
    public RecipeRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a recipe serializer.
     *
     * @param <R>             the type of the recipe
     * @param <D>             the type of the recipe input
     * @param name            the name of the recipe serializer
     * @param serializer      the recipe serializer to register
     * @return the registered recipe serializer
     */
    public <R extends Recipe<@NotNull D>, D extends RecipeInput> RecipeSerializer<@NotNull R> register(String name, RecipeSerializer<@NotNull R> serializer)
    {
        ResourceKey<RecipeSerializer<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.RECIPE_SERIALIZER);
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, key, serializer);
    }

    /**
     * Registers a recipe type.
     *
     * @param <R>             the type of the recipe
     * @param name            the name of the recipe type
     * @param recipeType      the recipe type to register
     * @return the registered recipe type
     */
    public <R extends Recipe<@NotNull D>, D extends RecipeInput> RecipeType<@NotNull R> register(String name, RecipeType<@NotNull R> recipeType)
    {
        ResourceKey<RecipeType<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.RECIPE_TYPE);
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, key, recipeType);
    }

    public RecipeBookCategory registerCategory(String name)
    {
        return Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, BaseHelper.id(this.modId, name), new RecipeBookCategory());
    }
}