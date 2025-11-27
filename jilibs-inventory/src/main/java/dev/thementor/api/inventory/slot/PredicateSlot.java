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

package dev.thementor.api.inventory.slot;

import java.util.function.Predicate;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import dev.thementor.api.shared.annotations.*;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class PredicateSlot extends Slot
{
    private final Predicate<ItemStack> predicate;
    private int maxCount;

    public PredicateSlot(Container inventory, int index, int x, int y, Predicate<ItemStack> predicate)
    {
        super(inventory, index, x, y);
        this.predicate = predicate;
    }

    public PredicateSlot(Container inventory, int index, int x, int y, int maxCount, Predicate<ItemStack> predicate)
    {
        this(inventory, index, x, y, predicate);
        this.maxCount = maxCount;
    }

    public PredicateSlot(SimpleContainer inventory, int index, int x, int y)
    {
        this(inventory, index, x, y, stack -> inventory.canPlaceItem(index, stack));
    }

    public PredicateSlot(SimpleContainer inventory, int index, int x, int y, int maxCount)
    {
        this(inventory, index, x, y);
        this.maxCount = maxCount;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        if(maxCount == 0)
            return this.predicate.test(stack);
        return this.predicate.test(stack) && getItem().getCount() < maxCount;
    }
}