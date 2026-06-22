package org.jellerijk.mccreatecalc.application.usecases.network.read;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;

import java.util.List;

public class FetchNetworksInfoUseCase implements NoArgsUseCase<List<NetworkInfo>> {
    private final NetworkRepository networkRepo;

    public FetchNetworksInfoUseCase(NetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
    }

    /**
     * @return A list containing the ID and Name of every stress network in the repository.
     */
    @Override
    public List<NetworkInfo> execute() {
        return networkRepo.getInfoForAllNetworks();
    }
}
