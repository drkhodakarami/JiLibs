/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
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

package dev.thementor.api.ticklogic.node;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.ticklogic.base.Node;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Supplier;

/**
 * Represents a condition node in a tick logic system.
 *
 * <p>Condition nodes evaluate a specified condition and return a tick status.</p>
 */
public class ConditionNode<T extends BlockEntity> extends Node<T>
{
    /**
     * The supplier for the condition to be evaluated.
     */
    private Supplier<Boolean> condition;

    /**
     * Constructs a ConditionNode with no Blackboard.
     *
     * @param condition the condition to be evaluated
     */
    public ConditionNode(Supplier<Boolean> condition)
    {
        super();
        this.condition = condition;
    }

    /**
     * Evaluates the condition and returns its status.
     *
     * @return the tick status based on the evaluation of the condition
     */
    @Override
    public TickStatus tick()
    {
        if(!children.isEmpty())
            return TickStatus.ERROR;

        try
        {
            if(condition.get())
                return TickStatus.SUCCESS;
            else
                return TickStatus.FAILURE;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Sets a new condition for the node.
     *
     * @param condition the new condition to be evaluated
     */
    public void setCondition(Supplier<Boolean> condition)
    {
        this.condition = condition;
    }
}