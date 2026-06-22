package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkService;

public class ObserveSelectedNetworkUC implements VoidUseCase<SelectedNetworkObserver> {
    private final NetworkService networkService;

    public ObserveSelectedNetworkUC(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void execute(SelectedNetworkObserver observer) {
        networkService.subscribeToSelectedNetwork(observer);
    }
}
