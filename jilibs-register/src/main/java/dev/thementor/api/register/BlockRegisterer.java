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

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

/**
 * Provides utility methods for registering blocks in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class BlockRegisterer
{
    /**
     * The unique identifier for the mod.
     */
    private final String modId;

    /**
     * Constructs a new JiBlockRegister with the specified mod ID.
     *
     * @param modId The unique identifier for the mod.
     */
    public BlockRegisterer(String modId)
    {
        this.modId = modId;
    }

    /**
     * Registers a block with default settings and uses the provided copy block as a template.
     *
     * @param name The name of the block to register.
     * @return The registered block.
     */
    public Block register(String name)
    {
        return register(name, Block::new);
    }

    /**
     * Registers a block with default settings and copies properties from another block.
     *
     * @param name The name of the block to register.
     * @param copyBlock The block whose properties to copy.
     * @return The registered block.
     */
    public Block register(String name, Block copyBlock)
    {
        return register(name, copyBlock, Block::new);
    }

    /**
     * Registers a block with custom settings and uses the provided copy block as a template.
     *
     * @param name The name of the block to register.
     * @param settings Custom settings for the block.
     * @return The registered block.
     */
    public Block register(String name, BlockBehaviour.Properties settings)
    {
        return register(name, settings, Block::new);
    }

    /**
     * Registers a block using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param block The block instance to register.
     * @return The registered block.
     */
    public <R extends Block> R registerBlock(String name, R block)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    /**
     * Registers a block with default settings and copies properties from another block using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param copyBlock The block whose properties to copy.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, Block copyBlock, Function<BlockBehaviour.Properties, R> factory)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        R block = factory.apply(BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    /**
     * Registers a block with custom settings and creates the block instance using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param settings Custom settings for the block.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, BlockBehaviour.Properties settings, Function<BlockBehaviour.Properties, R> factory)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        R block = factory.apply(settings.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    /**
     * Registers a block with default settings using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, Function<BlockBehaviour.Properties, R> factory)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        R block = factory.apply(BlockBehaviour.Properties.of().setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    /**
     * Registers a stairs block using the provided copy block.
     *
     * @param name The name of the stairs block to register.
     * @param stateBlock The state block for the stairs.
     * @param copyBlock The block whose properties to copy.
     * @return The registered stairs block.
     */
    public StairBlock registerStair(String name, Block stateBlock, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new StairBlock(stateBlock.defaultBlockState(),
                                                   BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a slab block using the provided copy block.
     *
     * @param name The name of the slab block to register.
     * @param copyBlock The block whose properties to copy.
     * @return The registered slab block.
     */
    public SlabBlock registerSlab(String name, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new SlabBlock(BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a button block using the provided settings and copy block.
     *
     * @param name The name of the button block to register.
     * @param blockType The type of block set for the button.
     * @param pressureTicks The number of ticks before the button activates.
     * @param copyBlock The block whose properties to copy.
     * @return The registered button block.
     */
    public ButtonBlock registerButton(String name, BlockSetType blockType, int pressureTicks, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new ButtonBlock(blockType, pressureTicks,
                                                   BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a pressure plate block using the provided settings and copy block.
     *
     * @param name The name of the pressure plate block to register.
     * @param blockType The type of block set for the pressure plate.
     * @param copyBlock The block whose properties to copy.
     * @return The registered pressure plate block.
     */
    public PressurePlateBlock registerPressurePlate(String name, BlockSetType blockType, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new PressurePlateBlock(blockType,
                                                   BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a fence block using the provided settings and copy block.
     *
     * @param name The name of the fence block to register.
     * @param copyBlock The block whose properties to copy.
     * @return The registered fence block.
     */
    public FenceBlock registerFence(String name, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new FenceBlock(BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a fence gate block using the provided settings and copy block.
     *
     * @param name The name of the fence gate block to register.
     * @param woodType The type of wood for the fence gate.
     * @param copyBlock The block whose properties to copy.
     * @return The registered fence gate block.
     */
    public FenceGateBlock registerFenceGate(String name, WoodType woodType, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new FenceGateBlock(woodType, BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a wall block using the provided settings and copy block.
     *
     * @param name The name of the wall block to register.
     * @param copyBlock The block whose properties to copy.
     * @return The registered wall block.
     */
    public WallBlock registerWall(String name, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new WallBlock(BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a door block using the provided settings and copy block.
     *
     * @param name The name of the door block to register.
     * @param blockType The type of block set for the door.
     * @param copyBlock The block whose properties to copy.
     * @return The registered door block.
     */
    public DoorBlock registerDoor(String name, BlockSetType blockType, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new DoorBlock(blockType,
                                              BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }

    /**
     * Registers a trapdoor block using the provided settings and copy block.
     *
     * @param name The name of the trapdoor block to register.
     * @param blockType The type of block set for the trapdoor.
     * @param copyBlock The block whose properties to copy.
     * @return The registered trapdoor block.
     */
    public TrapDoorBlock registerTrapdoor(String name, BlockSetType blockType, Block copyBlock)
    {
        ResourceKey<Block> key = BaseHelper.resourceKey(this.modId, name, Registries.BLOCK);
        return registerBlock(name, new TrapDoorBlock(blockType,
                                                 BlockBehaviour.Properties.ofFullCopy(copyBlock).setId(key)));
    }
}