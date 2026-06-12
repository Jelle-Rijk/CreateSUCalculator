package org.jellerijk.minecraft.persistence;

import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.services.dataaccess.StressNetworkDatabaseAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MockNetworkDB implements StressNetworkDatabaseAccess {
    List<StressNetwork> networks;
    @Override
    public void save(StressNetwork component) {
        networks.add(component);
    }

    @Override
    public Optional<StressNetwork> load(String id) {
        return networks.stream().filter(n -> n.getId().equals(id)).findAny();
    }

    @Override
    public List<StressNetwork> loadAll() {
        return networks;
    }

    @Override
    public void delete(String id) {
        networks = networks.stream().filter(n -> !n.getId().equals(id)).collect(Collectors.toCollection(ArrayList::new));
    }
}
