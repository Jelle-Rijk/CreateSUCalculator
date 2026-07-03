package org.jellerijk.mccreatecalc.presentation.componentselector;


import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.presentation.Controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class ComponentSelectorController extends Controller {
    private final ComponentSelectorInteractor interactor;
    private final ComponentSelectorViewBuilder viewBuilder;
    private final ComponentSelectorModel model = new ComponentSelectorModel();

    public ComponentSelectorController(
            Consumer<ComponentOption> addGeneratorGroupFunction, Supplier<List<ComponentOption>> optionsSupplier) {
        interactor = new ComponentSelectorInteractor(model, addGeneratorGroupFunction, optionsSupplier);
        viewBuilder = new ComponentSelectorViewBuilder(model, this::addGenerator);
        loadGeneratorOptions();
    }

    private void loadGeneratorOptions() {
        Task<Void> loadOptions = new Task<>() {
            @Override
            protected Void call() {
                interactor.loadComponentOptions();
                return null;
            }
        };
        startTaskOnNewThread(loadOptions);
    }

    private void addGenerator() {
        Task<Void> addGenerator = new Task<>() {
            @Override
            protected Void call() {
                interactor.addGenerator();
                return null;
            }
        };
        addGenerator.setOnSucceeded(_ -> model.setAddingDisabled(false));
        model.setAddingDisabled(true);
        startTaskOnNewThread(addGenerator);
    }


    public Node getView() {
        return viewBuilder.build();
    }
}
