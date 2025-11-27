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

package dev.thementor.api.shared.interfaces;

import java.util.List;

import dev.thementor.api.shared.annotations.*;

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