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

package dev.thementor.api.woodset;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

import dev.thementor.api.register.BlockItemRegisterer;
import dev.thementor.api.register.BlockRegisterer;
import dev.thementor.api.register.EntityRegisterer;
import dev.thementor.api.register.ItemRegisterer;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.utils.BaseHelper;

import dev.thementor.mixin.BlockEntityTypeAccessor;
import org.jetbrains.annotations.NotNull;

/**
 * Registers a new wood type with all the related family blocks.
 * Use provided builder for easy registration.
 * Use provided DatagenHelper class methods for data generation.
 * <pre>
 * {@code
 * //RubberWoodBlock extends BaseWoodBlock
 * //RubberLeavesBlock extends BaseLeavesBlock
 * WoodSet woodset = new WoodSet.Builder("test_mod_id", "rubber", ModSaplings.RUBBER)
 *             .set(RubberWoodBlock::new, RubberLeavesBlock::new).build();
 * }
 * </pre>
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-15")
@ModifiedAt("2025-04-19")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")
public class WoodSet
{
    private static final List<WoodSet> WOOD_SETS = new ArrayList<>();

    public static List<WoodSet> getWoodSets()
    {
        return WOOD_SETS;
    }

    private final String name;
    private final TreeGrower saplingGenerator;

    public final WoodType woodType;
    public final BlockSetType blockSetType;
    public final RotatedPillarBlock log, strippedLog;
    public final RotatedPillarBlock wood, strippedWood;
    public final Block planks;
    public final Block leaves;
    public final SaplingBlock sapling;

    public final StairBlock stairs;
    public final SlabBlock slab;
    public final FenceBlock fence;
    public final FenceGateBlock fenceGate;
    public final DoorBlock door;
    public final TrapDoorBlock trapdoor;
    public final PressurePlateBlock pressurePlate;
    public final ButtonBlock button;

    public final StandingSignBlock sign;
    public final WallSignBlock wallSign;
    public final CeilingHangingSignBlock hangingSign;
    public final WallHangingSignBlock wallHangingSign;
    public final SignItem signItem;
    public final HangingSignItem hangingSignItem;

    public final EntityType<@NotNull Boat> boatEntityType;
    public final EntityType<@NotNull ChestBoat> chestBoatEntityType;
    public final Item boatItem;
    public final Item chestBoatItem;

    public final TagKey<Item> logsItemTag;
    public final TagKey<Block> logsBlockTag;

    public WoodSet(String modid, String name,
                   TreeGrower saplingGenerator,
                   Supplier<WoodType> woodType,
                   Function<BlockBehaviour.Properties, Block> planks,
                   Function<BlockBehaviour.Properties, RotatedPillarBlock> log,
                   Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedLog,
                   Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedWood,
                   Function<BlockBehaviour.Properties, RotatedPillarBlock> wood,
                   Function<BlockBehaviour.Properties, Block> leaves,
                   Function<BlockBehaviour.Properties, SaplingBlock> sapling,
                   Function<BlockBehaviour.Properties, StairBlock> stairs,
                   Function<BlockBehaviour.Properties, SlabBlock> slab,
                   Function<BlockBehaviour.Properties, FenceBlock> fence,
                   Function<BlockBehaviour.Properties, FenceGateBlock> fenceGate,
                   Function<BlockBehaviour.Properties, DoorBlock> door,
                   Function<BlockBehaviour.Properties, TrapDoorBlock> trapdoor,
                   Function<BlockBehaviour.Properties, PressurePlateBlock> pressurePlate,
                   Function<BlockBehaviour.Properties, ButtonBlock> button,
                   Function<BlockBehaviour.Properties, StandingSignBlock> sign,
                   Function<BlockBehaviour.Properties, WallSignBlock> wallSign,
                   Function<BlockBehaviour.Properties, CeilingHangingSignBlock> hangingSign,
                   Function<BlockBehaviour.Properties, WallHangingSignBlock> wallHangingSign,
                   Function<Item.Properties, SignItem> signItem,
                   Function<Item.Properties, HangingSignItem> hangingSignItem,
                   Function<Supplier<Item>, EntityType.Builder<@NotNull Boat>> boatEntityType,
                   Function<Supplier<Item>, EntityType.Builder<@NotNull ChestBoat>> chestBoatEntityType,
                   Function<Item.Properties, Item> boatItem,
                   Function<Item.Properties, Item> chestBoatItem)
    {
        BlockRegisterer blockRegister = new BlockRegisterer(modid);
        ItemRegisterer itemRegister = new ItemRegisterer(modid);
        BlockItemRegisterer blockItemRegister = new BlockItemRegisterer(modid);
        EntityRegisterer entityRegister = new EntityRegisterer(modid);

        this.name = name;
        this.saplingGenerator = saplingGenerator;

        this.blockSetType = new BlockSetType(BaseHelper.id(modid, this.name).toString());

        this.woodType = WoodTypeBuilder.copyOf(woodType.get()).register(BaseHelper.id(modid, this.name), this.blockSetType);

        this.planks = blockRegister.register(this.name + "_planks", Blocks.OAK_PLANKS, planks == null ? Block::new : planks);
        blockItemRegister.register(this.planks);

        this.log = blockRegister.register(this.name + "_log", Blocks.OAK_LOG, log == null ? RotatedPillarBlock::new : log);
        blockItemRegister.register(this.log);

        this.strippedLog = blockRegister.register(this.name + "_stripped_log", Blocks.STRIPPED_OAK_LOG, strippedLog == null ? RotatedPillarBlock::new : strippedLog);
        blockItemRegister.register(this.strippedLog);

        this.strippedWood = blockRegister.register(this.name + "_stripped_wood", Blocks.STRIPPED_OAK_WOOD, strippedWood == null ? RotatedPillarBlock::new : strippedWood);
        blockItemRegister.register(this.strippedWood);

        this.wood = blockRegister.register(this.name + "_wood", Blocks.OAK_WOOD, wood == null ? RotatedPillarBlock::new : wood);
        blockItemRegister.register(this.wood);

        this.leaves = blockRegister.register(this.name + "_leaves", Blocks.OAK_LEAVES,
                                             leaves == null ? settings -> new TintedParticleLeavesBlock(0.01F, settings) : leaves);
        blockItemRegister.register(this.leaves);

        this.sapling = blockRegister.register(this.name + "_sapling", Blocks.OAK_SAPLING,
                                              sapling == null ? settings -> new SaplingBlock(this.saplingGenerator, settings) : sapling);
        blockItemRegister.register(this.sapling);

        this.stairs = blockRegister.register(this.name + "_stairs", Blocks.OAK_STAIRS,
                                             stairs == null ? settings -> new StairBlock(this.planks.defaultBlockState(), settings) : stairs);
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
                                               trapdoor == null ? settings -> new TrapDoorBlock(this.blockSetType, settings) : trapdoor);
        blockItemRegister.register(this.trapdoor);

        this.pressurePlate = blockRegister.register(this.name + "_pressure_plate", Blocks.OAK_PRESSURE_PLATE,
                                                    pressurePlate == null ? settings -> new PressurePlateBlock(this.blockSetType, settings) : pressurePlate);
        blockItemRegister.register(this.pressurePlate);

        this.button = blockRegister.register(this.name + "_button", Blocks.OAK_BUTTON,
                                             button == null ? settings -> new ButtonBlock(this.blockSetType, 30, settings) : button);
        blockItemRegister.register(this.button);

        this.sign = blockRegister.register(this.name + "_sign", Blocks.OAK_SIGN,
                                           sign == null ? settings -> new StandingSignBlock(this.woodType, settings) : sign);
        blockItemRegister.register(this.sign);

        this.wallSign = blockRegister.register(this.name + "_wall_sign", Blocks.OAK_WALL_SIGN,
                                               wallSign == null ? settings -> new WallSignBlock(this.woodType, settings) : wallSign);
        blockItemRegister.register(this.wallSign);

        this.hangingSign = blockRegister.register(this.name + "_hanging_sign", Blocks.OAK_HANGING_SIGN,
                                                  hangingSign == null ? settings -> new CeilingHangingSignBlock(this.woodType, settings) : hangingSign);
        blockItemRegister.register(this.hangingSign);

        this.wallHangingSign = blockRegister.register(this.name + "_wall_hanging_sign", Blocks.OAK_WALL_HANGING_SIGN,
                                                      wallHangingSign == null ? settings -> new WallHangingSignBlock(this.woodType, settings) : wallHangingSign);
        blockItemRegister.register(this.wallHangingSign);

        this.signItem = itemRegister.register(this.name + "_sign",
                                              signItem == null
                                              ? settings -> new SignItem(this.sign, this.wallSign, settings.stacksTo(16))
                                              : signItem);

        this.hangingSignItem = itemRegister.register(this.name + "_hanging_sign",
                                                     hangingSignItem == null
                                                     ? settings -> new HangingSignItem(this.hangingSign, this.wallHangingSign, settings.stacksTo(16))
                                                     : hangingSignItem);

        this.boatEntityType = entityRegister.register(this.name + "_boat",
                                                      boatEntityType == null
                                                      ? EntityType.Builder.<Boat>of(
                                                                          (type, world) ->
                                                                                  new Boat(type, world, this.planks::asItem), MobCategory.MISC)
                                                                          .noLootTable()
                                                                          .sized(1.375F, 0.5625F)
                                                                          .eyeHeight(0.5625F)
                                                                          .clientTrackingRange(10)
                                                      : boatEntityType.apply(this.planks::asItem));

        this.chestBoatEntityType = entityRegister.register(this.name + "_boat",
                                                           chestBoatEntityType == null
                                                           ? EntityType.Builder.<ChestBoat>of(
                                                                               (type, world) ->
                                                                                       new ChestBoat(type, world, this.planks::asItem), MobCategory.MISC)
                                                                               .noLootTable()
                                                                               .sized(1.375F, 0.5625F)
                                                                               .eyeHeight(0.5625F)
                                                                               .clientTrackingRange(10)
                                                           : chestBoatEntityType.apply(this.planks::asItem));

        this.boatItem = itemRegister.register(this.name + "_boat",
                                              boatItem == null
                                              ? settings -> new BoatItem(this.boatEntityType, settings.stacksTo(1))
                                              : boatItem);

        this.chestBoatItem = itemRegister.register(this.name + "_boat",
                                                   chestBoatItem == null
                                                   ? settings -> new BoatItem(this.chestBoatEntityType, settings.stacksTo(1))
                                                   : chestBoatItem);

        StrippableBlockRegistry.register(this.log, this.strippedLog);
        StrippableBlockRegistry.register(this.wood, this.strippedWood);

        this.logsItemTag = BaseHelper.tagKey(modid, this.name + "_logs", Registries.ITEM);
        this.logsBlockTag = BaseHelper.tagKey(modid, this.name + "_logs", Registries.BLOCK);

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

        DispenserBlock.registerBehavior(this.boatItem, new BoatDispenseItemBehavior(this.boatEntityType));
        DispenserBlock.registerBehavior(this.chestBoatItem, new BoatDispenseItemBehavior(this.chestBoatEntityType));

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
                .recipeGroupPrefix("wooden")
                .recipeUnlockedBy("has_planks")
                .getFamily();
    }

    public String getName()
    {
        return this.name;
    }

    public TreeGrower getSaplingGenerator()
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
        var that = (WoodSet) obj;
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
        private final TreeGrower saplingGenerator;

        private Supplier<WoodType> woodType = () -> WoodType.OAK;

        private Function<BlockBehaviour.Properties, Block> planks;
        private Function<BlockBehaviour.Properties, RotatedPillarBlock> log, strippedLog;
        private Function<BlockBehaviour.Properties, RotatedPillarBlock> wood, strippedWood;
        private Function<BlockBehaviour.Properties, Block> leaves;
        private Function<BlockBehaviour.Properties, SaplingBlock> sapling;

        private Function<BlockBehaviour.Properties, StairBlock> stairs;
        private Function<BlockBehaviour.Properties, SlabBlock> slab;
        private Function<BlockBehaviour.Properties, FenceBlock> fence;
        private Function<BlockBehaviour.Properties, FenceGateBlock> fenceGate;
        private Function<BlockBehaviour.Properties, DoorBlock> door;
        private Function<BlockBehaviour.Properties, TrapDoorBlock> trapdoor;
        private Function<BlockBehaviour.Properties, PressurePlateBlock> pressurePlate;
        private Function<BlockBehaviour.Properties, ButtonBlock> button;

        private Function<BlockBehaviour.Properties, StandingSignBlock> sign;
        private Function<BlockBehaviour.Properties, WallSignBlock> wallSign;
        private Function<BlockBehaviour.Properties, CeilingHangingSignBlock> hangingSign;
        private Function<BlockBehaviour.Properties, WallHangingSignBlock> wallHangingSign;
        private Function<Item.Properties, SignItem> signItem;
        private Function<Item.Properties, HangingSignItem> hangingSignItem;

        private Function<Supplier<Item>, EntityType.Builder<@NotNull Boat>> boatType;
        private Function<Supplier<Item>, EntityType.Builder<@NotNull ChestBoat>> chestBoatType;
        private Function<Item.Properties, Item> boatItem;
        private Function<Item.Properties, Item> chestBoatItem;

        public Builder(String modid, String name, TreeGrower saplingGenerator)
        {
            this.modid = modid;
            this.name = name;
            this.saplingGenerator = saplingGenerator;
        }

        //region Main SET

        public Builder set(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> woodFactory,
                           Function<BlockBehaviour.Properties, Block> leaves)
        {
            this.log = settings -> woodFactory.apply(settings, false);
            this.strippedLog = settings -> woodFactory.apply(settings, true);
            this.wood = settings -> woodFactory.apply(settings, false);
            this.strippedWood = settings -> woodFactory.apply(settings, true);
            this.leaves = leaves;
            return this;
        }

        public Builder set(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> woodFactory,
                           Function<BlockBehaviour.Properties, Block> leaves,
                           Supplier<WoodType> woodType)
        {
            this.woodType = woodType;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> woodFactory,
                           Function<BlockBehaviour.Properties, Block> leaves,
                           Function<BlockBehaviour.Properties, Block> planks)
        {
            this.planks = planks;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> woodFactory,
                           Function<BlockBehaviour.Properties, Block> leaves,
                           Function<BlockBehaviour.Properties, Block> planks,
                           Supplier<WoodType> woodType)
        {
            this.woodType = woodType;
            this.planks = planks;
            return set(woodFactory, leaves);
        }

        public Builder set(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> woodFactory,
                           Function<BlockBehaviour.Properties, Block> leaves,
                           Function<BlockBehaviour.Properties, Block> planks,
                           Function<BlockBehaviour.Properties, SaplingBlock> sapling,
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

        public Builder planks(Function<BlockBehaviour.Properties, Block> planks)
        {
            this.planks = planks;
            return this;
        }

        public Builder log(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> factory)
        {
            this.log = settings -> factory.apply(settings, false);
            this.strippedLog = settings -> factory.apply(settings, true);
            return this;
        }

        public Builder log(Function<BlockBehaviour.Properties, RotatedPillarBlock> log, Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedLog)
        {
            this.log = log;
            this.strippedLog = strippedLog;
            return this;
        }

        public Builder log(Function<BlockBehaviour.Properties, RotatedPillarBlock> log)
        {
            this.log = log;
            return this;
        }

        public Builder strippedLog(Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedLog)
        {
            this.strippedLog = strippedLog;
            return this;
        }

        public Builder wood(BiFunction<BlockBehaviour.Properties, Boolean, RotatedPillarBlock> factory)
        {
            this.wood = settings -> factory.apply(settings, false);
            this.strippedWood = settings -> factory.apply(settings, true);
            return this;
        }

        public Builder wood(Function<BlockBehaviour.Properties, RotatedPillarBlock> wood, Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedWood)
        {
            this.wood = wood;
            this.strippedWood = strippedWood;
            return this;
        }

        public Builder strippedWood(Function<BlockBehaviour.Properties, RotatedPillarBlock> strippedWood)
        {
            this.strippedWood = strippedWood;
            return this;
        }

        public Builder wood(Function<BlockBehaviour.Properties, RotatedPillarBlock> wood)
        {
            this.wood = wood;
            return this;
        }

        public Builder leaves(Function<BlockBehaviour.Properties, Block> leaves)
        {
            this.leaves = leaves;
            return this;
        }

        public Builder sapling(Function<BlockBehaviour.Properties, SaplingBlock> sapling)
        {
            this.sapling = sapling;
            return this;
        }

        public Builder stairs(Function<BlockBehaviour.Properties, StairBlock> stairs)
        {
            this.stairs = stairs;
            return this;
        }

        public Builder slab(Function<BlockBehaviour.Properties, SlabBlock> slab)
        {
            this.slab = slab;
            return this;
        }

        public Builder fence(Function<BlockBehaviour.Properties, FenceBlock> fence)
        {
            this.fence = fence;
            return this;
        }

        public Builder fenceGate(Function<BlockBehaviour.Properties, FenceGateBlock> fenceGate)
        {
            this.fenceGate = fenceGate;
            return this;
        }

        public Builder door(Function<BlockBehaviour.Properties, DoorBlock> door)
        {
            this.door = door;
            return this;
        }

        public Builder trapdoor(Function<BlockBehaviour.Properties, TrapDoorBlock> trapdoor)
        {
            this.trapdoor = trapdoor;
            return this;
        }

        public Builder pressurePlate(Function<BlockBehaviour.Properties, PressurePlateBlock> pressurePlate)
        {
            this.pressurePlate = pressurePlate;
            return this;
        }

        public Builder button(Function<BlockBehaviour.Properties, ButtonBlock> button)
        {
            this.button = button;
            return this;
        }

        public Builder sign(Function<BlockBehaviour.Properties, StandingSignBlock> sign,
                            Function<BlockBehaviour.Properties, WallSignBlock> wallSign,
                            Function<BlockBehaviour.Properties, CeilingHangingSignBlock> hangingSign,
                            Function<BlockBehaviour.Properties, WallHangingSignBlock> wallHangingSign)
        {
            this.sign = sign;
            this.wallSign = wallSign;
            this.hangingSign = hangingSign;
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder sign(Function<BlockBehaviour.Properties, StandingSignBlock> sign,
                            Function<BlockBehaviour.Properties, WallSignBlock> wallSign)
        {
            this.sign = sign;
            this.wallSign = wallSign;
            return this;
        }

        public Builder sign(Function<BlockBehaviour.Properties, StandingSignBlock> sign)
        {
            this.sign = sign;
            return this;
        }

        public Builder wallSign(Function<BlockBehaviour.Properties, WallSignBlock> wallSign)
        {
            this.wallSign = wallSign;
            return this;
        }

        public Builder hangingSign(Function<BlockBehaviour.Properties, CeilingHangingSignBlock> hangingSign)
        {
            this.hangingSign = hangingSign;
            return this;
        }

        public Builder wallHangingSign(Function<BlockBehaviour.Properties, WallHangingSignBlock> wallHangingSign)
        {
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder hangingSign(Function<BlockBehaviour.Properties, CeilingHangingSignBlock> hangingSign,
                                   Function<BlockBehaviour.Properties, WallHangingSignBlock> wallHangingSign)
        {
            this.hangingSign = hangingSign;
            this.wallHangingSign = wallHangingSign;
            return this;
        }

        public Builder signItem(Function<Item.Properties, SignItem> signItem)
        {
            this.signItem = signItem;
            return this;
        }

        public Builder hangingSignItem(Function<Item.Properties, HangingSignItem> hangingSignItem)
        {
            this.hangingSignItem = hangingSignItem;
            return this;
        }

        public Builder boatType(Function<Supplier<Item>, EntityType.Builder<@NotNull Boat>> boatType,
                                Function<Supplier<Item>, EntityType.Builder<@NotNull ChestBoat>> chestBoatType)
        {
            this.boatType = boatType;
            this.chestBoatType = chestBoatType;
            return this;
        }

        public Builder boatType(Function<Supplier<Item>, EntityType.Builder<@NotNull Boat>> boatType)
        {
            this.boatType = boatType;
            return this;
        }

        public Builder chestBoatType(Function<Supplier<Item>, EntityType.Builder<@NotNull ChestBoat>> chestBoatType)
        {
            this.chestBoatType = chestBoatType;
            return this;
        }

        public Builder boatItem(Function<Item.Properties, Item> boatItem,
                                Function<Item.Properties, Item> chestBoatItem)
        {
            this.boatItem = boatItem;
            this.chestBoatItem = chestBoatItem;
            return this;
        }

        public Builder boatItem(Function<Item.Properties, Item> boatItem)
        {
            this.boatItem = boatItem;
            return this;
        }

        public Builder chestBoatItem(Function<Item.Properties, Item> chestBoatItem)
        {
            this.chestBoatItem = chestBoatItem;
            return this;
        }

        //endregion

        public WoodSet build()
        {
            var set = new WoodSet(modid, name, saplingGenerator, woodType,
                                  planks, log, strippedLog, strippedWood, wood, leaves, sapling,
                                  stairs, slab, fence, fenceGate, door, trapdoor,
                                  pressurePlate, button, sign, wallSign, hangingSign, wallHangingSign, signItem, hangingSignItem,
                                  boatType, chestBoatType, boatItem, chestBoatItem);
            WOOD_SETS.add(set);
            return set;
        }
    }
}