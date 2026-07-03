package org.jellerijk.mccreatecalc.presentation.componentselector;


import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class GeneratorSelectorController extends Controller {
    private final GeneratorSelectorInteractor interactor;
    private final GeneratorSelectorViewBuilder viewBuilder;
    private final GeneratorSelectorModel model = new GeneratorSelectorModel();

    public GeneratorSelectorController(
            UseCaseFactory factory, Consumer<ComponentOption> addGeneratorGroupFunction, Supplier<List<ComponentOption>> optionsSupplier) {
        interactor = new GeneratorSelectorInteractor(model, factory, addGeneratorGroupFunction, optionsSupplier);
        viewBuilder = new GeneratorSelectorViewBuilder(model, this::addGenerator);
        loadGeneratorOptions();
    }

    private void loadGeneratorOptions() {
//        Task<Void> loadOptions = new Task<>() {
//            @Override
//            protected Void call() {
//                interactor.loadComponentOptions();
//                return null;
//            }
//        };
//        startTaskOnNewThread(loadOptions);
        interactor.loadComponentOptions();
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
