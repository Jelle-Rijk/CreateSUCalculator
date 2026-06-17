package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;

import java.util.List;

public class FetchNetworksInfoUseCase implements NoArgsUseCase<List<NetworkInfo>> {
    private final NetworkRepository networkRepo;

    public FetchNetworksInfoUseCase(NetworkRepository networkRepo) {
        this.networkRepo = networkRepo;
    }

    @Override
    public List<NetworkInfo> execute() {
        return networkRepo.getInfoForAllNetworks();
    }
}
