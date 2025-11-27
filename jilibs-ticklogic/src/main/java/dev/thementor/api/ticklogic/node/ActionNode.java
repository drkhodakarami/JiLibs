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
 * Represents an action node in a tick logic system.
 *
 * <p>Action nodes execute a specified action and return a tick status.</p>
 */
public class ActionNode<T extends BlockEntity> extends Node<T>
{
    /**
     * The supplier for the action to be executed.
     */
    private Supplier<TickStatus> action;

    /**
     * Constructs an ActionNode with no Blackboard.
     *
     * @param action the action to be executed
     */
    public ActionNode(Supplier<TickStatus> action)
    {
        super();
        this.action = action;
    }

    /**
     * Executes the action and returns its status.
     *
     * @return the tick status of the action execution
     */
    @Override
    public TickStatus tick()
    {
        if(!children.isEmpty())
            return TickStatus.ERROR;

        try
        {
            TickStatus status = action.get();

            if(status != TickStatus.RUNNING)
                return status;

            this.tree.addRunningNode(this);
            return TickStatus.RUNNING;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Sets a new action for the node.
     *
     * @param action the new action to be executed
     */
    public void setAction(Supplier<TickStatus> action)
    {
        this.action = action;
    }
}