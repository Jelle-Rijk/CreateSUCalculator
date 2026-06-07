package org.jellerijk.minecraft.model.network;

import org.jellerijk.minecraft.model.components.Consumer;
import org.jellerijk.minecraft.model.components.Generator;

import java.util.Map;

public interface StressNetwork {
    /**
     * @return The SU balance of the complete system.
     */
    int calculateSUBalance();

    /**
     * @return The total amount of SU consumed by the network's consumers.
     */
    int calculateTotalSUConsumed();

    /**
     * @return The total amount of SU produced by the network's generators.
     */
    int calculateTotalSUProduced();

    /**
     * @return A map containing all the consumers in the system as the keys and the amount of each consumer as the
     * values.
     */
    Map<Consumer, Integer> getConsumers();

    /**
     * @return A map containing all the generators in the system as the keys and the amount of each generator as the
     * values.
     */
    Map<Generator, Integer> getGenerators();

    /**
     * @return The network's unique identifier.
     */
    String getId();

    /**
     * @return The name to be displayed for the network.
     */
    String getName();

    /**
     * @return True if the total SU balance is negative. False otherwise.
     */
    boolean isOverstressed();
}
