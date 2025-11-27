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

package dev.thementor.api.shared.network;

import java.util.function.Function;

import com.mojang.datafixers.util.*;
import org.jetbrains.annotations.NotNull;

import net.minecraft.network.codec.StreamCodec;

import dev.thementor.api.shared.annotations.*;

/**
 * Provides utility methods to create packet codecs for composites of various sizes.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class StreamCodecComposites
{
    /**
     * Creates a packet codec for a composite of nine elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> to)
    {
        return new StreamCodec<>()
        {
            @Override
            public @NotNull C decode(B buf)
            {
                T1 object1 = codec1.decode(buf);
                T2 object2 = codec2.decode(buf);
                T3 object3 = codec3.decode(buf);
                T4 object4 = codec4.decode(buf);
                T5 object5 = codec5.decode(buf);
                T6 object6 = codec6.decode(buf);
                T7 object7 = codec7.decode(buf);
                T8 object8 = codec8.decode(buf);
                T9 object9 = codec9.decode(buf);
                return to.apply(object1, object2, object3, object4, object5, object6, object7, object8, object9);
            }

            @Override
            public void encode(B buf, C value)
            {
                codec1.encode(buf, from1.apply(value));
                codec2.encode(buf, from2.apply(value));
                codec3.encode(buf, from3.apply(value));
                codec4.encode(buf, from4.apply(value));
                codec5.encode(buf, from5.apply(value));
                codec6.encode(buf, from6.apply(value));
                codec7.encode(buf, from7.apply(value));
                codec8.encode(buf, from8.apply(value));
                codec9.encode(buf, from9.apply(value));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of ten elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> to)
    {
        return new StreamCodec<>()
        {
            @Override
            public @NotNull C decode(B buf)
            {
                T1 object1 = codec1.decode(buf);
                T2 object2 = codec2.decode(buf);
                T3 object3 = codec3.decode(buf);
                T4 object4 = codec4.decode(buf);
                T5 object5 = codec5.decode(buf);
                T6 object6 = codec6.decode(buf);
                T7 object7 = codec7.decode(buf);
                T8 object8 = codec8.decode(buf);
                T9 object9 = codec9.decode(buf);
                T10 object10 = codec10.decode(buf);
                return to.apply(object1, object2, object3, object4, object5, object6, object7, object8, object9, object10);
            }

            @Override
            public void encode(B buf, C value)
            {
                codec1.encode(buf, from1.apply(value));
                codec2.encode(buf, from2.apply(value));
                codec3.encode(buf, from3.apply(value));
                codec4.encode(buf, from4.apply(value));
                codec5.encode(buf, from5.apply(value));
                codec6.encode(buf, from6.apply(value));
                codec7.encode(buf, from7.apply(value));
                codec8.encode(buf, from8.apply(value));
                codec9.encode(buf, from9.apply(value));
                codec10.encode(buf, from10.apply(value));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of eleven elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1,
            Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2,
            Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3,
            Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4,
            Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5,
            Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6,
            Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7,
            Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8,
            Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9,
            Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10,
            Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11,
            Function<C, T11> from11,
            Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of twelve elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param <T12>      the type of the twelfth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param codec12    the packet codec for the twelfth element
     * @param from12     a function to extract the twelfth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1, Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2, Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3, Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4, Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5, Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6, Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7, Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8, Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9, Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10, Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11, Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12, Function<C, T12> from12,
            Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12, object13);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of thirteen elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param <T12>      the type of the twelfth element in the composite
     * @param <T13>      the type of the thirteenth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param codec12    the packet codec for the twelfth element
     * @param from12     a function to extract the twelfth element from the value being decoded
     * @param codec13    the packet codec for the thirteenth element
     * @param from13     a function to extract the thirteenth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1, Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2, Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3, Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4, Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5, Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6, Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7, Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8, Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9, Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10, Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11, Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12, Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13, Function<C, T13> from13,
            Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12, object13, object14);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of fourteen elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param <T12>      the type of the twelfth element in the composite
     * @param <T13>      the type of the thirteenth element in the composite
     * @param <T14>      the type of the fourteenth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param codec12    the packet codec for the twelfth element
     * @param from12     a function to extract the twelfth element from the value being decoded
     * @param codec13    the packet codec for the thirteenth element
     * @param from13     a function to extract the thirteenth element from the value being decoded
     * @param codec14    the packet codec for the fourteenth element
     * @param from14     a function to extract the fourteenth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1, Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2, Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3, Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4, Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5, Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6, Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7, Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8, Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9, Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10, Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11, Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12, Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13, Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14, Function<C, T14> from14,
            Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12, object13, object14, object15);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of fifteen elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param <T12>      the type of the twelfth element in the composite
     * @param <T13>      the type of the thirteenth element in the composite
     * @param <T14>      the type of the fourteenth element in the composite
     * @param <T15>      the type of the fifteenth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param codec12    the packet codec for the twelfth element
     * @param from12     a function to extract the twelfth element from the value being decoded
     * @param codec13    the packet codec for the thirteenth element
     * @param from13     a function to extract the thirteenth element from the value being decoded
     * @param codec14    the packet codec for the fourteenth element
     * @param from14     a function to extract the fourteenth element from the value being decoded
     * @param codec15    the packet codec for the fifteenth element
     * @param from15     a function to extract the fifteenth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1, Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2, Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3, Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4, Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5, Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6, Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7, Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8, Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9, Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10, Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11, Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12, Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13, Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14, Function<C, T14> from14,
            StreamCodec<? super B, T15> codec15, Function<C, T15> from15,
            Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                T15 object16 = codec15.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12, object13, object14, object15, object16);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
                codec15.encode(object, from15.apply(object2));
            }
        };
    }

    /**
     * Creates a packet codec for a composite of sixteen elements.
     *
     * @param <B>        the type of byte buffer being used
     * @param <C>        the type of value being encoded and decoded
     * @param <T1>       the type of the first element in the composite
     * @param <T2>       the type of the second element in the composite
     * @param <T3>       the type of the third element in the composite
     * @param <T4>       the type of the fourth element in the composite
     * @param <T5>       the type of the fifth element in the composite
     * @param <T6>       the type of the sixth element in the composite
     * @param <T7>       the type of the seventh element in the composite
     * @param <T8>       the type of the eighth element in the composite
     * @param <T9>       the type of the ninth element in the composite
     * @param <T10>      the type of the tenth element in the composite
     * @param <T11>      the type of the eleventh element in the composite
     * @param <T12>      the type of the twelfth element in the composite
     * @param <T13>      the type of the thirteenth element in the composite
     * @param <T14>      the type of the fourteenth element in the composite
     * @param <T15>      the type of the fifteenth element in the composite
     * @param <T16>      the type of the sixteenth element in the composite
     * @param codec1     the packet codec for the first element
     * @param from1      a function to extract the first element from the value being decoded
     * @param codec2     the packet codec for the second element
     * @param from2      a function to extract the second element from the value being decoded
     * @param codec3     the packet codec for the third element
     * @param from3      a function to extract the third element from the value being decoded
     * @param codec4     the packet codec for the fourth element
     * @param from4      a function to extract the fourth element from the value being decoded
     * @param codec5     the packet codec for the fifth element
     * @param from5      a function to extract the fifth element from the value being decoded
     * @param codec6     the packet codec for the sixth element
     * @param from6      a function to extract the sixth element from the value being decoded
     * @param codec7     the packet codec for the seventh element
     * @param from7      a function to extract the seventh element from the value being decoded
     * @param codec8     the packet codec for the eighth element
     * @param from8      a function to extract the eighth element from the value being decoded
     * @param codec9     the packet codec for the ninth element
     * @param from9      a function to extract the ninth element from the value being decoded
     * @param codec10    the packet codec for the tenth element
     * @param from10     a function to extract the tenth element from the value being decoded
     * @param codec11    the packet codec for the eleventh element
     * @param from11     a function to extract the eleventh element from the value being decoded
     * @param codec12    the packet codec for the twelfth element
     * @param from12     a function to extract the twelfth element from the value being decoded
     * @param codec13    the packet codec for the thirteenth element
     * @param from13     a function to extract the thirteenth element from the value being decoded
     * @param codec14    the packet codec for the fourteenth element
     * @param from14     a function to extract the fourteenth element from the value being decoded
     * @param codec15    the packet codec for the fifteenth element
     * @param from15     a function to extract the fifteenth element from the value being decoded
     * @param codec16    the packet codec for the sixteenth element
     * @param from16     a function to extract the sixteenth element from the value being decoded
     * @param to         a function to combine the elements into the final value
     * @return a packet codec for the composite
     */
    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16> StreamCodec<B, C> composite(
            StreamCodec<? super B, T1> codec1, Function<C, T1> from1,
            StreamCodec<? super B, T2> codec2, Function<C, T2> from2,
            StreamCodec<? super B, T3> codec3, Function<C, T3> from3,
            StreamCodec<? super B, T4> codec4, Function<C, T4> from4,
            StreamCodec<? super B, T5> codec5, Function<C, T5> from5,
            StreamCodec<? super B, T6> codec6, Function<C, T6> from6,
            StreamCodec<? super B, T7> codec7, Function<C, T7> from7,
            StreamCodec<? super B, T8> codec8, Function<C, T8> from8,
            StreamCodec<? super B, T9> codec9, Function<C, T9> from9,
            StreamCodec<? super B, T10> codec10, Function<C, T10> from10,
            StreamCodec<? super B, T11> codec11, Function<C, T11> from11,
            StreamCodec<? super B, T12> codec12, Function<C, T12> from12,
            StreamCodec<? super B, T13> codec13, Function<C, T13> from13,
            StreamCodec<? super B, T14> codec14, Function<C, T14> from14,
            StreamCodec<? super B, T15> codec15, Function<C, T15> from15,
            StreamCodec<? super B, T16> codec16, Function<C, T16> from16,
            Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, C> to
    ) {
        return new StreamCodec<>() {
            @Override
            public @NotNull C decode(B object) {
                T1 object2 = codec1.decode(object);
                T2 object3 = codec2.decode(object);
                T3 object4 = codec3.decode(object);
                T4 object5 = codec4.decode(object);
                T5 object6 = codec5.decode(object);
                T6 object7 = codec6.decode(object);
                T7 object8 = codec7.decode(object);
                T8 object9 = codec8.decode(object);
                T9 object10 = codec9.decode(object);
                T10 object11 = codec10.decode(object);
                T11 object12 = codec11.decode(object);
                T12 object13 = codec12.decode(object);
                T13 object14 = codec13.decode(object);
                T14 object15 = codec14.decode(object);
                T15 object16 = codec15.decode(object);
                T16 object17 = codec16.decode(object);
                return to.apply(object2, object3, object4, object5, object6,
                                object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17);
            }

            @Override
            public void encode(B object, C object2) {
                codec1.encode(object, from1.apply(object2));
                codec2.encode(object, from2.apply(object2));
                codec3.encode(object, from3.apply(object2));
                codec4.encode(object, from4.apply(object2));
                codec5.encode(object, from5.apply(object2));
                codec6.encode(object, from6.apply(object2));
                codec7.encode(object, from7.apply(object2));
                codec8.encode(object, from8.apply(object2));
                codec9.encode(object, from9.apply(object2));
                codec10.encode(object, from10.apply(object2));
                codec11.encode(object, from11.apply(object2));
                codec12.encode(object, from12.apply(object2));
                codec13.encode(object, from13.apply(object2));
                codec14.encode(object, from14.apply(object2));
                codec15.encode(object, from15.apply(object2));
                codec16.encode(object, from16.apply(object2));
            }
        };
    }
}