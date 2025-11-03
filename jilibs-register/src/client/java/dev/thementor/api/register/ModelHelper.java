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
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.client.render.model.json.ModelVariant;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;

import java.util.Optional;

/**
 * Provides utility methods for generating block state models.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ModelHelper
{
    public static final TextureKey ORE_TEXTURE_KEY = TextureKey.of("ore");
    public static final TextureKey BASE_TEXTURE_KEY = TextureKey.of("base");

    public static final String ORE_MODEL_PARENT_NAME = "ore";

    /**
     * Private constructor to prevent instantiation.
     */
    public ModelHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    // look into registerCooker from BlockStateModelGenerator
    // look into register() from BlockStateModelGenerator and it's Furnace registration

    /**
     * Registers an orientable variant block with boolean property.
     *
     * @param generator the block state model generator
     * @param block       the block to register
     * @param property    the boolean property used for determining the model state
     */
    public static void registerOrientableVariantBlock(BlockStateModelGenerator generator, Block block, BooleanProperty property)
    {
        WeightedVariant blockOFF = BlockStateModelGenerator.createWeightedVariant(TexturedModel.ORIENTABLE.upload(block, generator.modelCollector));
        Identifier blockFront = TextureMap.getSubId(block, "_front_on");
        WeightedVariant blockON = BlockStateModelGenerator.createWeightedVariant(TexturedModel.ORIENTABLE.get(block)
                                                                                                      .textures(textureMap ->
                                                                                                                        textureMap.put(TextureKey.FRONT, blockFront))
                                                                                                      .upload(block, "_on", generator.modelCollector));
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block)
                                                        .with(BlockStateModelGenerator.createBooleanModelMap(property, blockON, blockOFF))
                                                                                .apply(BlockStateModelGenerator.NORTH_DEFAULT_HORIZONTAL_ROTATION_OPERATIONS));
    }

    /**
     * Registers a cube variant block with boolean property.
     *
     * @param generator the block state model generator
     * @param block       the block to register
     * @param property    the boolean property used for determining the model state
     */
    @ThanksTo(discordUsers = "Waveless")
    public static void registerCubeVariantBlock(BlockStateModelGenerator generator, Block block, BooleanProperty property)
    {
        WeightedVariant cubeOff = BlockStateModelGenerator.createWeightedVariant(TexturedModel.CUBE_ALL.upload(block, generator.modelCollector));
        WeightedVariant cubeOn = BlockStateModelGenerator.createWeightedVariant((generator.createSubModel(block, "_on", Models.CUBE_ALL, TextureMap::all)));
        BlockStateVariantMap<WeightedVariant> cubeStatus = BlockStateModelGenerator.createBooleanModelMap(property, cubeOn, cubeOff);
        generator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(block)
                                                                                .with(cubeStatus));
    }

    public static void registerSingleton(BlockStateModelGenerator generator, Block block, TexturedModel.Factory modelFactory)
    {
        generator.blockStateCollector.accept(
                BlockStateModelGenerator
                        .createSingletonBlockState(block,
                                                   BlockStateModelGenerator
                                                           .createWeightedVariant(modelFactory.upload(block, generator.modelCollector))));
    }


    // private static final Model ORE_MODEL = ModelHelper.getBlockModel(MOD_ID, "ore", ModelHelper.ORE_TEXTURE_KEY, ModelHelper.BASE_TEXTURE_KEY)
    public static Model getBlockModel(String modid, String parent, TextureKey... requiredTextureKeys)
    {
        return new Model(Optional.of(Identifier.of(modid, "block/parent/" + parent)),
                         Optional.empty(), requiredTextureKeys);
    }

    // private static final Model ORE_MODEL = ModelHelper.getBlockModel(MOD_ID, "ore")
    public static Model getBlockModel(String modid, String parent)
    {
        return new Model(Optional.of(Identifier.of(modid, "block/parent/" + parent)),
                         Optional.empty(), ORE_TEXTURE_KEY, BASE_TEXTURE_KEY);
    }

    // private static final Model ORE_MODEL = ModelHelper.getOreBlockModel(MOD_ID)
    public static Model getOREBlockModel(String modid)
    {
        return new Model(Optional.of(Identifier.of(modid, "block/parent/" + ORE_MODEL_PARENT_NAME)),
                         Optional.empty(), ORE_TEXTURE_KEY, BASE_TEXTURE_KEY);
    }

    //private static final TexturedModel.Factory ORE = makeFactory(ModelProvider::ore, ORE_MODEL);

    //private static final TexturedModel.Factory STONE = makeFactory(ModelProvider::stoneOre, ORE_MODEL); // Block Name: "pentlandite_ore"
    //private static final TexturedModel.Factory DEEPSLATE = makeFactory(ModelProvider::deepslateOre, ORE_MODEL); // Block Name: "deepslate_pentlandite_ore"
    //public static final TexturedModel.Factory NETHER = makeFactory(ModelProvider::netherOre, ORE_MODEL); // Block Name:  "nether_pentlandite_ore"
    //public static final TexturedModel.Factory END = makeFactory(ModelProvider::endOre, ORE_MODEL); // Block Name:  "end_pentlandite_ore"

    // registerSingleton(generator, block, DEEPSLATE)

    public static TextureMap getOreTextureMap(Block block)
    {
        Identifier identifier = TextureMap.getId(block);
        return getOreTextureMap(identifier);
    }

    public static TextureMap getOreTextureMap(Identifier id)
    {
        return (new TextureMap()).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMap getStoneOreTextureMap(Block block)
    {
        Identifier identifier = TextureMap.getId(block);
        return getStoneOreTextureMap(identifier);
    }

    public static TextureMap getStoneOreTextureMap(Identifier id)
    {
        return (new TextureMap()).put(BASE_TEXTURE_KEY, Identifier.ofVanilla("block/stone")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMap getDeepslateOreTextureMap(Block block)
    {
        Identifier identifier = TextureMap.getId(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("deepslate_", "");
        return getDeepslateOreTextureMap(Identifier.of(namespace, path));
    }

    public static TextureMap getDeepslateOreTextureMap(Identifier id)
    {
        return (new TextureMap()).put(BASE_TEXTURE_KEY, Identifier.ofVanilla("block/deepslate")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMap getNetherOreTextureMap(Block block)
    {
        Identifier identifier = TextureMap.getId(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("nether_", "");
        return getNetherOreTextureMap(Identifier.of(namespace, path));
    }

    public static TextureMap getNetherOreTextureMap(Identifier id)
    {
        return (new TextureMap()).put(BASE_TEXTURE_KEY, Identifier.ofVanilla("block/netherrack")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMap getEndOreTextureMap(Block block)
    {
        Identifier identifier = TextureMap.getId(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("end_", "");
        return getEndOreTextureMap(Identifier.of(namespace, path));
    }

    public static TextureMap getEndOreTextureMap(Identifier id)
    {
        return (new TextureMap()).put(BASE_TEXTURE_KEY, Identifier.ofVanilla("block/end_stone")).put(ORE_TEXTURE_KEY, id);
    }

    public static WeightedVariant createWeightedVariant(Identifier id, ModelVariant.ModelState modelState)
    {
        return new WeightedVariant(Pool.of(new ModelVariant(id, modelState)));
    }

}