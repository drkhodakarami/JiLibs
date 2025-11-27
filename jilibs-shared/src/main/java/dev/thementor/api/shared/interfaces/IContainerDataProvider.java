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

import net.minecraft.world.inventory.ContainerData;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents an interface for providing property delegate information.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IContainerDataProvider
{
    /**
     * Retrieves the property delegate associated with this provider.
     *
     * @return the property delegate
     */
    ContainerData getContainerData();

    /**
     * Retrieves the size of the property delegate.
     *
     * @return the size of the property delegate
     */
    int getContainerDataSize();
}