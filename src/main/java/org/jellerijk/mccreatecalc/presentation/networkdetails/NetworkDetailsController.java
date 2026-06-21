package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;

public class NetworkDetailsController {
    private final NetworkDetailsInteractor interactor;
    private final NetworkDetailsModel model;
    private final NetworkDetailsViewBuilder viewBuilder;

    public NetworkDetailsController(UseCaseFactory factory) {
        model = new NetworkDetailsModel();
        interactor = new NetworkDetailsInteractor(model, factory);
        viewBuilder = new NetworkDetailsViewBuilder(model);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
