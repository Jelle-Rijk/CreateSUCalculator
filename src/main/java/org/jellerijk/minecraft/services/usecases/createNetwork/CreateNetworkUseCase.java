package org.jellerijk.minecraft.services.usecases.createNetwork;

import org.jellerijk.minecraft.dtos.StressNetworkDTO;
import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.model.network.implementations.StressNetworkImpl;
import org.jellerijk.minecraft.services.repositories.StressNetworkRepository;
import org.jellerijk.minecraft.services.usecases.UseCase;

import java.util.HashMap;
import java.util.UUID;

public class CreateNetworkUseCase implements UseCase<NetworkCreationData, StressNetworkDTO> {
    private final StressNetworkRepository networkRepo;

    public CreateNetworkUseCase(StressNetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
    }

    /**
     * Creates a new network from the supplied <code>networkCreationData</code> and saves it.
     *
     * @param networkCreationData The data for the new network.
     * @return StressNetworkDTO for the newly created network.
     */
    @Override
    public StressNetworkDTO execute(NetworkCreationData networkCreationData) {
        StressNetwork network = createNetwork(networkCreationData);
        networkRepo.save(network);
        return StressNetworkDTO.from(network);
    }

    /**
     * Creates a new stress network and assigns it a random UUID.
     *
     * @param data The data for the new stress network.
     * @return The newly created stress network.
     */
    private StressNetwork createNetwork(NetworkCreationData data) {
        String id = UUID.randomUUID().toString();
        return new StressNetworkImpl(id, data.getName(), new HashMap<>(), new HashMap<>());
    }
}
