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

package dev.thementor.api.inventory.be;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;

import dev.thementor.api.base.block.AbstractBaseBlock;
import dev.thementor.api.base.blockentity.AbstractScreenBE;
import dev.thementor.api.inventory.base.InventoryConnector;
import dev.thementor.api.inventory.interfaces.IContentDrop;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.BlockPosPayload;
import dev.thementor.api.shared.utils.DirectionHelper;
/**
 * Abstract base class for block entities that support inventory storage.
 *
 * <p>This class extends {@link AbstractScreenBE} and implements the {@link IContentDrop}, {@link IStorageProvider},
 * and {@link IStorageConnector} interfaces, providing a framework for managing inventory contents and storage connections.</p>
 *
 * @param <T> The type of this block entity.
 * @param <B> The type of the inventory used by this block entity.
 */
@SuppressWarnings("unused")
@Developer("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public abstract class AbstractBaseInventoryBE<T extends AbstractBaseInventoryBE<T, B>, B extends SimpleContainer> extends AbstractScreenBE<@NotNull T, BlockPosPayload>
    implements IContentDrop<B>, IStorageProvider<InventoryStorage>, IStorageConnector<InventoryConnector<?>>
{
    protected final InventoryConnector<B> inventoryConnector;

    /**
     * Constructs a new instance of JInventoryBE.
     *
     * @param type The block entity type.
     * @param pos  The position of the block entity.
     * @param state The block state of the block entity.
     */
    public AbstractBaseInventoryBE(BlockEntityType<@NotNull T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        inventoryConnector = new InventoryConnector<>();
        this.properties.update()
                        .sync()
                        .waitEndTick();
    }

    /**
     * Retrieves the screen opening data for a server player entity.
     *
     * @param serverPlayerEntity The server player entity.
     * @return A {@link BlockPosPayload} containing the block position of this block entity.
     */
    @Override
    public @NotNull BlockPosPayload getScreenOpeningData(@NotNull ServerPlayer serverPlayerEntity)
    {
        return new BlockPosPayload(this.worldPosition);
    }

    /**
     * Retrieves the inventory connector associated with this block entity.
     *
     * @return The {@link InventoryConnector} instance for this block entity.
     */
    @Override
    public InventoryConnector<B> getInventoryConnector()
    {
        return inventoryConnector;
    }

    /**
     * Retrieves the storage provider for a given mapped direction and facing direction.
     *
     * @param mappedDirection The mapped direction.
     * @param facing          The facing direction.
     * @return The {@link InventoryStorage} instance or null if not available.
     */
    @Override
    public @Nullable InventoryStorage getStorageProvider(MappedDirection mappedDirection, Direction facing)
    {
        return getStorageProvider(MappedDirection.toDirection(mappedDirection), facing);
    }

    /**
     * Retrieves the storage provider for a given direction and facing direction.
     *
     * @param direction The direction.
     * @param facing    The facing direction.
     * @return The {@link InventoryStorage} instance or null if not available.
     */
    @Override
    public @Nullable InventoryStorage getStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        return this.inventoryConnector.getSidedMap().containsKey(MappedDirection.fromDirection(side))
                ? this.inventoryConnector.getStorage(side)
                : this.inventoryConnector.getSidedMap().containsKey(MappedDirection.NONE)
                    ? this.inventoryConnector.getStorage(MappedDirection.NONE)
                    : null;
    }

    /**
     * Retrieves the storage connector associated with this block entity.
     *
     * @return The {@link InventoryConnector} instance for this block entity.
     */
    @Override
    public InventoryConnector<?> getConnector()
    {
        return this.inventoryConnector;
    }

    /**
     * Reads data from a read view and populates this block entity's state and inventory.
     *
     * @param view The read view containing the data to be read.
     */
    @Override
    protected void loadAdditional(@NotNull ValueInput view)
    {
        super.loadAdditional(view);
        inventoryConnector.loadAdditional(view);
    }

    /**
     * Writes data from this block entity's state and inventory to a write view.
     *
     * @param view The write view where the data will be written.
     */
    @Override
    protected void saveAdditional(@NotNull ValueOutput view)
    {
        super.saveAdditional(view);
        inventoryConnector.saveAdditional(view);
    }

    /**
     * Handles block replacement logic, including dropping inventory contents if applicable.
     *
     * @param pos      The position of the block being replaced.
     * @param oldState The state of the old block.
     */
    @Override
    public void preRemoveSideEffects(@NotNull BlockPos pos, @NotNull BlockState oldState)
    {
        if(this.level != null)
        {
            Block newBlock = this.level.getBlockState(pos).getBlock();
            Block oldBlock = oldState.getBlock();
            if(oldBlock instanceof AbstractBaseBlock block)
            {
                if(block.hasInventory() && !oldState.is(newBlock))
                    this.dropContent(this.level, pos);
            }
        }
    }

    /**
     * Retrieves the inventory storage for a given direction.
     *
     * @param direction The direction for which to retrieve the inventory storage.
     * @return The {@link InventoryStorage} instance or null if not available.
     */
    public InventoryStorage getInventoryStorage(Direction direction)
    {
        if(level == null)
            return null;
        return this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.FACING)
                ? this.getStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.FACING))
                : this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.HORIZONTAL_FACING)
                    ? this.getStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.HORIZONTAL_FACING))
                    : this.inventoryConnector.getStorage(direction);
    }

    /**
     * Retrieves the inventory storage for a given mapped direction.
     *
     * @param direction The mapped direction for which to retrieve the inventory storage.
     */
    public InventoryStorage getInventoryStorage(MappedDirection direction)
    {
        return getInventoryStorage(MappedDirection.toDirection(direction));
    }
}