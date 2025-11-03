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
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Util;

import java.util.EnumMap;

/**
 * Provides utility methods for managing armor-related data.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
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
                                                 int bodyDefence, int enchantmentValue, RegistryEntry<SoundEvent> equipSound, float toughness,
                                                 float knockbackResistance, TagKey<Item> repairIngredient)
    {
        RegistryKey<EquipmentAsset> key = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, BaseHelper.identifier(modID, name));
        return new ArmorMaterial(durability,
                                 Util.make(new EnumMap<>(EquipmentType.class),
                                            (map) ->
                                                           {
                                                               map.put(EquipmentType.BOOTS, bootDefence);
                                                               map.put(EquipmentType.LEGGINGS, leggingsDefence);
                                                               map.put(EquipmentType.CHESTPLATE, chestplateDefence);
                                                               map.put(EquipmentType.HELMET, helmetDefence);
                                                               map.put(EquipmentType.BODY, bodyDefence);
                                                           }),
                                 enchantmentValue, equipSound,
                                 toughness, knockbackResistance, repairIngredient,
                                 key);
    }
}