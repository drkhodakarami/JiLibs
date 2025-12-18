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

package dev.thementor.api.shared.data;

import java.util.Objects;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import dev.thementor.api.shared.enumerations.RedstoneMode;

/**
 * Represents the data related to redstone control in the JustDireThings mod.
 *
 * @author Direwolf20
 * @created_at 2025-04-18
 * @repository <a href="https://github.com/Direwolf20-MC/JustDireThings">Repository</a>
 * @youtube <a href="https://www.youtube.com/@direwolf20">Youtube</a>
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class RedstoneControlData
{
    /**
     * Indicates whether the block is currently receiving redstone power.
     */
    public boolean receivingRedstone = false;

    /**
     * Indicates whether the redstone signal has been checked.
     */
    public boolean checkedRedstone = false;

    /**
     * Indicates whether a pulse has occurred.
     */
    public boolean pulsed = false;

    /**
     * The current mode of redstone handling.
     *
     * @see RedstoneMode
     */
    public RedstoneMode redstoneMode = RedstoneMode.IGNORED;

    /**
     * Default constructor for the RedstoneControlData class.
     */
    public RedstoneControlData() {}

    /**
     * Constructor for the RedstoneControlData class that initializes the redstone mode.
     *
     * @param redstoneMode The initial mode of redstone handling.
     * @see RedstoneMode
     */
    public RedstoneControlData(RedstoneMode redstoneMode)
    {
        this.redstoneMode = redstoneMode;
    }

    /**
     * Generates a hash code based on the object's state.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(receivingRedstone, pulsed, redstoneMode);
    }

    /**
     * Compares this object with another for equality.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RedstoneControlData that = (RedstoneControlData) obj;
        return receivingRedstone == that.receivingRedstone &&
                pulsed == that.pulsed &&
                redstoneMode == that.redstoneMode;
    }
}