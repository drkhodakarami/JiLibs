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

/**
 * Represents a functional interface for getting a value from a block entity.
 *
 * @param <T> the type of the value to get
 * @param <B> the type of the block entity
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@FunctionalInterface
public interface IFieldGetter<T, B extends BlockEntity>
{
    /**
     * Retrieves the value from the specified block entity.
     *
     * @param blockEntity the block entity from which to retrieve the value
     * @return the retrieved value
     */
    T get(B blockEntity);
}