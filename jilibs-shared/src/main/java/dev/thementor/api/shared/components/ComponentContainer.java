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

package dev.thementor.api.shared.components;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.IComponent;
import dev.thementor.api.shared.interfaces.IComponentConsumer;

/**
 * A container that holds and manages components of type {@link IComponent}.
 *
 * @author TurtyWurty, Modified by TheMentor
 * @since 2025-04-18
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class ComponentContainer
{
    /**
     * A map to store components indexed by their class type.
     */
    private final Map<Class<? extends IComponent>, IComponent> components = new HashMap<>();

    /**
     * Adds a component to the container.
     *
     * @param clazz The class of the component to add.
     * @param component The component instance to add.
     * @param <T> The type of the component.
     */
    public <T extends IComponent> void addComponent(Class<T> clazz, T component)
    {
        this.components.put(clazz, component);
    }

    /**
     * Retrieves a component from the container by its class type.
     *
     * @param clazz The class of the component to retrieve.
     * @param <T> The type of the component.
     * @return The retrieved component instance or null if not found.
     */
    public <T extends IComponent> T getComponent(Class<T> clazz)
    {
        return clazz.cast(this.components.get(clazz));
    }

    /**
     * Removes a component from the container by its class type.
     *
     * @param clazz The class of the component to remove.
     */
    public void removeComponent(Class<? extends IComponent> clazz)
    {
        this.components.remove(clazz);
    }

    /**
     * Checks if the container contains a component of the specified class type.
     *
     * @param clazz The class of the component to check for.
     * @return true if the component exists in the container; false otherwise.
     */
    public boolean hasComponent(Class<? extends IComponent> clazz)
    {
        return this.components.containsKey(clazz);
    }

    /**
     * Clears all components from the container.
     */
    public void clear()
    {
        this.components.clear();
    }

    /**
     * Retrieves a copy of the map containing all components.
     *
     * @return A copy of the map with component class types as keys and component instances as values.
     */
    public Map<Class<? extends IComponent>, IComponent> getComponents()
    {
        return this.components;
    }

    /**
     * Iterates over all components in the container, applying a consumer to each.
     *
     * @param consumer The consumer to apply to each component.
     */
    public void forEach(IComponentConsumer<IComponent> consumer)
    {
        this.components.forEach((clazz, component) -> consumer.accept(component));
    }

    /**
     * Returns a stream of all components in the container.
     *
     * @return A stream containing all component instances.
     */
    public Stream<IComponent> stream()
    {
        return this.components.values().stream();
    }
}