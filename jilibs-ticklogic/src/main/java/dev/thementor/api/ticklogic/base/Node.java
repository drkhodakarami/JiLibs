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

package dev.thementor.api.ticklogic.base;

import dev.thementor.api.shared.enumerations.TickStatus;
import net.minecraft.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

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
     * @param properties the properties associated with the block entity
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