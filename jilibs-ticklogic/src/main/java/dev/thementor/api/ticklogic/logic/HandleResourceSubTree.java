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

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.ticklogic.base.AggregationPolicy;
import dev.thementor.api.ticklogic.base.Blackboard;
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.node.*;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A logic tree that handles resource adjustments based on a condition.
 *
 * @param <T> the type of block entity associated with this logic tree
 */
public class HandleResourceSubTree<T extends BlockEntity> extends LogicTree<T>
{
    /**
     * Constructs a HandleResourceSubTree instance with the specified parameters.
     *
     * @param shouldUseResource whether to use resource adjustments
     * @param properties        properties associated with the block entity
     * @param blackboard        the blackboard used for data storage
     * @param adjustmentAmount  the amount to adjust resources by
     * @param resourceAmountSupplier a supplier that provides the current resource amount
     * @param resourceMaxAmountSupplier a supplier that provides the maximum resource amount (can be null)
     * @param adjustResourceConsumer a consumer that adjusts the resource amount
     */
    public HandleResourceSubTree(boolean shouldUseResource, BEProperties<T> properties, Blackboard blackboard,
                                 long adjustmentAmount,
                                 Supplier<Long> resourceAmountSupplier, Supplier<Long> resourceMaxAmountSupplier,
                                 Consumer<Long> adjustResourceConsumer)
    {
        super();
        this.properties = properties;
        this.blackboard = blackboard;
        initTree(shouldUseResource, adjustmentAmount, resourceAmountSupplier, resourceMaxAmountSupplier, adjustResourceConsumer);
    }

    /**
     * Initializes the logic tree based on the provided parameters.
     *
     * @param useResource whether to use resource adjustments
     * @param adjustmentAmount the amount to adjust resources by
     * @param resourceAmountSupplier a supplier that provides the current resource amount
     * @param resourceMaxAmountSupplier a supplier that provides the maximum resource amount (can be null)
     * @param adjustResourceConsumer a consumer that adjusts the resource amount
     */
    private void initTree(boolean useResource, long adjustmentAmount, Supplier<Long> resourceAmountSupplier, Supplier<Long> resourceMaxAmountSupplier, Consumer<Long> adjustResourceConsumer)
    {
        ConditionNode<T> shouldUseResource = new ConditionNode<T>(() -> useResource);
        ConditionNode<T> shouldUseResourceNegated = new ConditionNode<T>(() -> useResource);

        ConditionNode<T> hasResourceNode = new ConditionNode<T>(() -> resourceAmountSupplier != null &&
                                                                                       ((adjustmentAmount >= 0 && resourceMaxAmountSupplier != null &&
                                                                                            resourceAmountSupplier.get() + adjustmentAmount <= resourceMaxAmountSupplier.get()) ||
                                                                                        (adjustmentAmount < 0 && resourceAmountSupplier.get() >= adjustmentAmount)));

        SequenceNode<T> resourceCheckSequence = new SequenceNode<T>();
        NotNode<T> shouldUseResourceNot = new NotNode<T>();

        ActionNode<T> reduceResourceAction = new ActionNode<>(() ->
        {
           if(adjustResourceConsumer != null)
           {
               adjustResourceConsumer.accept(adjustmentAmount);
               return TickStatus.SUCCESS;
           }
           return TickStatus.FAILURE;
        });

        SequenceNode<T> root = new SequenceNode<>();

        resourceCheckSequence.addChild(shouldUseResource);
        resourceCheckSequence.addChild(hasResourceNode);
        resourceCheckSequence.addChild(reduceResourceAction);

        hasResourceNode.addChild(shouldUseResourceNegated);

        root.addChild(resourceCheckSequence);
        root.addChild(shouldUseResourceNot);

        startNode = new StartNode<>(root, AggregationPolicy.SUCCEED_IF_ALL_SUCCESS);
        this.startNode.setTree(this);
    }
}