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

package dev.thementor.api.register;

import java.util.function.UnaryOperator;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Registers custom component types for Minecraft data.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ComponentTypeRegisterer
{
    /**
     * Registers custom component types for Minecraft data.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiComponentTypeRegister with the specified mod ID.
     *
     * @param modId the mod ID
     */
    public ComponentTypeRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a new component type.
     *
     * @param <R>           the type of the component
     * @param name          the name of the component type
     * @param buildOperator an operator that builds the component type
     * @return the registered component type
     */
    public <R> DataComponentType<R> register(String name, UnaryOperator<DataComponentType.Builder<R>> buildOperator)
    {
        ResourceKey<DataComponentType<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.DATA_COMPONENT_TYPE);
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, key, buildOperator.apply(DataComponentType.builder()).build());
    }
}