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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a random selector node in a tick logic system.
 *
 * <p>Random selector nodes randomly select and execute one of their child nodes until a success or running status is returned.</p>
 */
public class RandomSelectorNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The current index of the child node being executed.
     */
    private int currentChildIndex = 0;

    /**
     * The child node currently being executed.
     */
    private Node<T> runningChild = null;

    /**
     * A shuffled list of child nodes to ensure random selection.
     */
    List<Node<T>> shuffledChildren;

    /**
     * A random number generator used for shuffling the child nodes.
     */
    private final Random rand = new Random();

    /**
     * Constructs a RandomSelectorNode with no Blackboard.
     */
    public RandomSelectorNode()
    {
        super();
    }

    /**
     * Executes a randomly selected child node and returns its status until a success or running status is returned.
     *
     * @return the tick status of the executed child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(runningChild == null)
            reset();

        if(runningChild != null)
            currentChildIndex = shuffledChildren.indexOf(runningChild);

        while (currentChildIndex < shuffledChildren.size())
        {
            Node<T> child = shuffledChildren.get(currentChildIndex);

            TickStatus status = child.tick();

            if(status == TickStatus.SUCCESS)
            {
                reset();
                return TickStatus.SUCCESS;
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
        return TickStatus.FAILURE;
    }

    /**
     * Resets the state of this node, shuffles the child nodes, and clears the running child.
     */
    @Override
    public void reset()
    {
        super.reset();
        currentChildIndex = 0;
        runningChild = null;
        shuffledChildren = new ArrayList<>(children);
        Collections.shuffle(shuffledChildren, rand);
    }
}