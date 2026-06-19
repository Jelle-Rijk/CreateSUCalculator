package org.jellerijk.mccreatecalc.presentation.interactor;

import org.jellerijk.mccreatecalc.application.usecases.network.GetSelectedNetworkUseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.presentation.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.model.NetworkDetailsModel;

public class NetworkDetailsInteractor {
    private final NetworkDetailsModel model;
    private final GetSelectedNetworkUseCase selectedNetworkFetcher;

    public NetworkDetailsInteractor(NetworkDetailsModel model, NetworkUseCaseFactory factory) {
        this.model = model;
        selectedNetworkFetcher = factory.buildGetSelectedNetworkUseCase();
    }

    public void fetchNetwork() {
        StressNetwork network = selectedNetworkFetcher.execute();
    }
}
