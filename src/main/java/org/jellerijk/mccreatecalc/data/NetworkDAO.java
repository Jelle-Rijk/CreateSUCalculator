package org.jellerijk.mccreatecalc.data;

import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;

public interface NetworkDAO {
    void insert(StressNetwork network);

    List<NetworkInfo> loadInfoAndIdsForAllNetworks();
}
