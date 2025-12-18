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

package dev.thementor.api.register;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import org.jetbrains.annotations.NotNull;

/**
 * Registers custom screens and screen handlers for Minecraft.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class ScreenRegisterer
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
    public ScreenRegisterer(String modId)
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
    public <R extends AbstractContainerMenu, D extends CustomPacketPayload> ExtendedScreenHandlerType<@NotNull R, D>
            register(String name, ExtendedScreenHandlerType.ExtendedFactory<@NotNull R, D> factory,
                     StreamCodec<? super RegistryFriendlyByteBuf, D> codec)
    {
        ResourceKey<MenuType<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.MENU);
        return Registry.register(BuiltInRegistries.MENU, key, new ExtendedScreenHandlerType<>(factory, codec));
    }
}