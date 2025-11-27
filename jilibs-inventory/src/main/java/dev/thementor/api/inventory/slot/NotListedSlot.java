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

import java.util.List;

import net.minecraft.world.Container;
import net.minecraft.world.item.Item;

import dev.thementor.api.shared.annotations.*;

@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public class NotListedSlot  extends PredicateSlot
{
    public NotListedSlot(Container inventory, int index, int x, int y, List<Item> list)
    {
        super(inventory, index, x, y, itemStack -> !list.contains(itemStack.getItem()));
    }

    public NotListedSlot(Container inventory, int index, int x, int y, int maxCount, List<Item> list)
    {
        super(inventory, index, x, y, maxCount, itemStack -> !list.contains(itemStack.getItem()));
    }
}