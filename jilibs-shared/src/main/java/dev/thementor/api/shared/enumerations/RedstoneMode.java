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

package dev.thementor.api.shared.enumerations;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import dev.thementor.api.shared.interfaces.ITraversableEnum;

/**
 * Enum representing different redstone modes.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public enum RedstoneMode implements ITraversableEnum<RedstoneMode>
{
    /**
     * Represents a mode where redstone is ignored.
     */
    IGNORED,

    /**
     * Represents a mode where the redstone signal level is considered low.
     */
    LOW,

    /**
     * Represents a mode where the redstone signal level is considered high.
     */
    HIGH,

    /**
     * Represents a mode where the redstone signal creates a pulse.
     */
    PULSE
}