package org.jellerijk.mccreatecalc.presentation.interactor;

import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.presentation.model.NetworkListModel;

public class NetworkListInteractor {
    private final NetworkListModel model;
    private final FetchNetworksInfoUseCase networkFetcher;

    public NetworkListInteractor(NetworkListModel model, FetchNetworksInfoUseCase networkFetcher) {
        this.model = model;
        this.networkFetcher = networkFetcher;
        createModelBindings();
    }

    public void fetchNetworks() {
        model.setNetworks(networkFetcher.execute());
    }

    private void createModelBindings() {
        model.selectedNetworkProperty().addListener((_, _, networkInfo) -> System.out.println(networkInfo.name()));
    }

}
