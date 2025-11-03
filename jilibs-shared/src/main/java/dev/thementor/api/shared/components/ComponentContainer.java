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

package dev.thementor.api.shared.components;

import dev.thementor.api.shared.interfaces.IComponent;
import dev.thementor.api.shared.interfaces.IComponentConsumer;
import dev.thementor.api.shared.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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