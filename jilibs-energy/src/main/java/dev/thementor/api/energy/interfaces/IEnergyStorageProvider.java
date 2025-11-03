package dev.thementor.api.energy.interfaces;

import dev.thementor.api.shared.enumerations.MappedDirection;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public interface IEnergyStorageProvider<T>
{
    /**
     * Retrieves a energy storage provider based on the specified {@link MappedDirection} and facing direction.
     *
     * @param direction  the mapped direction
     * @param facing   the facing direction
     * @return the energy storage provider, or null if none is found
     */
    @Nullable
    T getEnergyStorageProvider(MappedDirection direction, Direction facing);

    /**
     * Retrieves a energy storage provider based on the specified facing direction.
     *
     * @param direction  the facing direction
     * @param facing   the facing direction
     * @return the energy storage provider, or null if none is found
     */
    @Nullable
    T getEnergyStorageProvider(Direction direction, Direction facing);
}