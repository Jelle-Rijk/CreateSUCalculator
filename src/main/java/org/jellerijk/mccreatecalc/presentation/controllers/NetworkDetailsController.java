package org.jellerijk.mccreatecalc.presentation.controllers;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.presentation.interactor.NetworkDetailsInteractor;
import org.jellerijk.mccreatecalc.presentation.model.NetworkDetailsModel;
import org.jellerijk.mccreatecalc.presentation.views.NetworkDetailsViewBuilder;

public class NetworkDetailsController {
    private final NetworkDetailsInteractor interactor;
    private final NetworkDetailsModel model;
    private final NetworkDetailsViewBuilder viewBuilder;

    public NetworkDetailsController() {
        model = new NetworkDetailsModel();
        interactor = new NetworkDetailsInteractor(model);
        viewBuilder = new NetworkDetailsViewBuilder(model);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
