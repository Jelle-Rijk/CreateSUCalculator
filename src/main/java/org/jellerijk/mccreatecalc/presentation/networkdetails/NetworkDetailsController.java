package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.scene.layout.Region;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;
import org.jellerijk.mccreatecalc.presentation.componentselector.GeneratorSelectorController;

public class NetworkDetailsController extends Controller {
    private final NetworkDetailsViewBuilder viewBuilder;

    public NetworkDetailsController(UseCaseFactory factory) {
        NetworkDetailsModel model = new NetworkDetailsModel();
        NetworkDetailsInteractor interactor = new NetworkDetailsInteractor(model, factory);
        viewBuilder = new NetworkDetailsViewBuilder(model, new GeneratorSelectorController(factory,
                interactor::addGeneratorGroup,
                interactor::getGeneratorOptions).getView());
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
