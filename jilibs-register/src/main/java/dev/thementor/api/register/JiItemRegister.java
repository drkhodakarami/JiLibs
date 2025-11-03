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

import dev.thementor.api.register.factory.IToolFactory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

/**
 * Registers custom items for Minecraft.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiItemRegister
{
    /**
     * The mod ID used for registering items.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiItemRegister with the specified mod ID.
     *
     * @param mod_ID the mod ID
     */
    public JiItemRegister(String mod_ID)
    {
        this.modId = mod_ID;
    }

    /**
     * Registers an item using default settings and a factory function.
     *
     * @param name    the name of the item
     * @return the registered item
     */
    public Item register(String name)
    {
        return register(name, Item::new);
    }

    /**
     * Registers an item with custom settings and a factory function.
     *
     * @param name    the name of the item
     * @param settings the custom settings for the item
     * @return the registered item
     */
    public Item register(String name, Item.Settings settings)
    {
        return register(name, settings, Item::new);
    }

    /**
     * Registers an item with a stack count and default settings using a factory function.
     *
     * @param name      the name of the item
     * @param stackCount the maximum stack size for the item
     * @return the registered item
     */
    public Item register(String name, int stackCount)
    {
        return register(name, stackCount, Item::new);
    }

    /**
     * Registers an item with a stack count and custom settings using a factory function.
     *
     * @param name      the name of the item
     * @param stackCount the maximum stack size for the item
     * @param settings  the custom settings for the item
     * @return the registered item
     */
    public Item register(String name, int stackCount, Item.Settings settings)
    {
        return register(name, stackCount, settings, Item::new);
    }

    /**
     * Registers an item using a factory function.
     *
     * @param <R>             the type of the item
     * @param name            the name of the item
     * @param factory         the factory used to create instances of the item
     * @return the registered item
     */
    public <R extends Item> R register(String name, Function<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers an item with a stack count using a factory function.
     *
     * @param <R>             the type of the item
     * @param name            the name of the item
     * @param stackCount      the maximum stack size for the item
     * @param factory         the factory used to create instances of the item
     * @return the registered item
     */
    public <R extends Item> R register(String name, int stackCount, Function<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(new Item.Settings().registryKey(key). maxCount(stackCount));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers an item with custom settings using a factory function.
     *
     * @param <R>             the type of the item
     * @param name            the name of the item
     * @param settings        the custom settings for the item
     * @param factory         the factory used to create instances of the item
     * @return the registered item
     */
    public <R extends Item> R register(String name, Item.Settings settings, Function<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers an item with a stack count and custom settings using a factory function.
     *
     * @param <R>             the type of the item
     * @param name            the name of the item
     * @param stackCount      the maximum stack size for the item
     * @param settings        the custom settings for the item
     * @param factory         the factory used to create instances of the item
     * @return the registered item
     */
    public <R extends Item> R register(String name, int stackCount, Item.Settings settings, Function<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(settings.registryKey(key). maxCount(stackCount));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers an axe with default settings and a material.
     *
     * @param name            the name of the axe
     * @param material        the tool material for the axe
     * @param attackDamage    the attack damage of the axe
     * @param attackSpeed     the attack speed of the axe
     * @return the registered axe item
     */
    public Item registerAxe(String name, ToolMaterial material, float attackDamage, float attackSpeed)
    {
        return register(name, new Item.Settings().axe(material, attackDamage, attackSpeed), Item::new);
    }

    /**
     * Registers a hoe with default settings and a material.
     *
     * @param name            the name of the hoe
     * @param material        the tool material for the hoe
     * @param attackDamage    the attack damage of the hoe
     * @param attackSpeed     the attack speed of the hoe
     * @return the registered hoe item
     */
    public Item registerHoe(String name, ToolMaterial material, float attackDamage, float attackSpeed)
    {
        return register(name, new Item.Settings().hoe(material, attackDamage, attackSpeed), Item::new);
    }

    /**
     * Registers a pickaxe with default settings and a material.
     *
     * @param name            the name of the pickaxe
     * @param material        the tool material for the pickaxe
     * @param attackDamage    the attack damage of the pickaxe
     * @param attackSpeed     the attack speed of the pickaxe
     * @return the registered pickaxe item
     */
    public Item registerPickaxe(String name, ToolMaterial material, float attackDamage, float attackSpeed)
    {
        return register(name, new Item.Settings().pickaxe(material, attackDamage, attackSpeed), Item::new);
    }

    /**
     * Registers a shovel with default settings and a material.
     *
     * @param name            the name of the shovel
     * @param material        the tool material for the shovel
     * @param attackDamage    the attack damage of the shovel
     * @param attackSpeed     the attack speed of the shovel
     * @return the registered shovel item
     */
    public Item registerShovel(String name, ToolMaterial material, float attackDamage, float attackSpeed)
    {
        return register(name, new Item.Settings().shovel(material, attackDamage, attackSpeed), Item::new);
    }

    /**
     * Registers a sword with default settings and a material.
     *
     * @param name            the name of the sword
     * @param material        the tool material for the sword
     * @param attackDamage    the attack damage of the sword
     * @param attackSpeed     the attack speed of the sword
     * @return the registered sword item
     */
    public Item registerSword(String name, ToolMaterial material, float attackDamage, float attackSpeed)
    {
        return register(name, new Item.Settings().sword(material, attackDamage, attackSpeed), Item::new);
    }

    /**
     * Registers a tool with default settings and a material.
     *
     * @param <R>             the type of the tool item
     * @param name            the name of the tool item
     * @param material        the tool material for the tool
     * @param attackDamage    the attack damage of the tool
     * @param attackSpeed     the attack speed of the tool
     * @param factory         the factory used to create instances of the tool item
     * @return the registered tool item
     */
    public <R extends Item> R registerTool(String name, ToolMaterial material, float attackDamage, float attackSpeed,
                                           IToolFactory<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(material, attackDamage, attackSpeed, new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers a tool with custom settings and a material.
     *
     * @param <R>             the type of the tool item
     * @param name            the name of the tool item
     * @param material        the tool material for the tool
     * @param attackDamage    the attack damage of the tool
     * @param attackSpeed     the attack speed of the tool
     * @param settings        the custom settings for the tool item
     * @param factory         the factory used to create instances of the tool item
     * @return the registered tool item
     */
    public <R extends Item> R registerTool(String name, ToolMaterial material, float attackDamage, float attackSpeed,
                                           Item.Settings settings, IToolFactory<Item.Settings, R> factory)
    {
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(material, attackDamage, attackSpeed, settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers an armor item with default settings and a material.
     *
     * @param name            the name of the armor item
     * @param material        the armor material for the armor item
     * @param equipment   the type of armor (e.g., helmet, chestplate)
     * @return the registered armor item
     */
    public Item registerArmor(String name, ArmorMaterial material, EquipmentType equipment)
    {
        return register(name, new Item.Settings().armor(material, equipment), Item::new);
    }

    /**
     * Registers an armor item with custom settings and a material.
     *
     * @param name            the name of the armor item
     * @param material        the armor material for the armor item
     * @param equipment   the type of armor (e.g., helmet, chestplate)
     * @param settings        the custom settings for the armor item
     * @return the registered armor item
     */
    public Item registerArmor(String name, ArmorMaterial material, EquipmentType equipment, Item.Settings settings)
    {
        return register(name, settings.armor(material, equipment), Item::new);
    }

    /**
     * Registers a snack food item with default settings.
     *
     * @param name            the name of the food item
     * @param stackCount      the maximum stack size for the food item
     * @param nutrition       the nutritional value of the food item
     * @param saturation      the saturation modifier of the food item
     * @return the registered snack food item
     */
    public Item registerSnackFood(String name, int stackCount, int nutrition, float saturation)
    {
        return register(name, stackCount,
                        new Item.Settings()
                                            .food(new FoodComponent.Builder()
                                                          .nutrition(nutrition)
                                                          .saturationModifier(saturation)
                                                          .alwaysEdible()
                                                          .build()));
    }

    /**
     * Registers a food item with default settings.
     *
     * @param name            the name of the food item
     * @param stackCount      the maximum stack size for the food item
     * @param nutrition       the nutritional value of the food item
     * @param saturation      the saturation modifier of the food item
     * @return the registered food item
     */
    public Item registerFood(String name, int stackCount, int nutrition, float saturation)
    {
        return register(name, stackCount,
                        new Item.Settings()
                                .food(new FoodComponent.Builder()
                                              .nutrition(nutrition)
                                              .saturationModifier(saturation)
                                              .build()));
    }
}