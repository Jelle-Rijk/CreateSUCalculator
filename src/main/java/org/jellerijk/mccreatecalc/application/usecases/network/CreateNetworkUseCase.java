package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.repositories.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.OutputBoundary;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.UUID;

public class CreateNetworkUseCase implements UseCase<CreateNetworkRequest> {
    private final NetworkRepository networkRepo;
    private final OutputBoundary<String> presenter;

    public CreateNetworkUseCase(OutputBoundary<String> presenter, NetworkRepository networkRepo) {
        this.presenter = presenter;
        this.networkRepo = networkRepo;
    }

    /**
     * Creates a new network, saves it to the org.jellerijk.mccreatecalc.database and returns the id for the newly
     * created network.
     *
     * @param createNetworkRequest The data needed for the new network.
     */
    @Override
    public void execute(CreateNetworkRequest createNetworkRequest) {
        String id = UUID.randomUUID().toString();
        StressNetwork network = new StressNetwork(id, createNetworkRequest.name());
        networkRepo.add(network);
        presenter.present(id);
    }
}
