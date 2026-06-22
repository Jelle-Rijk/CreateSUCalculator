package org.jellerijk.mccreatecalc.application.usecases.network.creation;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.StressNetworkBuilder;

import java.util.ArrayList;
import java.util.UUID;

public class CreateNetworkUseCase implements UseCase<CreateNetworkRequest, String> {
    private final NetworkRepository networkRepo;

    public CreateNetworkUseCase(NetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
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
        StressNetwork network = StressNetworkBuilder.aStressNetwork()
                .withId(id)
                .withName(createNetworkRequest.name())
                .withGenerators(new ArrayList<>())
                .build();
        networkRepo.add(network);
        return id;
    }
}
