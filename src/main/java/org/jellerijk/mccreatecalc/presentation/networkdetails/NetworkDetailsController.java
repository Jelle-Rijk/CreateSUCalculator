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
        GeneratorSelectorController generatorSelector = new GeneratorSelectorController(factory, interactor::addComponentGroup, interactor::getGeneratorOptions);
        GeneratorSelectorController consumerSelector = new GeneratorSelectorController(factory, interactor::addComponentGroup, interactor::getConsumerOptions);
        viewBuilder = new NetworkDetailsViewBuilder(model, generatorSelector.getView(), consumerSelector.getView());
    }

    public Region getView() {
        return viewBuilder.build();
    }
}
