package org.jellerijk.minecraft.services.dataaccess;

import org.jellerijk.minecraft.model.network.StressNetwork;

import java.util.List;
import java.util.Optional;

public interface StressNetworkDatabaseAccess {
    void save(StressNetwork component);

    Optional<StressNetwork> load(String id);

    List<StressNetwork> loadAll();

    void delete(String id);
}
