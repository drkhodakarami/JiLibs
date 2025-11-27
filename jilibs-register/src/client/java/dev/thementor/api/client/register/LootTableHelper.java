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

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Provides utility methods for generating loot tables.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
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
    public static LootTable.Builder addOreDrops(FabricBlockLootTableProvider provider, HolderLookup.Provider registries,
                                                Block block, Item item, float min, float max)
    {
        HolderLookup.RegistryLookup<Enchantment> impl = registries.lookupOrThrow(Registries.ENCHANTMENT);
        return provider.createSilkTouchDispatchTable(block,
                                           (LootItem.lootTableItem(item)
                                                       .apply(SetItemCountFunction
                                                                  .setCount(UniformGenerator.between(min, max))))
                                                       .apply(ApplyBonusCount
                                                                  .addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE))));
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
    public static LootTable.Builder addOreDrops(FabricBlockLootTableProvider provider, HolderLookup.Provider registries,
                                                Block block, Item item)
    {
        return addOreDrops(provider, registries, block, item, 2.0f, 5.0f);
    }
}