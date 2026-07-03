package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;

import java.util.function.Consumer;

public class GeneratorSelectorInteractor {
    private final Consumer<ComponentOption> addGeneratorGroup;
    private final GeneratorSelectorModel model;
    private final GetGeneratorsUseCase generatorOptionFetcher;

    public GeneratorSelectorInteractor(GeneratorSelectorModel model, UseCaseFactory factory, Consumer<ComponentOption> addGeneratorGroup) {
        this.model = model;
        generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        this.addGeneratorGroup = addGeneratorGroup;
        bindModelProperties();
    }

    public void addGenerator() {
        addGeneratorGroup.accept(model.getSelectedComponentOption());
    }

    public void loadGeneratorSelectorOptions() {
        model.setComponentOptions(generatorOptionFetcher.execute());
    }

    private void bindModelProperties() {
        model.selectedProperty().bind(model.selectedComponentOptionProperty().isNotNull());
    }
}
