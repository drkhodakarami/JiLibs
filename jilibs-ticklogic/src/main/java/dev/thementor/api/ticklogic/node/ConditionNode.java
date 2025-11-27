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

package dev.thementor.api.ticklogic.node;

import java.util.function.Supplier;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.ticklogic.base.Node;

/**
 * Represents a condition node in a tick logic system.
 *
 * <p>Condition nodes evaluate a specified condition and return a tick status.</p>
 */
public class ConditionNode<T extends BlockEntity> extends Node<T>
{
    /**
     * The supplier for the condition to be evaluated.
     */
    private Supplier<Boolean> condition;

    /**
     * Constructs a ConditionNode with no Blackboard.
     *
     * @param condition the condition to be evaluated
     */
    public ConditionNode(Supplier<Boolean> condition)
    {
        super();
        this.condition = condition;
    }

    /**
     * Evaluates the condition and returns its status.
     *
     * @return the tick status based on the evaluation of the condition
     */
    @Override
    public TickStatus tick()
    {
        if(!children.isEmpty())
            return TickStatus.ERROR;

        try
        {
            if(condition.get())
                return TickStatus.SUCCESS;
            else
                return TickStatus.FAILURE;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Sets a new condition for the node.
     *
     * @param condition the new condition to be evaluated
     */
    public void setCondition(Supplier<Boolean> condition)
    {
        this.condition = condition;
    }
}