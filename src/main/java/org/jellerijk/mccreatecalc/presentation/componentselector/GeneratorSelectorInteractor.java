package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddGeneratorToSelectedNetworkRequest;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddGeneratorToSelectedNetworkUseCase;

public class GeneratorSelectorInteractor {
    private final GeneratorSelectorModel model;
    private final GetGeneratorsUseCase generatorOptionFetcher;
    private final AddGeneratorToSelectedNetworkUseCase addGeneratorUC;


    public GeneratorSelectorInteractor(GeneratorSelectorModel model, UseCaseFactory factory) {
        this.model = model;
        generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        addGeneratorUC = factory.buildAddGeneratorToNetworkUseCase();
        bindModelProperties();
    }

    public void addGenerator() {
        AddGeneratorToSelectedNetworkRequest request = new AddGeneratorToSelectedNetworkRequest(model.getSelectedGeneratorOption()
                .name(), model.getAmount());
        addGeneratorUC.execute(request);

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
