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

package dev.thementor.api.shared.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.FloatProviderType;
import net.minecraft.util.valueproviders.IntProviderType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Provides utility methods for working with various Minecraft registries and identifiers.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@ModifiedAt("2025-04-23")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class BaseHelper
{
    /**
     * Private constructor to prevent instantiation.
     */
    public BaseHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Creates a new Minecraft identifier with the given mod ID and path.
     *
     * @param modID The mod ID.
     * @param path  The path for the identifier.
     * @return The created Identifier.
     */
    public static ResourceLocation id(String modID, @NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(modID, path);
    }

    /**
     * Creates a new registry key with the given name and registry type.
     *
     * @param <T>          The type of the registry.
     * @param modID        The mod ID.
     * @param name         The name for the registry key.
     * @param registryKey  The registry key for the target registry.
     * @return The created RegistryKey.
     */
    public static <T> ResourceKey<T> resourceKey(String modID, String name, ResourceKey<? extends Registry<T>> registryKey)
    {
        return ResourceKey.create(registryKey, id(modID, name));
    }

    public static <T> TagKey<T> tagKey(String modID, String name, ResourceKey<? extends Registry<T>> registryKey)
    {
        return TagKey.create(registryKey, id(modID, name));
    }

    /**
     * Generates a tag check string for the given item tag.
     *
     * @param tag The item tag key.
     * @return A tag check string.
     */
    public static @NotNull String hasTag(@NotNull TagKey<Item> tag)
    {
        return "has_" + tag.location().toString();
    }

    /**
     * Retrieves the registry name for a given fluid.
     *
     * @param fluid The fluid to get the registry name for.
     * @return The registry name of the fluid.
     */
    public static String registryName(Fluid fluid)
    {
        return BuiltInRegistries.FLUID.getKey(fluid).getPath();
    }

    /**
     * Retrieves the registry name for a given item.
     *
     * @param item The item to get the registry name for.
     * @return The registry name of the item.
     */
    public static String registryName(Item item)
    {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }

    /**
     * Retrieves the registry name for a given item stack.
     *
     * @param stack The item stack to get the registry name for.
     * @return The registry name of the item in the stack.
     */
    public static String registryName(ItemStack stack)
    {
        return BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
    }

    /**
     * Retrieves the registry name for a given block.
     *
     * @param block The block to get the registry name for.
     * @return The registry name of the block.
     */
    public static String registryName(Block block)
    {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    /**
     * Retrieves the registry name for a given entity type.
     *
     * @param entityType The entity type to get the registry name for.
     * @return The registry name of the entity type.
     */
    public static String registryName(EntityType<?> entityType)
    {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath();
    }

    /**
     * Retrieves the registry name for a given potion.
     *
     * @param potion The potion to get the registry name for.
     * @return The registry name of the potion.
     */
    public static String registryName(Potion potion)
    {
        return Objects.requireNonNull(BuiltInRegistries.POTION.getKey(potion)).getPath();
    }

    /**
     * Retrieves the registry name for a given block entity type.
     *
     * @param blockEntityType The block entity type to get the registry name for.
     * @return The registry name of the block entity type.
     */
    public static String registryName(BlockEntityType<?> blockEntityType)
    {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(blockEntityType)).getPath();
    }

    /**
     * Retrieves the registry name for a given custom stat identifier.
     *
     * @param identifier The custom stat identifier to get the registry name for.
     * @return The registry name of the custom stat.
     */
    public static String registryName(ResourceLocation identifier)
    {
        return Objects.requireNonNull(BuiltInRegistries.CUSTOM_STAT.getKey(identifier)).getPath();
    }

    /**
     * Retrieves the registry name for a given chunk status.
     *
     * @param chunkStatus The chunk status to get the registry name for.
     * @return The registry name of the chunk status.
     */
    public static String registryName(ChunkStatus chunkStatus)
    {
        return BuiltInRegistries.CHUNK_STATUS.getKey(chunkStatus).getPath();
    }

    /**
     * Retrieves the registry name for a given entity attribute.
     *
     * @param entityAttribute The entity attribute to get the registry name for.
     * @return The registry name of the entity attribute.
     */
    public static String registryName(Attribute entityAttribute)
    {
        return Objects.requireNonNull(BuiltInRegistries.ATTRIBUTE.getKey(entityAttribute)).getPath();
    }

    /**
     * Retrieves the registry name for a given screen handler type.
     *
     * @param screenHandlerType The screen handler type to get the registry name for.
     * @return The registry name of the screen handler type.
     */
    public static String registryName(MenuType<?> screenHandlerType)
    {
        return Objects.requireNonNull(BuiltInRegistries.MENU.getKey(screenHandlerType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe type.
     *
     * @param recipeType The recipe type to get the registry name for.
     * @return The registry name of the recipe type.
     */
    public static String registryName(RecipeType<?> recipeType)
    {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_TYPE.getKey(recipeType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe serializer.
     *
     * @param recipeSerializer The recipe serializer to get the registry name for.
     * @return The registry name of the recipe serializer.
     */
    public static String registryName(RecipeSerializer<?> recipeSerializer)
    {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_SERIALIZER.getKey(recipeSerializer)).getPath();
    }

    /**
     * Retrieves the registry name for a given villager type.
     *
     * @param villagerType The villager type to get the registry name for.
     * @return The registry name of the villager type.
     */
    public static String registryName(VillagerType villagerType)
    {
        return BuiltInRegistries.VILLAGER_TYPE.getKey(villagerType).getPath();
    }

    /**
     * Retrieves the registry name for a given villager profession.
     *
     * @param villagerProfession The villager profession to get the registry name for.
     * @return The registry name of the villager profession.
     */
    public static String registryName(VillagerProfession villagerProfession)
    {
        return BuiltInRegistries.VILLAGER_PROFESSION.getKey(villagerProfession).getPath();
    }

    /**
     * Retrieves the registry name for a given float provider type.
     *
     * @param floatProviderType The float provider type to get the registry name for.
     * @return The registry name of the float provider type.
     */
    public static String registryName(FloatProviderType<?> floatProviderType)
    {
        return Objects.requireNonNull(BuiltInRegistries.FLOAT_PROVIDER_TYPE.getKey(floatProviderType)).getPath();
    }

    /**
     * Retrieves the registry name for a given int provider type.
     *
     * @param intProviderType The int provider type to get the registry name for.
     * @return The registry name of the int provider type.
     */
    public static String registryName(IntProviderType<?> intProviderType)
    {
        return Objects.requireNonNull(BuiltInRegistries.INT_PROVIDER_TYPE.getKey(intProviderType)).getPath();
    }

    /**
     * Retrieves the registry name for a given decorated pot pattern.
     *
     * @param decoratedPotPattern The decorated pot pattern to get the registry name for.
     * @return The registry name of the decorated pot pattern.
     */
    public static String registryName(DecoratedPotPattern decoratedPotPattern)
    {
        return Objects.requireNonNull(BuiltInRegistries.DECORATED_POT_PATTERN.getKey(decoratedPotPattern)).getPath();
    }

    /**
     * Retrieves the registry name for a given item group.
     *
     * @param itemGroup The item group to get the registry name for.
     * @return The registry name of the item group.
     */
    public static String registryName(CreativeModeTab itemGroup)
    {
        return Objects.requireNonNull(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(itemGroup)).getPath();
    }

    /**
     * Retrieves the registry name for a given criterion.
     *
     * @param criterion The criterion to get the registry name for.
     * @return The registry name of the criterion.
     */
    public static String registryName(CriterionTrigger<?> criterion)
    {
        return Objects.requireNonNull(BuiltInRegistries.TRIGGER_TYPES.getKey(criterion)).getPath();
    }

    /**
     * Retrieves the registry name for a given data component type or enchantment effect component type.
     *
     * @param componentType The component type to get the registry name for.
     * @param isEnchantment Whether this is an enchantment effect component type.
     * @return The registry name of the component type.
     */
    public static String registryName(DataComponentType<?> componentType, boolean isEnchantment)
    {
        return isEnchantment
               ? Objects.requireNonNull(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE.getKey(componentType)).getPath()
               : Objects.requireNonNull(BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(componentType)).getPath();
    }

    /**
     * Retrieves the registry name for a given map decoration type.
     *
     * @param mapDecorationType The map decoration type to get the registry name for.
     * @return The registry name of the map decoration type.
     */
    public static String registryName(MapDecorationType mapDecorationType)
    {
        return Objects.requireNonNull(BuiltInRegistries.MAP_DECORATION_TYPE.getKey(mapDecorationType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe book category.
     *
     * @param recipeBookCategory The recipe book category to get the registry name for.
     * @return The registry name of the recipe book category.
     */
    public static String registryName(RecipeBookCategory recipeBookCategory)
    {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_BOOK_CATEGORY.getKey(recipeBookCategory)).getPath();
    }

    /**
     * Retrieves a list of items that are in the given item tag.
     *
     * @param tagKey The item tag key to get the items for.
     * @return A list of items in the tag.
     */
    public static List<Item> itemsWithTag(TagKey<Item> tagKey)
    {
        List<Item> list = new ArrayList<>();
        BuiltInRegistries.ITEM.getTagOrEmpty(tagKey).forEach(
                holder -> list.add(holder.value()));
        return ImmutableList.copyOf(list);
    }

    /**
     * Retrieves a list of blocks that are in the given block tag.
     *
     * @param tagKey The block tag key to get the blocks for.
     * @return A list of blocks in the tag.
     */
    public static List<Block> blocksWithTag(TagKey<Block> tagKey)
    {
        List<Block> list = new ArrayList<>();
        BuiltInRegistries.BLOCK.getTagOrEmpty(tagKey).forEach(
                holder -> list.add(holder.value()));
        return ImmutableList.copyOf(list);
    }

    /**
     * Retrieves a list of block entity types that are in the given block entity type tag.
     *
     * @param tagKey The block entity type tag key to get the block entity types for.
     * @return A list of block entity types in the tag.
     */
    public static List<BlockEntityType<?>> blockEntitiesWithTag(TagKey<BlockEntityType<?>> tagKey)
    {
        List<BlockEntityType<?>> list = new ArrayList<>();
        BuiltInRegistries.BLOCK_ENTITY_TYPE.getTagOrEmpty(tagKey).forEach(
                holder -> list.add(holder.value()));
        return ImmutableList.copyOf(list);
    }

    /**
     * Retrieves a list of entity types that are in the given entity type tag.
     *
     * @param tagKey The entity type tag key to get the entity types for.
     * @return A list of entity types in the tag.
     */
    public static List<EntityType<?>> entitiesWithTag(TagKey<EntityType<?>> tagKey)
    {
        List<EntityType<?>> list = new ArrayList<>();
        BuiltInRegistries.ENTITY_TYPE.getTagOrEmpty(tagKey).forEach(
                holder -> list.add(holder.value()));
        return ImmutableList.copyOf(list);
    }

    /**
     * Retrieves the dimension identifier for the given level.
     *
     * @param level The level to get the dimension identifier for.
     * @return The dimension identifier.
     */
    public static ResourceLocation dimensionId(Level level)
    {
        return level.dimension().location();
    }

    /**
     * Retrieves the dimension name for the given level.
     *
     * @param level The level to get the dimension name for.
     * @return The dimension name.
     */
    public static String dimensionName(Level level)
    {
        return dimensionId(level).toString();
    }

    /**
     * Retrieves a cleaned, readable version of the dimension name.
     *
     * @param level The level to get the clean dimension name for.
     * @return A cleaned dimension name.
     */
    public static String dimensionNameClean(Level level)
    {
        return dimensionNameClean(dimensionName(level));
    }

    /**
     * Retrieves a cleaned, readable version of the dimension name from a string.
     *
     * @param dimensionName The dimension name as a string.
     * @return A cleaned dimension name.
     */
    public static String dimensionNameClean(String dimensionName)
    {
        return dimensionName.substring(dimensionName.indexOf(':') + 1).replace('_', ' ');
    }

    public static boolean validateResource(ResourceLocation id)
    {
        return ResourceLocation.isValidNamespace(id.getNamespace()) && ResourceLocation.isValidPath(id.getPath());
    }
}