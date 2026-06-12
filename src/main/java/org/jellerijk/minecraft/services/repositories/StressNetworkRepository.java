package org.jellerijk.minecraft.services.repositories;

import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.persistence.MockNetworkDB;
import org.jellerijk.minecraft.services.dataaccess.StressNetworkDatabaseAccess;

import java.util.Optional;

public class StressNetworkRepository {
    private final StressNetworkDatabaseAccess networkDAO;

    public StressNetworkRepository() {
        networkDAO = new MockNetworkDB();
    }

    public void save(StressNetwork stressNetwork) {
        networkDAO.save(stressNetwork);
    }

    public Optional<StressNetwork> load(String id) {
        return networkDAO.load(id);
    }

    public void delete(String id) {
        networkDAO.delete(id);
    }
}
