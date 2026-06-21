package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public interface NetworkService {
    /**
     * @param id The unique identifier for the network to retrieve.
     * @return An optional containing the stress network or null.
     */
    Optional<StressNetwork> getById(String id);

    /**
     * @return The currently selected network.
     */
    Optional<StressNetwork> getSelectedNetwork();

    /**
     * Sets the currently selected network.
     *
     * @param network The network to set as selected.
     */
    void setSelectedNetwork(StressNetwork network);

    void save(StressNetwork network);


}
