package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.NetworkNotFoundException;

public class SelectNetworkUseCase implements VoidUseCase<String> {
    private final NetworkService networkService;

    public SelectNetworkUseCase(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * Selects a network.
     *
     * @param networkId The network to be selected.
     */
    @Override
    public void execute(String networkId) {
        StressNetwork network = networkService.getById(networkId)
                .orElseThrow(() -> new NetworkNotFoundException(networkId));
        networkService.setSelectedNetwork(network);
    }
}
