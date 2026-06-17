package org.jellerijk.mccreatecalc.presentation.controllers;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.presentation.interactor.NetworkListInteractor;
import org.jellerijk.mccreatecalc.presentation.model.NetworkListModel;
import org.jellerijk.mccreatecalc.presentation.views.NetworkListViewBuilder;

public class NetworkListController {
    private final NetworkListInteractor interactor;
    private final NetworkListModel model;
    private final NetworkListViewBuilder viewBuilder;

    public NetworkListController(FetchNetworksInfoUseCase fetchNetworksInfoUseCase) {
        model = new NetworkListModel();
        interactor = new NetworkListInteractor(model, fetchNetworksInfoUseCase);
        viewBuilder = new NetworkListViewBuilder(model);
        interactor.fetchNetworks();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
