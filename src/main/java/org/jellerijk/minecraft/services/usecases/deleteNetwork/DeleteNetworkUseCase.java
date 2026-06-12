package org.jellerijk.minecraft.services.usecases.deleteNetwork;

import org.jellerijk.minecraft.services.repositories.StressNetworkRepository;
import org.jellerijk.minecraft.services.usecases.UseCase;

public class DeleteNetworkUseCase implements UseCase<String, Void> {
    private final StressNetworkRepository networkRepo;

    public DeleteNetworkUseCase(StressNetworkRepository networkRepository) {
        this.networkRepo = networkRepository;
    }

    /**
     * Deletes the network with the supplied <code>networkId</code>.
     *
     * @param networkId The id of the network to delete.
     */
    @Override
    public Void execute(String networkId) {
        networkRepo.delete(networkId);
        return null;
    }
}
