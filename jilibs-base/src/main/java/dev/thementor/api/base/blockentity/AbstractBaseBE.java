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

package dev.thementor.api.base.blockentity;

import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;

import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.ISyncedTick;
import dev.thementor.api.shared.interfaces.IUpdatable;
import dev.thementor.api.shared.properties.BEProperties;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")
public abstract class AbstractBaseBE<T extends AbstractBaseBE<T>> extends BlockEntity implements IUpdatable, ISyncedTick
{
    protected boolean isDirty = false;
    protected boolean isDirtyClient = false;
    protected int ticks;
    protected int clientTicks;

    protected BEProperties<@NotNull T> properties;

    @SuppressWarnings("unchecked")
    public AbstractBaseBE(BlockEntityType<@NotNull T> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        properties = new BEProperties<>((T)this);
    }

    @Override
    public void update()
    {
        this.isDirty = true;

        if(this.properties!= null && !this.properties.isWaitingEndTick())
        {
            setChanged();

            if(this.level != null && !this.level.isClientSide())
                this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public void onTickEnd()
    {
        if(this.isDirty)
        {
            this.isDirty = false;
            setChanged();
            if(this.level != null && !this.level.isClientSide())
                this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public @Nullable Packet<@NotNull ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.@NotNull Provider registries)
    {
        CompoundTag nbt = super.getUpdateTag(registries);
        Logger logger = LogUtils.getLogger();
        try (ProblemReporter.ScopedCollector logging = new ProblemReporter.ScopedCollector(this.problemPath(), logger)) {
            TagValueOutput view = TagValueOutput.createWithContext(logging, registries);
            saveAdditional(view);
            return view.buildResult();
        }
    }

    @Override
    public void onTick()
    {
        if(this.ticks == 0)
            this.onInternalFirstTick();

        if(this.properties.getTickRate() == 0 || (this.ticks % this.properties.getTickRate() == 0))
            this.onInternalTick();

        this.ticks++;
    }

    @Override
    public void onTickClient()
    {
        if(this.clientTicks == 0)
            this.onInternalFirstTickClient();

        if(this.properties.getTickRate() == 0 || (this.clientTicks % this.properties.getTickRate() == 0))
            this.onInternalTickClient();

        this.clientTicks++;
    }

    @Override
    public boolean shouldSync()
    {
        return this.properties.isSynced();
    }

    @SuppressWarnings("DataFlowIssue")
    protected void registerDefaultFields()
    {
        this.properties.fields().addField("isDirty", this.isDirty, blockEntity -> blockEntity.isDirty, ((blockEntity, value) -> blockEntity.isDirty = value));
        this.properties.fields().addField("isDirtyClient", this.isDirtyClient, blockEntity -> blockEntity.isDirtyClient, ((blockEntity, value) -> blockEntity.isDirtyClient = value));
        this.properties.fields().addField("ticks", this.ticks, blockEntity -> blockEntity.ticks, ((blockEntity, value) -> blockEntity.ticks = value));
        this.properties.fields().addField("ticksClient", this.clientTicks, blockEntity -> blockEntity.clientTicks, ((blockEntity, value) -> blockEntity.clientTicks = value));

        if(this.level != null)
            this.properties.fields().addField("world", this.level, AbstractBaseBE::getLevel, null);
        this.properties.fields().addField("pos", this.worldPosition, AbstractBaseBE::getBlockPos, null);
        this.properties.fields().addField("cachedState", getBlockState(), AbstractBaseBE::getBlockState, null);
    }

    public int getTicks()
    {
        return this.ticks;
    }

    public int getTicksClient()
    {
        return this.clientTicks;
    }

    private void onInternalFirstTick()
    {
        registerDefaultFields();
        registerFields();
        onFirstTick();
    }

    private void onInternalFirstTickClient()
    {
        onFirstTickClient();
    }

    private void onInternalTick()
    {
        if(this.properties.tickLogic() != null)
            this.properties.tickLogic().tick(this.properties);
    }

    private void onInternalTickClient()
    {
        if(this.properties.tickLogic() != null)
            this.properties.tickLogic().tickClient(this.properties);
    }

    protected void onFirstTick() {}
    protected void onFirstTickClient() {}
    protected void registerFields() {}

    public boolean isThundering()
    {
        return this.level != null && this.level.isThundering();
    }

    public boolean isRaining()
    {
        return this.level != null && this.level.isRaining();
    }

    public long getDayTime()
    {
        return this.level != null ? this.level.getDayTime() : 0;
    }

    public long getGameTime()
    {
        return this.level != null ? this.level.getGameTime() : 0;
    }

    public int getSkyLightLevelAbove()
    {
        return this.level != null ? this.level.getBrightness(LightLayer.SKY, getBlockPos().above()) : 0;
    }

    public boolean canSeeSky()
    {
        return this.level != null && this.level.canSeeSky(getBlockPos().above());
    }
}