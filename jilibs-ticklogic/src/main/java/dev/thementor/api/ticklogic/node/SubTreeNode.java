/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
 * ------------------------------------------------------------------------------- *
 * MIT License                                                                     *
 * =============================================================================== *
 * Permission is hereby granted, free of charge, to any person obtaining a copy    *
 * of this software and associated documentation files (the "Software"), to deal   *
 * in the Software without restriction, including without limitation the rights    *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell       *
 * copies of the Software, and to permit persons to whom the Software is           *
 * furnished to do so, subject to the following conditions:                        *
 * ------------------------------------------------------------------------------- *
 * The above copyright notice and this permission notice shall be included in all  *
 * copies or substantial portions of the Software.                                 *
 * ------------------------------------------------------------------------------- *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR      *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,        *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE     *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER          *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,   *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE   *
 * SOFTWARE.                                                                       *
 ***********************************************************************************/

package dev.thementor.api.ticklogic.node;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.ticklogic.base.IRunningNodeManager;
import dev.thementor.api.ticklogic.base.LogicTree;
import dev.thementor.api.ticklogic.base.Node;
import net.minecraft.block.entity.BlockEntity;

/**
 * Represents a sub-tree node in a tick logic system.
 *
 * <p>Sub-tree nodes encapsulate another LogicTree, allowing for nested tick logic structures.</p>
 */
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