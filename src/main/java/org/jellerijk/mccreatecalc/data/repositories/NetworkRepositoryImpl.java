package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.read.NetworkInfo;
import org.jellerijk.mccreatecalc.data.dao.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
import java.util.Optional;

public class NetworkRepositoryImpl implements NetworkRepository {
    private final NetworkDAO networkDAO;

    public NetworkRepositoryImpl(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }

    @Override
    public void add(StressNetwork network) {
        networkDAO.insert(network);
    }

    @Override
    public List<NetworkInfo> getInfoForAllNetworks() {
        return networkDAO.loadInfoAndIdsForAllNetworks();
    }

    @Override
    public Optional<StressNetwork> getById(String id) {
        return networkDAO.getById(id);
    }
}
