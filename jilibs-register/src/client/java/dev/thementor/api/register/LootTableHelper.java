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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

/**
 * Provides utility methods for generating loot tables.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class LootTableHelper
{
    /**
     * Private constructor to prevent instantiation.
     */
    public LootTableHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Generates a loot table entry for an ore block with random item drops and fortune enchantment.
     *
     * @param provider      the block loot table provider
     * @param registries    the registry wrapper lookup
     * @param block         the ore block
     * @param item          the item dropped by the ore
     * @param min           the minimum number of items to drop
     * @param max           the maximum number of items to drop
     * @return a loot table builder with the specified drops and enchantments
     */
    public static LootTable.Builder addOreDrops(FabricBlockLootTableProvider provider, RegistryWrapper.WrapperLookup registries,
                                                Block block, Item item, float min, float max)
    {
        RegistryWrapper.Impl<Enchantment> impl = registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return provider.dropsWithSilkTouch(block,
                                           (ItemEntry.builder(item)
                                                       .apply(SetCountLootFunction
                                                                  .builder(UniformLootNumberProvider.create(min, max))))
                                                       .apply(ApplyBonusLootFunction
                                                                  .oreDrops(impl.getOrThrow(Enchantments.FORTUNE))));
    }


    /**
     * Generates a loot table entry for an ore block with random item drops and a default range (2.0f to 5.0f).
     *
     * @param provider      the block loot table provider
     * @param registries    the registry wrapper lookup
     * @param block         the ore block
     * @param item          the item dropped by the ore
     * @return a loot table builder with the specified drops and enchantments
     */
    public static LootTable.Builder addOreDrops(FabricBlockLootTableProvider provider, RegistryWrapper.WrapperLookup registries,
                                                Block block, Item item)
    {
        return addOreDrops(provider, registries, block, item, 2.0f, 5.0f);
    }
}