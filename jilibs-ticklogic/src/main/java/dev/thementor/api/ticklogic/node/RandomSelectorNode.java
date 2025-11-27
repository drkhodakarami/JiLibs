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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.world.level.block.entity.BlockEntity;

import dev.thementor.api.shared.enumerations.TickStatus;
import dev.thementor.api.ticklogic.base.IRunningNodeManager;
import dev.thementor.api.ticklogic.base.Node;

/**
 * Represents a random selector node in a tick logic system.
 *
 * <p>Random selector nodes randomly select and execute one of their child nodes until a success or running status is returned.</p>
 */
public class RandomSelectorNode<T extends BlockEntity> extends Node<T> implements IRunningNodeManager
{
    /**
     * The current index of the child node being executed.
     */
    private int currentChildIndex = 0;

    /**
     * The child node currently being executed.
     */
    private Node<T> runningChild = null;

    /**
     * A shuffled list of child nodes to ensure random selection.
     */
    List<Node<T>> shuffledChildren;

    /**
     * A random number generator used for shuffling the child nodes.
     */
    private final Random rand = new Random();

    /**
     * Constructs a RandomSelectorNode with no Blackboard.
     */
    public RandomSelectorNode()
    {
        super();
    }

    /**
     * Executes a randomly selected child node and returns its status until a success or running status is returned.
     *
     * @return the tick status of the executed child node
     */
    @Override
    public TickStatus tick()
    {
        if(children.isEmpty())
            return TickStatus.ERROR;

        if(runningChild == null)
            reset();

        if(runningChild != null)
            currentChildIndex = shuffledChildren.indexOf(runningChild);

        while (currentChildIndex < shuffledChildren.size())
        {
            Node<T> child = shuffledChildren.get(currentChildIndex);

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
     * Resets the state of this node, shuffles the child nodes, and clears the running child.
     */
    @Override
    public void reset()
    {
        super.reset();
        currentChildIndex = 0;
        runningChild = null;
        shuffledChildren = new ArrayList<>(children);
        Collections.shuffle(shuffledChildren, rand);
    }
}