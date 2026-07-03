package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.data.dao.NetworkDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkRepositoryImpl implements NetworkRepository {
    private final NetworkDAO networkDAO;
    private final ComponentGroupDAO componentGroupDAO;

    public NetworkRepositoryImpl(NetworkDAO networkDAO, ComponentGroupDAO componentGroupDAO) {
        this.networkDAO = networkDAO;
        this.componentGroupDAO = componentGroupDAO;
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

    @Override
    public void update(StressNetwork network) {
        networkDAO.update(network);
        List<ComponentGroup> groups = Stream.concat(network.consumers().stream(), network.generators().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        componentGroupDAO.syncComponentGroups(network.id(), groups);
    }
}
