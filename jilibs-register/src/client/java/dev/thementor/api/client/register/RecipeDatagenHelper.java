package dev.thementor.api.client.register;

import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class RecipeDatagenHelper
{
    private void generateFamilyExcludeVariant(RecipeProvider provider, RecipeOutput output, BlockFamily family, FeatureFlagSet enabledFeatures, List<BlockFamily.Variant> excluded)
    {
        family.getVariants()
            .forEach((variant, block) ->
                     {
                         if (excluded.contains(variant))
                             return;

                         if (block.requiredFeatures().isSubsetOf(enabledFeatures))
                         {
                             ItemLike itemConvertible = provider.getBaseBlock(family, variant);
                             if (RecipeProvider.SHAPE_BUILDERS.get(variant) != null)
                             {
                                 RecipeBuilder craftingRecipeJsonBuilder =
                                         RecipeProvider.SHAPE_BUILDERS.get(variant)
                                                                      .create(provider, block, itemConvertible);
                                 family.getRecipeGroupPrefix().ifPresent(
                                         (group) ->
                                                 craftingRecipeJsonBuilder
                                                         .group(group + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getRecipeGroup())));
                                 craftingRecipeJsonBuilder
                                         .unlockedBy(family.getRecipeUnlockedBy()
                                                          .orElseGet(
                                                                  () ->
                                                                          RecipeProvider.getHasName(itemConvertible)),
                                                                                                    provider.has(itemConvertible));
                                 craftingRecipeJsonBuilder.save(output);
                             }

                             if (variant == BlockFamily.Variant.CRACKED)
                                 provider.smeltingResultFromBase(block, itemConvertible);
                         }
                     });
    }
}