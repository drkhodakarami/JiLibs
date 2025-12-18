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

package dev.thementor.api.client.woodset;

import java.util.function.Function;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import dev.thementor.api.shared.utils.StringHelper;
import dev.thementor.api.woodset.WoodSet;

/**
 * Generates data for wood-related items and blocks.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-15")
@ModifiedAt("2025-04-19")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class WoodSetDatagen
{
    public static void generateBlockLootTables(FabricBlockLootTableProvider provider)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            provider.dropSelf(woodSet.planks);
            provider.dropSelf(woodSet.log);
            provider.dropSelf(woodSet.strippedLog);
            provider.dropSelf(woodSet.strippedWood);
            provider.dropSelf(woodSet.wood);
            provider.dropSelf(woodSet.sapling);
            provider.dropSelf(woodSet.stairs);
            provider.add(woodSet.slab, provider::createSlabItemTable);
            provider.dropSelf(woodSet.fence);
            provider.dropSelf(woodSet.fenceGate);
            provider.dropSelf(woodSet.door);
            provider.dropSelf(woodSet.trapdoor);
            provider.dropSelf(woodSet.pressurePlate);
            provider.dropSelf(woodSet.button);
            provider.dropOther(woodSet.sign, woodSet.signItem);
            provider.dropOther(woodSet.wallSign, woodSet.signItem);
            provider.dropOther(woodSet.hangingSign, woodSet.hangingSignItem);
            provider.dropOther(woodSet.wallHangingSign, woodSet.hangingSignItem);

            provider.add(woodSet.leaves,
                             leavesBlock -> provider.createLeavesDrops(leavesBlock, woodSet.sapling, BlockLootSubProvider.NORMAL_LEAVES_SAPLING_CHANCES));
        }
    }

    public static void generateBlockStateAndModels(BlockModelGenerators generator)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            generator.woodProvider(woodSet.log)
                     .log(woodSet.log)
                     .wood(woodSet.wood);
            generator.woodProvider(woodSet.strippedLog)
                     .log(woodSet.strippedLog)
                     .wood(woodSet.strippedWood);
            generator.createTintedLeaves(woodSet.leaves, TexturedModel.LEAVES, 0x00BB0A);
            generator.createCrossBlockWithDefaultItem(woodSet.sapling, BlockModelGenerators.PlantType.NOT_TINTED);
            generator.createHangingSign(woodSet.strippedLog, woodSet.hangingSign, woodSet.wallHangingSign);
            generator.family(woodSet.planks)
                     .generateFor(woodSet.createBlockFamily());
        }
    }

    public static void generateRecipes(RecipeProvider generator, RecipeOutput exporter, HolderGetter<Item> registries)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            ShapelessRecipeBuilder.shapeless(registries, RecipeCategory.BUILDING_BLOCKS, woodSet.planks, 4)
                                      .requires(Ingredient.of(registries.getOrThrow(woodSet.logsItemTag)))
                                      .unlockedBy(BaseHelper.hasTag(woodSet.logsItemTag), generator.has(woodSet.logsItemTag))
                                      .save(exporter);

            ShapedRecipeBuilder.shaped(registries, RecipeCategory.DECORATIONS, woodSet.hangingSignItem, 6)
                                   .define('P', woodSet.planks)
                                   .define('C', ConventionalItemTags.CHAINS)
                                   .pattern("C C")
                                   .pattern("PPP")
                                   .pattern("PPP")
                                   .unlockedBy(RecipeProvider.getHasName(woodSet.planks), generator.has(woodSet.planks))
                                   .unlockedBy(BaseHelper.hasTag(ConventionalItemTags.CHAINS), generator.has(ConventionalItemTags.CHAINS))
                                   .save(exporter);

            ShapedRecipeBuilder.shaped(registries, RecipeCategory.TRANSPORTATION, woodSet.boatItem)
                                   .define('P', woodSet.planks)
                                   .pattern("P P")
                                   .pattern("PPP")
                                   .unlockedBy(RecipeProvider.getHasName(woodSet.planks), generator.has(woodSet.planks))
                                   .save(exporter);

            ShapelessRecipeBuilder.shapeless(registries, RecipeCategory.TRANSPORTATION, woodSet.chestBoatItem)
                                      .requires(Ingredient.of(registries.getOrThrow(woodSet.logsItemTag)))
                                      .requires(Ingredient.of(registries.getOrThrow(ConventionalItemTags.WOODEN_CHESTS)))
                                      .unlockedBy(BaseHelper.hasTag(woodSet.logsItemTag), generator.has(woodSet.logsItemTag))
                                      .unlockedBy(BaseHelper.hasTag(ConventionalItemTags.WOODEN_CHESTS), generator.has(ConventionalItemTags.WOODEN_CHESTS))
                                      .save(exporter);

            generator.generateRecipes(woodSet.createBlockFamily(), FeatureFlagSet.of(FeatureFlags.VANILLA));
        }
    }

    public static void generateItemTags(Function<TagKey<Item>, TagAppender<Item, Item>> provider)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            provider.apply(woodSet.logsItemTag)
                    .add(woodSet.log.asItem())
                    .add(woodSet.strippedLog.asItem())
                    .add(woodSet.wood.asItem())
                    .add(woodSet.strippedWood.asItem());

            provider.apply(ItemTags.LOGS_THAT_BURN)
                    .addTag(woodSet.logsItemTag);

            provider.apply(ItemTags.PLANKS)
                    .add(woodSet.planks.asItem());

            provider.apply(ItemTags.LEAVES)
                    .add(woodSet.leaves.asItem());

            provider.apply(ItemTags.SAPLINGS)
                    .add(woodSet.sapling.asItem());

            provider.apply(ItemTags.WOODEN_BUTTONS)
                    .add(woodSet.button.asItem());

            provider.apply(ItemTags.WOODEN_DOORS)
                    .add(woodSet.door.asItem());

            provider.apply(ItemTags.WOODEN_FENCES)
                    .add(woodSet.fence.asItem());

            provider.apply(ItemTags.FENCE_GATES)
                    .add(woodSet.fenceGate.asItem());

            provider.apply(ItemTags.WOODEN_PRESSURE_PLATES)
                    .add(woodSet.pressurePlate.asItem());

            provider.apply(ItemTags.WOODEN_TRAPDOORS)
                    .add(woodSet.trapdoor.asItem());

            provider.apply(ItemTags.WOODEN_STAIRS)
                    .add(woodSet.stairs.asItem());

            provider.apply(ItemTags.WOODEN_SLABS)
                    .add(woodSet.slab.asItem());

            provider.apply(ItemTags.SIGNS)
                    .add(woodSet.sign.asItem());

            provider.apply(ItemTags.HANGING_SIGNS)
                    .add(woodSet.hangingSign.asItem());

            provider.apply(ItemTags.BOATS)
                    .add(woodSet.boatItem);

            provider.apply(ItemTags.CHEST_BOATS)
                    .add(woodSet.chestBoatItem);
        }
    }

    public static void generateBlockTags(Function<TagKey<Block>, TagAppender<Block, Block>> provider)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            provider.apply(woodSet.logsBlockTag)
                    .add(woodSet.log)
                    .add(woodSet.strippedLog)
                    .add(woodSet.wood)
                    .add(woodSet.strippedWood);

            provider.apply(BlockTags.LOGS_THAT_BURN)
                    .addTag(woodSet.logsBlockTag);

            provider.apply(BlockTags.PLANKS)
                    .add(woodSet.planks);

            provider.apply(BlockTags.LEAVES)
                    .add(woodSet.leaves);

            provider.apply(BlockTags.SAPLINGS)
                    .add(woodSet.sapling);

            provider.apply(BlockTags.WOODEN_BUTTONS)
                    .add(woodSet.button);

            provider.apply(BlockTags.WOODEN_DOORS)
                    .add(woodSet.door);

            provider.apply(BlockTags.WOODEN_FENCES)
                    .add(woodSet.fence);

            provider.apply(BlockTags.FENCE_GATES)
                    .add(woodSet.fenceGate);

            provider.apply(BlockTags.WOODEN_PRESSURE_PLATES)
                    .add(woodSet.pressurePlate);

            provider.apply(BlockTags.WOODEN_TRAPDOORS)
                    .add(woodSet.trapdoor);

            provider.apply(BlockTags.WOODEN_STAIRS)
                    .add(woodSet.stairs);

            provider.apply(BlockTags.WOODEN_SLABS)
                    .add(woodSet.slab);

            provider.apply(BlockTags.STANDING_SIGNS)
                    .add(woodSet.sign);

            provider.apply(BlockTags.WALL_SIGNS)
                    .add(woodSet.wallSign);

            provider.apply(BlockTags.CEILING_HANGING_SIGNS)
                    .add(woodSet.hangingSign);

            provider.apply(BlockTags.WALL_HANGING_SIGNS)
                    .add(woodSet.wallHangingSign);
        }
    }

    public static void generateEntityTags(Function<TagKey<EntityType<?>>, TagAppender<EntityType<?>, EntityType<?>>> provider)
    {
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            provider.apply(EntityTypeTags.BOAT)
                    .add(woodSet.boatEntityType)
                    .add(woodSet.chestBoatEntityType);
        }
    }

    public static void generateEnglishLanguage(FabricLanguageProvider.TranslationBuilder builder)
    {
        String typeCaseName;
        for (WoodSet woodSet : WoodSet.getWoodSets())
        {
            if(woodSet.getName().isEmpty() || woodSet.getName().isBlank())
                throw new IllegalArgumentException("Woodset's name can't be empty or blank!");
            typeCaseName = StringHelper.snakeToTypeCase(woodSet.getName());
            builder.add(woodSet.planks, typeCaseName + " Planks");
            builder.add(woodSet.log, typeCaseName + " Log");
            builder.add(woodSet.strippedLog, "Stripped " + typeCaseName + " Log");
            builder.add(woodSet.strippedWood, "Stripped " + typeCaseName + " Wood");
            builder.add(woodSet.wood, typeCaseName + " Wood");
            builder.add(woodSet.leaves, typeCaseName + " Leaves");
            builder.add(woodSet.sapling, typeCaseName + " Sapling");
            builder.add(woodSet.stairs, typeCaseName + " Stairs");
            builder.add(woodSet.slab, typeCaseName + " Slab");
            builder.add(woodSet.fence, typeCaseName + " Fence");
            builder.add(woodSet.fenceGate, typeCaseName + " Fence Gate");
            builder.add(woodSet.door, typeCaseName + " Door");
            builder.add(woodSet.trapdoor, typeCaseName + " Trapdoor");
            builder.add(woodSet.pressurePlate, typeCaseName + " Pressure Plate");
            builder.add(woodSet.button, typeCaseName + " Button");
            builder.add(woodSet.sign, typeCaseName + " Sign");
            builder.add(woodSet.wallSign, typeCaseName + " Wall Sign");
            builder.add(woodSet.hangingSign, typeCaseName + " Hanging Sign");
            builder.add(woodSet.wallHangingSign, typeCaseName + " Wall Hanging Sign");
            builder.add(woodSet.boatItem, typeCaseName + " Boat");
            builder.add(woodSet.chestBoatItem, typeCaseName + " Chest Boat");
            builder.add(woodSet.boatEntityType, typeCaseName + " Boat");
            builder.add(woodSet.chestBoatEntityType, typeCaseName + " Chest Boat");
            builder.add(woodSet.signItem, typeCaseName + " Sign");
            builder.add(woodSet.hangingSignItem, typeCaseName + " Hanging Sign");
        }
    }
}