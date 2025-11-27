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

package dev.thementor.api.inventory.storage;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.annotations.*;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class OutputInventory extends PredicateInventory
{
    public OutputInventory(BlockEntity blockEntity, int size)
    {
        super(blockEntity, size, (slotIndex, stack) -> false);
    }

    public OutputInventory(BlockEntity blockEntity, ItemStack... items)
    {
        super(blockEntity, (slotIndex, stack) -> false, items);
    }
}