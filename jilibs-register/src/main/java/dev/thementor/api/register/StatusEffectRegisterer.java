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

import java.util.function.BiFunction;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Registers custom status effects for Minecraft.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class StatusEffectRegisterer
{
    /**
     * The mod ID used for registering status effects.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiStatusEffectRegister with the specified mod ID.
     *
     * @param modId the mod ID
     */
    public StatusEffectRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a status effect using a factory function.
     *
     * @param name          the name of the status effect
     * @param category      the category of the status effect (e.g., BENEFICIAL, HARMFUL)
     * @param color         the color of the status effect icon
     * @param factory       the factory used to create instances of the status effect
     * @return the registered status effect entry
     */
    public Holder<MobEffect> register(String name, MobEffectCategory category, int color,
                                               BiFunction<MobEffectCategory, Integer, MobEffect> factory)
    {
        ResourceKey<MobEffect> key = BaseHelper.resourceKey(this.modId, name, Registries.MOB_EFFECT);
        MobEffect effect = factory.apply(category, color);
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, key, effect);
    }
}