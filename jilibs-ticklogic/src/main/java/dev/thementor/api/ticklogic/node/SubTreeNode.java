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
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.base.Node;

/**
 * Represents a sub-tree node in a tick logic system.
 *
 * <p>Sub-tree nodes encapsulate another LogicTree, allowing for nested tick logic structures.</p>
 */
@SuppressWarnings("unused")
public class SubTreeNode <T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The encapsulated LogicTree to be executed as a sub-tree.
     */
    private final LogicTree<T> subTree;

    /**
     * Constructs a SubTreeNode with no Blackboard and an encapsulated LogicTree.
     *
     * @param subTree the LogicTree to be executed as a sub-tree
     */
    public SubTreeNode(LogicTree<T> subTree)
    {
        super();
        this.subTree = subTree;
    }

    /**
     * Executes the encapsulated LogicTree and returns its tick status.
     *
     * @return the tick status of the executed LogicTree
     */
    @Override
    public TickStatus tick()
    {
        if(subTree == null)
            return TickStatus.ERROR;

        if(!children.isEmpty())
            return TickStatus.ERROR;

        TickStatus status = subTree.tick();

        if(status == TickStatus.RUNNING)
            tree.addRunningNode(this);

        reset();
        return status;
    }

    /**
     * Resets the state of this node and its encapsulated LogicTree.
     */
    @Override
    public void reset()
    {
        super.reset();
        if(subTree != null)
            subTree.reset();
    }
}