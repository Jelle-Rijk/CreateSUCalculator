package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
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

    void subscribeToSelectedNetwork(SelectedNetworkObserver observer);

    /**
     * Sets the currently selected network.
     *
     * @param network The network to set as selected.
     */
    void setSelectedNetwork(StressNetwork network);

    /**
     * Saves an existing network to the repository.
     * @param network The network to save.
     */
    void save(StressNetwork network);

    /**
     * Saves a new network to the repository.
     * @param network The network to add.
     */
    void add(StressNetwork network);

    /**
     * @return List of objects containing a name and id for each network in the repository.
     */
    List<NetworkInfo> getAllNamesAndIds();
}
