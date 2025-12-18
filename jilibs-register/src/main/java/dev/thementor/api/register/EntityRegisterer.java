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
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Registers custom block entities and entity types for Minecraft.
 */
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class EntityRegisterer
{
    /**
     * The mod ID used for registering block entities and entity types.
     */
    private final String modId;

    /**
     * Constructs a new instance of JiEntityRegister with the specified mod ID.
     *
     * @param modId the mod ID
     */
    public EntityRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a new block entity type.
     *
     * @param <R>             the type of the block entity
     * @param name            the name of the block entity type
     * @param block           the block associated with the block entity
     * @param factory         the factory used to create instances of the block entity
     * @return the registered block entity type
     */
    public <R extends BlockEntity> BlockEntityType<R> register(String name, Block block, FabricBlockEntityTypeBuilder.Factory<R> factory)
    {
        ResourceKey<BlockEntityType<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK_ENTITY_TYPE);
        BlockEntityType<R> beType = FabricBlockEntityTypeBuilder.create(factory, block).build();
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, key, beType);
    }

    /**
     * Registers a new entity type.
     *
     * @param <R>             the type of the entity
     * @param name            the name of the entity type
     * @param spawnGroup      the spawn group for the entity
     * @param factory         the factory used to create instances of the entity
     * @return the registered entity type
     */
    public <R extends Entity> EntityType<R> register(String name, MobCategory spawnGroup, EntityType.EntityFactory<R> factory)
    {
        ResourceKey<EntityType<?>> key = BaseHelper.resourceKey(this.modId, name, Registries.ENTITY_TYPE);
        EntityType<R> entityType = EntityType.Builder.of(factory, spawnGroup).build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, entityType);
    }

    /**
     * Registers a new entity type.
     *
     * @param <R>             the type of the entity
     * @param name            the name of the entity type
     * @param type            the entity type builder used to create instances of the entity
     * @return the registered entity type
     */
    public <R extends Entity> EntityType<R> register(String name, EntityType.Builder<R> type)
    {
        Identifier id = BaseHelper.id(this.modId, name);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, type.build(ResourceKey.create(Registries.ENTITY_TYPE, id)));
    }
}