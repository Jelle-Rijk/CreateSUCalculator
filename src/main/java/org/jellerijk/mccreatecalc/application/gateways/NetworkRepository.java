package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
import java.util.Optional;

public interface NetworkRepository {
    void add(StressNetwork network);

    List<NetworkInfo> getInfoForAllNetworks();

    Optional<StressNetwork> getById(String id);

    void update(StressNetwork network);
}
