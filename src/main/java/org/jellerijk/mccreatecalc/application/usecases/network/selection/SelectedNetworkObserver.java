package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.entities.StressNetwork;

public interface SelectedNetworkObserver {
    /**
     * This handler is called whenever the selected network changes.
     * @param selectedNetwork The new selected network.
     */
    void onNetworkSelectionChanged(StressNetwork selectedNetwork);
}
