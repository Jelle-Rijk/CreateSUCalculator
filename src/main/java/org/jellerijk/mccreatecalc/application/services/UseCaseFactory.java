package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.usecases.ObserveComponentGroupUC;
import org.jellerijk.mccreatecalc.application.usecases.components.GetConsumersUseCase;
import org.jellerijk.mccreatecalc.application.usecases.components.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.ObserveSelectedNetworkUC;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddComponentGroupToSelectedNetworkUC;
import org.jellerijk.mccreatecalc.application.usecases.network.update.DeleteComponentGroupFromSelectedNetworkUseCase;

public class UseCaseFactory {
    private final ComponentGroupService componentGroupService;
    private final ComponentService componentService;
    private final NetworkService networkService;
    public UseCaseFactory(NetworkService networkService, ComponentService componentService, ComponentGroupService componentGroupService) {
        this.networkService = networkService;
        this.componentService = componentService;
        this.componentGroupService = componentGroupService;
    }

    //===== Public methods =====
    public AddComponentGroupToSelectedNetworkUC buildAddComponentGroupUC() {
        return new AddComponentGroupToSelectedNetworkUC(networkService, componentService);
    }

    public CreateNetworkUseCase buildCreateNetworkUseCase() {
        return new CreateNetworkUseCase(networkService);
    }

    public DeleteComponentGroupFromSelectedNetworkUseCase buildDeleteComponentGroupFromSelectedNetworkUseCase() {
        return new DeleteComponentGroupFromSelectedNetworkUseCase(componentGroupService, networkService);
    }

    public FetchNetworksInfoUseCase buildFetchNetworksInfoUseCase() {
        return new FetchNetworksInfoUseCase(networkService);
    }

    public GetConsumersUseCase buildGetConsumersUC() {
        return new GetConsumersUseCase(componentService);
    }

    public GetGeneratorsUseCase buildGetGeneratorsUseCase() {
        return new GetGeneratorsUseCase(componentService);
    }

    public ObserveComponentGroupUC buildObserveComponentGroupUC() {
        return new ObserveComponentGroupUC(componentGroupService);
    }

    public ObserveSelectedNetworkUC buildObserveSelectedNetworkUC() {
        return new ObserveSelectedNetworkUC(networkService);
    }

    public SelectNetworkUseCase buildSelectNetworkUseCase() {
        return new SelectNetworkUseCase(networkService);
    }
}

