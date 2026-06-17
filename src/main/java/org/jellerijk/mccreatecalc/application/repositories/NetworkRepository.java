package org.jellerijk.mccreatecalc.application.repositories;

import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;

public interface NetworkRepository {
    void add(StressNetwork network);

    List<NetworkInfo> getInfoForAllNetworks();
}
