package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.network.*;

public class NetworkUseCaseFactory {
    private final NetworkRepository networkRepo;
    private final SelectedNetworkData selectedData;
    private final SelectedNetworkPublisher selectedNetworkPublisher;

    public NetworkUseCaseFactory(NetworkRepository networkRepo, SelectedNetworkData selectedData, SelectedNetworkPublisher selectedNetworkPublisher) {
        this.networkRepo = networkRepo;
        this.selectedData = selectedData;
        this.selectedNetworkPublisher = selectedNetworkPublisher;
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
        return new SelectNetworkUseCase(networkRepo, selectedData, selectedNetworkPublisher);
    }

    public ObserveSelectedNetworkUseCase buildObserveSelectedNetworkUseCase() {
        return new ObserveSelectedNetworkUseCase(selectedNetworkPublisher);
    }
}

