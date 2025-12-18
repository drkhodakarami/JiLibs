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

package dev.thementor.api.register;

import java.util.EnumMap;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

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

public class ArmorHelper
{
    /**
     * Creates a new armor material with the specified properties.
     *
     * @param modID               The unique identifier for the mod.
     * @param name                The name of the armor material.
     * @param durability          The durability of the armor material.
     * @param bootDefence         The defence value for boots.
     * @param leggingsDefence     The defence value for leggings.
     * @param chestplateDefence   The defence value for chestplates.
     * @param helmetDefence       The defence value for helmets.
     * @param bodyDefence         The defence value for body armor.
     * @param enchantmentValue    The enchantment value of the armor material.
     * @param equipSound          The sound event to play when equipping the armor.
     * @param toughness           The toughness value of the armor material.
     * @param knockbackResistance The knockback resistance value of the armor material.
     * @param repairIngredient    The tag key for repairing ingredients.
     * @return A new ArmorMaterial instance.
     */
    public static ArmorMaterial getArmorMaterial(String modID, String name, int durability, int bootDefence, int leggingsDefence, int chestplateDefence, int helmetDefence,
                                                 int bodyDefence, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness,
                                                 float knockbackResistance, TagKey<Item> repairIngredient)
    {
        ResourceKey<EquipmentAsset> key = ResourceKey.create(EquipmentAssets.ROOT_ID, BaseHelper.id(modID, name));
        return new ArmorMaterial(durability,
                                 Util.make(new EnumMap<>(ArmorType.class),
                                           (map) ->
                                                           {
                                                               map.put(ArmorType.BOOTS, bootDefence);
                                                               map.put(ArmorType.LEGGINGS, leggingsDefence);
                                                               map.put(ArmorType.CHESTPLATE, chestplateDefence);
                                                               map.put(ArmorType.HELMET, helmetDefence);
                                                               map.put(ArmorType.BODY, bodyDefence);
                                                           }),
                                 enchantmentValue, equipSound,
                                 toughness, knockbackResistance, repairIngredient,
                                 key);
    }
}