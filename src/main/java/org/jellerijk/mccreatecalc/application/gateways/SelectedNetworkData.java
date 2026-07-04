package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public interface SelectedNetworkData {
    /**
     * Saves the supplied <code>network</code> as the selected network.
     *
     * @param network The network to write to memory.
     */
    void write(StressNetwork network);

    /**
     * @return An optional containing the currently selected stress network.
     */
    Optional<StressNetwork> read();

    Optional<String> getId();
}
