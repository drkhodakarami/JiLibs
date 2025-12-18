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

package dev.thementor.api.shared.utils;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.TransferVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;

/**
 * Provides utility methods for handling item and fluid transfers in Minecraft using Fabric API.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class TransferHelper
{
    /**
     * Private constructor to prevent instantiation.
     */
    public TransferHelper()
    {
        Exceptions.throwCtorAssertion();
    }

    /**
     * Finds the first non-blank transfer variant in a storage that matches or is compatible with the given variant, attempting to extract the specified amount.
     *
     * @param <V> The type of values transferred by the storage.
     * @param <T> The type of transfer variants used by the storage.
     * @param storage The storage to search within.
     * @param variant The desired transfer variant, or null if any non-blank variant is acceptable.
     * @param transferAmount The amount of the variant to attempt extracting.
     * @return An Optional containing the found transfer variant if one is available and extractable; otherwise, an empty Optional.
     */
    public static <V, T extends TransferVariant<V>>Optional<T> findFirstVariant(Storage<T> storage, @Nullable T variant, long transferAmount)
    {
        if(storage instanceof SingleVariantStorage<@NotNull T> singleVariantStorage)
            return Optional.of(singleVariantStorage.variant);

        if(variant != null && !variant.isBlank())
        {
            try (Transaction transaction = Transaction.openOuter())
            {
                if(storage.extract(variant, transferAmount, transaction) > 0)
                    return Optional.of(variant);

                return Optional.empty();
            }
        }

        for (StorageView<T> storageView : storage.nonEmptyViews())
            return Optional.of(storageView.getResource());

        return Optional.empty();
    }
}