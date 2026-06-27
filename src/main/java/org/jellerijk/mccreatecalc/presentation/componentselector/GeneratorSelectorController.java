package org.jellerijk.mccreatecalc.presentation.componentselector;


import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;

import java.util.function.Consumer;


public class GeneratorSelectorController extends Controller {
    private final GeneratorSelectorInteractor interactor;
    private final GeneratorSelectorViewBuilder viewBuilder;
    private final GeneratorSelectorModel model = new GeneratorSelectorModel();

    public GeneratorSelectorController(
            UseCaseFactory factory, Consumer<GeneratorOption> addGeneratorGroupFunction) {
        interactor = new GeneratorSelectorInteractor(model, factory, addGeneratorGroupFunction);
        viewBuilder = new GeneratorSelectorViewBuilder(model, this::addGenerator);
        loadGeneratorOptions();
    }

    private void loadGeneratorOptions() {
        Task<Void> loadOptions = new Task<>() {
            @Override
            protected Void call() {
                interactor.loadGeneratorSelectorOptions();
                return null;
            }
        };
        startTaskOnNewThread(loadOptions);
    }

    private void addGenerator() {
        Task<Void> addGenerator = new Task<>() {
            @Override
            protected Void call() {
                model.setAddingDisabled(true);
                interactor.addGenerator();
                return null;
            }
        };
        addGenerator.setOnSucceeded(_ -> model.setAddingDisabled(false));
        startTaskOnNewThread(addGenerator);
    }


    public Node getView() {
        return viewBuilder.build();
    }
}
