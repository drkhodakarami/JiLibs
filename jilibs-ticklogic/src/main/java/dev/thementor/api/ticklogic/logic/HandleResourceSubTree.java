/*
 * Copyright (c) 2025 Alireza Khodakarami
 *
 * Licensed under the MIT, (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/license/mit
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.thementor.api.ticklogic.logic;

import java.util.function.Consumer;
import java.util.function.Supplier;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.ticklogic.base.AggregationPolicy;
import dev.thementor.api.ticklogic.base.Blackboard;
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.node.*;

/**
 * A logic tree that handles resource adjustments based on a condition.
 *
 * @param <T> the type of block entity associated with this logic tree
 */
@SuppressWarnings("unused")
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
        ConditionNode<T> shouldUseResource = new ConditionNode<>(() -> useResource);
        ConditionNode<T> shouldUseResourceNegated = new ConditionNode<>(() -> useResource);

        ConditionNode<T> hasResourceNode = new ConditionNode<>(() -> resourceAmountSupplier != null &&
                                                                     ((adjustmentAmount >= 0 && resourceMaxAmountSupplier != null &&
                                                                       resourceAmountSupplier.get() + adjustmentAmount <= resourceMaxAmountSupplier.get()) ||
                                                                      (adjustmentAmount < 0 && resourceAmountSupplier.get() >= adjustmentAmount)));

        SequenceNode<T> resourceCheckSequence = new SequenceNode<>();
        NotNode<T> shouldUseResourceNot = new NotNode<>();

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