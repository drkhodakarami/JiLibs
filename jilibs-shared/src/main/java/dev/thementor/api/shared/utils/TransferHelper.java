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

package dev.thementor.api.shared.utils;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.exceptions.Exceptions;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.TransferVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

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
        if(storage instanceof SingleVariantStorage<T> singleVariantStorage)
            return Optional.ofNullable(singleVariantStorage.variant);

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
            return Optional.ofNullable(storageView.getResource());

        return Optional.empty();
    }
}