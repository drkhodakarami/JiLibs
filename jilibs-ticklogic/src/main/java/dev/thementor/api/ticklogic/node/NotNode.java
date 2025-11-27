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
 * Represents a not node in a tick logic system.
 *
 * <p>Not nodes invert the tick status of their child node.</p>
 */
public class NotNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * Constructs a NotNode with no Blackboard.
     */
    public NotNode()
    {
        super();
    }

    /**
     * Inverts the tick status of the child node and returns it.
     *
     * @return the inverted tick status based on the evaluation of the child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(children.size() > 1)
            return TickStatus.ERROR;

        TickStatus status = children.getFirst().tick();

        if(status == TickStatus.FAILURE)
            return TickStatus.SUCCESS;

        if(status == TickStatus.SUCCESS)
            return TickStatus.FAILURE;

        if(status == TickStatus.RUNNING)
            tree.addRunningNode(this);

        return status;
    }
}