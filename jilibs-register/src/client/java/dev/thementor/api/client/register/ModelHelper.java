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

package dev.thementor.api.client.register;

import java.util.Optional;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Provides utility methods for generating block state models.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ModelHelper
{
    public static final TextureSlot ORE_TEXTURE_KEY = TextureSlot.create("ore");
    public static final TextureSlot BASE_TEXTURE_KEY = TextureSlot.create("base");

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
    public static void registerOrientableVariantBlock(BlockModelGenerators generator, Block block, BooleanProperty property)
    {
        MultiVariant blockOFF = BlockModelGenerators.plainVariant(TexturedModel.ORIENTABLE_ONLY_TOP.create(block, generator.modelOutput));
        ResourceLocation blockFront = TextureMapping.getBlockTexture(block, "_front_on");
        MultiVariant blockON = BlockModelGenerators.plainVariant(TexturedModel.ORIENTABLE_ONLY_TOP.get(block)
                                                                                                      .updateTextures(textureMap ->
                                                                                                                        textureMap.put(TextureSlot.FRONT, blockFront))
                                                                                                      .createWithSuffix(block, "_on", generator.modelOutput));
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                                                        .with(BlockModelGenerators.createBooleanModelDispatch(property, blockON, blockOFF))
                                                                                .with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING));
    }

    /**
     * Registers a cube variant block with boolean property.
     *
     * @param generator the block state model generator
     * @param block       the block to register
     * @param property    the boolean property used for determining the model state
     */
    @ThanksTo(discordUsers = "Waveless")
    public static void registerCubeVariantBlock(BlockModelGenerators generator, Block block, BooleanProperty property)
    {
        MultiVariant cubeOff = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(block, generator.modelOutput));
        MultiVariant cubeOn = BlockModelGenerators.plainVariant((generator.createSuffixedVariant(block, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube)));
        PropertyDispatch<MultiVariant> cubeStatus = BlockModelGenerators.createBooleanModelDispatch(property, cubeOn, cubeOff);
        generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                                                                                .with(cubeStatus));
    }

    public static void registerSingleton(BlockModelGenerators generator, Block block, TexturedModel.Provider modelFactory)
    {
        generator.blockStateOutput.accept(
                BlockModelGenerators
                        .createSimpleBlock(block,
                                                   BlockModelGenerators
                                                           .plainVariant(modelFactory.create(block, generator.modelOutput))));
    }


    // private static final Model ORE_MODEL = ModelHelper.getBlockModel(MOD_ID, "ore", ModelHelper.ORE_TEXTURE_KEY, ModelHelper.BASE_TEXTURE_KEY)
    public static ModelTemplate getBlockModel(String modid, String parent, TextureSlot... requiredTextureKeys)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modid, "block/parent/" + parent)),
                         Optional.empty(), requiredTextureKeys);
    }

    // private static final Model ORE_MODEL = ModelHelper.getBlockModel(MOD_ID, "ore")
    public static ModelTemplate getBlockModel(String modid, String parent)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modid, "block/parent/" + parent)),
                         Optional.empty(), ORE_TEXTURE_KEY, BASE_TEXTURE_KEY);
    }

    // private static final Model ORE_MODEL = ModelHelper.getOreBlockModel(MOD_ID)
    public static ModelTemplate getOREBlockModel(String modid)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(modid, "block/parent/" + ORE_MODEL_PARENT_NAME)),
                         Optional.empty(), ORE_TEXTURE_KEY, BASE_TEXTURE_KEY);
    }

    //private static final TexturedModel.Factory ORE = makeFactory(ModelProvider::ore, ORE_MODEL);

    //private static final TexturedModel.Factory STONE = makeFactory(ModelProvider::stoneOre, ORE_MODEL); // Block Name: "pentlandite_ore"
    //private static final TexturedModel.Factory DEEPSLATE = makeFactory(ModelProvider::deepslateOre, ORE_MODEL); // Block Name: "deepslate_pentlandite_ore"
    //public static final TexturedModel.Factory NETHER = makeFactory(ModelProvider::netherOre, ORE_MODEL); // Block Name:  "nether_pentlandite_ore"
    //public static final TexturedModel.Factory END = makeFactory(ModelProvider::endOre, ORE_MODEL); // Block Name:  "end_pentlandite_ore"

    // registerSingleton(generator, block, DEEPSLATE)

    public static TextureMapping getOreTextureMap(Block block)
    {
        ResourceLocation identifier = TextureMapping.getBlockTexture(block);
        return getOreTextureMap(identifier);
    }

    public static TextureMapping getOreTextureMap(ResourceLocation id)
    {
        return (new TextureMapping()).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMapping getStoneOreTextureMap(Block block)
    {
        ResourceLocation identifier = TextureMapping.getBlockTexture(block);
        return getStoneOreTextureMap(identifier);
    }

    public static TextureMapping getStoneOreTextureMap(ResourceLocation id)
    {
        return (new TextureMapping()).put(BASE_TEXTURE_KEY, ResourceLocation.withDefaultNamespace("block/stone")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMapping getDeepslateOreTextureMap(Block block)
    {
        ResourceLocation identifier = TextureMapping.getBlockTexture(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("deepslate_", "");
        return getDeepslateOreTextureMap(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    public static TextureMapping getDeepslateOreTextureMap(ResourceLocation id)
    {
        return (new TextureMapping()).put(BASE_TEXTURE_KEY, ResourceLocation.withDefaultNamespace("block/deepslate")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMapping getNetherOreTextureMap(Block block)
    {
        ResourceLocation identifier = TextureMapping.getBlockTexture(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("nether_", "");
        return getNetherOreTextureMap(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    public static TextureMapping getNetherOreTextureMap(ResourceLocation id)
    {
        return (new TextureMapping()).put(BASE_TEXTURE_KEY, ResourceLocation.withDefaultNamespace("block/netherrack")).put(ORE_TEXTURE_KEY, id);
    }

    public static TextureMapping getEndOreTextureMap(Block block)
    {
        ResourceLocation identifier = TextureMapping.getBlockTexture(block);
        String namespace = identifier.getNamespace();
        String path = identifier.getPath().replace("end_", "");
        return getEndOreTextureMap(ResourceLocation.fromNamespaceAndPath(namespace, path));
    }

    public static TextureMapping getEndOreTextureMap(ResourceLocation id)
    {
        return (new TextureMapping()).put(BASE_TEXTURE_KEY, ResourceLocation.withDefaultNamespace("block/end_stone")).put(ORE_TEXTURE_KEY, id);
    }

    public static MultiVariant createWeightedVariant(ResourceLocation id, Variant.SimpleModelState modelState)
    {
        return new MultiVariant(WeightedList.of(new Variant(id, modelState)));
    }

}