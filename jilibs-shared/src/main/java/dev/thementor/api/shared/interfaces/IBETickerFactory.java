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

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a factory for creating block entity tickers.
 *
 * @param <T> the type of the block entity
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@FunctionalInterface
public interface IBETickerFactory<T extends BlockEntity>
{
    /**
     * Creates a new block entity ticker for the specified world, block state, and block entity type.
     *
     * @param world the world where the block entity is located
     * @param state the block state of the block
     * @param type  the type of the block entity
     * @return a new block entity ticker
     */
    BlockEntityTicker<T> create(Level world, BlockState state, BlockEntityType<?> type);
}