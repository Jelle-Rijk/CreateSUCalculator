package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;

import java.util.function.Consumer;

public class GeneratorSelectorInteractor {
    private final Consumer<GeneratorOption> addGeneratorGroup;
    private final GeneratorSelectorModel model;
    private final GetGeneratorsUseCase generatorOptionFetcher;

    public GeneratorSelectorInteractor(GeneratorSelectorModel model, UseCaseFactory factory, Consumer<GeneratorOption> addGeneratorGroup) {
        this.model = model;
        generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        this.addGeneratorGroup = addGeneratorGroup;
        bindModelProperties();
    }

    public void addGenerator() {
        addGeneratorGroup.accept(model.getSelectedGeneratorOption());
    }

    public void loadGeneratorSelectorOptions() {
        model.setGeneratorOptions(generatorOptionFetcher.execute()
                .stream()
                .map(GeneratorOption::map)
                .toList());
    }

    private void bindModelProperties() {
        model.selectedProperty().bind(model.selectedGeneratorOptionProperty().isNotNull());
    }
}
