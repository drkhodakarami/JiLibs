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
 * Represents an action node in a tick logic system.
 *
 * <p>Action nodes execute a specified action and return a tick status.</p>
 */
public class ActionNode<T extends BlockEntity> extends Node<T>
{
    /**
     * The supplier for the action to be executed.
     */
    private Supplier<TickStatus> action;

    /**
     * Constructs an ActionNode with no Blackboard.
     *
     * @param action the action to be executed
     */
    public ActionNode(Supplier<TickStatus> action)
    {
        super();
        this.action = action;
    }

    /**
     * Executes the action and returns its status.
     *
     * @return the tick status of the action execution
     */
    @Override
    public TickStatus tick()
    {
        if(!children.isEmpty())
            return TickStatus.ERROR;

        try
        {
            TickStatus status = action.get();

            if(status != TickStatus.RUNNING)
                return status;

            this.tree.addRunningNode(this);
            return TickStatus.RUNNING;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Sets a new action for the node.
     *
     * @param action the new action to be executed
     */
    public void setAction(Supplier<TickStatus> action)
    {
        this.action = action;
    }
}