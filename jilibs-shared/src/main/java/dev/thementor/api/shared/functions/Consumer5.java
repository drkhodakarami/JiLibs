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

package dev.thementor.api.shared.functions;

import java.util.Objects;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a functional interface that accepts five arguments and returns no result.
 *
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <V> the type of the third argument
 * @param <W> the type of the fourth argument
 * @param <R> the type of the fifth argument
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@FunctionalInterface
public interface Consumer5<T, U, V, W, R>
{
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first argument
     * @param u the second argument
     * @param v the third argument
     * @param w the fourth argument
     * @param r the fifth argument
     */
    void accept(T t, U u, V v, W w, R r);

    /**
     * Returns a composed {@code PentaConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code PentaConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer5<T, U, V, W, R> andThen(Consumer5<? super T, ? super U, ? super V, ? super W, ? super R> after) {
        Objects.requireNonNull(after);

        return (t, u, v, w, r) -> {
            accept(t, u, v, w, r);
            after.accept(t, u, v, w, r);
        };
    }
}