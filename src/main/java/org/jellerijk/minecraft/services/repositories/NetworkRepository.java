package org.jellerijk.minecraft.services.repositories;

import org.jellerijk.minecraft.model.network.Network;

import java.util.Map;
import java.util.Optional;

public class NetworkRepository {
    private final NetworkDAO networkDAO;

    public NetworkRepository(NetworkDAO networkDAO) {
        this.networkDAO = networkDAO;
    }

    public void add(Network network) {
        networkDAO.add(network);
    }

    public void update(Network network) {
        networkDAO.update(network);
    }

    public Optional<Network> load(String id) {
        return networkDAO.load(id);
    }

    public Map<String, String> getAllNamesAndIDs() {
        return networkDAO.loadAllNamesAndIDs();
    }

    public void delete(String id) {
        networkDAO.delete(id);
    }
}
