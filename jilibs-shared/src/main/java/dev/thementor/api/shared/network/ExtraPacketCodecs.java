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

package dev.thementor.api.shared.network;

import dev.thementor.api.shared.annotations.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.math.floatprovider.*;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.collection.Pool;

import java.util.HashMap;
import java.util.Map;

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
    private static final Map<IntProviderType<?>, PacketCodec<RegistryByteBuf, ? extends IntProvider>> INT_PROVIDER_CODECS = new HashMap<>();

    /**
     * A map of float provider types to their corresponding packet codecs.
     */
    private static final Map<FloatProviderType<?>, PacketCodec<RegistryByteBuf, ? extends FloatProvider>> FLOAT_PROVIDER_CODECS = new HashMap<>();

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
    public static <T extends IntProvider> void registerIntProvider(IntProviderType<T> providerType, PacketCodec<RegistryByteBuf, T> codec)
    {
        INT_PROVIDER_CODECS.put(providerType, codec);
    }

    /**
     * Registers a packet codec for a float provider type.
     *
     * @param providerType the float provider type
     * @param codec        the packet codec to register
     */
    public static <T extends FloatProvider> void registerFloatProvider(FloatProviderType<T> providerType, PacketCodec<RegistryByteBuf, T> codec)
    {
        FLOAT_PROVIDER_CODECS.put(providerType, codec);
    }

    /**
     * Retrieves the packet codec for a given integer provider type.
     *
     * @param providerType the integer provider type
     * @return the corresponding packet codec
     */
    public static PacketCodec<RegistryByteBuf, ? extends IntProvider> getIntProviderCodec(IntProviderType<?> providerType)
    {
        return INT_PROVIDER_CODECS.get(providerType);
    }

    /**
     * Retrieves the packet codec for a given float provider type.
     *
     * @param providerType the float provider type
     * @return the corresponding packet codec
     */
    public static PacketCodec<RegistryByteBuf, ? extends FloatProvider> getFloatProviderCodec(FloatProviderType<?> providerType)
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
        PacketCodec<RegistryByteBuf, T> codec = (PacketCodec<RegistryByteBuf, T>) getIntProviderCodec(intProvider.getType());
        codec.encode((RegistryByteBuf) buf, intProvider);
    }

    /**
     * Decodes an integer provider from a byte buffer.
     *
     * @param buf       the byte buffer to decode from
     * @param providerType the type of the integer provider
     * @return the decoded integer provider
     */
    public static  <T extends IntProvider> T decode(ByteBuf buf, IntProviderType<T> providerType)
    {
        PacketCodec<RegistryByteBuf, T> codec = (PacketCodec<RegistryByteBuf, T>) getIntProviderCodec(providerType);
        return codec.decode((RegistryByteBuf) buf);
    }

    /**
     * Encodes a float provider to a byte buffer.
     *
     * @param buf      the byte buffer to encode into
     * @param floatProvider the float provider to encode
     */
    public static <T extends FloatProvider> void encode(ByteBuf buf, T floatProvider)
    {
        PacketCodec<RegistryByteBuf, T> codec = (PacketCodec<RegistryByteBuf, T>) getFloatProviderCodec(floatProvider.getType());
        codec.encode((RegistryByteBuf) buf, floatProvider);
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
        PacketCodec<RegistryByteBuf, T> codec = (PacketCodec<RegistryByteBuf, T>) getFloatProviderCodec(providerType);
        return codec.decode((RegistryByteBuf) buf);
    }

    /**
     * Registers default packet codecs for integer and float providers.
     */
    public static void registerDefaults()
    {
        registerIntProvider(IntProviderType.CONSTANT,
                            PacketCodec.ofStatic(
                                    (buf, intProvider) -> buf.writeInt(intProvider.getValue()),
                                    buf -> ConstantIntProvider.create(buf.readInt())
                            ));

        registerIntProvider(IntProviderType.UNIFORM,
                            PacketCodec.ofStatic(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeInt(intProvider.getMin());
                                        buf.writeInt(intProvider.getMax());
                                    },
                                    buf -> UniformIntProvider.create(buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.BIASED_TO_BOTTOM,
                            PacketCodec.ofStatic(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeInt(intProvider.getMin());
                                        buf.writeInt(intProvider.getMax());
                                    },
                                    buf -> BiasedToBottomIntProvider.create(buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.CLAMPED,
                            PacketCodec.ofStatic(
                                    (buf, intProvider) ->
                                    {
                                        IntProviderType<?> type = Registries.INT_PROVIDER_TYPE.get(buf.readIdentifier());
                                        PacketCodec<RegistryByteBuf, IntProvider> codec = (PacketCodec<RegistryByteBuf, IntProvider>) getIntProviderCodec(type);
                                        codec.encode(buf, intProvider.source);
                                        buf.writeInt(intProvider.getMin());
                                        buf.writeInt(intProvider.getMax());
                                    },
                                    buf -> ClampedIntProvider.create(
                                            getIntProviderCodec(IntProviderType.CONSTANT).decode(buf),
                                                buf.readInt(), buf.readInt())
                            ));

        registerIntProvider(IntProviderType.WEIGHTED_LIST,
                            PacketCodec.ofStatic(
                                    (buf, value) ->
                                    {
                                        PacketCodec<RegistryByteBuf, ? extends IntProvider> codec = getIntProviderCodec(value.getType());
                                        Pool<IntProvider> entries = value.weightedList;
                                        PacketCodec<RegistryByteBuf, Pool<IntProvider>> entriesCodec = weightedListCodec((PacketCodec<RegistryByteBuf, IntProvider>) codec);
                                        entriesCodec.encode(buf, entries);
                                    },
                                    buf ->
                                    {
                                        PacketCodec<RegistryByteBuf, ? extends IntProvider> codec = getIntProviderCodec(IntProviderType.CONSTANT);
                                        Pool<IntProvider> entries = weightedListCodec((PacketCodec<RegistryByteBuf, IntProvider>) codec).decode(buf);
                                        return new WeightedListIntProvider(entries);
                                    }
                            ));

        registerIntProvider(IntProviderType.CLAMPED_NORMAL,
                            PacketCodec.ofStatic(
                                    (buf, intProvider) ->
                                    {
                                        buf.writeFloat(intProvider.mean);
                                        buf.writeFloat(intProvider.deviation);
                                        buf.writeInt(intProvider.getMin());
                                        buf.writeInt(intProvider.getMax());
                                    },
                                    buf -> ClampedNormalIntProvider.of(buf.readFloat(),buf.readFloat(),
                                                                           buf.readInt(), buf.readInt())
                            ));

        registerFloatProvider(FloatProviderType.CONSTANT,
                              PacketCodec.ofStatic(
                                      (buf, floatProvider) -> buf.writeFloat(floatProvider.getValue()),
                                      buf -> ConstantFloatProvider.create(buf.readFloat())
                              ));

        registerFloatProvider(FloatProviderType.UNIFORM,
                              PacketCodec.ofStatic(
                                      (buf, floatProvider) ->
                                      {
                                          buf.writeFloat(floatProvider.getMin());
                                          buf.writeFloat(floatProvider.getMax());
                                      },
                                      buf -> UniformFloatProvider.create(buf.readFloat(), buf.readFloat())
                              ));

        registerFloatProvider(FloatProviderType.CLAMPED_NORMAL,
                            PacketCodec.ofStatic(
                                    (buf, floatProvider) ->
                                    {
                                        buf.writeFloat(floatProvider.mean);
                                        buf.writeFloat(floatProvider.deviation);
                                        buf.writeFloat(floatProvider.getMin());
                                        buf.writeFloat(floatProvider.getMax());
                                    },
                                    buf -> ClampedNormalFloatProvider.create(buf.readFloat(), buf.readFloat(),
                                                                         buf.readFloat(), buf.readFloat())
                            ));

        registerFloatProvider(FloatProviderType.TRAPEZOID,
                              PacketCodec.ofStatic(
                                      (buf, floatProvider) ->
                                      {
                                          buf.writeFloat(floatProvider.getMin());
                                          buf.writeFloat(floatProvider.getMax());
                                          buf.writeFloat(floatProvider.plateau);
                                      },
                                      buf -> TrapezoidFloatProvider.create(buf.readFloat(), buf.readFloat(),
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
    public static <B extends ByteBuf, E> PacketCodec<B, Pool<E>> weightedListCodec(PacketCodec<B, E> elementCodec) {
        return PacketCodec.<B, Weighted<E>, E, Integer>tuple(
                elementCodec, Weighted::value,
                PacketCodecs.VAR_INT, Weighted::weight,
                Weighted::new
        ).collect(PacketCodecs.toList()).xmap(Pool::new, Pool::getEntries);
    }
}