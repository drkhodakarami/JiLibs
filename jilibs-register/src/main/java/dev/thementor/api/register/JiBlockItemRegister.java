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

import dev.thementor.api.register.factory.IBlockItemFactory;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

/**
 * Provides utility methods for registering block items in Minecraft.
 */
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiBlockItemRegister
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
    public JiBlockItemRegister(String modId)
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
        String name = BaseHelper.getRegistryName(block);
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        return Registry.register(Registries.ITEM, key, new BlockItem(block,
                                                                     new Item.Settings()
                                                                             .useBlockPrefixedTranslationKey()
                                                                             .registryKey(key)));
    }

    /**
     * Registers a block item using a custom IBlockItemFactory.
     *
     * @param block The block to register as an item.
     * @param factory The factory to create the block item with custom settings.
     * @return The registered block item.
     */
    public <R extends BlockItem> R register(Block block, IBlockItemFactory<Item.Settings, R> factory)
    {
        String name = BaseHelper.getRegistryName(block);
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(block, new Item.Settings()
                                                .useBlockPrefixedTranslationKey()
                                                .registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    /**
     * Registers a block item with custom settings using a custom IBlockItemFactory.
     *
     * @param block The block to register as an item.
     * @param settings Custom settings for the block item.
     * @param factory The factory to create the block item with custom settings.
     * @return The registered block item.
     */
    public <R extends BlockItem> R register(Block block, Item.Settings settings, IBlockItemFactory<Item.Settings, R> factory)
    {
        String name = BaseHelper.getRegistryName(block);
        RegistryKey<Item> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ITEM);
        R item = factory.apply(block, settings
                                        .useBlockPrefixedTranslationKey()
                                        .registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }
}