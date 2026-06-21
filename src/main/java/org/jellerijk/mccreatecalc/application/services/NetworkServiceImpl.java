package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepository networkRepo;
    private final SelectedNetworkData selectedNetwork;

    public NetworkServiceImpl(NetworkRepository networkRepo, SelectedNetworkData selectedNetwork) {
        this.networkRepo = networkRepo;
        this.selectedNetwork = selectedNetwork;
    }

    @Override
    public Optional<StressNetwork> getById(String id) {
        return networkRepo.getById(id);
    }

    @Override
    public Optional<StressNetwork> getSelectedNetwork() {
        return selectedNetwork.read();
    }

    @Override
    public void setSelectedNetwork(StressNetwork network) {
        selectedNetwork.write(network);
    }

    @Override
    public void save(StressNetwork network) {
        networkRepo.update(network);
    }
}
