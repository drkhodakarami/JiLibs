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

import com.google.common.collect.ImmutableSet;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;
import dev.thementor.mixin.BlockEntityTypeAccessor;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.tag.TagKey;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a set of wood-related items and blocks.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@CreatedAt("2025-04-15")
@ModifiedAt("2025-04-19")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class JiWoodSet
{
    private static final List<JiWoodSet> WOOD_SETS = new ArrayList<>();

    public static List<JiWoodSet> getWoodSets()
    {
        return WOOD_SETS;
    }

    private final String name;
    private final SaplingGenerator saplingGenerator;

    public final WoodType woodType;
    public final BlockSetType blockSetType;
    public final PillarBlock log, strippedLog;
    public final PillarBlock wood, strippedWood;
    public final Block planks;
    public final Block leaves;
    public final SaplingBlock sapling;

    public final StairsBlock stairs;
    public final SlabBlock slab;
    public final FenceBlock fence;
    public final FenceGateBlock fenceGate;
    public final DoorBlock door;
    public final TrapdoorBlock trapdoor;
    public final PressurePlateBlock pressurePlate;
    public final ButtonBlock button;

    public final SignBlock sign;
    public final WallSignBlock wallSign;
    public final HangingSignBlock hangingSign;
    public final WallHangingSignBlock wallHangingSign;
    public final SignItem signItem;
    public final HangingSignItem hangingSignItem;

    public final EntityType<BoatEntity> boatEntityType;
    public final EntityType<ChestBoatEntity> chestBoatEntityType;
    public final Item boatItem;
    public final Item chestBoatItem;

    public final TagKey<Item> logsItemTag;
    public final TagKey<Block> logsBlockTag;

    public JiWoodSet(String modid, String name,
                     SaplingGenerator saplingGenerator,
                     Supplier<WoodType> woodType,
                     Function<AbstractBlock.Settings, Block> planks,
                     Function<AbstractBlock.Settings, PillarBlock> log,
                     Function<AbstractBlock.Settings, PillarBlock> strippedLog,
                     Function<AbstractBlock.Settings, PillarBlock> strippedWood,
                     Function<AbstractBlock.Settings, PillarBlock> wood,
                     Function<AbstractBlock.Settings, Block> leaves,
                     Function<AbstractBlock.Settings, SaplingBlock> sapling,
                     Function<AbstractBlock.Settings, StairsBlock> stairs,
                     Function<AbstractBlock.Settings, SlabBlock> slab,
                     Function<AbstractBlock.Settings, FenceBlock> fence,
                     Function<AbstractBlock.Settings, FenceGateBlock> fenceGate,
                     Function<AbstractBlock.Settings, DoorBlock> door,
                     Function<AbstractBlock.Settings, TrapdoorBlock> trapdoor,
                     Function<AbstractBlock.Settings, PressurePlateBlock> pressurePlate,
                     Function<AbstractBlock.Settings, ButtonBlock> button,
                     Function<AbstractBlock.Settings, SignBlock> sign,
                     Function<AbstractBlock.Settings, WallSignBlock> wallSign,
                     Function<AbstractBlock.Settings, HangingSignBlock> hangingSign,
                     Function<AbstractBlock.Settings, WallHangingSignBlock> wallHangingSign,
                     Function<Item.Settings, SignItem> signItem,
                     Function<Item.Settings, HangingSignItem> hangingSignItem,
                     Function<Supplier<Item>, EntityType.Builder<BoatEntity>> boatEntityType,
                     Function<Supplier<Item>, EntityType.Builder<ChestBoatEntity>> chestBoatEntityType,
                     Function<Item.Settings, Item> boatItem,
                     Function<Item.Settings, Item> chestBoatItem)
    {
        JiBlockRegister blockRegister = new JiBlockRegister(modid);
        JiItemRegister itemRegister = new JiItemRegister(modid);
        JiBlockItemRegister blockItemRegister = new JiBlockItemRegister(modid);
        JiEntityRegister entityRegister = new JiEntityRegister(modid);

        this.name = name;
        this.saplingGenerator = saplingGenerator;

        this.blockSetType = new BlockSetType(BaseHelper.identifier(modid, this.name).toString());

        this.woodType = WoodTypeBuilder.copyOf(woodType.get()).register(BaseHelper.identifier(modid, this.name), this.blockSetType);

        this.planks = blockRegister.register(this.name + "_planks", Blocks.OAK_PLANKS, planks == null ? Block::new : planks);
        blockItemRegister.register(this.planks);

        this.log = blockRegister.register(this.name + "_log", Blocks.OAK_LOG, log == null ? PillarBlock::new : log);
        blockItemRegister.register(this.log);

        this.strippedLog = blockRegister.register(this.name + "_stripped_log", Blocks.STRIPPED_OAK_LOG, strippedLog == null ? PillarBlock::new : strippedLog);
        blockItemRegister.register(this.strippedLog);

        this.strippedWood = blockRegister.register(this.name + "_stripped_wood", Blocks.STRIPPED_OAK_WOOD, strippedWood == null ? PillarBlock::new : strippedWood);
        blockItemRegister.register(this.strippedWood);

        this.wood = blockRegister.register(this.name + "_wood", Blocks.OAK_WOOD, wood == null ? PillarBlock::new : wood);
        blockItemRegister.register(this.wood);

        this.leaves = blockRegister.register(this.name + "_leaves", Blocks.OAK_LEAVES,
                                             leaves == null ? settings -> new TintedParticleLeavesBlock(0.01F, settings) : leaves);
        blockItemRegister.register(this.leaves);

        this.sapling = blockRegister.register(this.name + "_sapling", Blocks.OAK_SAPLING,
                                              sapling == null ? settings -> new SaplingBlock(this.saplingGenerator, settings) : sapling);
        blockItemRegister.register(this.sapling);

        this.stairs = blockRegister.register(this.name + "_stairs", Blocks.OAK_STAIRS,
                                             stairs == null ? settings -> new StairsBlock(this.planks.getDefaultState(), settings) : stairs);
        blockItemRegister.register(this.stairs);

        this.slab = blockRegister.register(this.name + "_slab", Blocks.OAK_SLAB, slab == null ? SlabBlock::new : slab);
        blockItemRegister.register(this.slab);

        this.fence = blockRegister.register(this.name + "_fence", Blocks.OAK_FENCE, fence == null ? FenceBlock::new : fence);
        blockItemRegister.register(this.fence);

        this.fenceGate = blockRegister.register(this.name + "_fence_gate", Blocks.OAK_FENCE_GATE,
                                                fenceGate == null ? settings -> new FenceGateBlock(this.woodType, settings) : fenceGate);
        blockItemRegister.register(this.fenceGate);

        this.door = blockRegister.register(this.name + "_door", Blocks.OAK_DOOR,
                                           door == null ? settings -> new DoorBlock(this.blockSetType, settings) : door);
        blockItemRegister.register(this.door);

        this.trapdoor = blockRegister.register(this.name + "_trapdoor", Blocks.OAK_TRAPDOOR,
                                               trapdoor == null ? settings -> new TrapdoorBlock(this.blockSetType, settings) : trapdoor);
        blockItemRegister.register(this.trapdoor);

        this.pressurePlate = blockRegister.register(this.name + "_pressure_plate", Blocks.OAK_PRESSURE_PLATE,
                                                    pressurePlate == null ? settings -> new PressurePlateBlock(this.blockSetType, settings) : pressurePlate);
        blockItemRegister.register(this.pressurePlate);

        this.button = blockRegister.register(this.name + "_button", Blocks.OAK_BUTTON,
                                             button == null ? settings -> new ButtonBlock(this.blockSetType, 30, settings) : button);
        blockItemRegister.register(this.button);

        this.sign = blockRegister.register(this.name + "_sign", Blocks.OAK_SIGN,
                                           sign == null ? settings -> new SignBlock(this.woodType, settings) : sign);
        blockItemRegister.register(this.sign);

        this.wallSign = blockRegister.register(this.name + "_wall_sign", Blocks.OAK_WALL_SIGN,
                                               wallSign == null ? settings -> new WallSignBlock(this.woodType, settings) : wallSign);
        blockItemRegister.register(this.wallSign);

        this.hangingSign = blockRegister.register(this.name + "_hanging_sign", Blocks.OAK_HANGING_SIGN,
                                                  hangingSign == null ? settings -> new HangingSignBlock(this.woodType, settings) : hangingSign);
        blockItemRegister.register(this.hangingSign);

        this.wallHangingSign = blockRegister.register(this.name + "_wall_hanging_sign", Blocks.OAK_WALL_HANGING_SIGN,
                                                      wallHangingSign == null ? settings -> new WallHangingSignBlock(this.woodType, settings) : wallHangingSign);
        blockItemRegister.register(this.wallHangingSign);

        this.signItem = itemRegister.register(this.name + "_sign",
                                              signItem == null
                                              ? settings -> new SignItem(this.sign, this.wallSign, settings.maxCount(16))
                                              : signItem);

        this.hangingSignItem = itemRegister.register(this.name + "_hanging_sign",
                                                     hangingSignItem == null
                                                     ? settings -> new HangingSignItem(this.hangingSign, this.wallHangingSign, settings.maxCount(16))
                                                     : hangingSignItem);

        this.boatEntityType = entityRegister.register(this.name + "_boat",
                                                      boatEntityType == null
                                                      ? EntityType.Builder.<BoatEntity>create(
                                                                          (type, world) ->
                                                                                  new BoatEntity(type, world, this.planks::asItem), SpawnGroup.MISC)
                                                                          .dropsNothing()
                                                                          .dimensions(1.375F, 0.5625F)
                                                                          .eyeHeight(0.5625F)
                                                                          .maxTrackingRange(10)
                                                      : boatEntityType.apply(this.planks::asItem));

        this.chestBoatEntityType = entityRegister.register(this.name + "_boat",
                                                           chestBoatEntityType == null
                                                           ? EntityType.Builder.<ChestBoatEntity>create(
                                                                               (type, world) ->
                                                                                       new ChestBoatEntity(type, world, this.planks::asItem), SpawnGroup.MISC)
                                                                               .dropsNothing()
                                                                               .dimensions(1.375F, 0.5625F)
                                                                               .eyeHeight(0.5625F)
                                                                               .maxTrackingRange(10)
                                                           : chestBoatEntityType.apply(this.planks::asItem));

        this.boatItem = itemRegister.register(this.name + "_boat",
                                              boatItem == null
                                              ? settings -> new BoatItem(this.boatEntityType, settings.maxCount(1))
                                              : boatItem);

        this.chestBoatItem = itemRegister.register(this.name + "_boat",
                                                   chestBoatItem == null
                                                   ? settings -> new BoatItem(this.chestBoatEntityType, settings.maxCount(1))
                                                   : chestBoatItem);

        StrippableBlockRegistry.register(this.log, this.strippedLog);
        StrippableBlockRegistry.register(this.wood, this.strippedWood);

        this.logsItemTag = BaseHelper.ItemTagOf(modid, this.name + "_logs");
        this.logsBlockTag = BaseHelper.BlockTagOf(modid, this.name + "_logs");

        FlammableBlockRegistry flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableBlockRegistry.add(this.planks, 5, 20);
        flammableBlockRegistry.add(this.log, 5, 5);
        flammableBlockRegistry.add(this.strippedLog, 5, 5);
        flammableBlockRegistry.add(this.strippedWood, 5, 5);
        flammableBlockRegistry.add(this.wood, 5, 5);
        flammableBlockRegistry.add(this.leaves, 30, 60);
        flammableBlockRegistry.add(this.sapling, 60, 20);
        flammableBlockRegistry.add(this.stairs, 5, 20);
        flammableBlockRegistry.add(this.slab, 5, 20);
        flammableBlockRegistry.add(this.fence, 5, 20);
        flammableBlockRegistry.add(this.fenceGate, 5, 20);
        flammableBlockRegistry.add(this.door, 5, 20);
        flammableBlockRegistry.add(this.trapdoor, 5, 20);
        flammableBlockRegistry.add(this.pressurePlate, 5, 20);
        flammableBlockRegistry.add(this.button, 5, 20);
        flammableBlockRegistry.add(this.sign, 5, 20);
        flammableBlockRegistry.add(this.wallSign, 5, 20);
        flammableBlockRegistry.add(this.hangingSign, 5, 20);
        flammableBlockRegistry.add(this.wallHangingSign, 5, 20);

        DispenserBlock.registerBehavior(this.boatItem, new BoatDispenserBehavior(this.boatEntityType));
        DispenserBlock.registerBehavior(this.chestBoatItem, new BoatDispenserBehavior(this.chestBoatEntityType));

        addBlocksToBlockEntityType(BlockEntityType.SIGN, this.sign, this.wallSign);
        addBlocksToBlockEntityType(BlockEntityType.HANGING_SIGN, this.hangingSign, this.wallHangingSign);
    }

    public BlockFamily createBlockFamily()
    {
        return new BlockFamily.Builder(this.planks)
                .button(this.button)
                .fence(this.fence)
                .fenceGate(this.fenceGate)
                .pressurePlate(this.pressurePlate)
                .sign(this.sign, this.wallSign)
                .slab(this.slab)
                .stairs(this.stairs)
                .door(this.door)
                .trapdoor(this.trapdoor)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();
    }

    public String getName()
    {
        return this.name;
    }

    public SaplingGenerator getSaplingGenerator()
    {
        return this.saplingGenerator;
    }

    @Override
    public String toString()
    {
        return "JiWoodSet[name=" + name + ", saplingGenerator=" + this.saplingGenerator + "]";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        var that = (JiWoodSet) obj;
        return name.equals(that.name) && saplingGenerator.equals(that.saplingGenerator);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, saplingGenerator);
    }

    static void addBlocksToBlockEntityType(BlockEntityType<?> blockEntityType, Block... blocks)
    {
        addBlocksToBlockEntityType(blockEntityType, Arrays.asList(blocks));
    }

    static void addBlocksToBlockEntityType(BlockEntityType<?> blockEntityType, Collection<Block> blocks)
    {
        BlockEntityTypeAccessor accessor = (BlockEntityTypeAccessor) blockEntityType;
        Set<Block> originalBlocks = accessor.getBlocks();
        accessor.setBlocks(ImmutableSet.<Block>builderWithExpectedSize(originalBlocks.size() + blocks.size())
                                       .addAll(originalBlocks)
                                       .addAll(blocks)
                                       .build());
    }

    public static class Builder
    {
        private final String modid;
        private final String name;
        private final SaplingGenerator saplingGenerator;

        private Supplier<WoodType> woodType = () -> WoodType.OAK;

        private Function<AbstractBlock.Settings, Block> planks;
        private Function<AbstractBlock.Settings, PillarBlock> log, strippedLog;
        private Function<AbstractBlock.Settings, PillarBlock> wood, strippedWood;
        private Function<AbstractBlock.Settings, Block> leaves;
        private Function<AbstractBlock.Settings, SaplingBlock> sapling;

        private Function<AbstractBlock.Settings, StairsBlock> stairs;
        private Function<AbstractBlock.Settings, SlabBlock> slab;
        private Function<AbstractBlock.Settings, FenceBlock> fence;
        private Function<AbstractBlock.Settings, FenceGateBlock> fenceGate;
        private Function<AbstractBlock.Settings, DoorBlock> door;
        private Function<AbstractBlock.Settings, TrapdoorBlock> trapdoor;
        private Function<AbstractBlock.Settings, PressurePlateBlock> pressurePlate;
        private Function<AbstractBlock.Settings, ButtonBlock> button;

        private Function<AbstractBlock.Settings, SignBlock> sign;
        private Function<AbstractBlock.Settings, WallSignBlock> wallSign;
        private Function<AbstractBlock.Settings, HangingSignBlock> hangingSign;
        private Function<AbstractBlock.Settings, WallHangingSignBlock> wallHangingSign;
        private Function<Item.Settings, SignItem> signItem;
        private Function<Item.Settings, HangingSignItem> hangingSignItem;

        private Function<Supplier<Item>, EntityType.Builder<BoatEntity>> boatType;
        private Function<Supplier<Item>, EntityType.Builder<ChestBoatEntity>> chestBoatType;
        private Function<Item.Settings, Item> boatItem;
        private Function<Item.Settings, Item> chestBoatItem;

        public Builder(String modid, String name, SaplingGenerator saplingGenerator)
        {
            this.modid = modid;
            this.name = name;
            this.saplingGenerator = saplingGenerator;
        }

        //region Main SET

        /*JiWoodSet woodset = new JiWoodSet.Builder("test", "rubber", SaplingGeneratorList.RUBBER)
            .set(RubberWoodBlock::new, RubberLeavesBlock::new).build();*/

        public Builder set(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> woodFactory,
                           Function<AbstractBlock.Settings, Block> leaves)
        {
            this.log = settings -> woodFactory.apply(settings, false);
            this.strippedLog = settings -> woodFactory.apply(settings, true);
            this.wood = settings -> woodFactory.apply(settings, false);
            this.strippedWood = settings -> woodFactory.apply(settings, true);
            this.leaves = leaves;
            return this;
        }

        public Builder set(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> woodFactory,
                           Function<AbstractBlock.Settings, Block> leaves,
                           Supplier<WoodType> woodType)
        {
            this.woodType = woodType;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> woodFactory,
                           Function<AbstractBlock.Settings, Block> leaves,
                           Function<AbstractBlock.Settings, Block> planks)
        {
            this.planks = planks;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> woodFactory,
                           Function<AbstractBlock.Settings, Block> leaves,
                           Function<AbstractBlock.Settings, Block> planks,
                           Supplier<WoodType> woodType)
        {
            this.woodType = woodType;
            this.planks = planks;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> woodFactory,
                           Function<AbstractBlock.Settings, Block> leaves,
                           Function<AbstractBlock.Settings, Block> planks,
                           Function<AbstractBlock.Settings, SaplingBlock> sapling,
                           Supplier<WoodType> woodType)
        {
            this.sapling = sapling;
            this.woodType = woodType;
            this.planks = planks;
            return set(woodFactory, leaves);
        }

        //endregion

        //region special Setters

        public Builder woodType(Supplier<WoodType> woodType)
        {
            this.woodType = woodType;

            return this;
        }

        public Builder planks(Function<AbstractBlock.Settings, Block> planks)
        {
            this.planks = planks;
            return this;
        }

        public Builder log(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> factory)
        {
            this.log = settings -> factory.apply(settings, false);
            this.strippedLog = settings -> factory.apply(settings, true);
            return this;
        }

        public Builder log(Function<AbstractBlock.Settings, PillarBlock> log, Function<AbstractBlock.Settings, PillarBlock> strippedLog)
        {
            this.log = log;
            this.strippedLog = strippedLog;
            return this;
        }

        public Builder log(Function<AbstractBlock.Settings, PillarBlock> log)
        {
            this.log = log;
            return this;
        }

        public Builder strippedLog(Function<AbstractBlock.Settings, PillarBlock> strippedLog)
        {
            this.strippedLog = strippedLog;
            return this;
        }

        public Builder wood(BiFunction<AbstractBlock.Settings, Boolean, PillarBlock> factory)
        {
            this.wood = settings -> factory.apply(settings, false);
            this.strippedWood = settings -> factory.apply(settings, true);
            return this;
        }

        public Builder wood(Function<AbstractBlock.Settings, PillarBlock> wood, Function<AbstractBlock.Settings, PillarBlock> strippedWood)
        {
            this.wood = wood;
            this.strippedWood = strippedWood;
            return this;
        }

        public Builder strippedWood(Function<AbstractBlock.Settings, PillarBlock> strippedWood)
        {
            this.strippedWood = strippedWood;
            return this;
        }

        public Builder wood(Function<AbstractBlock.Settings, PillarBlock> wood)
        {
            this.wood = wood;
            return this;
        }

        public Builder leaves(Function<AbstractBlock.Settings, Block> leaves)
        {
            this.leaves = leaves;
            return this;
        }

        public Builder sapling(Function<AbstractBlock.Settings, SaplingBlock> sapling)
        {
            this.sapling = sapling;
            return this;
        }

        public Builder stairs(Function<AbstractBlock.Settings, StairsBlock> stairs)
        {
            this.stairs = stairs;
            return this;
        }

        public Builder slab(Function<AbstractBlock.Settings, SlabBlock> slab)
        {
            this.slab = slab;
            return this;
        }

        public Builder fence(Function<AbstractBlock.Settings, FenceBlock> fence)
        {
            this.fence = fence;
            return this;
        }

        public Builder fenceGate(Function<AbstractBlock.Settings, FenceGateBlock> fenceGate)
        {
            this.fenceGate = fenceGate;
            return this;
        }

        public Builder door(Function<AbstractBlock.Settings, DoorBlock> door)
        {
            this.door = door;
            return this;
        }

        public Builder trapdoor(Function<AbstractBlock.Settings, TrapdoorBlock> trapdoor)
        {
            this.trapdoor = trapdoor;
            return this;
        }

        public Builder pressurePlate(Function<AbstractBlock.Settings, PressurePlateBlock> pressurePlate)
        {
            this.pressurePlate = pressurePlate;
            return this;
        }

        public Builder button(Function<AbstractBlock.Settings, ButtonBlock> button)
        {
            this.button = button;
            return this;
        }

        public Builder sign(Function<AbstractBlock.Settings, SignBlock> sign,
                            Function<AbstractBlock.Settings, WallSignBlock> wallSign,
                            Function<AbstractBlock.Settings, HangingSignBlock> hangingSign,
                            Function<AbstractBlock.Settings, WallHangingSignBlock> wallHangingSign)
        {
            this.sign = sign;
            this.wallSign = wallSign;
            this.hangingSign = hangingSign;
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder sign(Function<AbstractBlock.Settings, SignBlock> sign,
                            Function<AbstractBlock.Settings, WallSignBlock> wallSign)
        {
            this.sign = sign;
            this.wallSign = wallSign;
            return this;
        }

        public Builder sign(Function<AbstractBlock.Settings, SignBlock> sign)
        {
            this.sign = sign;
            return this;
        }

        public Builder wallSign(Function<AbstractBlock.Settings, WallSignBlock> wallSign)
        {
            this.wallSign = wallSign;
            return this;
        }

        public Builder hangingSign(Function<AbstractBlock.Settings, HangingSignBlock> hangingSign)
        {
            this.hangingSign = hangingSign;
            return this;
        }

        public Builder wallHangingSign(Function<AbstractBlock.Settings, WallHangingSignBlock> wallHangingSign)
        {
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder hangingSign(Function<AbstractBlock.Settings, HangingSignBlock> hangingSign,
                                   Function<AbstractBlock.Settings, WallHangingSignBlock> wallHangingSign)
        {
            this.hangingSign = hangingSign;
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder signItem(Function<Item.Settings, SignItem> signItem)
        {
            this.signItem = signItem;
            return this;
        }

        public Builder hangingSignItem(Function<Item.Settings, HangingSignItem> hangingSignItem)
        {
            this.hangingSignItem = hangingSignItem;
            return this;
        }

        public Builder boatType(Function<Supplier<Item>, EntityType.Builder<BoatEntity>> boatType,
                                Function<Supplier<Item>, EntityType.Builder<ChestBoatEntity>> chestBoatType)
        {
            this.boatType = boatType;
            this.chestBoatType = chestBoatType;
            return this;
        }

        public Builder boatType(Function<Supplier<Item>, EntityType.Builder<BoatEntity>> boatType)
        {
            this.boatType = boatType;
            return this;
        }

        public Builder chestBoatType(Function<Supplier<Item>, EntityType.Builder<ChestBoatEntity>> chestBoatType)
        {
            this.chestBoatType = chestBoatType;
            return this;
        }

        public Builder boatItem(Function<Item.Settings, Item> boatItem,
                                Function<Item.Settings, Item> chestBoatItem)
        {
            this.boatItem = boatItem;
            this.chestBoatItem = chestBoatItem;
            return this;
        }

        public Builder boatItem(Function<Item.Settings, Item> boatItem)
        {
            this.boatItem = boatItem;
            return this;
        }

        public Builder chestBoatItem(Function<Item.Settings, Item> chestBoatItem)
        {
            this.chestBoatItem = chestBoatItem;
            return this;
        }

        //endregion

        public JiWoodSet build()
        {
            var set = new JiWoodSet(modid, name, saplingGenerator, woodType,
                                    planks, log, strippedLog, strippedWood, wood, leaves, sapling,
                                    stairs, slab, fence, fenceGate, door, trapdoor,
                                    pressurePlate, button, sign, wallSign, hangingSign, wallHangingSign, signItem, hangingSignItem,
                                    boatType, chestBoatType, boatItem, chestBoatItem);
            WOOD_SETS.add(set);
            return set;
        }
    }
}