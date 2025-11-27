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

import java.util.function.Supplier;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.properties.BEProperties;
import dev.thementor.api.ticklogic.base.AggregationPolicy;
import dev.thementor.api.ticklogic.base.Blackboard;
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.node.ConditionNode;
import dev.thementor.api.ticklogic.node.NotNode;
import dev.thementor.api.ticklogic.node.SequenceNode;
import dev.thementor.api.ticklogic.node.StartNode;

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