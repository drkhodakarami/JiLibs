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

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviderType;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviderType;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;

import dev.thementor.api.shared.annotations.*;
import org.jetbrains.annotations.NotNull;

/**
 * Provides packet codecs for various integer and float providers used in the game.
 */
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

@SuppressWarnings("unchecked")
public class ExtraPacketCodecs
{
    /**
     * A map of integer provider types to their corresponding packet codecs.
     */
    private static final Map<IntProviderType<?>, StreamCodec<RegistryFriendlyByteBuf, ? extends IntProvider>> INT_PROVIDER_CODECS = new HashMap<>();

    /**
     * A map of float provider types to their corresponding packet codecs.
     */
    private static final Map<FloatProviderType<?>, StreamCodec<RegistryFriendlyByteBuf, ? extends FloatProvider>> FLOAT_PROVIDER_CODECS = new HashMap<>();

    // Static block to register default packet codecs for integer and float providers.
    static
    {
        registerDefaults();
    }

    /**
     * Registers a packet codec for an integer provider type.
     *
     * @param providerType the integer provider type
     * @param codec        the packet codec to register
     */
    public static <T extends IntProvider> void registerIntProvider(IntProviderType<@NotNull T> providerType, StreamCodec<RegistryFriendlyByteBuf, T> codec)
    {
        INT_PROVIDER_CODECS.put(providerType, codec);
    }

    /**
     * Registers a packet codec for a float provider type.
     *
     * @param providerType the float provider type
     * @param codec        the packet codec to register
     */
    public static <T extends FloatProvider> void registerFloatProvider(FloatProviderType<T> providerType, StreamCodec<RegistryFriendlyByteBuf, T> codec)
    {
        FLOAT_PROVIDER_CODECS.put(providerType, codec);
    }

    /**
     * Retrieves the packet codec for a given integer provider type.
     *
     * @param providerType the integer provider type
     * @return the corresponding packet codec
     */
    public static StreamCodec<RegistryFriendlyByteBuf, ? extends IntProvider> getIntProviderCodec(IntProviderType<?> providerType)
    {
        return INT_PROVIDER_CODECS.get(providerType);
    }

    /**
     * Retrieves the packet codec for a given float provider type.
     *
     * @param providerType the float provider type
     * @return the corresponding packet codec
     */
    public static StreamCodec<RegistryFriendlyByteBuf, ? extends FloatProvider> getFloatProviderCodec(FloatProviderType<?> providerType)
    {
        return FLOAT_PROVIDER_CODECS.get(providerType);
    }

    /**
     * Encodes an integer provider to a byte buffer.
     *
     * @param buf      the byte buffer to encode into
     * @param intProvider the integer provider to encode
     */
    public static <T extends IntProvider> void encode(ByteBuf buf, T intProvider)
    {
        StreamCodec<RegistryFriendlyByteBuf, T> codec = (StreamCodec<RegistryFriendlyByteBuf, T>) getIntProviderCodec(intProvider.getType());
        codec.encode((RegistryFriendlyByteBuf) buf, intProvider);
    }

    /**
     * Decodes an integer provider from a byte buffer.
     *
     * @param buf       the byte buffer to decode from
     * @param providerType the type of the integer provider
     * @return the decoded integer provider
     */
    public static  <T extends IntProvider> T decode(ByteBuf buf, IntProviderType<@NotNull T> providerType)
    {
        StreamCodec<RegistryFriendlyByteBuf, T> codec = (StreamCodec<RegistryFriendlyByteBuf, T>) getIntProviderCodec(providerType);
        return codec.decode((RegistryFriendlyByteBuf) buf);
    }

    /**
     * Encodes a float provider to a byte buffer.
     *
     * @param buf      the byte buffer to encode into
     * @param floatProvider the float provider to encode
     */
    public static <T extends FloatProvider> void encode(ByteBuf buf, T floatProvider)
    {
        StreamCodec<RegistryFriendlyByteBuf, T> codec = (StreamCodec<RegistryFriendlyByteBuf, T>) getFloatProviderCodec(floatProvider.getType());
        codec.encode((RegistryFriendlyByteBuf) buf, floatProvider);
    }

    /**
     * Decodes a float provider from a byte buffer.
     *
     * @param buf       the byte buffer to decode from
     * @param providerType the type of the float provider
     * @return the decoded float provider
     */
    public static  <T extends FloatProvider> T decode(ByteBuf buf, FloatProviderType<T> providerType)
    {
        StreamCodec<RegistryFriendlyByteBuf, T> codec = (StreamCodec<RegistryFriendlyByteBuf, T>) getFloatProviderCodec(providerType);
        return codec.decode((RegistryFriendlyByteBuf) buf);
    }

    /**
     * Registers default packet codecs for integer and float providers.
     */
    public static void registerDefaults()
    {
        registerIntProvider(IntProviderType.CONSTANT,
                            StreamCodec.of(
                                    (buf, intProvider) -> buf.writeInt(intProvider.getValue()),
                                    buf -> ConstantInt.of(buf.readInt())
                            ));

        registerIntProvider(IntProviderType.UNIFORM,
                            StreamCodec.of(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeInt(intProvider.getMinValue());
                                        buf.writeInt(intProvider.getMaxValue());
                                    },
                                    buf -> UniformInt.of(buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.BIASED_TO_BOTTOM,
                            StreamCodec.of(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeInt(intProvider.getMinValue());
                                        buf.writeInt(intProvider.getMaxValue());
                                    },
                                    buf -> BiasedToBottomInt.of(buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.CLAMPED,
                            StreamCodec.of(
                                    (buf, intProvider) ->
                                    {
                                        IntProviderType<?> type = BuiltInRegistries.INT_PROVIDER_TYPE.getValue(buf.readIdentifier());
                                        StreamCodec<RegistryFriendlyByteBuf, IntProvider> codec = (StreamCodec<RegistryFriendlyByteBuf, IntProvider>) getIntProviderCodec(type);
                                        codec.encode(buf, intProvider.source);
                                        buf.writeInt(intProvider.getMinValue());
                                        buf.writeInt(intProvider.getMaxValue());
                                    },
                                    buf -> ClampedInt.of(
                                            getIntProviderCodec(IntProviderType.CONSTANT).decode(buf),
                                                buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.WEIGHTED_LIST,
                            StreamCodec.of(
                                    (buf, value) ->
                                    {
                                        StreamCodec<RegistryFriendlyByteBuf, ? extends IntProvider> codec = getIntProviderCodec(value.getType());
                                        WeightedList<IntProvider> entries = value.distribution;
                                        StreamCodec<RegistryFriendlyByteBuf, WeightedList<IntProvider>> entriesCodec = weightedListCodec((StreamCodec<RegistryFriendlyByteBuf, IntProvider>) codec);
                                        entriesCodec.encode(buf, entries);
                                    },
                                    buf ->
                                    {
                                        StreamCodec<RegistryFriendlyByteBuf, ? extends IntProvider> codec = getIntProviderCodec(IntProviderType.CONSTANT);
                                        WeightedList<IntProvider> entries = weightedListCodec((StreamCodec<RegistryFriendlyByteBuf, IntProvider>) codec).decode(buf);
                                        return new WeightedListInt(entries);
                                    }
                            ));

        registerIntProvider(IntProviderType.CLAMPED_NORMAL,
                            StreamCodec.of(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeFloat(intProvider.mean);
                                        buf.writeFloat(intProvider.deviation);
                                        buf.writeInt(intProvider.getMinValue());
                                        buf.writeInt(intProvider.getMaxValue());
                                    },
                                    buf -> ClampedNormalInt.of(buf.readFloat(),buf.readFloat(),
                                                                           buf.readInt(), buf.readInt())
                            ));

        registerFloatProvider(FloatProviderType.CONSTANT,
                              StreamCodec.of(
                                      (buf, floatProvider) -> buf.writeFloat(floatProvider.getValue()),
                                      buf -> ConstantFloat.of(buf.readFloat())
                              ));

        registerFloatProvider(FloatProviderType.UNIFORM,
                              StreamCodec.of(
                                      (buf, floatProvider) ->
                                      {
                                          buf.writeFloat(floatProvider.getMinValue());
                                          buf.writeFloat(floatProvider.getMaxValue());
                                      },
                                      buf -> UniformFloat.of(buf.readFloat(), buf.readFloat())
                              ));

        registerFloatProvider(FloatProviderType.CLAMPED_NORMAL,
                            StreamCodec.of(
                                    (buf, floatProvider) ->
                                    {
                                        buf.writeFloat(floatProvider.mean);
                                        buf.writeFloat(floatProvider.deviation);
                                        buf.writeFloat(floatProvider.getMinValue());
                                        buf.writeFloat(floatProvider.getMaxValue());
                                    },
                                    buf -> ClampedNormalFloat.of(buf.readFloat(), buf.readFloat(),
                                                                         buf.readFloat(), buf.readFloat())
                            ));

        registerFloatProvider(FloatProviderType.TRAPEZOID,
                              StreamCodec.of(
                                      (buf, floatProvider) ->
                                      {
                                          buf.writeFloat(floatProvider.getMinValue());
                                          buf.writeFloat(floatProvider.getMaxValue());
                                          buf.writeFloat(floatProvider.plateau);
                                      },
                                      buf -> TrapezoidFloat.of(buf.readFloat(), buf.readFloat(),
                                                                               buf.readFloat())
                              ));
    }

    /**
     * Creates a packet codec for encoding and decoding weight lists.
     *
     * @param elementCodec the codec for encoding and decoding individual elements in the weight list
     * @param <B>          the type of byte buffer being used
     * @param <E>          the type of elements in the weight list
     * @return a packet codec for encoding and decoding weight lists
     */
    public static <B extends ByteBuf, E> StreamCodec<B, WeightedList<E>> weightedListCodec(StreamCodec<B, E> elementCodec) {
        return StreamCodec.<B, Weighted<E>, E, Integer>composite(
                elementCodec, Weighted::value,
                ByteBufCodecs.VAR_INT, Weighted::weight,
                Weighted::new
        ).apply(ByteBufCodecs.list()).map(WeightedList::new, WeightedList::unwrap);
    }
}