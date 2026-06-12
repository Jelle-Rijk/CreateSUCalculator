package org.jellerijk.minecraft.services.usecases.viewNetwork;

import org.jellerijk.minecraft.dtos.StressNetworkDTO;
import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.services.repositories.StressNetworkRepository;
import org.jellerijk.minecraft.services.usecases.UseCase;

public class ViewNetworkUseCase implements UseCase<String, StressNetworkDTO> {
    private final StressNetworkRepository networkRepo;

    public ViewNetworkUseCase(StressNetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
    }

    @Override
    public StressNetworkDTO execute(String id) {
        StressNetwork network = networkRepo.load(id).orElseThrow(() -> new NetworkNotFoundException(id));
        return StressNetworkDTO.from(network);
    }
}
