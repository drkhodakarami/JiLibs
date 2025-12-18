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
 * Represents a repeat node in a tick logic system.
 *
 * <p>Repeat nodes execute their child node multiple times, specified by the repeat count.</p>
 */
@SuppressWarnings("unused")
public class RepeatNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * Represents a repeat node in a tick logic system.
     *
     * <p>Repeat nodes execute their child node multiple times, specified by the repeat count.</p>
     */
    private final int repeatCount;

    /**
     * The current iteration count during the repeat cycle.
     */
    private int currentIteration;

    /**
     * Constructs a RepeatNode with no Blackboard and a specified repeat count.
     *
     * @param repeatCount the number of times to repeat the execution of the child node
     */
    public RepeatNode(int repeatCount)
    {
        this.repeatCount = repeatCount;
        currentIteration = 0;
    }

    /**
     * Executes the child node multiple times based on the specified repeat count.
     *
     * @return the tick status of the executed child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(children.size() > 1)
            return TickStatus.ERROR;

        while (currentIteration < repeatCount)
        {
            Node<T> child = children.getFirst();

            TickStatus status = child.tick();

            if(status == TickStatus.RUNNING)
            {
                tree.addRunningNode(this);
                return TickStatus.RUNNING;
            }

            currentIteration++;
            if(currentIteration >= repeatCount)
            {
                reset();
                return status;
            }
        }

        reset();
        return TickStatus.SUCCESS;
    }

    /**
     * Resets the state of this node and its child nodes.
     */
    @Override
    public void reset()
    {
        super.reset();
        currentIteration = 0;
    }
}