package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;

public class GeneratorSelectorInteractor {
    private final GeneratorSelectorModel model;
    private final GetGeneratorsUseCase generatorOptionFetcher;

    public GeneratorSelectorInteractor(GeneratorSelectorModel model, UseCaseFactory factory) {
        this.model = model;
        this.generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        bindModelProperties();
    }

    public void addGenerator() {
        System.out.println("Adding generator");
        try {
        Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Generator added.");

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
