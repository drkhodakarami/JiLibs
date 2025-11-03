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
import java.util.List;

/**
 * Represents a parallel node in a tick logic system.
 *
 * <p>Parallel nodes evaluate multiple child nodes concurrently and return a tick status based on a success threshold.</p>
 */
public class ParallelNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The minimum number of successful child nodes required for this node to be considered successful.
     */
    private int successThreshold;

    /**
     * The count of child nodes that have successfully completed.
     */
    private int successCount;

    /**
     * The count of child nodes that are currently running.
     */
    private int runningCount;

    /**
     * A list to store the tick statuses of each child node.
     */
    private final List<TickStatus> childStatuses;

    /**
     * Constructs a ParallelNode with no Blackboard and a specified success threshold.
     *
     * @param successThreshold the minimum number of successful child nodes required
     */
    public ParallelNode(int successThreshold)
    {
        super();
        this.successThreshold = successThreshold;
        this.childStatuses = new ArrayList<>();
        this.successCount = 0;
        this.runningCount = 0;
    }

    /**
     * Evaluates the child nodes and returns a tick status based on the success threshold.
     *
     * @return the tick status based on the evaluation of the child nodes
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(childStatuses.isEmpty())
            for (int i = 0; i < children.size(); i++)
                childStatuses.add(TickStatus.FAILURE);

        for (int i = 0; i < children.size(); i++)
        {
            Node<T> child = children.get(i);

            if(childStatuses.get(i) != TickStatus.SUCCESS)
            {
                TickStatus status = child.tick();
                childStatuses.set(i, status);

                if(status == TickStatus.RUNNING)
                    runningCount++;
            }
        }

        if(successCount >= successThreshold)
        {
            reset();
            return TickStatus.SUCCESS;
        }

        if(successCount + runningCount >= successThreshold)
        {
            tree.addRunningNode(this);
            return TickStatus.RUNNING;
        }

        reset();
        return TickStatus.FAILURE;
    }

    /**
     * Resets the state of this node and its child nodes.
     */
    @Override
    public void reset()
    {
        super.reset();
        childStatuses.clear();
        successCount = 0;
        runningCount = 0;
    }

    /**
     * Sets a new success threshold for this node.
     *
     * @param threshold the new minimum number of successful child nodes required
     */
    public void setSuccessThreshold(int threshold)
    {
        this.successThreshold = threshold;
    }
}