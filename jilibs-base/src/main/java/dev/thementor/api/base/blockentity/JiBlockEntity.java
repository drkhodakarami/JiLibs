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

package dev.thementor.api.base.blockentity;

import com.mojang.logging.LogUtils;
import dev.thementor.api.shared.annotations.*;
import dev.thementor.api.shared.interfaces.ISyncedTick;
import dev.thementor.api.shared.interfaces.IUpdatable;
import dev.thementor.api.shared.properties.BEProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("Jiraiyah")
@ThanksTo(discordUsers = "TheWhyEvenHow")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")
public abstract class JiBlockEntity<T extends JiBlockEntity<T>> extends BlockEntity implements IUpdatable, ISyncedTick
{
    protected boolean isDirty = false;
    protected boolean isDirtyClient = false;
    protected int ticks;
    protected int clientTicks;

    protected BEProperties<T> properties;

    @SuppressWarnings("unchecked")
    public JiBlockEntity(BlockEntityType<T> type, BlockPos pos, BlockState state)
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
            markDirty();

            if(this.world != null && !this.world.isClient())
                this.world.updateListeners(this.pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public void onTickEnd()
    {
        if(this.isDirty)
        {
            this.isDirty = false;
            markDirty();
            if(this.world != null && !this.world.isClient())
                this.world.updateListeners(this.pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket()
    {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries)
    {
        NbtCompound nbt = super.toInitialChunkDataNbt(registries);
        Logger logger = LogUtils.getLogger();
        try (ErrorReporter.Logging logging = new ErrorReporter.Logging(this.getReporterContext(), logger)) {
            NbtWriteView view = NbtWriteView.create(logging, registries);
            writeData(view);
            return view.getNbt();
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

    protected void registerDefaultFields()
    {
        this.properties.fields().addField("isDirty", this.isDirty, blockEntity -> blockEntity.isDirty, ((blockEntity, value) -> blockEntity.isDirty = value));
        this.properties.fields().addField("isDirtyClient", this.isDirtyClient, blockEntity -> blockEntity.isDirtyClient, ((blockEntity, value) -> blockEntity.isDirtyClient = value));
        this.properties.fields().addField("ticks", this.ticks, blockEntity -> blockEntity.ticks, ((blockEntity, value) -> blockEntity.ticks = value));
        this.properties.fields().addField("ticksClient", this.clientTicks, blockEntity -> blockEntity.clientTicks, ((blockEntity, value) -> blockEntity.clientTicks = value));

        this.properties.fields().addField("world", this.world, JiBlockEntity::getWorld, null);
        this.properties.fields().addField("pos", this.pos, JiBlockEntity::getPos, null);
        this.properties.fields().addField("cachedState", getCachedState(), JiBlockEntity::getCachedState, null);
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
}