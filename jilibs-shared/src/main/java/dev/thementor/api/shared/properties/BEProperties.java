/***********************************************************************************
 * Copyright (c) 2025 Alireza Khodakarami (TheMentor)                               *
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

package dev.thementor.api.shared.properties;

import dev.thementor.api.shared.interfaces.ITickLogic;
import dev.thementor.api.shared.annotations.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.World;

/**
 * Represents properties and utilities for a BlockEntity.
 *
 * @param <T> the type of BlockEntity being managed
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class BEProperties<T extends BlockEntity>
{
    /**
     * The BlockEntity instance associated with these properties.
     */
    private final T blockEntity;

    /**
     * Indicates whether the BlockEntity should be synchronized across clients and servers.
     */
    private boolean shouldSync;

    /**
     * Indicates whether the BlockEntity should wait until the end of the current tick before processing.
     */
    private boolean shouldWaitEndTick;

    /**
     * Indicates whether the BlockEntity should be processed during ticks.
     */
    private boolean shouldTick;

    /**
     * Indicates whether the BlockEntity should be updated.
     */
    private boolean shouldUpdate;

    /**
     * The tick rate at which the BlockEntity should be processed. A value of 0 means it should be processed every tick.
     */
    private int tickRate = 0;

    /**
     * The logic to be executed when processing the BlockEntity during ticks.
     */
    private ITickLogic<T, BlockEntityFields<T>> tickLogic;

    /**
     * A collection of fields associated with the BlockEntity.
     */
    private final BlockEntityFields<T> fields = new BlockEntityFields<>();

    /**
     * Constructs a new instance of BEProperties for the specified BlockEntity.
     *
     * @param blockEntity the BlockEntity to manage
     */
    public BEProperties(T blockEntity)
    {
        this.blockEntity = blockEntity;
        this.shouldSync = false;
        this.shouldWaitEndTick = false;
        this.shouldTick = false;
        this.shouldUpdate = false;
    }

    //region GETTERS

    /**
     * Retrieves whether the BlockEntity is currently synchronized.
     *
     * @return true if the BlockEntity should be synchronized, false otherwise
     */
    public boolean isSynced()
    {
        return this.shouldSync;
    }

    /**
     * Retrieves whether the BlockEntity is updatable.
     *
     * @return true if the BlockEntity should be updated, false otherwise
     */
    public boolean isUpdatable()
    {
        return this.shouldUpdate;
    }

    /**
     * Retrieves whether the BlockEntity is tickable.
     *
     * @return true if the BlockEntity should be processed during ticks, false otherwise
     */
    public boolean isTickable()
    {
        return this.shouldTick;
    }

    /**
     * Retrieves whether the BlockEntity is waiting until the end of the current tick before processing.
     *
     * @return true if the BlockEntity should wait until the end of the current tick, false otherwise
     */
    public boolean isWaitingEndTick()
    {
        return this.shouldWaitEndTick;
    }

    /**
     * Determines whether the current environment is a client environment.
     *
     * @return true if the current environment is a client, false otherwise
     */
    public boolean isClient()
    {
        return world() != null && world().isClient();
    }

    /**
     * Retrieves the World object associated with the BlockEntity.
     *
     * @return the World object if it exists; null otherwise
     */
    public World world()
    {
        return this.fields.containsField("world")
               ? this.fields.getField("world", World.class).getValue()
               : null;
    }

    /**
     * Retrieves the current tick rate at which the BlockEntity should be processed.
     *
     * @return the tick rate
     */
    public int getTickRate()
    {
        return this.tickRate;
    }

    /**
     * Retrieves a collection of fields associated with the BlockEntity.
     *
     * @return the collection of fields
     */
    public BlockEntityFields<T> fields()
    {
        return this.fields;
    }

    /**
     * Retrieves the BlockEntity being managed by these properties.
     *
     * @return the BlockEntity
     */
    public T blockEntity()
    {
        return this.blockEntity;
    }

    /**
     * Retrieves the logic to be executed when processing the BlockEntity during ticks.
     *
     * @return the tick logic
     */
    public ITickLogic<T, BlockEntityFields<T>> tickLogic()
    {
        return this.tickLogic;
    }

    //endregion

    //region SETTERS

    /**
     * Sets the tick rate at which the BlockEntity should be processed. A value of 0 means it should be processed every tick.
     *
     * @param tickRate the tick rate to set
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setTickRate(int tickRate)
    {
        if(tickRate < 0)
            throw new IllegalArgumentException("Tick rate must be greater than or equal to 0");
        this.tickRate = tickRate;
        return this;
    }

    /**
     * Sets the logic to be executed when processing the BlockEntity during ticks.
     *
     * @param tickLogic the tick logic to set
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setTickLogic(ITickLogic<T, BlockEntityFields<T>> tickLogic)
    {
        this.tickLogic = tickLogic;
        return this;
    }

    /**
     * Sets both synchronization and end-of-tick waiting flags.
     *
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> sync()
    {
        return setSync(true).setWaitEndTick(true);
    }

    /**
     * Enables or disables synchronization.
     *
     * @param flag true to enable synchronization, false to disable
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setSync(boolean flag)
    {
        this.shouldSync = flag;
        return this;
    }

    /**
     * Enables or disables waiting until the end of the current tick.
     *
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> waitEndTick()
    {
        return setWaitEndTick(true);
    }

    /**
     * Enables or disables waiting until the end of the current tick.
     *
     * @param flag true to enable waiting, false to disable
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setWaitEndTick(boolean flag)
    {
        this.shouldWaitEndTick = flag;
        return this;
    }

    /**
     * Enables or disables tick processing.
     *
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> tick()
    {
        return setTickable(shouldSync);
    }

    /**
     * Enables or disables tick processing.
     *
     * @param flag true to enable tick processing, false to disable
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setTickable(boolean flag)
    {
        this.shouldTick = flag;
        return this;
    }

    /**
     * Enables or disables update.
     *
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> update()
    {
        return setUpdatable(shouldSync);
    }

    /**
     * Enables or disables update.
     *
     * @param flag true to enable update, false to disable
     * @return the current instance of BEProperties for method chaining
     */
    public BEProperties<T> setUpdatable(boolean flag)
    {
        this.shouldUpdate = flag;
        return this;
    }

    //endregion

























}