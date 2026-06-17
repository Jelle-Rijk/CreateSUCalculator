package org.jellerijk.mccreatecalc.data;

import org.jellerijk.mccreatecalc.application.repositories.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;

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
}
