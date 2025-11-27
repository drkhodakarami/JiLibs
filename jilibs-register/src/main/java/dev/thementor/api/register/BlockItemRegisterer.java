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

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import dev.thementor.api.register.factory.IBlockItemFactory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Provides utility methods for registering block items in Minecraft.
 */
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class BlockItemRegisterer
{
    /**
     * The unique identifier for the mod.
     */
    private final String modId;

    /**
     * Constructs a new JiBlockItemRegister with the specified mod ID.
     *
     * @param modId The unique identifier for the mod.
     */
    public BlockItemRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a block item using default settings.
     *
     * @param block The block to register as an item.
     * @return The registered block item.
     */
    public BlockItem register(Block block)
    {
        String name = BaseHelper.registryName(block);
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block,
                                                                     new Item.Properties()
                                                                             .useBlockDescriptionPrefix()
                                                                             .setId(key)));
    }

    /**
     * Registers a block item using a custom IBlockItemFactory.
     *
     * @param block The block to register as an item.
     * @param factory The factory to create the block item with custom settings.
     * @return The registered block item.
     */
    public <R extends BlockItem> R register(Block block, IBlockItemFactory<Item.Properties, R> factory)
    {
        String name = BaseHelper.registryName(block);
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(block, new Item.Properties()
                                                .useBlockDescriptionPrefix()
                                                .setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    /**
     * Registers a block item with custom settings using a custom IBlockItemFactory.
     *
     * @param block The block to register as an item.
     * @param settings Custom settings for the block item.
     * @param factory The factory to create the block item with custom settings.
     * @return The registered block item.
     */
    public <R extends BlockItem> R register(Block block, Item.Properties settings, IBlockItemFactory<Item.Properties, R> factory)
    {
        String name = BaseHelper.registryName(block);
        ResourceKey<Item> key = BaseHelper.resourceKey(this.modId, name, Registries.ITEM);
        R item = factory.apply(block, settings
                                        .useBlockDescriptionPrefix()
                                        .setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
}