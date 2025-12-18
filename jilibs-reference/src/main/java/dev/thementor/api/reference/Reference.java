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

package dev.thementor.api.reference;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Provides utility methods for managing mod-related references and IDs.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class Reference
{
    /**
     * The mod ID.
     */
    private final String modId;

    /**
     * Constructs an instance of JiReference with the specified mod ID.
     *
     * @param mod_ID The unique identifier for the mod.
     */
    public Reference(String mod_ID)
     {
         this.modId = mod_ID;
     }

    /**
     * Retrieves the current mod ID.
     *
     * @return The mod ID.
     */
    public String ModID()
     {
         return modId;
     }

    /**
     * Creates a Gson instance for pretty printing and disabling HTML escaping.
     *
     */
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * Retrieves a registry key for the given name and registry.
     *
     * @param <T> The type of the registry entries.
     * @param name The name of the registry entry.
     * @param registryKey The registry key.
     * @return A registry key for the specified name and registry.
     */
    public <T> ResourceKey<T> getKey(String name, ResourceKey<? extends Registry<T>> registryKey)
    {
        return BaseHelper.resourceKey(ModID(), name, registryKey);
    }

    /**
     * Creates an identifier for a mod-specific path.
     *
     * @param path The path of the identifier.
     * @return An Identifier instance for the specified path.
     */
    @NotNull
    public Identifier id(@NotNull String path)
     {
         return Identifier.fromNamespaceAndPath(ModID(), path);
     }

    /**
     * Creates an identifier for a vanilla Minecraft path.
     *
     * @param path The path of the identifier.
     * @return An Identifier instance for the specified path.
     */
    @NotNull
    public Identifier vanillaID(@NotNull String path)
     {
         return Identifier.withDefaultNamespace(path);
     }

    /**
     * Creates an identifier from a given path.
     *
     * @param path The path of the identifier.
     * @return An Identifier instance for the specified path.
     */
    @NotNull
    public Identifier idOf(@NotNull String path)
     {
         return Objects.requireNonNull(Identifier.tryParse(path));
     }

    /**
     * Translates a key into a mod-specific text message with optional parameters.
     *
     * @param key The translation key.
     * @param params Optional parameters for the translation.
     * @return A MutableText instance representing the translated message.
     */
    public MutableComponent translate(String key, Object... params)
     {
         return Component.translatable(ModID() + "." + key, params);
     }

    /**
     * Translates a key into a mod-specific text message within a container with optional parameters.
     *
     * @param key The translation key.
     * @param params Optional parameters for the translation.
     * @return A MutableText instance representing the translated message in a container.
     */
    public MutableComponent translateContainer(String key, Object... params)
     {
         return Component.translatable(ModID() + ".container." + key, params);
     }

    /**
     * Creates a block tag with the specified name.
     *
     * @param name The name of the block tag.
     * @return A TagKey instance for the specified block tag.
     */
    protected TagKey<Block> createBlockTag(String name)
     {
         return TagKey.create(Registries.BLOCK, id(name));
     }

    /**
     * Creates a common block tag with the specified name.
     *
     * @param name The name of the common block tag.
     * @return A TagKey instance for the specified common block tag.
     */
    protected TagKey<Block> createBlockCommonTag(String name)
     {
         return TagKey.create(Registries.BLOCK, Objects.requireNonNull(Identifier.tryBuild("c", name)));
     }

    /**
     * Creates an item tag with the specified name.
     *
     * @param name The name of the item tag.
     * @return A TagKey instance for the specified item tag.
     */
    protected TagKey<Item> createItemTag(String name)
     {
         return TagKey.create(Registries.ITEM, id(name));
     }

    /**
     * Creates a common item tag with the specified name.
     *
     * @param name The name of the common item tag.
     * @return A TagKey instance for the specified common item tag.
     */
    protected TagKey<Item> createItemCommonTag(String name)
     {
         return TagKey.create(Registries.ITEM, Objects.requireNonNull(Identifier.tryBuild("c", name)));
     }

    /**
     * Creates an entity tag with the specified name.
     *
     * @param name The name of the entity tag.
     * @return A TagKey instance for the specified entity tag.
     */
    protected TagKey<EntityType<?>> createEntityTag(String name)
     {
         return TagKey.create(Registries.ENTITY_TYPE, id(name));
     }

    /**
     * Creates a common entity tag with the specified name.
     *
     * @param name The name of the common entity tag.
     * @return A TagKey instance for the specified common entity tag.
     */
    protected TagKey<EntityType<?>> createEntityCommonTag(String name)
     {
         return TagKey.create(Registries.ENTITY_TYPE, Objects.requireNonNull(Identifier.tryBuild("c", name)));
     }

    /**
     * Retrieves a key for energy amount storage.
     *
     * @return The key for energy amount storage.
     */
    public String energyAmountKey()
     {
         return this.modId + BEKeys.ENERGY_AMOUNT;
     }

    /**
     * Retrieves a key for energy capacity storage.
     *
     * @return The key for energy capacity storage.
     */
    public String energyCapacityKey()
     {
         return this.modId + BEKeys.ENERGY_CAPACITY;
     }

    /**
     * Retrieves a key indicating if the component has energy.
     *
     * @return The key indicating if the component has energy.
     */
    public String hasEnergyKey()
     {
         return this.modId + BEKeys.HAS_ENERGY;
     }

    /**
     * Retrieves a key for fluid amount storage.
     *
     * @return The key for fluid amount storage.
     */
    public String fluidAmountKey()
     {
         return this.modId + BEKeys.FLUID_AMOUNT;
     }

    /**
     * Retrieves a key for fluid capacity storage.
     *
     * @return The key for fluid capacity storage.
     */
    public String fluidCapacityKey()
     {
         return this.modId + BEKeys.FLUID_CAPACITY;
     }

    /**
     * Retrieves a key indicating if the component has fluid.
     *
     * @return The key indicating if the component has fluid.
     */
    public String hasFluidKey()
     {
         return this.modId + BEKeys.HAS_FLUID;
     }

    /**
     * Retrieves a key for progress amount storage.
     *
     * @return The key for progress amount storage.
     */
    public String progressAmountKey()
     {
         return this.modId + BEKeys.PROGRESS_AMOUNT;
     }

    /**
     * Retrieves a key for maximum progress storage.
     *
     * @return The key for maximum progress storage.
     */
    public String maxProgressKey()
     {
         return this.modId + BEKeys.PROGRESS_MAX;
     }

    /**
     * Retrieves a key for cooldown amount storage.
     *
     * @return The key for cooldown amount storage.
     */
    public String cooldownAmountKey()
     {
         return this.modId + BEKeys.COOLDOWN_AMOUNT;
     }

    /**
     * Retrieves a key for maximum cooldown storage.
     *
     * @return The key for maximum cooldown storage.
     */
    public String maxCooldownKey()
     {
         return this.modId + BEKeys.COOLDOWN_MAX;
     }

    /**
     * Retrieves a key for burn amount storage.
     *
     * @return The key for burn amount storage.
     */
    public String burnAmountKey()
     {
         return this.modId + BEKeys.BURN_AMOUNT;
     }

    /**
     * Retrieves a key for maximum burn storage.
     *
     * @return The key for maximum burn storage.
     */
    public String maxBurnKey()
     {
         return this.modId + BEKeys.BURN_MAX;
     }

    /**
     * Retrieves a key indicating if the component has an inventory.
     *
     * @return The key indicating if the component has an inventory.
     */
    public String hasInventoryKey()
     {
         return this.modId + BEKeys.HAS_INVENTORY;
     }

    /**
     * Retrieves a key for dirty status.
     *
     * @return The key for dirty status.
     */
    public String dirtyKey()
    {
        return this.modId + BEKeys.IS_DIRTY;
    }

    /**
     * Retrieves a key for dirty client status.
     *
     * @return The key for dirty client status.
     */
    public String dirtyClientKey()
    {
        return this.modId + BEKeys.IS_DIRTY_CLIENT;
    }

    /**
     * Retrieves a key for world data storage.
     *
     * @return The key for world data storage.
     */
    public String worldKey()
    {
        return this.modId + BEKeys.WORLD;
    }

    /**
     * Retrieves a key for position data storage.
     *
     * @return The key for position data storage.
     */
    public String posKey()
    {
        return this.modId + BEKeys.POS;
    }

    /**
     * Retrieves a key for cached state data storage.
     *
     * @return The key for cached state data storage.
     */
    public String cachedStateKey()
    {
        return this.modId + BEKeys.CACHED_STATE;
    }

    /**
     * Retrieves a key for ticks data storage (duplicate of dirtyKey).
     *
     * @return The key for ticks data storage.
     */
    public String ticksKey()
    {
        return this.modId + BEKeys.IS_DIRTY;
    }

    /**
     * Retrieves a key for ticks client data storage (duplicate of dirtyClientKey).
     *
     * @return The key for ticks client data storage.
     */
    public String ticksClientKey()
    {
        return this.modId + BEKeys.IS_DIRTY;
    }
}