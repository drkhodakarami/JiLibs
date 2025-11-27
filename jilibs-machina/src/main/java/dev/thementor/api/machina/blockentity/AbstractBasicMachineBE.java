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

import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

import dev.thementor.api.energy.storage.SyncedEnergyStorage;
import dev.thementor.api.fluid.storage.SyncedFluidStorage;
import dev.thementor.api.inventory.storage.OutputInventory;
import dev.thementor.api.inventory.storage.SyncedInventory;
import dev.thementor.api.shared.enumerations.MappedDirection;
import dev.thementor.api.shared.interfaces.IContainerDataProvider;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class AbstractBasicMachineBE<T extends AbstractBasicMachineBE<T>>
        extends AbstractBaseMachineBE<T, SyncedInventory, SyncedFluidStorage, SimpleEnergyStorage>
        implements IContainerDataProvider
{
    protected SyncedFluidStorage fluidStorage;
    protected SyncedEnergyStorage energyStorage;
    protected final ContainerData containerData;

    protected int energyUpgradeCount;
    protected int fluidUpgradeCount;
    protected int speedUpgradeCount;

    protected int progress;
    protected int maxProgress;

    public static final int DELEGATE_SIZE = 2;
    public static final int OUTPUT_INVENTORY_INDEX = 0;
    public static final int INPUT_INVENTORY_INDEX = 1;
    public static final int UPGRADE_INPUT_INVENTORY_INDEX = 2;

    public static final int RESULT_OUTPUT_SLOT = 0; // Output slot for cleaned gems
    public static final int BUCKET_OUTPUT_SLOT = 1; // Slot for empty bucket storage

    public static final int INGREDIENT_INPUT_SLOT = 0; // Input slot for raw gems
    public static final int BUCKET_INPUT_SLOT = 1; // Input slot for water buckets

    public static final int ENERGY_UPGRADE_SLOT = 0; // Input slot for energy upgrades
    public static final int SPEED_UPGRADE_SLOT = 1; // Input slot for speed upgrades
    public static final int FLUID_UPGRADE_SLOT = 2; // Input slot for fluid upgrades

    public static final String PROP_ENERGY_AMOUNT_ID = "energy_amount";
    public static final String PROP_ENERGY_CAPACITY_ID = "energy_capacity";
    public static final String PROP_FLUID_AMOUNT_ID = "fluid_amount";
    public static final String PROP_FLUID_CAPACITY_ID = "fluid_capacity";
    public static final String PROP_PROGRESS_ID = "progress";
    public static final String PROP_PROGRESS_MAX_ID = "progress_max";
    public static final String PROP_UPGRADE_FLUID_ID = "fluid_upgrade";
    public static final String PROP_UPGRADE_ENERGY_ID = "energy_upgrade";
    public static final String PROP_UPGRADE_SPEED_ID = "speed_upgrade";

    private final String modID;

    public AbstractBasicMachineBE(String modID, BlockEntityType<T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        this.modID = modID;
        this.energyUpgradeCount = 0;
        this.fluidUpgradeCount = 0;
        this.speedUpgradeCount = 0;
        this.progress = 0;
        this.maxProgress = 0;

        if(outputInventorySize() > 0)
            this.inventoryConnector.addStorage(this, outputInventorySize(), inventoryOutputDirection(), OutputInventory::new);
        if(inputInventorySize() > 0)
            this.inventoryConnector.addStorage(this, inputInventorySize(), inventoryInputDirection(), SyncedInventory::new);
        if(upgradeInventorySize() > 0)
            this.inventoryConnector.addStorage(this, upgradeInventorySize(), inventoryUpgradeDirection(), SyncedInventory::new);

        if(hasFluidStorage())
        {
            fluidStorage = createFluidStorage();
            this.fluidConnector.addStorage(fluidStorage, fluidIODirection());
        }
        if(hasEnergyStorage())
        {
            energyStorage = createEnergyStorage();
            this.energyConnector.addStorage(energyStorage, energyIODirection());
        }

        containerData = createContainerData();
    }

    //region overrides
    @Override
    public ContainerData getContainerData()
    {
        return containerData;
    }

    @Override
    public int getContainerDataSize()
    {
        return DELEGATE_SIZE;
    }

    @Override
    protected void saveAdditional(ValueOutput view)
    {
        super.saveAdditional(view);
        view.putInt(modID + ".energy.upgrade.count", this.energyUpgradeCount);
        view.putInt(modID + ".fluid.upgrade.count", this.fluidUpgradeCount);
        view.putInt(modID + ".speed.upgrade.count", this.speedUpgradeCount);

        view.putInt(modID + ".machine.progress", this.progress);
        view.putInt(modID + ".machine.progress.max", this.maxProgress);
    }

    @Override
    protected void loadAdditional(ValueInput view)
    {
        super.loadAdditional(view);
        this.energyUpgradeCount = view.getIntOr(modID + ".energy.upgrade.count", 0);
        this.fluidUpgradeCount = view.getIntOr(modID + ".fluid.upgrade.count", 0);
        this.speedUpgradeCount = view.getIntOr(modID + ".speed.upgrade.count", 0);

        this.progress = view.getIntOr(modID + ".machine.progress", 0);
        this.maxProgress = view.getIntOr(modID + ".machine.progress.max", 0);
    }

    @Override
    protected void registerDefaultFields()
    {
        super.registerDefaultFields();

        this.properties.fields().addField(PROP_ENERGY_AMOUNT_ID,
                                          this.energyStorage.amount,
                                          AbstractBasicMachineBE::getEnergyAmount,
                                          AbstractBasicMachineBE::setEnergyAmount);

        this.properties.fields().addField(PROP_ENERGY_CAPACITY_ID,
                                          this.energyStorage.amount,
                                          AbstractBasicMachineBE::getEnergyCapacity,
                                          null);

        this.properties.fields().addField(PROP_FLUID_AMOUNT_ID,
                                          this.fluidStorage.amount,
                                          AbstractBasicMachineBE::getFluidAmount,
                                          AbstractBasicMachineBE::setFluidAmount);

        this.properties.fields().addField(PROP_FLUID_CAPACITY_ID,
                                          this.fluidStorage.amount,
                                          AbstractBasicMachineBE::getFluidCapacity,
                                          null);

        this.properties.fields().addField(PROP_PROGRESS_ID,
                                          this.progress,
                                          blockEntity -> blockEntity.progress,
                                          (blockEntity, value) -> blockEntity.progress = value);

        this.properties.fields().addField(PROP_PROGRESS_MAX_ID,
                                          this.maxProgress,
                                          blockEntity -> blockEntity.maxProgress,
                                          (blockEntity, value) -> blockEntity.maxProgress = value);

        this.properties.fields().addField(PROP_UPGRADE_FLUID_ID,
                                          this.fluidUpgradeCount,
                                          blockEntity -> blockEntity.fluidUpgradeCount,
                                          (blockEntity, value) -> blockEntity.fluidUpgradeCount = value);

        this.properties.fields().addField(PROP_UPGRADE_ENERGY_ID,
                                          this.energyUpgradeCount,
                                          blockEntity -> blockEntity.energyUpgradeCount,
                                          (blockEntity, value) -> blockEntity.energyUpgradeCount = value);

        this.properties.fields().addField(PROP_UPGRADE_SPEED_ID,
                                          this.speedUpgradeCount,
                                          blockEntity -> blockEntity.speedUpgradeCount,
                                          (blockEntity, value) -> blockEntity.speedUpgradeCount = value);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState oldState)
    {
        super.preRemoveSideEffects(pos, oldState);
        List<ItemStack> stacks = new ArrayList<>();

        Item energy = getEnergyUpgradeItem();
        Item fluid = getFluidUpgradeItem();
        Item speed = getSpeedUpgradeItem();

        if(energyUpgradeCount > 0)
        {
            for (int i = 0; i < energyUpgradeCount; i++)
            {
                stacks.add(new ItemStack(energy, 1));
            }
        }
        if(fluidUpgradeCount > 0)
        {
            for (int i = 0; i < fluidUpgradeCount; i++)
            {
                stacks.add(new ItemStack(fluid, 1));
            }
        }
        if(speedUpgradeCount > 0)
        {
            for (int i = 0; i < speedUpgradeCount; i++)
            {
                stacks.add(new ItemStack(speed, 1));
            }
        }

        if(stacks.isEmpty() || this.level == null)
            return;

        for (ItemStack stack : stacks)
            Containers.dropItemStack(this.level, this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(), stack);
    }
    //endregion

    //region public methods
    public SyncedFluidStorage getFluidStorage()
    {
        return fluidStorage;
    }

    public long getFluidAmount()
    {
        return this.fluidStorage.getAmount();
    }

    public void setFluidAmount(long amount)
    {
        this.fluidStorage.amount = amount;
    }

    public long getFluidCapacity()
    {
        return this.fluidStorage.getCapacity();
    }

    public SyncedEnergyStorage getEnergyStorage()
    {
        return energyStorage;
    }

    public long getEnergyAmount()
    {
        return this.energyStorage.getAmount();
    }

    public void setEnergyAmount(long amount)
    {
        this.energyStorage.amount = amount;
    }

    public long getEnergyCapacity()
    {
        return this.energyStorage.getCapacity();
    }

    public int getProgress()
    {
        return this.progress;
    }

    public int getMaxProgress()
    {
        return this.maxProgress;
    }

    public void incrementFluidUpgrades(int count)
    {
        this.fluidUpgradeCount += count;
        update();
    }

    public void incrementEnergyUpgrades(int count)
    {
        this.energyUpgradeCount += count;
        update();
    }

    public void incrementSpeedUpgrades(int count)
    {
        this.speedUpgradeCount += count;
        update();
    }

    public void increaseProgress()
    {
        this.progress++;
        update();
    }

    public void resetProgress()
    {
        this.progress = 0;
        this.maxProgress = 0;
        update();
    }

    public void setMaxProgress(int amount)
    {
        this.maxProgress = amount;
        update();
    }

    public OutputInventory getOutputInventory()
    {
        return (OutputInventory) this.inventoryConnector.getInventory(OUTPUT_INVENTORY_INDEX);
    }

    public SyncedInventory getInputInventory()
    {
        return this.inventoryConnector.getInventory(INPUT_INVENTORY_INDEX);
    }

    public SyncedInventory getUpgradeInventory()
    {
        return this.inventoryConnector.getInventory(UPGRADE_INPUT_INVENTORY_INDEX);
    }

    public int totalInventorySize()
    {
        return outputInventorySize() + inputInventorySize() + upgradeInventorySize();
    }

    public long getFluidUpgradeCount()
    {
        return this.fluidUpgradeCount;
    }

    public long getEnergyUpgradeCount()
    {
        return this.energyUpgradeCount;
    }

    public int getSpeedUpgradeCount()
    {
        return this.speedUpgradeCount;
    }

    public MappedDirection inventoryOutputDirection()
    {
        return MappedDirection.DOWN;
    }

    public MappedDirection energyIODirection()
    {
        return MappedDirection.WEST;
    }

    public MappedDirection fluidIODirection()
    {
        return MappedDirection.SOUTH;
    }

    public MappedDirection inventoryUpgradeDirection()
    {
        return MappedDirection.EAST;
    }

    public MappedDirection inventoryInputDirection()
    {
        return MappedDirection.UP;
    }
    //endregion

    //region protected methods
    protected ContainerData createContainerData()
    {
        return new ContainerData()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> AbstractBasicMachineBE.this.progress;
                    case 1 -> AbstractBasicMachineBE.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> AbstractBasicMachineBE.this.progress = value;
                    case 1 -> AbstractBasicMachineBE.this.maxProgress = value;
                }
            }

            @Override
            public int getCount()
            {
                return DELEGATE_SIZE;
            }
        };
    }

    protected int upgradeInventorySize()
    {
        return 0;
    }

    protected int inputInventorySize()
    {
        return 0;
    }

    protected int outputInventorySize()
    {
        return 0;
    }

    protected boolean hasFluidStorage()
    {
        return false;
    }

    protected SyncedFluidStorage createFluidStorage()
    {
        return new SyncedFluidStorage(this, getBaseFluidCapacity())
        {
            @SuppressWarnings("unchecked")
            @Override
            protected long getCapacity(FluidVariant variant)
            {
                T be = ((T)getBlockEntity());
                return be.getBaseFluidCapacity() + be.getFluidUpgradeAmount();
            }

            @Override
            protected void onFinalCommit()
            {
                super.onFinalCommit();
                update();
            }

            @SuppressWarnings("unchecked")
            @Override
            public FluidVariant getResource()
            {
                T be = ((T)getBlockEntity());
                return be.fluidStorageVariant();
            }
        };
    }

    protected FluidVariant fluidStorageVariant()
    {
        return FluidVariant.of(Fluids.WATER);
    }

    protected boolean hasEnergyStorage()
    {
        return false;
    }

    protected SyncedEnergyStorage createEnergyStorage()
    {
        return new SyncedEnergyStorage(this, 100_000, 1000, 0)
        {
            @SuppressWarnings("unchecked")
            @Override
            public long getCapacity()
            {
                T be = ((T)getBlockEntity());
                return be.getBaseEnergyCapacity() + be.getEnergyUpgradeAmount();
            }

            @Override
            protected void onFinalCommit()
            {
                super.onFinalCommit();
                update();
            }
        };
    }

    protected long getFluidUpgradeAmount()
    {
        return FluidConstants.BUCKET * this.getFluidUpgradeCount();
    }

    protected long getBaseFluidCapacity()
    {
        return FluidConstants.BUCKET * 10;
    }

    protected long getEnergyUpgradeAmount()
    {
        return getEnergyUpgradeCount() * 10_000L;
    }

    protected long getBaseEnergyCapacity()
    {
        return 100_000;
    }

    protected Item getEnergyUpgradeItem()
    {
        return ItemStack.EMPTY.getItem();
    }

    protected Item getFluidUpgradeItem()
    {
        return ItemStack.EMPTY.getItem();
    }

    protected Item getSpeedUpgradeItem()
    {
        return ItemStack.EMPTY.getItem();
    }
    //endregion
}