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
import dev.thementor.api.shared.utils.StringHelper;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.TexturedModel;
import net.minecraft.data.loottable.BlockLootTableGenerator;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.tag.ProvidedTagBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.function.Function;

/**
 * Generates data for wood-related items and blocks.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@CreatedAt("2025-04-15")
@ModifiedAt("2025-04-19")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class JiWoodSetDatagen
{
    public static void generateBlockLootTables(FabricBlockLootTableProvider provider)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
        {
            provider.addDrop(woodSet.planks);
            provider.addDrop(woodSet.log);
            provider.addDrop(woodSet.strippedLog);
            provider.addDrop(woodSet.strippedWood);
            provider.addDrop(woodSet.wood);
            provider.addDrop(woodSet.sapling);
            provider.addDrop(woodSet.stairs);
            provider.addDrop(woodSet.slab, provider::slabDrops);
            provider.addDrop(woodSet.fence);
            provider.addDrop(woodSet.fenceGate);
            provider.addDrop(woodSet.door);
            provider.addDrop(woodSet.trapdoor);
            provider.addDrop(woodSet.pressurePlate);
            provider.addDrop(woodSet.button);
            provider.addDrop(woodSet.sign, woodSet.signItem);
            provider.addDrop(woodSet.wallSign, woodSet.signItem);
            provider.addDrop(woodSet.hangingSign, woodSet.hangingSignItem);
            provider.addDrop(woodSet.wallHangingSign, woodSet.hangingSignItem);

            provider.addDrop(woodSet.leaves,
                             leavesBlock -> provider.leavesDrops(leavesBlock, woodSet.sapling, BlockLootTableGenerator.SAPLING_DROP_CHANCE));
        }
    }

    public static void generateBlockStateAndModels(BlockStateModelGenerator generator)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
        {
            generator.createLogTexturePool(woodSet.log)
                     .log(woodSet.log)
                     .wood(woodSet.wood);
            generator.createLogTexturePool(woodSet.strippedLog)
                     .log(woodSet.strippedLog)
                     .wood(woodSet.strippedWood);
            generator.registerTintedBlockAndItem(woodSet.leaves, TexturedModel.LEAVES, 0x00BB0A);
            generator.registerTintableCross(woodSet.sapling, BlockStateModelGenerator.CrossType.NOT_TINTED);
            generator.registerHangingSign(woodSet.strippedLog, woodSet.hangingSign, woodSet.wallHangingSign);
            generator.registerCubeAllModelTexturePool(woodSet.planks)
                     .family(woodSet.createBlockFamily());
        }
    }

    public static void generateRecipes(RecipeGenerator generator, RecipeExporter exporter, RegistryEntryLookup<Item> registries)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
        {
            ShapelessRecipeJsonBuilder.create(registries, RecipeCategory.BUILDING_BLOCKS, woodSet.planks, 4)
                                      .input(Ingredient.ofTag(registries.getOrThrow(woodSet.logsItemTag)))
                                      .criterion(BaseHelper.hasTag(woodSet.logsItemTag), generator.conditionsFromTag(woodSet.logsItemTag))
                                      .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(registries, RecipeCategory.DECORATIONS, woodSet.hangingSignItem, 6)
                                   .input('P', woodSet.planks)
                                   .input('C', ConventionalItemTags.CHAINS)
                                   .pattern("C C")
                                   .pattern("PPP")
                                   .pattern("PPP")
                                   .criterion(RecipeGenerator.hasItem(woodSet.planks), generator.conditionsFromItem(woodSet.planks))
                                   .criterion(BaseHelper.hasTag(ConventionalItemTags.CHAINS), generator.conditionsFromTag(ConventionalItemTags.CHAINS))
                                   .offerTo(exporter);

            ShapedRecipeJsonBuilder.create(registries, RecipeCategory.TRANSPORTATION, woodSet.boatItem)
                                   .input('P', woodSet.planks)
                                   .pattern("P P")
                                   .pattern("PPP")
                                   .criterion(RecipeGenerator.hasItem(woodSet.planks), generator.conditionsFromItem(woodSet.planks))
                                   .offerTo(exporter);

            ShapelessRecipeJsonBuilder.create(registries, RecipeCategory.TRANSPORTATION, woodSet.chestBoatItem)
                                      .input(Ingredient.ofTag(registries.getOrThrow(woodSet.logsItemTag)))
                                      .input(Ingredient.ofTag(registries.getOrThrow(ConventionalItemTags.WOODEN_CHESTS)))
                                      .criterion(BaseHelper.hasTag(woodSet.logsItemTag), generator.conditionsFromTag(woodSet.logsItemTag))
                                      .criterion(BaseHelper.hasTag(ConventionalItemTags.WOODEN_CHESTS), generator.conditionsFromTag(ConventionalItemTags.WOODEN_CHESTS))
                                      .offerTo(exporter);

            generator.generateFamily(woodSet.createBlockFamily(), FeatureSet.empty());
        }
    }

    public static void generateItemTags(Function<TagKey<Item>, ProvidedTagBuilder<Item, Item>> provider)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
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

    public static void generateBlockTags(Function<TagKey<Block>, ProvidedTagBuilder<Block, Block>> provider)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
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

    public static void generateEntityTags(Function<TagKey<EntityType<?>>, ProvidedTagBuilder<EntityType<?>, EntityType<?>>> provider)
    {
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
        {
            provider.apply(EntityTypeTags.BOAT)
                    .add(woodSet.boatEntityType)
                    .add(woodSet.chestBoatEntityType);
        }
    }

    public static void generateEnglishLanguage(FabricLanguageProvider.TranslationBuilder builder)
    {
        String typeCaseName;
        for (JiWoodSet woodSet : JiWoodSet.getWoodSets())
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