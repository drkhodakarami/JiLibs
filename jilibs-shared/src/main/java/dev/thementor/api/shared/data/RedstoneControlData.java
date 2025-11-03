/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package dev.thementor.api.shared.data;

import dev.thementor.api.shared.enumerations.RedstoneMode;
import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

import java.util.Objects;

/**
 * Represents the data related to redstone control in the JustDireThings mod.
 *
 * @author Direwolf20
 * @created_at 2025-04-18
 * @repository https://github.com/Direwolf20-MC/JustDireThings
 * @youtube https://www.youtube.com/@direwolf20
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