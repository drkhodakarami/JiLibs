/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
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

package dev.thementor.api.register;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

/**
 * Registers custom recipes and recipe serializers for Minecraft.
 */
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiRecipeRegister
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
    public JiRecipeRegister(String modId)
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
    public <R extends Recipe<D>, D extends RecipeInput> RecipeSerializer<R> register(String name, RecipeSerializer<R> serializer)
    {
        RegistryKey<RecipeSerializer<?>> key = BaseHelper.getKey(this.modId, name, RegistryKeys.RECIPE_SERIALIZER);
        return Registry.register(Registries.RECIPE_SERIALIZER, key, serializer);
    }

    /**
     * Registers a recipe type.
     *
     * @param <R>             the type of the recipe
     * @param name            the name of the recipe type
     * @param recipeType      the recipe type to register
     * @return the registered recipe type
     */
    public <R extends Recipe<D>, D extends RecipeInput> RecipeType<R> register(String name, RecipeType<R> recipeType)
    {
        RegistryKey<RecipeType<?>> key = BaseHelper.getKey(this.modId, name, RegistryKeys.RECIPE_TYPE);
        return Registry.register(Registries.RECIPE_TYPE, key, recipeType);
    }
}