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

import java.util.Objects;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;

/**
 * Represents a key for an {@link ItemStack} that considers both the item and its data components.
 */
@SuppressWarnings("unused")
@Developer("Direwolf20")
@CreatedAt("2025-04-18")
@Repository("https://github.com/Direwolf20-MC/JustDireThings")
@Youtube("https://www.youtube.com/@direwolf20")
public class ItemStackKey
{
    /**
     * The registry entry for the item.
     */
    public final Holder<Item> item;

    /**
     * The component changes associated with the item's data.
     */
    public final DataComponentPatch dataComponents;

    /**
     * The hash code of this key.
     */
    private final int hash;

    /**
     * Creates a new ItemStackKey for an ItemStack, optionally considering its NBT data components.
     *
     * @param stack      The ItemStack to create the key from.
     * @param compareNBT If true, consider the NBT data components; otherwise, ignore them.
     */
    public ItemStackKey(ItemStack stack, boolean compareNBT)
    {
        this.item = stack.getItemHolder();
        this.dataComponents = compareNBT ? stack.getComponentsPatch() : DataComponentPatch.EMPTY;
        this.hash = Objects.hash(item, dataComponents);
    }

    /**
     * Gets a new ItemStack with the same item and data components as this key.
     *
     * @return A new ItemStack.
     */
    public ItemStack getStack()
    {
        return new ItemStack(item, 1, dataComponents);
    }

    /**
     * Gets a new ItemStack with the specified count and the same item and data components as this key.
     *
     * @param count The number of items in the stack.
     * @return A new ItemStack.
     */
    public ItemStack getStack(int count)
    {
        return new ItemStack(item, count, dataComponents);
    }

    /**
     * Generates a hash code for this key.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode()
    {
        return hash;
    }

    /**
     * Checks if this key is equal to another object.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof ItemStackKey)
            return (((ItemStackKey) obj).item == this.item) && Objects.equals(((ItemStackKey) obj).dataComponents, this.dataComponents);
        return false;
    }
}