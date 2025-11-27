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
 * Represents a functional interface that accepts ten arguments and returns no result.
 *
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <V> the type of the third argument
 * @param <W> the type of the fourth argument
 * @param <R> the type of the fifth argument
 * @param <E> the type of the sixth argument
 * @param <A> the type of the seventh argument
 * @param <Z> the type of the eighth argument
 * @param <X> the type of the ninth argument
 * @param <M> the type of the tenth argument
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@FunctionalInterface
public interface Consumer10<T, U, V, W, R, E, A, Z, X, M>
{
    /**
     * Performs this operation on the given arguments.
     *
     * @param t the first argument
     * @param u the second argument
     * @param v the third argument
     * @param w the fourth argument
     * @param r the fifth argument
     * @param e the sixth argument
     * @param a the seventh argument
     * @param z the eighth argument
     * @param x the ninth argument
     * @param m the tenth argument
     */
    void accept(T t, U u, V v, W w, R r, E e, A a, Z z, X x, M m);

    /**
     * Returns a composed {@code DecaConsumer} that performs, in sequence, this
     * operation followed by the {@code after} operation.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code DecaConsumer} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default Consumer10<T, U, V, W, R, E, A, Z, X, M> andThen(Consumer10<? super T, ? super U, ? super V, ? super W, ? super R, ? super E, ? super A, ? super Z, ? super X, ? super M> after) {
        Objects.requireNonNull(after);

        return (t, u, v, w, r, e, a, z, x, m) -> {
            accept(t, u, v, w, r, e, a, z, x, m);
            after.accept(t, u, v, w, r, e, a, z, x, m);
        };
    }
}