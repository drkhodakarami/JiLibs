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

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Provides utility methods for registering world generation features and placed features.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
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
    public static void registerPlacedFeature(BootstrapContext<PlacedFeature> context,
                                             ResourceKey<PlacedFeature> key,
                                             Holder<ConfiguredFeature<?, ?>> configuration,
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
    public static void registerPlacedFeature(BootstrapContext<PlacedFeature> context,
                                             ResourceKey<PlacedFeature> key,
                                             Holder<ConfiguredFeature<?, ?>> configuration,
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
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerConfiguredFeature(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key,
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
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
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
        return modifiers(CountPlacement.of(count), heightModifier);
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
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }
}