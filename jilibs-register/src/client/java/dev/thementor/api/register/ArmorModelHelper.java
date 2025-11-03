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
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.component.Component;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides utility methods for managing armor-related data.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ArmorModelHelper
{
    /**
     * Maps equipment slots to their corresponding armor model names.
     */
    public static Map<String, Identifier> SlotToArmorNames = new HashMap<>();

    static
    {
        SlotToArmorNames.put(EquipmentSlot.HEAD.getName(), ItemModelGenerator.HELMET_TRIM_ID_PREFIX);
        SlotToArmorNames.put(EquipmentSlot.CHEST.getName(), ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX);
        SlotToArmorNames.put(EquipmentSlot.LEGS.getName(), ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX);
        SlotToArmorNames.put(EquipmentSlot.FEET.getName(), ItemModelGenerator.BOOTS_TRIM_ID_PREFIX);
        SlotToArmorNames.put(EquipmentSlot.BODY.getName(), ItemModelGenerator.getTrimAssetIdPrefix(EquipmentSlot.BODY.getName()));
    }

    /**
     * Generates all armor models for the specified armors.
     *
     * @param modID The unique identifier for the mod.
     * @param generator The ItemModelGenerator instance for generating models.
     * @param armors An array of armor items to generate models for.
     */
    public static void generateAllArmorModels(String modID, ItemModelGenerator generator, Item[] armors)
    {
        EquipmentType slot;

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
    public static void generateArmorModel(String modID, ItemModelGenerator generator, Item armor)
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
    public static void generateArmorModel(String modID, ItemModelGenerator generator, Item armor, boolean dyeable)
    {
        Component<EquippableComponent> component = armor.getComponents().getTyped(DataComponentTypes.EQUIPPABLE);
        if (component == null)
            throw new IllegalArgumentException("The item " + armor.getName() + " does not have equippable component");
        Identifier TrimID = SlotToArmorNames.get(component.value().slot().getName());
        String name = Registries.ITEM.getId(armor).getPath();
        RegistryKey<EquipmentAsset> key = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BaseHelper.identifier(modID, name));
        generator.registerArmor(armor, key, TrimID, dyeable);
    }

    /**
     * Builds an EquipmentModel for a humanoid entity.
     *
     * @param modID The unique identifier for the mod.
     * @param name The name of the model.
     * @return An EquipmentModel instance with humanoid layers.
     */
    public static EquipmentModel buildHumanoid(String modID, String name)
    {
        return EquipmentModel.builder().addHumanoidLayers(BaseHelper.identifier(modID, name)).build();
    }

    /**
     * Builds an EquipmentModel for a humanoid and horse entity.
     *
     * @param modID The unique identifier for the mod.
     * @param name The name of the model.
     * @return An EquipmentModel instance with humanoid and horse layers.
     */
    public static EquipmentModel buildHumanoidAndHorse(String modID, String name)
    {
        return EquipmentModel.builder()
                             .addHumanoidLayers(BaseHelper.identifier(modID, name))
                .addLayers(EquipmentModel.LayerType.HORSE_BODY,
                           EquipmentModel.Layer.createWithLeatherColor(BaseHelper.identifier(modID, name), false))
                             .build();
    }
}