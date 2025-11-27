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

package dev.thementor.api.shared.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import net.minecraft.world.entity.Entity;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

/**
 * Class to manage filter data for entities and items.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class FilterData
{
    /**
     * Determines whether the filter should allow only listed items or all items.
     */
    public boolean allowList = false;

    /**
     * Determines whether the filter should compare NBT tags of items.
     */
    public boolean compareNBT = false;

    /**
     * Filters block items based on a numerical identifier.
     */
    public int blockItemFilter = -1;

    /**
     * A cache to store filtering results for item stacks, indexed by their keys.
     * This cache is not saved in NBT and is recreated as needed on demand
     */
    public final Map<ItemStackKey, Boolean> filterCache = new HashMap<>();

    /**
     * A weak hash map to store filtering results for entities, with the entity as the key.
     */
    public final WeakHashMap<Entity, Boolean> entityCache = new WeakHashMap<>();

    /**
     * Default constructor for FilterData.
     */
    public FilterData() {}

    /**
     * Constructor for FilterData with initial values.
     *
     * @param allowList      Determines whether the filter should allow only listed items or all items.
     * @param compareNBT     Determines whether the filter should compare NBT tags of items.
     * @param blockItemFilter Filters block items based on a numerical identifier.
     */
    public FilterData(boolean allowList, boolean compareNBT, int blockItemFilter)
    {
        this.allowList = allowList;
        this.compareNBT = compareNBT;
        this.blockItemFilter = blockItemFilter;
    }

    /**
     * Generates a hash code for the FilterData object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(allowList, compareNBT, blockItemFilter);
    }

    /**
     * Checks if the FilterData object is equal to another object.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        FilterData that = (FilterData) obj;
        return allowList == that.allowList &&
               compareNBT == that.compareNBT &&
               blockItemFilter == that.blockItemFilter;
    }
}