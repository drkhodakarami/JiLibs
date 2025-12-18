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

package dev.thementor.api.fluid.be;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

import dev.thementor.api.fluid.base.FluidConnector;
import dev.thementor.api.fluid.interfaces.IFluidConnector;
import dev.thementor.api.fluid.interfaces.IFluidSpreader;
import dev.thementor.api.fluid.interfaces.IFluidStorageProvider;
import dev.thementor.api.inventory.be.AbstractBaseInventoryBE;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.records.FluidStackPayload;
import dev.thementor.api.shared.records.lists.FluidStackList;
import dev.thementor.api.shared.utils.DirectionHelper;

@SuppressWarnings("unused")
public abstract class AbstractBaseFluidBE<T extends AbstractBaseFluidBE<T, B, C>, B extends SimpleContainer, C extends SingleFluidStorage>
        extends AbstractBaseInventoryBE<T, B>
        implements IFluidStorageProvider<C>, IFluidConnector<C>, IFluidSpreader<C>
{
    protected final FluidConnector<C> fluidConnector;

    public AbstractBaseFluidBE(BlockEntityType<@NotNull T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        fluidConnector = new FluidConnector<>();
        this.properties.tick();
    }

    @Override
    public FluidConnector<C> getFluidConnector()
    {
        return fluidConnector;
    }

    @Override
    protected void loadAdditional(@NotNull ValueInput view)
    {
        super.loadAdditional(view);
        fluidConnector.loadAdditional(view);
    }

    @Override
    protected void saveAdditional(@NotNull ValueOutput view)
    {
        super.saveAdditional(view);
        fluidConnector.saveAdditional(view);
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
        if(level == null)
            return null;
        return this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.FACING)
               ? this.getFluidStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.FACING))
               : this.level.getBlockState(this.worldPosition).getProperties().contains(BlockStateProperties.HORIZONTAL_FACING)
                 ? this.getFluidStorageProvider(direction, this.level.getBlockState(this.worldPosition).getValue(BlockStateProperties.HORIZONTAL_FACING))
                 : this.fluidConnector.getStorage(direction);
    }

    public Storage<FluidVariant> getFluidStorage(MappedDirection direction)
    {
        return getFluidStorage(MappedDirection.toDirection(direction));
    }

    //TODO: Explain this
    @Override
    protected void applyImplicitComponents(@NotNull DataComponentGetter dataComponentGetter)
    {
        super.applyImplicitComponents(dataComponentGetter);

        if(fluidConnector.getStorages().isEmpty() || getFluidComponent() == null)
            return;

        FluidStackList list = dataComponentGetter.getOrDefault(getFluidComponent(), FluidStackList.EMPTY);

        if(list.values().isEmpty())
            return;

        int index = 0;

        for (FluidStackPayload fluidStack : list.values())
        {
            if(index >= fluidConnector.getStorages().size())
                break;

            var storage = fluidConnector.getStorages().get(index);

            if(storage instanceof SingleFluidStorage simpleFluidStorage)
            {
                simpleFluidStorage.variant = fluidStack.fluid();
                simpleFluidStorage.amount = fluidStack.amount();
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

        if(getFluidComponent() == null)
            return;

        List<FluidStackPayload> list = new ArrayList<>();

        for (var storage : fluidConnector.getStorages())
        {
            list.add(new FluidStackPayload(storage.variant, storage.getAmount()));
        }

        builder.set(getFluidComponent(), new FluidStackList(list));
    }

    protected abstract DataComponentType<FluidStackList> getFluidComponent();
}