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
 * Represents a selector node in a tick logic system.
 *
 * <p>Selector nodes evaluate its child nodes sequentially and returns the first success or running status encountered.</p>
 */
public class SelectorNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The current index of the child node being evaluated.
     */
    private int currentChildIndex = 0;

    /**
     * The child node currently being evaluated.
     */
    private Node<T> runningChild = null;

    /**
     * Constructs a SelectorNode with no Blackboard.
     */
    public SelectorNode()
    {
        super();
    }

    /**
     * Evaluates child nodes sequentially and returns the first success or running status encountered.
     *
     * @return the tick status of the evaluated child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(runningChild != null)
            currentChildIndex = children.indexOf(runningChild);

        //noinspection DuplicatedCode
        while (currentChildIndex < children.size())
        {
            Node<T> child = children.get(currentChildIndex);

            TickStatus status = child.tick();

            if(status == TickStatus.SUCCESS)
            {
                reset();
                return TickStatus.SUCCESS;
            }

            if(status == TickStatus.RUNNING)
            {
                tree.addRunningNode(this);
                runningChild = child;
                return TickStatus.RUNNING;
            }

            currentChildIndex++;
        }

        reset();
        return TickStatus.FAILURE;
    }

    /**
     * Evaluates child nodes sequentially and returns the first success or running status encountered.
     *
     */
    @Override
    public void reset()
    {
        super.reset();
        currentChildIndex = 0;
        runningChild = null;
    }
}