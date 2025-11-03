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
import dev.thementor.api.shared.exceptions.Exceptions;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

/**
 * Provides utility methods for registering world generation features and placed features.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class WorldGenHelper
{
    /**
     * Private constructor to prevent instantiation.
     */
    public WorldGenHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Registers a placed feature with the given configuration and modifiers.
     *
     * @param context       the registerable context for placing features
     * @param key           the registry key for the placed feature
     * @param configuration the configured feature to place
     * @param modifiers     the placement modifiers to apply
     */
    public static void registerPlacedFeature(Registerable<PlacedFeature> context,
                                             RegistryKey<PlacedFeature> key,
                                             RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                             List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    /**
     * Registers a placed feature with the given configuration and modifiers.
     *
     * @param context       the registerable context for placing features
     * @param key           the registry key for the placed feature
     * @param configuration the configured feature to place
     * @param modifiers     the placement modifiers to apply
     */
    public static void registerPlacedFeature(Registerable<PlacedFeature> context,
                                             RegistryKey<PlacedFeature> key,
                                             RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                             PlacementModifier... modifiers)
    {
        registerPlacedFeature(context, key, configuration, List.of(modifiers));
    }

    /**
     * Registers a configured feature with the given feature and configuration.
     *
     * @param context       the registerable context for placing features
     * @param key           the registry key for the configured feature
     * @param feature       the feature to configure
     * @param configuration the configuration for the feature
     */
    public static <FC extends FeatureConfig, F extends Feature<FC>> void registerConfiguredFeature(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                                                   F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    /**
     * Creates a list of placement modifiers with the given count and height modifier.
     *
     * @param countModifier   the count placement modifier
     * @param heightModifier  the height placement modifier
     * @return a list of placement modifiers
     */
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier)
    {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    /**
     * Creates a list of placement modifiers with the given count and height modifier.
     *
     * @param count           the number of times to place the feature
     * @param heightModifier  the height placement modifier
     * @return a list of placement modifiers
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier)
    {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    /**
     * Creates a list of placement modifiers with the given rarity and height modifier.
     *
     * @param chance          the rarity chance
     * @param heightModifier  the height placement modifier
     * @return a list of placement modifiers
     */
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier)
    {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}