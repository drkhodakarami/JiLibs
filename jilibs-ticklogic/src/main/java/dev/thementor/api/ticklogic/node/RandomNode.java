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

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.ticklogic.base.IRunningNodeManager;
import dev.thementor.api.ticklogic.base.Node;

/**
 * Represents a random node in a tick logic system.
 *
 * <p>Random nodes randomly select and execute one of their child nodes.</p>
 */
public class RandomNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The child node currently being executed.
     */
    private Node<T> runningChild = null;

    /**
     * Constructs a RandomNode with no Blackboard.
     */
    public RandomNode()
    {
        super();
    }

    /**
     * Executes a randomly selected child node and returns its status.
     *
     * @return the tick status of the executed child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        TickStatus status;

        if(runningChild == null)
        {
            int randomIndex = (int) (Math.random() * (children.size() - 1));

            status = children.get(randomIndex).tick();
        }
        else
            status = runningChild.tick();

        if(status == TickStatus.RUNNING)
        {
            tree.addRunningNode(this);
            return TickStatus.RUNNING;
        }

        reset();
        return status;
    }

    /**
     * Resets the state of this node and clears the running child.
     */
    @Override
    public void reset()
    {
        super.reset();
        runningChild = null;
    }
}