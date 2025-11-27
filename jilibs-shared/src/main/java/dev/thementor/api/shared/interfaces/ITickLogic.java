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

package dev.thementor.api.shared.interfaces;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.shared.properties.BlockEntityFields;

/**
 * Represents an interface for tick logic that can be executed on a block entity.
 *
 * @param <T> the type of the block entity
 * @param <U> the type of the block entity fields
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface ITickLogic<T extends BlockEntity, U extends BlockEntityFields<T>>
{
    /**
     * Executes the tick logic for the given block entity properties on the server side.
     *
     * @param properties the block entity properties
     */
    void tick(BEProperties<T> properties);

    /**
     * Executes any client-specific tick logic for the given block entity properties (default implementation does nothing).
     *
     * @param properties the block entity properties
     */
    default void tickClient(BEProperties<T> properties){}
}