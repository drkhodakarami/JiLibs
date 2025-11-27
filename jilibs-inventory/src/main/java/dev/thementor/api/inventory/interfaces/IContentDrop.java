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

package dev.thementor.api.inventory.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.Level;

import dev.thementor.api.inventory.base.InventoryConnector;
import dev.thementor.api.shared.annotations.*;

@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")
public interface IContentDrop<T extends SimpleContainer> extends IInventoryConnector<InventoryConnector<T>>
{
    default void dropContent(Level world, BlockPos pos)
    {
        InventoryConnector<?> inventory = getInventoryConnector();

        if(inventory != null)
            inventory.dropContent(world, pos);
    }
}