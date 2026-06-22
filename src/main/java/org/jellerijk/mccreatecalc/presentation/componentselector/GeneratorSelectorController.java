package org.jellerijk.mccreatecalc.presentation.componentselector;


import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;


public class GeneratorSelectorController {
    private final GeneratorSelectorInteractor interactor;
    private final GeneratorSelectorViewBuilder viewBuilder;
    private final GeneratorSelectorModel model = new GeneratorSelectorModel();

    public GeneratorSelectorController(
            UseCaseFactory factory) {
        interactor = new GeneratorSelectorInteractor(model, factory);
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
        Thread optionLoadingThread = new Thread(loadOptions);
        optionLoadingThread.start();
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
        Thread addGeneratorThread = new Thread(addGenerator);
        addGeneratorThread.start();
    }


    public Node getView() {
        return viewBuilder.build();
    }
}
