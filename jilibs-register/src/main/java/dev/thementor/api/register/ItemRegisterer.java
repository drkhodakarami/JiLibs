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

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import dev.thementor.api.register.factory.IToolFactory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Registers custom items for Minecraft.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ItemRegisterer
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
    public ItemRegisterer(String mod_ID)
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
    public Item register(String name, Item.Properties settings)
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
    public Item register(String name, int stackCount, Item.Properties settings)
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
    public <R extends Item> R register(String name, Function<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(new Item.Properties().setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
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
    public <R extends Item> R register(String name, int stackCount, Function<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(new Item.Properties().setId(key). stacksTo(stackCount));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
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
    public <R extends Item> R register(String name, Item.Properties settings, Function<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(settings.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
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
    public <R extends Item> R register(String name, int stackCount, Item.Properties settings, Function<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(settings.setId(key). stacksTo(stackCount));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
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
        return register(name, new Item.Properties().axe(material, attackDamage, attackSpeed), Item::new);
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
        return register(name, new Item.Properties().hoe(material, attackDamage, attackSpeed), Item::new);
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
        return register(name, new Item.Properties().pickaxe(material, attackDamage, attackSpeed), Item::new);
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
        return register(name, new Item.Properties().shovel(material, attackDamage, attackSpeed), Item::new);
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
        return register(name, new Item.Properties().sword(material, attackDamage, attackSpeed), Item::new);
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
                                           IToolFactory<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(material, attackDamage, attackSpeed, new Item.Properties().setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
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
                                           Item.Properties settings, IToolFactory<Item.Properties, R> factory)
    {
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(material, attackDamage, attackSpeed, settings.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    /**
     * Registers an armor item with default settings and a material.
     *
     * @param name            the name of the armor item
     * @param material        the armor material for the armor item
     * @param equipment   the type of armor (e.g., helmet, chestplate)
     * @return the registered armor item
     */
    public Item registerArmor(String name, ArmorMaterial material, ArmorType equipment)
    {
        return register(name, new Item.Properties().humanoidArmor(material, equipment), Item::new);
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
    public Item registerArmor(String name, ArmorMaterial material, ArmorType equipment, Item.Properties settings)
    {
        return register(name, settings.humanoidArmor(material, equipment), Item::new);
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
                        new Item.Properties()
                                            .food(new FoodProperties.Builder()
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
                        new Item.Properties()
                                .food(new FoodProperties.Builder()
                                              .nutrition(nutrition)
                                              .saturationModifier(saturation)
                                              .build()));
    }
}