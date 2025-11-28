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

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PredicateEnergyStorage extends SyncedEnergyStorage
{
    private final Predicate<Boolean> canInsert;
    private final Predicate<Boolean> canExtract;
    private boolean insertionFlag;
    private boolean extractionFlag;

    public PredicateEnergyStorage(BlockEntity blockEntity, long capacity, long maxInsert, long maxExtract,
                                  Supplier<Boolean> insertionFlag, Supplier<Boolean> extractionFlag,
                                  Predicate<Boolean> canInsert, Predicate<Boolean> canExtract)
    {
        this(blockEntity, capacity, maxInsert, maxExtract,
             insertionFlag.get(), extractionFlag.get(),
             canInsert, canExtract);
    }

    public PredicateEnergyStorage(BlockEntity blockEntity, long capacity, long maxInsert, long maxExtract,
                                  Predicate<Boolean> canInsert, Predicate<Boolean> canExtract)
    {
        this(blockEntity, capacity, maxInsert, maxExtract, true, true, canInsert, canExtract);
    }

    public PredicateEnergyStorage(BlockEntity blockEntity, long capacity, long maxInsert, long maxExtract,
                                  boolean insertionFlag, boolean extractionFlag,
                                  Predicate<Boolean> canInsert, Predicate<Boolean> canExtract)
    {
        super(blockEntity, capacity, maxInsert, maxExtract);
        this.canInsert = canInsert;
        this.canExtract = canExtract;
        this.insertionFlag = insertionFlag;
        this.extractionFlag = extractionFlag;
    }

    public void setInsertionFlag(boolean flag)
    {
        this.insertionFlag = flag;
    }

    public void setExtractionFlag(boolean flag)
    {
        this.extractionFlag = flag;
    }

    @Override
    public boolean supportsInsertion()
    {
        return this.canInsert.test(this.insertionFlag);
    }

    @Override
    public boolean supportsExtraction()
    {
        return this.canExtract.test(this.extractionFlag);
    }
}