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

package dev.thementor.api.fluid.be;

import dev.thementor.api.fluid.base.FluidConnector;
import dev.thementor.api.fluid.interfaces.IFluidConnector;
import dev.thementor.api.fluid.interfaces.IFluidStorageProvider;
import dev.thementor.api.inventory.be.JInventoryBE;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.utils.DirectionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.state.property.Properties;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public abstract class JiFluidBE<T extends JiFluidBE<T, B, C>, B extends SimpleInventory, C extends SingleFluidStorage> extends JInventoryBE<T, B>
    implements IFluidStorageProvider<C>, IFluidConnector<C>
{
    protected final FluidConnector<C> fluidConnector;

    public JiFluidBE(BlockEntityType<T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        fluidConnector = new FluidConnector<>();
    }

    @Override
    public FluidConnector<C> getFluidConnector()
    {
        return fluidConnector;
    }

    @Override
    protected void readData(ReadView view)
    {
        super.readData(view);
        fluidConnector.readData(view);
    }

    @Override
    protected void writeData(WriteView view)
    {
        super.writeData(view);
        fluidConnector.writeData(view);
    }

    @Override
    public @Nullable C getFluidStorageProvider(MappedDirection mappedDirection, Direction facing)
    {
        return getFluidStorageProvider(MappedDirection.toDirection(mappedDirection), facing);
    }

    @Override
    public @Nullable C getFluidStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        return this.fluidConnector.getSidedMap().containsKey(MappedDirection.fromDirection(side))
               ? this.fluidConnector.getStorage(side)
               : this.fluidConnector.getSidedMap().containsKey(MappedDirection.NONE)
                 ? this.fluidConnector.getStorage(MappedDirection.NONE)
                 : null;
    }

    public Storage<FluidVariant> getFluidStorage(Direction direction)
    {
        if(world == null)
            return null;
        return this.world.getBlockState(this.pos).getProperties().contains(Properties.FACING)
               ? this.getFluidStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.FACING))
               : this.world.getBlockState(this.pos).getProperties().contains(Properties.HORIZONTAL_FACING)
                 ? this.getFluidStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.HORIZONTAL_FACING))
                 : this.fluidConnector.getStorage(direction);
    }

    public Storage<FluidVariant> getFluidStorage(MappedDirection direction)
    {
        return getFluidStorage(MappedDirection.toDirection(direction));
    }
}