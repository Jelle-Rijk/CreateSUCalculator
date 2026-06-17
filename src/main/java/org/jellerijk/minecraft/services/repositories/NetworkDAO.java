package org.jellerijk.minecraft.services.repositories;

import org.jellerijk.minecraft.model.network.Network;

import java.util.Map;
import java.util.Optional;

public interface NetworkDAO {
    void add(Network network);
    void update(Network network);

    Optional<Network> load(String id);

    Map<String, String> loadAllNamesAndIDs();

    void delete(String id);
}
