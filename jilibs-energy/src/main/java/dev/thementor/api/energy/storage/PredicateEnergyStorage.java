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

package dev.thementor.api.energy.storage;

import net.minecraft.world.level.block.entity.BlockEntity;

public class PredicateEnergyStorage extends SyncedEnergyStorage
{
    private final boolean canInsert;
    private final boolean canExtract;

    public PredicateEnergyStorage(BlockEntity blockEntity, long capacity, long maxInsert, long maxExtract, boolean canInsert, boolean canExtract)
    {
        super(blockEntity, capacity, maxInsert, maxExtract);
        this.canInsert = canInsert;
        this.canExtract = canExtract;
    }

    @Override
    public boolean supportsInsertion()
    {
        return this.canInsert;
    }

    @Override
    public boolean supportsExtraction()
    {
        return this.canExtract;
    }
}