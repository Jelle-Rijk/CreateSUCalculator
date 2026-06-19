package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.NetworkNotFoundException;

public class SelectNetworkUseCase implements VoidUseCase<String> {
    private final SelectedNetworkData data;
    private final NetworkRepository networkRepo;

    public SelectNetworkUseCase(NetworkRepository networkRepo, SelectedNetworkData selectedData) {
        this.networkRepo = networkRepo;
        this.data = selectedData;
    }

    /**
     * Selects a network.
     * @param networkId The network to be selected.
     */
    @Override
    public void execute(String networkId) {
        StressNetwork network = networkRepo.getById(networkId)
                .orElseThrow(() -> new NetworkNotFoundException(networkId));
        data.write(network);
    }
}
