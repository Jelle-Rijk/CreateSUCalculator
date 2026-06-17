package org.jellerijk.mccreatecalc.presentation.controllers;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.presentation.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.interactor.NetworkListInteractor;
import org.jellerijk.mccreatecalc.presentation.model.NetworkListModel;
import org.jellerijk.mccreatecalc.presentation.views.NetworkListViewBuilder;

public class NetworkListController {
    private final NetworkListViewBuilder viewBuilder;

    public NetworkListController(NetworkUseCaseFactory factory) {
        NetworkListModel model = new NetworkListModel();
        NetworkListInteractor interactor = new NetworkListInteractor(model, factory);
        viewBuilder = new NetworkListViewBuilder(model, interactor::createNetwork);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
