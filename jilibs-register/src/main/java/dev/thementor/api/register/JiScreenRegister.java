/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (Jiraiyah)                               *
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

package dev.thementor.api.register;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

/**
 * Registers custom screens and screen handlers for Minecraft.
 */
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiScreenRegister
{
    /**
     * The mod ID used for registering screens and handlers.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiScreenRegister with the specified mod ID.
     *
     * @param modId the mod ID
     */
    public JiScreenRegister(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers an extended screen handler type.
     *
     * @param <R>             the type of the screen handler
     * @param <D>             the type of the custom payload
     * @param name            the name of the screen handler type
     * @param factory         the factory used to create instances of the screen handler
     * @param codec           the packet codec for the custom payload
     * @return the registered extended screen handler type
     */
    public <R extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<R, D>
            register(String name, ExtendedScreenHandlerType.ExtendedFactory<R, D> factory,
                     PacketCodec<? super RegistryByteBuf, D> codec)
    {
        RegistryKey<ScreenHandlerType<?>> key = BaseHelper.getKey(this.modId, name, RegistryKeys.SCREEN_HANDLER);
        return Registry.register(Registries.SCREEN_HANDLER, key, new ExtendedScreenHandlerType<>(factory, codec));
    }
}