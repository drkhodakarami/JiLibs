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

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public interface ITickProvider
{
    <T extends BlockEntity> @Nullable BlockEntityTicker<T> fallbackTicker(Level level, BlockState state, BlockEntityType<T> type);

    default <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level)
    {
        return ((level1, blockPos, blockState, blockEntity) -> {
            if(blockEntity instanceof ISyncedTick syncedTicker)
            {
                if(!level.isClientSide())
                    syncedTicker.tick();
                else
                    syncedTicker.tickClient();
            }
            else if(blockEntity instanceof ITick ticker)
            {
                if(!level.isClientSide())
                    ticker.tick();
                else
                    ticker.tickClient();
            }
        });
    };
}