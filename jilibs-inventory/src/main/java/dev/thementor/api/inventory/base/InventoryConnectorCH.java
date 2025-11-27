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

package dev.thementor.api.inventory.base;

import oshi.util.tuples.Pair;

import net.minecraft.world.SimpleContainer;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")
public class InventoryConnectorCH<T extends SimpleContainer> extends InventoryConnector<T>
{
    public static InventoryConnectorCH<SimpleContainer> copyOf(InventoryConnector<?> inventory)
    {
        var storage = new InventoryConnectorCH<>();
        for(Pair<MappedDirection, ? extends SimpleContainer> entry : inventory.getSidedInventories())
            storage.addStorage(new SimpleContainer(entry.getB().getContainerSize()), entry.getA());
        return storage;
    }
}