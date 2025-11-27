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

package dev.thementor.api.ticklogic.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.enumerations.TickStatus;

/**
 * Represents a node in a logic tree used for managing tick logic.
 *
 * @param <T> the type of block entity associated with this node
 */
public abstract class Node<T extends BlockEntity>
{
    /**
     * A list of child nodes that are part of this node's subtree.
     */
    protected List<Node<T>> children = new ArrayList<>();

    /**
     * The parent node of this node.
     */
    protected Node<T> parent;

    /**
     * The logic tree to which this node belongs.
     */
    protected LogicTree<T> tree;

    /**
     * Constructs an empty Node instance.
     */
    public Node()
    {}

    /**
     * Adds a child node to this node's subtree.
     *
     * @param child the child node to add
     */
    public void addChild(Node<T> child)
    {
        child.parent = this;
        children.add(child);
    }

    /**
     * Sets the logic tree and properties for this node and all its children.
     *
     * @param tree    the logic tree to which this node belongs
     */
    public void setTree(LogicTree<T> tree)
    {
        this.tree = tree;
        for(Node<T> child : children)
            child.setTree(tree);
    }

    /**
     * Resets all nodes in the subtree rooted at this node.
     */
    public void reset()
    {
        for (Node<T> child : children)
            child.reset();
    }

    /**
     * Retrieves the parent node of this node.
     *
     * @return the parent node, or null if there is no parent
     */
    public Node<T> getParent()
    {
        return parent;
    }

    /**
     * Ticks this node and its subtree.
     *
     * @return the tick status of this node
     */
    public abstract TickStatus tick();
}