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

package dev.thementor.api.ticklogic.base;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.ticklogic.node.StartNode;
import net.minecraft.block.entity.BlockEntity;

/**
 * Represents a logic tree used for managing tick logic in a block entity.
 *
 * @param <T> the type of block entity associated with this logic tree
 */
@SuppressWarnings("FieldCanBeLocal")
public class LogicTree<T extends BlockEntity>
{
    /**
     * The blackboard used to store and retrieve data during tick operations.
     */
    protected Blackboard blackboard;

    /**
     * Properties associated with the block entity.
     */
    protected BEProperties<T> properties;

    /**
     * The start node of the logic tree, which initiates the tick process.
     */
    protected StartNode<T> startNode;

    /**
     * Constructs an empty LogicTree instance.
     */
    public LogicTree()
    {}

    /**
     * Constructs a LogicTree instance with the specified properties, root node, blackboard, and aggregation policy.
     *
     * @param properties       the properties associated with the block entity
     * @param root             the root node of the logic tree
     * @param blackboard       the blackboard used to store data during tick operations
     * @param aggregationPolicy the aggregation policy for combining results of individual nodes
     */
    public LogicTree(BEProperties<T> properties, Node<T> root, Blackboard blackboard, AggregationPolicy aggregationPolicy)
    {
        this.properties = properties;
        startNode = new StartNode<>(root, aggregationPolicy);
        this.blackboard = blackboard;
        this.startNode.setTree(this);
    }

    /**
     * Ticks the logic tree by starting the tick process from the root node.
     *
     * @return the tick status of the logic tree
     */
    public TickStatus tick()
    {
        return startNode.tick();
    }

    /**
     * Resets the logic tree, preparing it for the next tick cycle.
     */
    public void reset()
    {
        this.startNode.reset();
    }

    /**
     * Retrieves the blackboard used by this logic tree.
     *
     * @return the blackboard instance
     */
    public Blackboard getBlackboard()
    {
        return blackboard;
    }

    /**
     * Adds a running node to the logic tree.
     *
     * @param node the running node to add
     */
    public void addRunningNode(Node<T> node)
    {
        startNode.addRunningNode(node);
    }

    public BEProperties<T> getProperties()
    {
        return this.properties;
    }
}