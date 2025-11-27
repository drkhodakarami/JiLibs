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

package dev.thementor.api.shared.interfaces;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import dev.thementor.api.shared.annotations.*;

/**
 * Represents an interface for objects that can be serialized and deserialized to/from NBT data.
 */
@SuppressWarnings("unused")
@Developer("TurtyWurty")
@ModifiedBy("TheMentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public interface IViewSerializable
{
    /**
     * Writes the object's data to an NBT view.
     *
     * @param view the write view where the data should be written
     */
    void saveAdditional(ValueOutput view);

    /**
     * Reads the object's data from an NBT view.
     *
     * @param view the read view from which the data should be read
     */
    void loadAdditional(ValueInput view);
}