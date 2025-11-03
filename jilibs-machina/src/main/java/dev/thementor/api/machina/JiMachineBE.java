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

package dev.thementor.api.machina;

import dev.thementor.api.energy.base.EnergyConnector;
import dev.thementor.api.energy.interfaces.IEnergyConnector;
import dev.thementor.api.energy.interfaces.IEnergyStorageProvider;
import dev.thementor.api.fluid.be.JiFluidBE;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.utils.DirectionHelper;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.state.property.Properties;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

public abstract class JiMachineBE<T extends JiMachineBE<T, B, C, D>,
                         B extends SimpleInventory,
                         C extends SingleFluidStorage,
                         D extends EnergyStorage>
        extends JiFluidBE<T, B, C>
        implements IEnergyStorageProvider<D>, IEnergyConnector<D>
{
    protected final EnergyConnector<D> energyConnector;

    public JiMachineBE(BlockEntityType<T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        energyConnector = new EnergyConnector<>();
    }

    @Override
    public EnergyConnector<D> getEnergyConnector()
    {
        return this.energyConnector;
    }

    @Override
    protected void readData(ReadView view)
    {
        super.readData(view);
        energyConnector.readData(view);
    }

    @Override
    protected void writeData(WriteView view)
    {
        super.writeData(view);
        energyConnector.writeData(view);
    }

    @Override
    public @Nullable D getEnergyStorageProvider(MappedDirection mappedDirection, Direction facing)
    {
        return getEnergyStorageProvider(MappedDirection.toDirection(mappedDirection), facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable D getEnergyStorageProvider(Direction direction, Direction facing)
    {
        Direction side = DirectionHelper.relativeDirection(direction, facing);
        return this.energyConnector.getSidedMap().containsKey(MappedDirection.fromDirection(side))
               ? (D) this.energyConnector.getStorage(side)
               : this.energyConnector.getSidedMap().containsKey(MappedDirection.NONE)
                 ? (D) this.energyConnector.getStorage(MappedDirection.NONE)
                 : null;
    }

    @SuppressWarnings("unchecked")
    public D getEnergyStorage(Direction direction)
    {
        if(world == null)
            return null;
        return this.world.getBlockState(this.pos).getProperties().contains(Properties.FACING)
               ? this.getEnergyStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.FACING))
               : this.world.getBlockState(this.pos).getProperties().contains(Properties.HORIZONTAL_FACING)
                 ? this.getEnergyStorageProvider(direction, this.world.getBlockState(this.pos).get(Properties.HORIZONTAL_FACING))
                 : (D) this.energyConnector.getStorage(direction);
    }

    public D getEnergyStorage(MappedDirection direction)
    {
        return getEnergyStorage(MappedDirection.toDirection(direction));
    }
}