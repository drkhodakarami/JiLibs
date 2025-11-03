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

package dev.thementor.api.shared.data;

import dev.thementor.api.shared.annotations.CreatedAt;
import dev.thementor.api.shared.annotations.Developer;
import dev.thementor.api.shared.annotations.Repository;
import dev.thementor.api.shared.annotations.Youtube;
import net.minecraft.component.ComponentChanges;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Objects;

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
    public final RegistryEntry<Item> item;

    /**
     * The component changes associated with the item's data.
     */
    public final ComponentChanges dataComponents;

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
        this.item = stack.getRegistryEntry();
        this.dataComponents = compareNBT ? stack.getComponentChanges() : ComponentChanges.EMPTY;
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