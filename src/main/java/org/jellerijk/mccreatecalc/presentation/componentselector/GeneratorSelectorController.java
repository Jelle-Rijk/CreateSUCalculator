package org.jellerijk.mccreatecalc.presentation.componentselector;


import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;


public class GeneratorSelectorController {
    private final GeneratorSelectorInteractor interactor;
    private final GeneratorSelectorViewBuilder viewBuilder;

    public GeneratorSelectorController(
            UseCaseFactory factory) {
        GeneratorSelectorModel model = new GeneratorSelectorModel();
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
                interactor.addGenerator();
                return null;
            }
        };
        Thread addGeneratorThread = new Thread(addGenerator);
        addGeneratorThread.start();
    }


    public Node getView() {
        return viewBuilder.build();
    }
}
