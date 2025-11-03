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

package dev.thementor.api.inventory.storage;

import dev.thementor.api.shared.annotations.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;

import java.util.function.BiPredicate;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class PredicateInventory extends SyncedInventory
{
    private final BiPredicate<ItemStack, Integer> predicate;

    public PredicateInventory(BlockEntity blockEntity, int size, BiPredicate<ItemStack, Integer> predicate)
    {
        super(blockEntity, size);
        this.predicate = predicate;
    }

    public PredicateInventory(BlockEntity blockEntity, BiPredicate<ItemStack, Integer> predicate, ItemStack... items)
    {
        super(blockEntity, items);
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(int slotIndex, ItemStack stack)
    {
        return this.predicate.test(stack, slotIndex);
    }

    public BiPredicate<ItemStack, Integer> getPredicate()
    {
        return this.predicate;
    }

    //TODO: Check if we need the fluid and slurry code
}