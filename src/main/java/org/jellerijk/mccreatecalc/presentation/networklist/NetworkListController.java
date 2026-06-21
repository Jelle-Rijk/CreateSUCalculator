package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;

public class NetworkListController {
    private final NetworkListViewBuilder viewBuilder;

    public NetworkListController(UseCaseFactory factory) {
        NetworkListModel model = new NetworkListModel();
        NetworkListInteractor interactor = new NetworkListInteractor(model, factory);
        viewBuilder = new NetworkListViewBuilder(model, interactor::createNetwork, interactor::selectNetwork);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
