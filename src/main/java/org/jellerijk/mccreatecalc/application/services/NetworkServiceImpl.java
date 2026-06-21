package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public class NetworkServiceImpl implements NetworkService {
    private final NetworkRepository networkRepo;

    public NetworkServiceImpl(NetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
    }

    @Override
    public Optional<StressNetwork> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<StressNetwork> getSelectedNetwork() {
        return Optional.empty();
    }

    @Override
    public void setSelectedNetwork(StressNetwork network) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(StressNetwork network) {
        throw new UnsupportedOperationException();
    }
}
