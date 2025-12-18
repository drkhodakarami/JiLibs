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

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an interface for providing storage entities based on direction and facing.
 *
 * @param <T> the type of the storage entity
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-12-16")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IProgress<T>
{
    /**
     * Retrieves the current progress.
     *
     * @return the progress
     */
    @Nullable
    T getProgress();

    /**
     * Retrieves the max progress.
     *
     * @return the max progress
     */
    @Nullable
    T getMaxProgress();
}