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

package dev.thementor.api.inventory.be;

import dev.thementor.api.base.block.JiBlock;
import dev.thementor.api.base.blockentity.JiScreenBE;
import dev.thementor.api.inventory.base.InventoryConnector;
import dev.thementor.api.inventory.interfaces.IContentDrop;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IStorageConnector;
import dev.thementor.api.shared.interfaces.IStorageProvider;
import dev.thementor.api.shared.records.BlockPosPayload;
import dev.thementor.api.shared.utils.DirectionHelper;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
/**
 * Abstract base class for block entities that support inventory storage.
 *
 * <p>This class extends {@link JiScreenBE} and implements the {@link IContentDrop}, {@link IStorageProvider},
 * and {@link IStorageConnector} interfaces, providing a framework for managing inventory contents and storage connections.</p>
 *
 * @param <T> The type of this block entity.
 * @param <B> The type of the inventory used by this block entity.
 */
@SuppressWarnings("unused")
@Developer("Jiraiyah")
@CreatedAt("2025-04-18")
@Repository("https://github.com/drkhodakarami/___PROJECTS___")
@Discord("https://discord.gg/pmM4emCbuH")
@Youtube("https://www.youtube.com/@TheMentorCodeLab")
public abstract class JInventoryBE<T extends JInventoryBE<T, B>, B extends SimpleInventory> extends JiScreenBE<T, BlockPosPayload>
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
    public JInventoryBE(BlockEntityType<T> type, BlockPos pos, BlockState state)
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
    public BlockPosPayload getScreenOpeningData(ServerPlayerEntity serverPlayerEntity)
    {
        return new BlockPosPayload(this.pos);
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
                ? (InventoryStorage) this.inventoryConnector.getStorage(side)
                : this.inventoryConnector.getSidedMap().containsKey(MappedDirection.NONE)
                    ? (InventoryStorage) this.inventoryConnector.getStorage(MappedDirection.NONE)
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
    protected void readData(ReadView view)
    {
        super.readData(view);
        inventoryConnector.readData(view);
    }

    /**
     * Writes data from this block entity's state and inventory to a write view.
     *
     * @param view The write view where the data will be written.
     */
    @Override
    protected void writeData(WriteView view)
    {
        super.writeData(view);
        inventoryConnector.writeData(view);
    }

    /**
     * Handles block replacement logic, including dropping inventory contents if applicable.
     *
     * @param pos      The position of the block being replaced.
     * @param oldState The state of the old block.
     */
    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState)
    {
        if(world == null || !(world.getBlockState(pos).getBlock() instanceof JiBlock block))
            return;
        if(block.hasInventory())
        {
            if(!oldState.isOf(world.getBlockState(pos).getBlock()))
                this.dropContent(world, pos);
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
        if(world == null)
            return null;
        return this.world.getBlockState(this.pos).getProperties().contains(Properties.FACING)
                ? this.getStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.FACING))
                : this.world.getBlockState(this.pos).getProperties().contains(Properties.HORIZONTAL_FACING)
                    ? this.getStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.HORIZONTAL_FACING))
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