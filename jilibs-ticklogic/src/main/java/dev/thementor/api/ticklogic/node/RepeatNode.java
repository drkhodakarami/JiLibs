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
import dev.thementor.api.ticklogic.base.IRunningNodeManager;
import dev.thementor.api.ticklogic.base.Node;
import net.minecraft.block.entity.BlockEntity;

/**
 * Represents a repeat node in a tick logic system.
 *
 * <p>Repeat nodes execute their child node multiple times, specified by the repeat count.</p>
 */
public class RepeatNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * Represents a repeat node in a tick logic system.
     *
     * <p>Repeat nodes execute their child node multiple times, specified by the repeat count.</p>
     */
    private final int repeatCount;

    /**
     * The current iteration count during the repeat cycle.
     */
    private int currentIteration;

    /**
     * Constructs a RepeatNode with no Blackboard and a specified repeat count.
     *
     * @param repeatCount the number of times to repeat the execution of the child node
     */
    public RepeatNode(int repeatCount)
    {
        this.repeatCount = repeatCount;
        currentIteration = 0;
    }

    /**
     * Executes the child node multiple times based on the specified repeat count.
     *
     * @return the tick status of the executed child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(children.size() > 1)
            return TickStatus.ERROR;

        while (currentIteration < repeatCount)
        {
            Node<T> child = children.getFirst();

            TickStatus status = child.tick();

            if(status == TickStatus.RUNNING)
            {
                tree.addRunningNode(this);
                return TickStatus.RUNNING;
            }

            currentIteration++;
            if(currentIteration >= repeatCount)
            {
                reset();
                return status;
            }
        }

        reset();
        return TickStatus.SUCCESS;
    }

    /**
     * Resets the state of this node and its child nodes.
     */
    @Override
    public void reset()
    {
        super.reset();
        currentIteration = 0;
    }
}