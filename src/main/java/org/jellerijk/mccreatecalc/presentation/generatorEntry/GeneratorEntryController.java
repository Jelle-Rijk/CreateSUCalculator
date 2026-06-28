package org.jellerijk.mccreatecalc.presentation.generatorEntry;

import javafx.scene.Node;
import org.jellerijk.mccreatecalc.entities.GeneratorEntry;

public class GeneratorEntryController {
    private final GeneratorEntryInteractor interactor;
    private final GeneratorEntryViewBuilder viewBuilder;

    public GeneratorEntryController() {
        GeneratorEntryModel model = new GeneratorEntryModel();
        interactor = new GeneratorEntryInteractor(model);
        viewBuilder = new GeneratorEntryViewBuilder(model);
    }

    public void setEntry(GeneratorEntry entry) {
        interactor.setEntry(entry);
    }

    public Node getView() {
        return viewBuilder.getView();
    }
}
