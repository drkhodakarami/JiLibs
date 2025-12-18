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

package dev.thementor.api.machina.blockentity;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;

import dev.thementor.api.energy.base.EnergyConnector;
import dev.thementor.api.energy.interfaces.IEnergyConnector;
import dev.thementor.api.energy.interfaces.IEnergySpreader;
import dev.thementor.api.energy.interfaces.IEnergyStorageProvider;
import dev.thementor.api.fluid.be.AbstractBaseFluidBE;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.records.LongPayload;
import dev.thementor.api.shared.records.lists.LongList;
import dev.thementor.api.shared.utils.DirectionHelper;

@SuppressWarnings("unused")
public abstract class AbstractBaseMachineBE<T extends AbstractBaseMachineBE<T, B, C, D>,
                         B extends SimpleContainer,
                         C extends SingleFluidStorage,
                         D extends EnergyStorage>
        extends AbstractBaseFluidBE<T, B, C>
        implements IEnergyStorageProvider<D>, IEnergyConnector<D>, IEnergySpreader<D>
{
    protected final EnergyConnector<D> energyConnector;

    public AbstractBaseMachineBE(BlockEntityType<@NotNull T> type, BlockPos pos, BlockState state)
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
    protected void loadAdditional(@NotNull ValueInput view)
    {
        super.loadAdditional(view);
        energyConnector.loadAdditional(view);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput view)
    {
        super.saveAdditional(view);
        energyConnector.saveAdditional(view);
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
        if(level == null)
            return null;
        return this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.FACING)
               ? this.getEnergyStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.FACING))
               : this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.HORIZONTAL_FACING)
                 ? this.getEnergyStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.HORIZONTAL_FACING))
                 : (D) this.energyConnector.getStorage(direction);
    }

    public D getEnergyStorage(MappedDirection direction)
    {
        return getEnergyStorage(MappedDirection.toDirection(direction));
    }

    //TODO: Explain this
    @Override
    protected void applyImplicitComponents(@NotNull DataComponentGetter dataComponentGetter)
    {
        super.applyImplicitComponents(dataComponentGetter);

        if(energyConnector.getStorages().isEmpty() || getEnergyComponent() == null)
            return;

        LongList list = dataComponentGetter.getOrDefault(getEnergyComponent(), LongList.EMPTY);

        if(list.values().isEmpty())
            return;

        int index = 0;

        for (LongPayload longPayload : list.values())
        {
            if(index >= energyConnector.getStorages().size())
                break;

            EnergyStorage storage = energyConnector.getStorages().get(index);
            if(storage instanceof SimpleEnergyStorage simpleEnergyStorage)
            {
                simpleEnergyStorage.amount = longPayload.value();
                update();
            }

            index++;
        }
    }

    //TODO: Explain this
    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder builder)
    {
        super.collectImplicitComponents(builder);

        if(getEnergyComponent() == null)
            return;

        List<LongPayload> list = new ArrayList<>();

        for (EnergyStorage storage : energyConnector.getStorages())
            list.add(new LongPayload(storage.getAmount()));

        builder.set(getEnergyComponent(), new LongList(list));
    }

    protected abstract DataComponentType<LongList> getEnergyComponent();
}