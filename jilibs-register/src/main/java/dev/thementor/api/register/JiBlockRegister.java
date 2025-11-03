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
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

/**
 * Provides utility methods for registering blocks in Minecraft.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")

public class JiBlockRegister
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
    public JiBlockRegister(String modId)
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
    public Block register(String name, AbstractBlock.Settings settings)
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Registers a block with default settings and copies properties from another block using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param copyBlock The block whose properties to copy.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, Block copyBlock, Function<AbstractBlock.Settings, R> factory)
    {
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        R block = factory.apply(AbstractBlock.Settings.copy(copyBlock).registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Registers a block with custom settings and creates the block instance using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param settings Custom settings for the block.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, AbstractBlock.Settings settings, Function<AbstractBlock.Settings, R> factory)
    {
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        R block = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Registers a block with default settings using the provided factory function.
     *
     * @param name The name of the block to register.
     * @param factory The factory function to create the block instance.
     * @return The registered block.
     */
    public <R extends Block> R register(String name, Function<AbstractBlock.Settings, R> factory)
    {
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        R block = factory.apply(AbstractBlock.Settings.create().registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }

    /**
     * Registers a stairs block using the provided copy block.
     *
     * @param name The name of the stairs block to register.
     * @param stateBlock The state block for the stairs.
     * @param copyBlock The block whose properties to copy.
     * @return The registered stairs block.
     */
    public StairsBlock registerStair(String name, Block stateBlock, Block copyBlock)
    {
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new StairsBlock(stateBlock.getDefaultState(),
                                                   AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new SlabBlock(AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new ButtonBlock(blockType, pressureTicks,
                                                   AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new PressurePlateBlock(blockType,
                                                   AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new FenceBlock(AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new FenceGateBlock(woodType, AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new WallBlock(AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
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
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new DoorBlock(blockType,
                                              AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
    }

    /**
     * Registers a trapdoor block using the provided settings and copy block.
     *
     * @param name The name of the trapdoor block to register.
     * @param blockType The type of block set for the trapdoor.
     * @param copyBlock The block whose properties to copy.
     * @return The registered trapdoor block.
     */
    public TrapdoorBlock registerTrapdoor(String name, BlockSetType blockType, Block copyBlock)
    {
        RegistryKey<Block> key = BaseHelper.getKey(this.modId, name, RegistryKeys.BLOCK);
        return registerBlock(name, new TrapdoorBlock(blockType,
                                                 AbstractBlock.Settings.copy(copyBlock).registryKey(key)));
    }
}