package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.application.usecases.network.read.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
import java.util.Optional;

public interface NetworkDAO {
    void insert(StressNetwork network);

    List<NetworkInfo> loadInfoAndIdsForAllNetworks();

    Optional<StressNetwork> getById(String id);
}
