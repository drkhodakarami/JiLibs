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

package dev.thementor.api.ticklogic.logic;

import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.ticklogic.base.AggregationPolicy;
import dev.thementor.api.ticklogic.base.Blackboard;
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.node.ConditionNode;
import dev.thementor.api.ticklogic.node.NotNode;
import dev.thementor.api.ticklogic.node.SequenceNode;
import dev.thementor.api.ticklogic.node.StartNode;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Supplier;

/**
 * A logic tree that checks if a resource exists based on a condition.
 *
 * @param <T> the type of block entity associated with this logic tree
 */
public class HasResourceSubTree<T extends BlockEntity> extends LogicTree<T>
{
    /**
     * Constructs a HasResourceSubTree instance with the specified parameters.
     *
     * @param shouldUseResource whether to use resource checks
     * @param properties        properties associated with the block entity
     * @param blackboard        the blackboard used for data storage
     * @param resourceAmountSupplier a supplier that provides the current resource amount
     */
    public HasResourceSubTree(boolean shouldUseResource, BEProperties<T> properties, Blackboard blackboard,
                              Supplier<Long> resourceAmountSupplier)
    {
        super();
        this.properties = properties;
        this.blackboard = blackboard;
        initTree(shouldUseResource, resourceAmountSupplier);
    }

    /**
     * Initializes the logic tree based on the provided parameters.
     *
     * @param useResource whether to use resource checks
     * @param resourceAmountSupplier a supplier that provides the current resource amount
     */
    private void initTree(boolean useResource, Supplier<Long> resourceAmountSupplier)
    {
        ConditionNode<T> shouldUseResource = new ConditionNode<T>(() -> useResource);
        ConditionNode<T> shouldUseResourceNegated = new ConditionNode<T>(() -> useResource);
        ConditionNode<T> hasResourceNode = new ConditionNode<T>(() -> resourceAmountSupplier != null && resourceAmountSupplier.get() > 0);
        SequenceNode<T> resourceCheckSequence = new SequenceNode<T>();
        NotNode<T> shouldUseResourceNot = new NotNode<T>();
        SequenceNode<T> root = new SequenceNode<>();

        resourceCheckSequence.addChild(shouldUseResource);
        resourceCheckSequence.addChild(hasResourceNode);

        hasResourceNode.addChild(shouldUseResourceNegated);

        root.addChild(resourceCheckSequence);
        root.addChild(shouldUseResourceNot);

        startNode = new StartNode<>(root, AggregationPolicy.SUCCEED_IF_ALL_SUCCESS);
        this.startNode.setTree(this);
    }
}