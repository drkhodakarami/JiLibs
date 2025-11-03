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
import dev.thementor.api.ticklogic.base.AggregationPolicy;
import dev.thementor.api.ticklogic.base.IRunningNodeManager;
import dev.thementor.api.ticklogic.base.Node;
import net.minecraft.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the start node in a tick logic system.
 *
 * <p>The StartNode manages the execution of a tree of nodes, handling running nodes and aggregating their results based on the specified aggregation policy.</p>
 */
public class StartNode<T extends BlockEntity> extends Node<T>
{
    /**
     * A list of currently running child nodes.
     */
    private List<Node<T>> runningNodes = new ArrayList<>();

    /**
     * The aggregation policy to determine the final tick status based on child node results.
     */
    private final AggregationPolicy aggregationPolicy;

    /**
     * Constructs a StartNode with no Blackboard and a specified aggregation policy.
     *
     * @param root the root node of the tick logic tree
     * @param blackboard the Blackboard instance
     * @param aggregationPolicy the aggregation policy to determine the final tick status
     */
    public StartNode(Node<T> root,AggregationPolicy aggregationPolicy)
    {
        super();
        children.add(root);
        this.aggregationPolicy = aggregationPolicy;
    }

    /**
     * Evaluates the child nodes based on the specified aggregation policy and returns a tick status.
     *
     * @return the final tick status based on the evaluated child nodes
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(children.size() > 1)
            return TickStatus.ERROR;

        if(runningNodes.isEmpty())
        {
            this.tree.reset();

            TickStatus result = children.getFirst().tick();

            if(!(children.getFirst().getParent() instanceof IRunningNodeManager) && result == TickStatus.RUNNING)
                runningNodes.add(children.getFirst());

            return result;
        }
        else
        {
            List<Node<T>> nextRunningNodes = new ArrayList<>();

            int successCount = 0, failedCount = 0, runningCount = 0;

            for (Node<T> node : runningNodes)
            {
                TickStatus result = node.tick();

                switch (result)
                {
                    case TickStatus.SUCCESS: successCount++; break;
                    case TickStatus.FAILURE: failedCount++; break;
                    case TickStatus.RUNNING:
                        if (!(node.getParent() instanceof IRunningNodeManager))
                            nextRunningNodes.add(node);
                        runningCount++;
                        break;
                }
            }

            runningNodes = nextRunningNodes;

            if(runningCount > 0) return TickStatus.RUNNING;

            //noinspection EnhancedSwitchMigration
            switch (aggregationPolicy)
            {
                case SUCCEED_ON_ANY_SUCCESS, FAIL_IF_ALL_FAILURE:
                    if(successCount > 0) return TickStatus.SUCCESS;
                    return TickStatus.FAILURE;
                case FAIL_ON_ANY_FAILURE, SUCCEED_IF_ALL_SUCCESS:
                    if(failedCount > 0) return TickStatus.FAILURE;
                    return TickStatus.SUCCESS;
                default:
                    return TickStatus.FAILURE;
            }
        }
    }

    /**
     * Adds a running node to the list of currently running nodes.
     *
     * @param node the node to add as running
     */
    public void addRunningNode(Node<T> node)
    {
        if(node.getParent() instanceof IRunningNodeManager)
            return;

        if(!runningNodes.contains(node))
            runningNodes.add(node);
    }

    /**
     * Resets the state of this node and clears the list of running nodes.
     */
    @Override
    public void reset()
    {
        super.reset();
        runningNodes.clear();
    }
}