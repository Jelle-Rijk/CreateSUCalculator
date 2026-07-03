package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.components.GetGeneratorsUseCase;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GeneratorSelectorInteractor {
    private final Consumer<ComponentOption> addGeneratorGroup;
    private final GeneratorSelectorModel model;
    private final GetGeneratorsUseCase generatorOptionFetcher;
    private final Supplier<List<ComponentOption>> optionsSupplier;

    public GeneratorSelectorInteractor(GeneratorSelectorModel model, UseCaseFactory factory, Consumer<ComponentOption> addGeneratorGroup, Supplier<List<ComponentOption>> optionsSupplier) {
        this.model = model;
        generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        this.addGeneratorGroup = addGeneratorGroup;
        this.optionsSupplier = optionsSupplier;
        bindModelProperties();
    }

    public void addGenerator() {
        addGeneratorGroup.accept(model.getSelectedComponentOption());
    }

    public void loadComponentOptions() {
        model.setComponentOptions(optionsSupplier.get());
    }

    private void bindModelProperties() {
        model.selectedProperty().bind(model.selectedComponentOptionProperty().isNotNull());
    }
}
