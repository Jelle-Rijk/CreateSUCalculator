package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddGeneratorToSelectedNetworkUseCase;

public class UseCaseFactory {
    private final NetworkService networkService;
    private final GeneratorService generatorService;

    public UseCaseFactory(NetworkService networkService, GeneratorService generatorService) {
        this.networkService = networkService;
        this.generatorService = generatorService;

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

    public AddGeneratorToSelectedNetworkUseCase buildAddGeneratorToNetworkUseCase() {
        return new AddGeneratorToSelectedNetworkUseCase(networkService, generatorService);
    }

    public GetGeneratorsUseCase buildGetGeneratorsUseCase() {
        return new GetGeneratorsUseCase(generatorService);
    }
}

