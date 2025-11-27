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

import net.minecraft.world.level.block.Block;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a factory interface for creating item instances from blocks.
 */
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public interface IBlockItemFactory<U, R>
{
    /**
     * Applies the factory to create an item from a block and additional settings.
     *
     * @param block The block to create an item for.
     * @param settings Additional settings required to create the item.
     * @return The created item instance.
     */
    R apply(Block block, U settings);
}