package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.usecases.GetComponentGroupUC;
import org.jellerijk.mccreatecalc.application.usecases.ObserveComponentGroupUC;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.ObserveSelectedNetworkUC;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;

public class UseCaseFactory {
    private final NetworkService networkService;
    private final ComponentService componentService;

    public UseCaseFactory(NetworkService networkService, ComponentService componentService) {
        this.networkService = networkService;
        this.componentService = componentService;

    }

    //===== Public methods =====
    public CreateNetworkUseCase buildCreateNetworkUseCase() {
        return new CreateNetworkUseCase(networkService);
    }

    public FetchNetworksInfoUseCase buildFetchNetworksInfoUseCase() {
        return new FetchNetworksInfoUseCase(networkService);
    }

    public SelectNetworkUseCase buildSelectNetworkUseCase() {
        return new SelectNetworkUseCase(networkService);
    }

    public GetGeneratorsUseCase buildGetGeneratorsUseCase() {
        return new GetGeneratorsUseCase(componentService);
    }

    public ObserveSelectedNetworkUC buildObserveSelectedNetworkUC() {
        return new ObserveSelectedNetworkUC(networkService);
    }

    public GetComponentGroupUC buildGetComponentGroupUC() {
        return new GetComponentGroupUC();
    }

    public ObserveComponentGroupUC buildObserveComponentGroupUC() {
        return new ObserveComponentGroupUC();
    }
}

