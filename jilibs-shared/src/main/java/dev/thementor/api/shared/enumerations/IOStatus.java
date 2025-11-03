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

package dev.thementor.api.shared.enumerations;

import dev.thementor.api.shared.interfaces.ITraversableEnum;
import dev.thementor.api.shared.annotations.*;

/**
 * Enum representing different I/O statuses.
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public enum IOStatus implements ITraversableEnum<IOStatus>
{
    /**
     * Indicates no I/O operation is occurring.
     */
    NONE,

    /**
     * Indicates an input operation is occurring.
     */
    INPUT,

    /**
     * Indicates an output operation is occurring.
     */
    OUTPUT,

    /**
     * Indicates both input and output operations are occurring simultaneously.
     */
    INPUT_OUTPUT;

    /**
     * Checks if the current status represents an input operation.
     *
     * @return true if this status is INPUT or INPUT_OUTPUT, false otherwise.
     */
    public boolean isInput() {
        return this == INPUT || this == INPUT_OUTPUT;
    }

    /**
     * Checks if the current status represents an output operation.
     *
     * @return true if this status is OUTPUT or INPUT_OUTPUT, false otherwise.
     */
    public boolean isOutput() {
        return this == OUTPUT || this == INPUT_OUTPUT;
    }
}