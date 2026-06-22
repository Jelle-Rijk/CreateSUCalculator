package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.GetSelectedNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddGeneratorToNetworkUseCase;

public class UseCaseFactory {
    private final NetworkRepository networkRepo;
    private final NetworkService networkService;
    private final GeneratorService generatorService;
    private final SelectedNetworkData selectedData;
    private final SelectedNetworkPublisher selectedNetworkPublisher;

    public UseCaseFactory(NetworkRepository networkRepo, GeneratorRepository generatorRepo, SelectedNetworkData selectedData, SelectedNetworkPublisher selectedNetworkPublisher) {
        this.networkService = new NetworkServiceImpl(networkRepo, selectedData);
        this.generatorService = new GeneratorServiceImpl(generatorRepo);
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

    public AddGeneratorToNetworkUseCase buildAddGeneratorToNetworkUseCase() {
        return new AddGeneratorToNetworkUseCase(networkService, generatorService);
    }

    public GetGeneratorsUseCase buildGetGeneratorsUseCase() {
        return new GetGeneratorsUseCase(generatorService);
    }
}

