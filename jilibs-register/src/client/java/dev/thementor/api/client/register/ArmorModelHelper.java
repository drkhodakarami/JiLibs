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

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Provides utility methods for managing armor-related data.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ArmorModelHelper
{
    /**
     * Maps equipment slots to their corresponding armor model names.
     */
    public static Map<String, ResourceLocation> SlotToArmorNames = new HashMap<>();

    static
    {
        SlotToArmorNames.put(EquipmentSlot.HEAD.getName(), ItemModelGenerators.TRIM_PREFIX_HELMET);
        SlotToArmorNames.put(EquipmentSlot.CHEST.getName(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        SlotToArmorNames.put(EquipmentSlot.LEGS.getName(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        SlotToArmorNames.put(EquipmentSlot.FEET.getName(), ItemModelGenerators.TRIM_PREFIX_BOOTS);
        SlotToArmorNames.put(EquipmentSlot.BODY.getName(), ItemModelGenerators.prefixForSlotTrim(EquipmentSlot.BODY.getName()));
    }

    /**
     * Generates all armor models for the specified armors.
     *
     * @param modID The unique identifier for the mod.
     * @param generator The ItemModelGenerator instance for generating models.
     * @param armors An array of armor items to generate models for.
     */
    public static void generateAllArmorModels(String modID, ItemModelGenerators generator, Item[] armors)
    {
        ArmorType slot;

        for (Item armor : armors)
            generateArmorModel(modID, generator, armor);
    }

    /**
     * Generates an armor model for the specified armor item.
     *
     * @param modID The unique identifier for the mod.
     * @param generator The ItemModelGenerator instance for generating models.
     * @param armor The armor item to generate a model for.
     */
    public static void generateArmorModel(String modID, ItemModelGenerators generator, Item armor)
    {
        generateArmorModel(modID, generator, armor, false);
    }

    /**
     * Generates an armor model for the specified armor item with an option to mark it as dyeable.
     *
     * @param modID The unique identifier for the mod.
     * @param generator The ItemModelGenerator instance for generating models.
     * @param armor The armor item to generate a model for.
     * @param dyeable Whether the armor model should be marked as dyeable.
     */
    public static void generateArmorModel(String modID, ItemModelGenerators generator, Item armor, boolean dyeable)
    {
        TypedDataComponent<Equippable> component = armor.components().getTyped(DataComponents.EQUIPPABLE);
        if (component == null)
            throw new IllegalArgumentException("The item " + armor.getName() + " does not have equippable component");
        ResourceLocation TrimID = SlotToArmorNames.get(component.value().slot().getName());
        String name = BuiltInRegistries.ITEM.getKey(armor).getPath();
        ResourceKey<EquipmentAsset> key = ResourceKey.create(EquipmentAssets.ROOT_ID, BaseHelper.id(modID, name));
        generator.generateTrimmableItem(armor, key, TrimID, dyeable);
    }

    /**
     * Builds an EquipmentModel for a humanoid entity.
     *
     * @param modID The unique identifier for the mod.
     * @param name The name of the model.
     * @return An EquipmentModel instance with humanoid layers.
     */
    public static EquipmentClientInfo buildHumanoid(String modID, String name)
    {
        return EquipmentClientInfo.builder().addHumanoidLayers(BaseHelper.id(modID, name)).build();
    }

    /**
     * Builds an EquipmentModel for a humanoid and horse entity.
     *
     * @param modID The unique identifier for the mod.
     * @param name The name of the model.
     * @return An EquipmentModel instance with humanoid and horse layers.
     */
    public static EquipmentClientInfo buildHumanoidAndHorse(String modID, String name)
    {
        return EquipmentClientInfo.builder()
                             .addHumanoidLayers(BaseHelper.id(modID, name))
                .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY,
                           EquipmentClientInfo.Layer.leatherDyeable(BaseHelper.id(modID, name), false))
                             .build();
    }
}