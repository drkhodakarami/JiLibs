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
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

/**
 * Registers custom block entities and entity types for Minecraft.
 */
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiEntityRegister
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
    public JiEntityRegister(String modId)
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
        RegistryKey<BlockEntityType<?>> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK_ENTITY_TYPE);
        BlockEntityType<R> beType = FabricBlockEntityTypeBuilder.create(factory, block).build();
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, key, beType);
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
    public <R extends Entity> EntityType<R> register(String name, SpawnGroup spawnGroup, EntityType.EntityFactory<R> factory)
    {
        RegistryKey<EntityType<?>> key = BaseHelper.getKey(this.modId, name, RegistryKeys.ENTITY_TYPE);
        EntityType<R> entityType = EntityType.Builder.create(factory, spawnGroup).build(key);
        return Registry.register(Registries.ENTITY_TYPE, key, entityType);
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
        Identifier id = BaseHelper.identifier(this.modId, name);
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)));
    }
}