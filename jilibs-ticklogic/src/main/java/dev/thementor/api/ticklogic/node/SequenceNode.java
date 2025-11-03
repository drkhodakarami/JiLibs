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
 * Represents a sequence node in a tick logic system.
 *
 * <p>Sequence nodes evaluate its child nodes sequentially and returns the first failure or running status encountered.</p>
 */
public class SequenceNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The current index of the child node being evaluated.
     */
    private int currentChildIndex = 0;

    /**
     * The child node currently being evaluated.
     */
    private Node<T> runningChild = null;

    /**
     * Constructs a SequenceNode with no Blackboard.
     */
    public SequenceNode()
    {
        super();
    }

    /**
     * Evaluates child nodes sequentially and returns the first failure or running status encountered.
     *
     * @return the tick status of the evaluated child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(runningChild != null)
            currentChildIndex = children.indexOf(runningChild);

        //noinspection DuplicatedCode
        while (currentChildIndex < children.size())
        {
            Node<T> child = children.get(currentChildIndex);

            TickStatus status = child.tick();

            if(status == TickStatus.FAILURE)
            {
                reset();
                return TickStatus.FAILURE;
            }

            if(status == TickStatus.RUNNING)
            {
                tree.addRunningNode(this);
                runningChild = child;
                return TickStatus.RUNNING;
            }

            currentChildIndex++;
        }

        reset();
        return TickStatus.SUCCESS;
    }

    /**
     * Resets the state of this node and clears the running child.
     */
    @Override
    public void reset()
    {
        super.reset();
        currentChildIndex = 0;
        runningChild = null;
    }
}