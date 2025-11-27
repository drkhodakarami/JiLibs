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

package dev.thementor.api.shared.properties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Function4;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.IBEFactory;
import dev.thementor.api.shared.interfaces.IBETickerFactory;
import dev.thementor.api.shared.interfaces.IShapeFactory;
import dev.thementor.api.shared.interfaces.ITick;

/**
 * Represents properties and settings for a Minecraft block, including its behavior, render type, and state properties.
 *
 * @param <T> the type of BlockEntity associated with this block
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class BlockProperties<T extends BlockEntity>
{
    /**
     * Indicates whether the block should be placed facing opposite when created.
     */
    private boolean placeFacingOpposite;

    /**
     * Indicates whether the block has a comparator output.
     */
    private boolean hasComparatorOutput;

    /**
     * The function to calculate the comparator output of the block.
     */
    private Function4<BlockState, Level, BlockPos, Direction, Integer> comparatorOutput;

    /**
     * The render type of the block.
     */
    private RenderShape renderType;

    /**
     * The factory for creating voxel shapes of the block.
     */
    private IShapeFactory shapeFactory;

    /**
     * Manages and tracks various state properties of the block.
     */
    private final StateProperties stateProperties;

    /**
     * Predicate to determine if the block can exist at a given position in the world.
     */
    private BiPredicate<LevelReader, BlockPos> canExistAt;

    /**
     * Cache for direction-specific voxel shapes.
     */
    private final Map<Direction, VoxelShape> cachedDirectionalShapes;

    /**
     * Supplier to get the BlockEntityType of the associated BlockEntity.
     */
    private final Supplier<BlockEntityType<T>> blockEntityTypeSupplier;

    /**
     * Indicates whether the block should be tickable.
     */
    private boolean shouldTick;

    /**
     * Factory for creating BlockEntities for this block.
     */
    private final IBEFactory<T> blockEntityFactory;

    /**
     * Factory for creating tickers for BlockEntities of this type.
     */
    private final IBETickerFactory<T> blockEntityTicker;

    /**
     * Indicates whether right-clicking on the block should open a GUI.
     */
    private boolean rightClickToOpenGui;

    /**
     * Indicates whether the block should drop its contents when broken.
     */
    private boolean dropContentsOnBreak;

    /**
     * Constructs a new instance of BlockProperties with no specific BlockEntityType supplier.
     */
    public BlockProperties()
    {
        this(null);
    }

    /**
     * Constructs a new instance of BlockProperties with the specified BlockEntityType supplier.
     *
     * @param blockEntityTypeSupplier the supplier for getting the BlockEntityType
     */
    public BlockProperties(Supplier<BlockEntityType<T>> blockEntityTypeSupplier)
    {
        placeFacingOpposite = true;
        hasComparatorOutput = false;
        comparatorOutput = (state, world, pos, direction) -> AbstractContainerMenu.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
        renderType = RenderShape.MODEL;
        shapeFactory = (state, world, pos, context) -> Shapes.block();
        stateProperties = new StateProperties();
        canExistAt = (world, pos) -> true;
        cachedDirectionalShapes = new HashMap<>();

        this.blockEntityTypeSupplier = blockEntityTypeSupplier;
        shouldTick = false;
        rightClickToOpenGui = false;
        dropContentsOnBreak = false;

        this.blockEntityFactory = (pos, state) -> this.getBEType().create(pos, state);
        this.blockEntityTicker = (world, state, type) -> ITick.createTicker(world);
    }

    //region GETTERS

    //region BLOCK

    /**
     * Retrieves whether the block has a comparator output.
     *
     * @return true if the block has a comparator output, false otherwise
     */
    public boolean hasComparatorOutput()
    {
        return this.hasComparatorOutput;
    }

    /**
     * Determines if the block has a horizontal facing property defined.
     *
     * @return true if the block has a horizontal facing property, false otherwise
     */
    public boolean hasHorizontalFacing()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.HORIZONTAL_FACING);
    }

    /**
     * Determines if the block has a general facing property defined.
     *
     * @return true if the block has a facing property, false otherwise
     */
    public boolean hasFacing()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.FACING);
    }

    /**
     * Determines if the block has an axis property defined.
     *
     * @return true if the block has an axis property, false otherwise
     */
    public boolean hasAxisProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.AXIS);
    }

    /**
     * Determines if the block has an enabled property defined.
     *
     * @return true if the block has an enabled property, false otherwise
     */
    public boolean hasEnabledProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.ENABLED);
    }

    /**
     * Determines if the block has a locked property defined.
     *
     * @return true if the block has a locked property, false otherwise
     */
    public boolean hasLockedProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.LOCKED);
    }

    /**
     * Determines if the block has a powered property defined.
     *
     * @return true if the block has a powered property, false otherwise
     */
    public boolean hasPoweredProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.POWERED);
    }

    /**
     * Determines if the block has an unstable property defined.
     *
     * @return true if the block has an unstable property, false otherwise
     */
    public boolean hasUnstableProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.UNSTABLE);
    }

    /**
     * Determines if the block has a lit property defined.
     *
     * @return true if the block has a lit property, false otherwise
     */
    public boolean hasLitProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.LIT);
    }

    /**
     * Determines if the block has a waterlogged property defined.
     *
     * @return true if the block has a waterlogged property, false otherwise
     */
    public boolean hasWaterloggedProperty()
    {
        return this.stateProperties.containsProperty(BlockStateProperties.WATERLOGGED);
    }

    /**
     * Retrieves whether the block is configured to place facing opposite when created.
     *
     * @return true if the block should be placed facing opposite, false otherwise
     */
    public boolean isFacingOpposite()
    {
        return this.placeFacingOpposite;
    }

    /**
     * Retrieves the function to calculate the comparator output of the block.
     *
     * @return the comparator output function
     */
    public Function4<BlockState, Level, BlockPos, Direction, Integer> getComparatorOutput()
    {
        return this.comparatorOutput;
    }

    /**
     * Retrieves the render type of the block.
     *
     * @return the render type
     */
    public RenderShape getRenderShape()
    {
        return this.renderType;
    }

    /**
     * Retrieves the factory for creating voxel shapes of the block.
     *
     * @return the shape factory
     */
    public IShapeFactory getShapeFactory()
    {
        return this.shapeFactory;
    }

    /**
     * Retrieves the manager and tracker for various state properties of the block.
     *
     * @return the state properties manager
     */
    public StateProperties getStateProperties()
    {
        return this.stateProperties;
    }

    /**
     * Retrieves the cache for direction-specific voxel shapes.
     *
     * @return the cache of directional shapes
     */
    public Map<Direction, VoxelShape> getCachedDirectionalShapes()
    {
        return this.cachedDirectionalShapes;
    }

    /**
     * Retrieves the predicate to determine if the block can exist at a given position in the world.
     *
     * @return the existence check predicate
     */
    public BiPredicate<LevelReader, BlockPos> canExistAt()
    {
        return this.canExistAt;
    }

    //endregion

    //region BLOCK ENTITY

    /**
     * Retrieves the supplier for getting the BlockEntityType of the associated BlockEntity.
     *
     * @return the BlockEntityType supplier
     */
    public Supplier<BlockEntityType<T>> getBETypeSupplier()
    {
        return this.blockEntityTypeSupplier;
    }

    /**
     * Retrieves the BlockEntityType of the associated BlockEntity.
     *
     * @return the BlockEntityType
     */
    public BlockEntityType<T> getBEType()
    {
        if(this.blockEntityTypeSupplier == null || this.blockEntityTypeSupplier.get() == null)
            return null;
        return this.blockEntityTypeSupplier.get();
    }

    /**
     * Retrieves whether the block is configured to be tickable.
     *
     * @return true if the block should be tickable, false otherwise
     */
    public boolean isTickable()
    {
        return this.shouldTick;
    }

    /**
     * Retrieves the factory for creating BlockEntities for this block.
     *
     * @return the block entity factory
     */
    public IBEFactory<T> getBEFactory()
    {
        return this.blockEntityFactory;
    }

    /**
     * Retrieves the factory for creating tickers for BlockEntities of this type.
     *
     * @return the ticker factory
     */
    public IBETickerFactory<T> getBETicker()
    {
        return this.blockEntityTicker;
    }

    /**
     * Retrieves whether right-clicking on the block should open a GUI.
     *
     * @return true if right-clicking opens a GUI, false otherwise
     */
    public boolean hasGUI()
    {
        return this.rightClickToOpenGui;
    }

    /**
     * Retrieves whether the block is configured to drop its contents when broken.
     *
     * @return true if the block should drop its contents on break, false otherwise
     */
    public boolean hasInventory()
    {
        return this.dropContentsOnBreak;
    }

    //endregion

    //endregion

    //region SETTERS

    //region BLOCK

    /**
     * Adds a horizontal facing property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addHorizontalFacing()
    {
        return setHorizontalFacing(true);
    }

    /**
     * Sets whether the block should have a horizontal facing property.
     *
     * @param flag true to add the horizontal facing property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setHorizontalFacing(boolean flag)
    {
        if(stateProperties.containsProperty(BlockStateProperties.FACING))
            throw new IllegalArgumentException("Cannot add horizontal facing property when facing property is already present");
        if (flag)
            this.stateProperties.addHorizontalFacing();

        return this;
    }

    /**
     * Sets the default direction for the block.
     *
     * @param defaultDirection the default direction
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultDirection(Direction defaultDirection)
    {
        this.stateProperties.setDefaultValue("facing", defaultDirection);
        return this;
    }

    /**
     * Adds a general facing property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addFacing()
    {
        return setFacing(true);
    }

    /**
     * Sets whether the block should have a general facing property.
     *
     * @param flag true to add the facing property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setFacing(boolean flag)
    {
        if(stateProperties.containsProperty(BlockStateProperties.HORIZONTAL_FACING))
            throw new IllegalArgumentException("Cannot add facing property when horizontal facing property is already present");
        if (flag)
            this.stateProperties.addFacing();

        return this;
    }

    /**
     * Adds an axis property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addAxisProperty()
    {
        return setAxisProperty(true);
    }

    /**
     * Sets whether the block should have an axis property.
     *
     * @param flag true to add the axis property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setAxisProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addAxis();

        return this;
    }

    /**
     * Sets the default axis for the block.
     *
     * @param defaultAxis the default axis
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultAxis(Direction.Axis defaultAxis)
    {
        this.stateProperties.setDefaultValue("axis", defaultAxis);
        return this;
    }

    /**
     * Adds an enabled property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addEnabledProperty()
    {
        return setEnabledProperty(true);
    }

    /**
     * Sets whether the block should have an enabled property.
     *
     * @param flag true to add the enabled property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setEnabledProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addEnabled();

        return this;
    }

    /**
     * Sets the default value for the enabled property.
     *
     * @param flag the default value for the enabled property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultEnabled(boolean flag)
    {
        this.stateProperties.setDefaultValue("enabled", flag);
        return this;
    }

    /**
     * Adds a locked property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addLockedProperty()
    {
        return setLockedProperty(true);
    }

    /**
     * Sets whether the block should have a locked property.
     *
     * @param flag true to add the locked property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setLockedProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addLocked();

        return this;
    }

    /**
     * Sets the default value for the locked property.
     *
     * @param flag the default value for the locked property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultLocked(boolean flag)
    {
        this.stateProperties.setDefaultValue("locked", flag);
        return this;
    }

    /**
     * Adds a powered property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addPoweredProperty()
    {
        return setPoweredProperty(true);
    }

    /**
     * Sets whether the block should have a powered property.
     *
     * @param flag true to add the powered property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setPoweredProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addPowered();

        return this;
    }

    /**
     * Sets the default value for the powered property.
     *
     * @param flag the default value for the powered property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultPowered(boolean flag)
    {
        this.stateProperties.setDefaultValue("powered", flag);
        return this;
    }

    /**
     * Adds an unstable property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addUnstableProperty()
    {
        return setUnstableProperty(true);
    }

    /**
     * Sets whether the block should have an unstable property.
     *
     * @param flag true to add the unstable property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setUnstableProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addUnstable();

        return this;
    }

    /**
     * Sets the default value for the unstable property.
     *
     * @param flag the default value for the unstable property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultUnstable(boolean flag)
    {
        this.stateProperties.setDefaultValue("unstable", flag);
        return this;
    }

    /**
     * Adds a lit property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addLitProperty()
    {
        return setLitProperty(true);
    }

    /**
     * Sets whether the block should have a lit property.
     *
     * @param flag true to add the lit property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setLitProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addLit();

        return this;
    }

    /**
     * Sets the default value for the lit property.
     *
     * @param flag the default value for the lit property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultLit(boolean flag)
    {
        this.stateProperties.setDefaultValue("lit", flag);
        return this;
    }

    /**
     * Adds a waterloggable property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addWaterloggableProperty()
    {
        return setWaterloggableProperty(true);
    }

    /**
     * Sets whether the block should have a waterloggable property.
     *
     * @param flag true to add the waterloggable property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setWaterloggableProperty(boolean flag)
    {
        if (flag)
            this.stateProperties.addWaterlogged();

        return this;
    }

    /**
     * Sets the default value for the waterloggable property.
     *
     * @param flag the default value for the waterloggable property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setDefaultWaterlogged(boolean flag)
    {
        this.stateProperties.setDefaultValue("waterlogged", flag);
        return this;
    }

    /**
     * Sets whether the block should face opposite when created.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> notFacingOpposite()
    {
        return setFacingOpposite(false);
    }

    /**
     * Sets whether the block should face opposite when created.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> facingOpposite()
    {
        return setFacingOpposite(true);
    }

    /**
     * Sets whether the block should face opposite when created.
     *
     * @param flag true to make the block face opposite, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setFacingOpposite(boolean flag)
    {
        this.placeFacingOpposite = flag;
        return this;
    }

    /**
     * Adds a comparator output property to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addComparatorOutput() {
        return setComparatorOutput(true);
    }

    /**
     * Sets whether the block should have a comparator output.
     *
     * @param flag true to add the comparator output property, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setComparatorOutput(boolean flag)
    {
        this.hasComparatorOutput = flag;
        return this;
    }

    /**
     * Sets a custom function to calculate the comparator output of the block.
     *
     * @param comparatorOutput the custom comparator output function
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setComparatorOutput(Function4<BlockState, Level, BlockPos, Direction, Integer> comparatorOutput)
    {
        this.comparatorOutput = comparatorOutput;
        return this;
    }

    /**
     * Adds an invisible renderer to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addInvisibleRenderer()
    {
        return setInvisibleRenderer(true);
    }

    /**
     * Sets whether the block should have an invisible renderer.
     *
     * @param flag true to make the block invisible, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setInvisibleRenderer(boolean flag)
    {
        return flag ? setRenderType(RenderShape.INVISIBLE) : setRenderType(RenderShape.MODEL);
    }

    /**
     * Sets the render type of the block.
     *
     * @param renderType the render type
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setRenderType(RenderShape renderType)
    {
        this.renderType = renderType;
        return this;
    }

    /**
     * Adds an empty voxel shape to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addEmptyShape() {
        return setConstantShape(Shapes.empty());
    }

    /**
     * Sets a constant voxel shape for the block.
     *
     * @param shape the constant voxel shape
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setConstantShape(VoxelShape shape)
    {
        return setShapeFactory((state, world, pos, context) -> shape);
    }

    /**
     * Sets a factory to create voxel shapes for the block.
     *
     * @param shapeFactory the factory for creating voxel shapes
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> setShapeFactory(IShapeFactory shapeFactory)
    {
        this.shapeFactory = shapeFactory;
        return this;
    }

    /**
     * Adds rotated voxel shapes to the block.
     *
     * @param shape the base voxel shape to rotate
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addRotatedShapes(VoxelShape shape)
    {
        if(!this.stateProperties.containsProperty(BlockStateProperties.HORIZONTAL_FACING))
            addHorizontalFacing();

        if(this.cachedDirectionalShapes.isEmpty())
            runShapeCalculation(this.cachedDirectionalShapes, shape);

        return setShapeFactory((state, world, pos, context) -> this.cachedDirectionalShapes.get(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    /**
     * Sets a predicate to determine if the block can exist at a given position in the world.
     *
     * @param canExistAt the predicate for checking block existence
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> canExistAt(BiPredicate<LevelReader, BlockPos> canExistAt)
    {
        this.canExistAt = canExistAt;
        return this;
    }

    //endregion

    //region BLOCK ENTITY

    /**
     * Marks the block as tickable.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> tick()
    {
        return tick(true);
    }

    /**
     * Sets whether the block should be tickable.
     *
     * @param flag true to make the block tickable, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> tick(boolean flag)
    {
        this.shouldTick = flag;
        return this;
    }

    /**
     * Adds a GUI to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addGui()
    {
        return addGui(true);
    }

    /**
     * Sets whether right-clicking on the block should open a GUI.
     *
     * @param flag true to add a GUI, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addGui(boolean flag)
    {
        this.rightClickToOpenGui = flag;
        return this;
    }

    /**
     * Adds an inventory to the block.
     *
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addInventory()
    {
        return addInventory(true);
    }

    /**
     * Sets whether the block should have an inventory that drops its contents when broken.
     *
     * @param flag true to add an inventory, false otherwise
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addInventory(boolean flag)
    {
        this.dropContentsOnBreak = flag;
        return this;
    }

    //endregion

    //endregion

    //region HELPER METHODS
    /**
     * Runs a calculation to populate the directional shape cache.
     *
     * @param shapeCache the map to store calculated shapes
     * @param shape      the base voxel shape to rotate
     */
    public static void runShapeCalculation(Map<Direction, VoxelShape> shapeCache, VoxelShape shape)
    {
        for (final Direction direction : Direction.values()) {
            shapeCache.put(direction, calculateShape(direction, shape));
        }
    }

    /**
     * Calculates the rotated voxel shape based on the given direction.
     *
     * @param to       the target direction
     * @param shape  the base voxel shape to rotate
     * @return the rotated voxel shape
     */
    public static VoxelShape calculateShape(Direction to, VoxelShape shape)
    {
        // Create an array of two VoxelShapes: one for the current shape and one for building the rotated shape.
        final VoxelShape[] buffer = {shape, Shapes.empty()};

        // Calculate the number of 90-degree rotations needed to align the given 'to' direction with NORTH.
        // This uses the horizontal quarter-turns difference between the 'to' direction and NORTH.
        final int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        // Loop to rotate the shape the required number of times (calculated above).
        for (int i = 0; i < times; i++) {
            // Iterate through each box (cuboid) in the current shape.
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) ->
                                         // For each box, calculate its rotated position and add it to the second shape (buffer[1]).
                                         buffer[1] = Shapes.or(buffer[1],
                                                                       Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            // After processing all boxes, update buffer[0] to be the newly rotated shape.
            buffer[0] = buffer[1];
            // Reset buffer[1] to an empty shape for the next rotation iteration.
            buffer[1] = Shapes.empty();
        }
        // Return the final rotated shape after the loop.
        return buffer[0];
    }

    /**
     * Adds a custom state property to the block with the specified type and default value.
     *
     * @param <U>          the type of the state property
     * @param property     the state property to add
     * @param defaultValue the default value for the state property
     * @return the current instance of BlockProperties for method chaining
     */
    public <U extends Comparable<U>> BlockProperties<T> addStateProperty(Property<U> property, U defaultValue)
    {
        this.stateProperties.addProperty(new StateProperty<>(property, defaultValue));
        return this;
    }

    /**
     * Adds a boolean state property to the block with the specified name and default value.
     *
     * @param name         the name of the state property
     * @param defaultValue the default value for the state property
     * @return the current instance of BlockProperties for method chaining
     */
    public BlockProperties<T> addBooleanStateProperty(String name, boolean defaultValue)
    {
        this.stateProperties.addProperty(new StateProperty<>(BooleanProperty.create(name), defaultValue));
        return this;
    }

    /**
     * Adds an enum state property to the block with the specified name, class, and default value.
     *
     * @param <U>          the type of the enum state property
     * @param name         the name of the state property
     * @param clazz        the class of the enum
     * @param defaultValue the default value for the state property
     * @return the current instance of BlockProperties for method chaining
     */
    public <U extends Enum<U> & StringRepresentable> BlockProperties<T> addEnumStateProperty(String name, Class<U> clazz, U defaultValue)
    {
        this.stateProperties.addProperty(new StateProperty<>(EnumProperty.create(name, clazz), defaultValue));
        return this;
    }

    /**
     * Adds an enum state property to the block with the specified name, class, default value, and list of values.
     *
     * @param <U>          the type of the enum state property
     * @param name         the name of the state property
     * @param clazz        the class of the enum
     * @param defaultValue the default value for the state property
     * @param values       the list of values for the enum property
     * @return the current instance of BlockProperties for method chaining
     */
    public <U extends Enum<U> & StringRepresentable> BlockProperties<T> addEnumStateProperty(String name, Class<U> clazz, U defaultValue, List<U> values)
    {
        this.stateProperties.addProperty(new StateProperty<>(EnumProperty.create(name, clazz, values), defaultValue));
        return this;
    }
    //endregion
}