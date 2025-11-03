/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
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

package dev.thementor.api.shared.interfaces;

import dev.thementor.api.shared.annotations.*;

import java.util.List;

/**
 * Represents an interface for entities that require synchronized ticks and can be synced.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface ISyncedTick extends ITick, ISync
{
    /**
     * Called when the entity is ticked on the server.
     */
    void onTick();

    /**
     * Called when the entity is ticked on the client.
     */
    void onTickClient();

    /**
     * Determines whether the entity should be synced with other entities.
     *
     * @return true if the entity should be synced, false otherwise
     */
    boolean shouldSync();

    /**
     * Default implementation of the server-side tick method.
     */
    @Override
    default void tick()
    {
        onTick();

        List<ISyncable> syncables = getSyncables();

        if (shouldSync() && syncables != null && !syncables.isEmpty())
            syncables.forEach(ISyncable::sync);

        if(this instanceof IUpdatable updatable)
            updatable.onTickEnd();
    }

    /**
     * Default implementation of the client-side tick method.
     */
    @Override
    default void tickClient()
    {
        onTickClient();

        if(this instanceof IUpdatable updatable)
            updatable.onTickClientEnd();
    }
}