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

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a predicate (boolean-valued function) of ten arguments.
 *
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <V> the type of the third argument
 * @param <W> the type of the fourth argument
 * @param <X> the type of the fifth argument
 * @param <Y> the type of the sixth argument
 * @param <Z> the type of the seventh argument
 * @param <A> the type of the eighth argument
 * @param <M> the type of the ninth argument
 * @param <N> the type of the tenth argument
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@FunctionalInterface
public interface Predicate10<T, U, V, W, X, Y, Z, A, M, N>
{
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t the first argument
     * @param u the second argument
     * @param v the third argument
     * @param w the fourth argument
     * @param x the fifth argument
     * @param y the sixth argument
     * @param z the seventh argument
     * @param a the eighth argument
     * @param m the ninth argument
     * @param n the tenth argument
     * @return {@code true} if the arguments match this predicate, otherwise {@code false}
     */
    boolean test(T t, U u, V v, W w, X x, Y y, Z z, A a, M m, N n);

    /**
     * Returns a composed predicate that represents a logical AND of this
     * predicate and another.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the logical AND of this
     * predicate and the other predicate
     * @throws NullPointerException if other is null
     */
    default Predicate10<T, U, V, W, X, Y, Z, A, M, N> and(Predicate10<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z, ? super A, ? super M, ? super N> other)
    {
        return (t, u, v, w, x, y, z, a, m, n) -> test(t, u, v, w, x, y, z, a, m, n) && other.test(t, u, v, w, x, y, z, a, m, n);
    }

    /**
     * Returns a composed predicate that represents a logical OR of this
     * predicate and another.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the logical OR of this
     * predicate and the other predicate
     * @throws NullPointerException if other is null
     */
    default Predicate10<T, U, V, W, X, Y, Z, A, M, N> or(Predicate10<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z, ? super A, ? super M, ? super N> other)
    {
        return (t, u, v, w, x, y, z, a, m, n) -> test(t, u, v, w, x, y, z, a, m, n) || other.test(t, u, v, w, x, y, z, a, m, n);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default Predicate10<T, U, V, W, X, Y, Z, A, M, N> negate()
    {
        return (t, u, v, w, x, y, z, a, m, n) -> !this.test(t, u, v, w, x, y, z, a, m, n);
    }
}