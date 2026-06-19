package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.presentation.NetworkUseCaseFactory;

public class NetworkUseCaseFactoryImpl implements NetworkUseCaseFactory {
    private final NetworkRepository networkRepo;
    private final SelectedNetworkData selectedData;

    public NetworkUseCaseFactoryImpl(NetworkRepository networkRepo, SelectedNetworkData selectedData) {
        this.networkRepo = networkRepo;
        this.selectedData = selectedData;
    }

//===== Public methods =====
    public CreateNetworkUseCase buildCreateNetworkUseCase() {
        return new CreateNetworkUseCase(networkRepo);
    }

    public FetchNetworksInfoUseCase buildFetchNetworksInfoUseCase() {
        return new FetchNetworksInfoUseCase(networkRepo);
    }

    public GetSelectedNetworkUseCase buildGetSelectedNetworkUseCase() {
        return new GetSelectedNetworkUseCase(selectedData);
    }

    public SelectNetworkUseCase buildSelectNetworkUseCase() {
        return new SelectNetworkUseCase(networkRepo, selectedData);
    }
}

