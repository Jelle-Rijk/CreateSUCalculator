package org.jellerijk.minecraft.model.stressnetwork;

public interface StressNetwork {
    /**
     * @return The amount of SU produced within the network.
     */
    int calculateTotalSUProduced();

    /**
     * @return The amount of SU consumed within the network.
     */
    int calculateTotalSUConsumed();

    /**
     * Calculates the balance of all SU produced and consumed within the network.
     *
     * @return The balance of all SU produced and consumed within the network.
     */
    default int calculateSUBalance() {
        return calculateTotalSUProduced() - calculateTotalSUConsumed();
    }

    /**
     * @return True if the network consumes more SU than it produces, otherwise false.
     */
    default boolean isOverstressed() {
        return calculateSUBalance() < 0;
    }
}
