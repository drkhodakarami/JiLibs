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

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;

/**
 * Represents an interface for providing storage entities based on direction and facing.
 *
 * @param <T> the type of the storage entity
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IStorageProvider<T>
{
    /**
     * Retrieves a storage provider based on the specified {@link MappedDirection} and facing direction.
     *
     * @param direction  the mapped direction
     * @param facing   the facing direction
     * @return the storage provider, or null if none is found
     */
    @Nullable
    T getStorageProvider(MappedDirection direction, Direction facing);

    /**
     * Retrieves a storage provider based on the specified facing direction.
     *
     * @param direction  the facing direction
     * @param facing   the facing direction
     * @return the storage provider, or null if none is found
     */
    @Nullable
    T getStorageProvider(Direction direction, Direction facing);
}