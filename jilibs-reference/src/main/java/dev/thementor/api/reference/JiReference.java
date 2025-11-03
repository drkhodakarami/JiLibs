/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                              *
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

package dev.thementor.api.reference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.constants.BEKeys;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * Provides utility methods for managing mod-related references and IDs.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiReference
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
    public JiReference(String mod_ID)
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
    public <T> RegistryKey<T> getKey(String name, RegistryKey<? extends Registry<T>> registryKey)
    {
        return BaseHelper.getKey(ModID(), name, registryKey);
    }

    /**
     * Creates an identifier for a mod-specific path.
     *
     * @param path The path of the identifier.
     * @return An Identifier instance for the specified path.
     */
    @NotNull
    public Identifier identifier(@NotNull String path)
     {
         return Identifier.of(ModID(), path);
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
         return Identifier.ofVanilla(path);
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
         return Identifier.of(path);
     }

    /**
     * Translates a key into a mod-specific text message with optional parameters.
     *
     * @param key The translation key.
     * @param params Optional parameters for the translation.
     * @return A MutableText instance representing the translated message.
     */
    public MutableText translate(String key, Object... params)
     {
         return Text.translatable(ModID() + "." + key, params);
     }

    /**
     * Translates a key into a mod-specific text message within a container with optional parameters.
     *
     * @param key The translation key.
     * @param params Optional parameters for the translation.
     * @return A MutableText instance representing the translated message in a container.
     */
    public MutableText translateContainer(String key, Object... params)
     {
         return Text.translatable(ModID() + ".container." + key, params);
     }

    /**
     * Creates a block tag with the specified name.
     *
     * @param name The name of the block tag.
     * @return A TagKey instance for the specified block tag.
     */
    protected TagKey<Block> createBlockTag(String name)
     {
         return TagKey.of(RegistryKeys.BLOCK, identifier(name));
     }

    /**
     * Creates a common block tag with the specified name.
     *
     * @param name The name of the common block tag.
     * @return A TagKey instance for the specified common block tag.
     */
    protected TagKey<Block> createBlockCommonTag(String name)
     {
         return TagKey.of(RegistryKeys.BLOCK, Identifier.tryParse("c", name));
     }

    /**
     * Creates an item tag with the specified name.
     *
     * @param name The name of the item tag.
     * @return A TagKey instance for the specified item tag.
     */
    protected TagKey<Item> createItemTag(String name)
     {
         return TagKey.of(RegistryKeys.ITEM, identifier(name));
     }

    /**
     * Creates a common item tag with the specified name.
     *
     * @param name The name of the common item tag.
     * @return A TagKey instance for the specified common item tag.
     */
    protected TagKey<Item> createItemCommonTag(String name)
     {
         return TagKey.of(RegistryKeys.ITEM, Identifier.tryParse("c", name));
     }

    /**
     * Creates an entity tag with the specified name.
     *
     * @param name The name of the entity tag.
     * @return A TagKey instance for the specified entity tag.
     */
    protected TagKey<EntityType<?>> createEntityTag(String name)
     {
         return TagKey.of(RegistryKeys.ENTITY_TYPE, identifier(name));
     }

    /**
     * Creates a common entity tag with the specified name.
     *
     * @param name The name of the common entity tag.
     * @return A TagKey instance for the specified common entity tag.
     */
    protected TagKey<EntityType<?>> createEntityCommonTag(String name)
     {
         return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.tryParse("c", name));
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