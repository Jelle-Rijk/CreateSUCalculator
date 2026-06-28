package org.jellerijk.mccreatecalc.application.usecases.network.creation;

import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.ArrayList;
import java.util.UUID;

public class CreateNetworkUseCase implements UseCase<CreateNetworkRequest, String> {
    private final NetworkService networkService;

    public CreateNetworkUseCase(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * Creates a new network, saves it to the repository and returns the id for the newly
     * created network.
     *
     * @param createNetworkRequest The data needed for the new network.
     */
    @Override
    public String execute(CreateNetworkRequest createNetworkRequest) {
        String id = UUID.randomUUID().toString();
        StressNetwork network = StressNetwork.Builder.aStressNetwork()
                .withId(id)
                .withName(createNetworkRequest.name())
                .withGenerators(new ArrayList<>())
                .build();
        networkService.add(network);
        return id;
    }
}
