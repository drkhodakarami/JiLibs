package dev.thementor.api.fluid.interfaces;

import dev.thementor.api.shared.enumerations.MappedDirection;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public interface IFluidStorageProvider<T>
{
    /**
     * Retrieves a fluid storage provider based on the specified {@link MappedDirection} and facing direction.
     *
     * @param direction  the mapped direction
     * @param facing   the facing direction
     * @return the fluid storage provider, or null if none is found
     */
    @Nullable
    T getFluidStorageProvider(MappedDirection direction, Direction facing);

    /**
     * Retrieves a fluid storage provider based on the specified facing direction.
     *
     * @param direction  the facing direction
     * @param facing   the facing direction
     * @return the fluid storage provider, or null if none is found
     */
    @Nullable
    T getFluidStorageProvider(Direction direction, Direction facing);
}