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

package dev.thementor.api.inventory.base;

import java.util.function.Supplier;

import org.spongepowered.include.com.google.common.base.Objects;

import dev.thementor.api.shared.annotations.*;

@Developer("TurtyWurty")
@ModifiedBy("The Mentor")
@CreatedAt("2025-04-18")
@Repository("https://github.com/DaRealTurtyWurty/Industria")
@Discord("https://discord.turtywurty.dev/")
@Youtube("https://www.youtube.com/@TurtyWurty")

public class PredicateKey
{
    private final Supplier<Boolean> canInsert;
    private final Supplier<Boolean> canExtract;

    public PredicateKey(Supplier<Boolean> canInsert, Supplier<Boolean> canExtract)
    {
        this.canInsert = canInsert;
        this.canExtract = canExtract;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        PredicateKey that = (PredicateKey) obj;
        return Objects.equal(canInsert, that.canInsert) &&
               Objects.equal(canExtract, that.canExtract);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(canInsert, canExtract);
    }
}