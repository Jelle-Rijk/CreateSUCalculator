package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.StressNetworkBuilder;

import java.util.ArrayList;
import java.util.Optional;

public class MockNetworkService implements NetworkService {
    public static final StressNetwork MOCK_NETWORK = StressNetworkBuilder.aStressNetwork()
            .withName("Test-Network")
            .withId("Test123")
            .withGenerators(new ArrayList<>())
            .build();
    public static final String MOCK_NETWORK_ID = MOCK_NETWORK.id();

    @Override
    public Optional<StressNetwork> getById(String id) {
        return Optional.of(MOCK_NETWORK);
    }

    @Override
    public Optional<StressNetwork> getSelectedNetwork() {
        return Optional.of(MOCK_NETWORK);
    }

    @Override
    public void setSelectedNetwork(StressNetwork network) {
        System.out.println("Selected");
    }

    @Override
    public void save(StressNetwork network) {
        System.out.println("Saved");
    }
}
