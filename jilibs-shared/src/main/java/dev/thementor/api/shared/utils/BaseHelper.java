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

package dev.thementor.api.shared.utils;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.block.Block;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapDecorationType;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.floatprovider.FloatProviderType;
import net.minecraft.util.math.intprovider.IntProviderType;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import org.jetbrains.annotations.NotNull;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

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
    public static Identifier identifier(String modID, @NotNull String path)
    {
        return Identifier.of(modID, path);
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
    public static <T> RegistryKey<T> getKey(String modID, String name, RegistryKey<? extends Registry<T>> registryKey)
    {
        return RegistryKey.of(registryKey, identifier(modID, name));
    }

    /**
     * Generates a tag check string for the given item tag.
     *
     * @param tag The item tag key.
     * @return A tag check string.
     */
    public static @NotNull String hasTag(@NotNull TagKey<Item> tag)
    {
        return "has_" + tag.id().toString();
    }

    /**
     * Retrieves the registry name for a given fluid.
     *
     * @param fluid The fluid to get the registry name for.
     * @return The registry name of the fluid.
     */
    public static String getRegistryName(Fluid fluid)
    {
        return Registries.FLUID.getId(fluid).getPath();
    }

    /**
     * Retrieves the registry name for a given item.
     *
     * @param item The item to get the registry name for.
     * @return The registry name of the item.
     */
    public static String getRegistryName(Item item)
    {
        return Registries.ITEM.getId(item).getPath();
    }

    /**
     * Retrieves the registry name for a given item stack.
     *
     * @param stack The item stack to get the registry name for.
     * @return The registry name of the item in the stack.
     */
    public static String getRegistryName(ItemStack stack)
    {
        return Registries.ITEM.getId(stack.getItem()).getPath();
    }

    /**
     * Retrieves the registry name for a given block.
     *
     * @param block The block to get the registry name for.
     * @return The registry name of the block.
     */
    public static String getRegistryName(Block block)
    {
        return Registries.BLOCK.getId(block).getPath();
    }

    /**
     * Retrieves the registry name for a given entity type.
     *
     * @param entityType The entity type to get the registry name for.
     * @return The registry name of the entity type.
     */
    public static String getRegistryName(EntityType<?> entityType)
    {
        return Registries.ENTITY_TYPE.getId(entityType).getPath();
    }

    /**
     * Retrieves the registry name for a given potion.
     *
     * @param potion The potion to get the registry name for.
     * @return The registry name of the potion.
     */
    public static String getRegistryName(Potion potion)
    {
        return Objects.requireNonNull(Registries.POTION.getId(potion)).getPath();
    }

    /**
     * Retrieves the registry name for a given block entity type.
     *
     * @param blockEntityType The block entity type to get the registry name for.
     * @return The registry name of the block entity type.
     */
    public static String getRegistryName(BlockEntityType<?> blockEntityType)
    {
        return Objects.requireNonNull(Registries.BLOCK_ENTITY_TYPE.getId(blockEntityType)).getPath();
    }

    /**
     * Retrieves the registry name for a given custom stat identifier.
     *
     * @param identifier The custom stat identifier to get the registry name for.
     * @return The registry name of the custom stat.
     */
    public static String getRegistryName(Identifier identifier)
    {
        return Objects.requireNonNull(Registries.CUSTOM_STAT.getId(identifier)).getPath();
    }

    /**
     * Retrieves the registry name for a given chunk status.
     *
     * @param chunkStatus The chunk status to get the registry name for.
     * @return The registry name of the chunk status.
     */
    public static String getRegistryName(ChunkStatus chunkStatus)
    {
        return Registries.CHUNK_STATUS.getId(chunkStatus).getPath();
    }

    /**
     * Retrieves the registry name for a given entity attribute.
     *
     * @param entityAttribute The entity attribute to get the registry name for.
     * @return The registry name of the entity attribute.
     */
    public static String getRegistryName(EntityAttribute entityAttribute)
    {
        return Objects.requireNonNull(Registries.ATTRIBUTE.getId(entityAttribute)).getPath();
    }

    /**
     * Retrieves the registry name for a given screen handler type.
     *
     * @param screenHandlerType The screen handler type to get the registry name for.
     * @return The registry name of the screen handler type.
     */
    public static String getRegistryName(ScreenHandlerType<?> screenHandlerType)
    {
        return Objects.requireNonNull(Registries.SCREEN_HANDLER.getId(screenHandlerType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe type.
     *
     * @param recipeType The recipe type to get the registry name for.
     * @return The registry name of the recipe type.
     */
    public static String getRegistryName(RecipeType<?> recipeType)
    {
        return Objects.requireNonNull(Registries.RECIPE_TYPE.getId(recipeType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe serializer.
     *
     * @param recipeSerializer The recipe serializer to get the registry name for.
     * @return The registry name of the recipe serializer.
     */
    public static String getRegistryName(RecipeSerializer<?> recipeSerializer)
    {
        return Objects.requireNonNull(Registries.RECIPE_SERIALIZER.getId(recipeSerializer)).getPath();
    }

    /**
     * Retrieves the registry name for a given villager type.
     *
     * @param villagerType The villager type to get the registry name for.
     * @return The registry name of the villager type.
     */
    public static String getRegistryName(VillagerType villagerType)
    {
        return Registries.VILLAGER_TYPE.getId(villagerType).getPath();
    }

    /**
     * Retrieves the registry name for a given villager profession.
     *
     * @param villagerProfession The villager profession to get the registry name for.
     * @return The registry name of the villager profession.
     */
    public static String getRegistryName(VillagerProfession villagerProfession)
    {
        return Registries.VILLAGER_PROFESSION.getId(villagerProfession).getPath();
    }

    /**
     * Retrieves the registry name for a given float provider type.
     *
     * @param floatProviderType The float provider type to get the registry name for.
     * @return The registry name of the float provider type.
     */
    public static String getRegistryName(FloatProviderType<?> floatProviderType)
    {
        return Objects.requireNonNull(Registries.FLOAT_PROVIDER_TYPE.getId(floatProviderType)).getPath();
    }

    /**
     * Retrieves the registry name for a given int provider type.
     *
     * @param intProviderType The int provider type to get the registry name for.
     * @return The registry name of the int provider type.
     */
    public static String getRegistryName(IntProviderType<?> intProviderType)
    {
        return Objects.requireNonNull(Registries.INT_PROVIDER_TYPE.getId(intProviderType)).getPath();
    }

    /**
     * Retrieves the registry name for a given decorated pot pattern.
     *
     * @param decoratedPotPattern The decorated pot pattern to get the registry name for.
     * @return The registry name of the decorated pot pattern.
     */
    public static String getRegistryName(DecoratedPotPattern decoratedPotPattern)
    {
        return Objects.requireNonNull(Registries.DECORATED_POT_PATTERN.getId(decoratedPotPattern)).getPath();
    }

    /**
     * Retrieves the registry name for a given item group.
     *
     * @param itemGroup The item group to get the registry name for.
     * @return The registry name of the item group.
     */
    public static String getRegistryName(ItemGroup itemGroup)
    {
        return Objects.requireNonNull(Registries.ITEM_GROUP.getId(itemGroup)).getPath();
    }

    /**
     * Retrieves the registry name for a given criterion.
     *
     * @param criterion The criterion to get the registry name for.
     * @return The registry name of the criterion.
     */
    public static String getRegistryName(Criterion<?> criterion)
    {
        return Objects.requireNonNull(Registries.CRITERION.getId(criterion)).getPath();
    }

    /**
     * Retrieves the registry name for a given data component type or enchantment effect component type.
     *
     * @param componentType The component type to get the registry name for.
     * @param isEnchantment Whether this is an enchantment effect component type.
     * @return The registry name of the component type.
     */
    public static String getRegistryName(ComponentType<?> componentType, boolean isEnchantment)
    {
        return isEnchantment
               ? Objects.requireNonNull(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE.getId(componentType)).getPath()
               : Objects.requireNonNull(Registries.DATA_COMPONENT_TYPE.getId(componentType)).getPath();
    }

    /**
     * Retrieves the registry name for a given map decoration type.
     *
     * @param mapDecorationType The map decoration type to get the registry name for.
     * @return The registry name of the map decoration type.
     */
    public static String getRegistryName(MapDecorationType mapDecorationType)
    {
        return Objects.requireNonNull(Registries.MAP_DECORATION_TYPE.getId(mapDecorationType)).getPath();
    }

    /**
     * Retrieves the registry name for a given recipe book category.
     *
     * @param recipeBookCategory The recipe book category to get the registry name for.
     * @return The registry name of the recipe book category.
     */
    public static String getRegistryName(RecipeBookCategory recipeBookCategory)
    {
        return Objects.requireNonNull(Registries.RECIPE_BOOK_CATEGORY.getId(recipeBookCategory)).getPath();
    }

    /**
     * Retrieves a list of items that are in the given item tag.
     *
     * @param tagKey The item tag key to get the items for.
     * @return A list of items in the tag.
     */
    public static List<Item> getItemsWithTag(TagKey<Item> tagKey)
    {
        return Registries.ITEM.stream()
                              .filter(item -> Registries.ITEM.getEntry(item).isIn(tagKey))
                              .toList();
    }

    /**
     * Retrieves a list of blocks that are in the given block tag.
     *
     * @param tagKey The block tag key to get the blocks for.
     * @return A list of blocks in the tag.
     */
    public static List<Block> getBlocksWithTag(TagKey<Block> tagKey)
    {
        return Registries.BLOCK.stream()
                               .filter(block -> Registries.BLOCK.getEntry(block).isIn(tagKey))
                               .toList();
    }

    /**
     * Retrieves a list of block entity types that are in the given block entity type tag.
     *
     * @param tagKey The block entity type tag key to get the block entity types for.
     * @return A list of block entity types in the tag.
     */
    public static List<BlockEntityType<?>> getBlockEntitiesWithTag(TagKey<BlockEntityType<?>> tagKey)
    {
        return Registries.BLOCK_ENTITY_TYPE.stream()
                                           .filter(blockEntityType -> Registries.BLOCK_ENTITY_TYPE.getEntry(blockEntityType).isIn(tagKey))
                                           .toList();
    }

    /**
     * Retrieves a list of entity types that are in the given entity type tag.
     *
     * @param tagKey The entity type tag key to get the entity types for.
     * @return A list of entity types in the tag.
     */
    public static List<EntityType<?>> getEntitiesWithTag(TagKey<EntityType<?>> tagKey)
    {
        return Registries.ENTITY_TYPE.stream()
                                     .filter(entityType -> Registries.ENTITY_TYPE.getEntry(entityType).isIn(tagKey))
                                     .toList();
    }

    /**
     * Retrieves the dimension identifier for the given world.
     *
     * @param world The world to get the dimension identifier for.
     * @return The dimension identifier.
     */
    public static Identifier getDimensionId(World world)
    {
        return world.getRegistryKey().getValue();
    }

    /**
     * Retrieves the dimension name for the given world.
     *
     * @param world The world to get the dimension name for.
     * @return The dimension name.
     */
    public static String getDimensionName(World world)
    {
        return getDimensionId(world).toString();
    }

    /**
     * Retrieves a cleaned, readable version of the dimension name.
     *
     * @param world The world to get the clean dimension name for.
     * @return A cleaned dimension name.
     */
    public static String getDimensionNameClean(World world)
    {
        return getDimensionNameClean(getDimensionName(world));
    }

    /**
     * Retrieves a cleaned, readable version of the dimension name from a string.
     *
     * @param dimensionName The dimension name as a string.
     * @return A cleaned dimension name.
     */
    public static String getDimensionNameClean(String dimensionName)
    {
        return dimensionName.substring(dimensionName.indexOf(':') + 1).replace('_', ' ');
    }

    public static TagKey<Item> ItemTagOf(String modid, String id)
    {
        return TagKey.of(RegistryKeys.ITEM, identifier(modid, id));
    }

    public static TagKey<Block> BlockTagOf(String modid, String id)
    {
        return TagKey.of(RegistryKeys.BLOCK, identifier(modid, id));
    }

    public static TagKey<BlockEntityType<?>> BlockEntityTypeTagOf(String modid, String id)
    {
        return TagKey.of(RegistryKeys.BLOCK_ENTITY_TYPE, identifier(modid, id));
    }

    public static TagKey<EntityType<?>> EntityTypeTagOf(String modid, String id)
    {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, identifier(modid, id));
    }

    public static boolean validIdentifier(Identifier id)
    {
        return Identifier.isNamespaceValid(id.getNamespace()) && Identifier.isPathValid(id.getPath());
    }
}