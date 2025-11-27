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

package dev.thementor.api.register.factory;

import net.minecraft.world.item.ToolMaterial;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a factory interface for creating tool instances from materials and settings.
 */
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IToolFactory<U, R>
{
    /**
     * Applies the factory to create a tool from a material, damage stat, speed stat, and additional settings.
     *
     * @param material The material of the tool.
     * @param damage The base damage stat of the tool.
     * @param speed The attack speed stat of the tool.
     * @param settings Additional settings required to create the tool.
     * @return The created tool instance.
     */
    R apply(ToolMaterial material, Float damage, float speed, U settings);
}