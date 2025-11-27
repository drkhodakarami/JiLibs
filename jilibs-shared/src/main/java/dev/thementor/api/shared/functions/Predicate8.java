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
 * Represents a predicate that accepts eight arguments and produces a boolean result.
 *
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <V> the type of the third argument
 * @param <W> the type of the fourth argument
 * @param <X> the type of the fifth argument
 * @param <Y> the type of the sixth argument
 * @param <Z> the type of the seventh argument
 * @param <A> the type of the eighth argument
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@FunctionalInterface
public interface Predicate8<T, U, V, W, X, Y, Z, A>
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
     * @return {@code true} if the predicate evaluates to true for the given arguments, otherwise {@code false}
     */
    boolean test(T t, U u, V v, W w, X x, Y y, Z z, A a);

    /**
     * Returns a composed {@code OctaPredicate} that represents a logical AND of this predicate and another.
     *
     * @param other the predicate to combine with this predicate
     * @return a composed {@code OctaPredicate} that represents a logical AND of this predicate and another
     * @throws NullPointerException if {@code other} is null
     */
    default Predicate8<T, U, V, W, X, Y, Z, A> and(Predicate8<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z, ? super A> other)
    {
        return (t, u, v, w, x, y, z, a) -> test(t, u, v, w, x, y, z, a) && other.test(t, u, v, w, x, y, z, a);
    }

    /**
     * Returns a composed {@code OctaPredicate} that represents a logical OR of this predicate and another.
     *
     * @param other the predicate to combine with this predicate
     * @return a composed {@code OctaPredicate} that represents a logical OR of this predicate and another
     * @throws NullPointerException if {@code other} is null
     */
    default Predicate8<T, U, V, W, X, Y, Z, A> or(Predicate8<? super T, ? super U, ? super V, ? super W, ? super X, ? super Y, ? super Z, ? super A> other)
    {
        return (t, u, v, w, x, y, z, a) -> test(t, u, v, w, x, y, z, a) || other.test(t, u, v, w, x, y, z, a);
    }

    /**
     * Returns a composed {@code OctaPredicate} that represents the logical negation of this predicate.
     *
     * @return a composed {@code OctaPredicate} that represents the logical negation of this predicate
     */
    default Predicate8<T, U, V, W, X, Y, Z, A> negate()
    {
        return (t, u, v, w, x, y, z, a) -> !test(t, u, v, w, x, y, z, a);
    }
}