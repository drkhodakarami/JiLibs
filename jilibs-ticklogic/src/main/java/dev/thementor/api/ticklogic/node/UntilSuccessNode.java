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

public class UntilSuccessNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    public UntilSuccessNode()
    {
        super();
    }

    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(children.size() > 1)
            return TickStatus.ERROR;

        TickStatus status = children.getFirst().tick();

        if(status == TickStatus.SUCCESS)
            return TickStatus.SUCCESS;

        if(status == TickStatus.RUNNING)
            tree.addRunningNode(this);

        if(status == TickStatus.ERROR)
            return TickStatus.ERROR;

        return TickStatus.RUNNING;
    }
}