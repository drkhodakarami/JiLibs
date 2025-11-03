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

package dev.thementor.api.shared.functions;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents a predicate that accepts five arguments and produces a boolean result.
 *
 * @param <T> the type of the first argument
 * @param <U> the type of the second argument
 * @param <V> the type of the third argument
 * @param <W> the type of the fourth argument
 * @param <X> the type of the fifth argument
 */
@SuppressWarnings("unused")
@Developer("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

@FunctionalInterface
public interface Predicate5<T, U, V, W, X>
{
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t the first argument
     * @param u the second argument
     * @param v the third argument
     * @param w the fourth argument
     * @param x the fifth argument
     * @return {@code true} if the predicate evaluates to true for the given arguments, otherwise {@code false}
     */
    boolean test(T t, U u, V v, W w, X x);

    /**
     * Returns a composed {@code PentaPredicate} that represents a logical AND of this predicate and another.
     *
     * @param other the predicate to combine with this predicate
     * @return a composed {@code PentaPredicate} that represents a logical AND of this predicate and another
     * @throws NullPointerException if {@code other} is null
     */
    default Predicate5<T, U, V, W, X> and(Predicate5<? super T, ? super U, ? super V, ? super W, ? super X> other)
    {
        return (t, u, v, w, x) -> test(t, u, v, w, x) && other.test(t, u, v, w, x);
    }

    /**
     * Returns a composed {@code PentaPredicate} that represents a logical OR of this predicate and another.
     *
     * @param other the predicate to combine with this predicate
     * @return a composed {@code PentaPredicate} that represents a logical OR of this predicate and another
     * @throws NullPointerException if {@code other} is null
     */
    default Predicate5<T, U, V, W, X> or(Predicate5<? super T, ? super U, ? super V, ? super W, ? super X> other)
    {
        return (t, u, v, w, x) -> test(t, u, v, w, x) || other.test(t, u, v, w, x);
    }

    /**
     * Returns a composed {@code PentaPredicate} that represents the logical negation of this predicate.
     *
     * @return a composed {@code PentaPredicate} that represents the logical negation of this predicate
     */
    default Predicate5<T, U, V, W, X> negate()
    {
        return (t, u, v, w, x) -> !test(t, u, v, w, x);
    }
}