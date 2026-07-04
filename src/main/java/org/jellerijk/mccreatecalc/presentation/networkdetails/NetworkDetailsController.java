package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;
import org.jellerijk.mccreatecalc.presentation.componentselector.ComponentSelectorController;

public class NetworkDetailsController extends Controller {
    private final NetworkDetailsViewBuilder viewBuilder;

    public NetworkDetailsController(UseCaseFactory factory) {
        NetworkDetailsModel model = new NetworkDetailsModel();
        NetworkDetailsInteractor interactor = new NetworkDetailsInteractor(model, factory);
        ComponentSelectorController generatorSelector = new ComponentSelectorController(interactor::addComponentGroup, interactor::getGeneratorOptions);
        ComponentSelectorController consumerSelector = new ComponentSelectorController(interactor::addComponentGroup, interactor::getConsumerOptions);
        viewBuilder = new NetworkDetailsViewBuilder(model, generatorSelector.getView(), consumerSelector.getView(), factory);
    }

    public Node getView() {
        return viewBuilder.build();
    }
}
