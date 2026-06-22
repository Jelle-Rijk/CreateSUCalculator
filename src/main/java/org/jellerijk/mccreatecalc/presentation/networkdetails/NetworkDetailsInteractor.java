package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.concurrent.Task;
import org.jellerijk.mccreatecalc.application.usecases.generator.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.GetSelectedNetworkUseCase;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NetworkDetailsInteractor implements SelectedNetworkObserver {
    private final NetworkDetailsModel model;
    private final GetSelectedNetworkUseCase selectedNetworkFetcher;
    private final GetGeneratorsUseCase generatorOptionFetcher;

    public NetworkDetailsInteractor(NetworkDetailsModel model, UseCaseFactory factory) {
        this.model = model;
        selectedNetworkFetcher = factory.buildGetSelectedNetworkUseCase();
        generatorOptionFetcher = factory.buildGetGeneratorsUseCase();
        factory.buildObserveSelectedNetworkUseCase().execute(this);
        loadGeneratorSelectorOptions();
    }

    public void fetchNetwork() {
        Task<StressNetwork> fetchTask = new Task<>() {
            @Override
            protected StressNetwork call() {
                return selectedNetworkFetcher.execute();
            }
        };
        try {
            mapStressNetworkToModel(fetchTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Task could not be fetched.", e);
        }
    }

    public void addGenerator() {
        System.out.println("Adding generator");
    }

    public void loadGeneratorSelectorOptions() {
        List<GeneratorOption> generatorOptions = generatorOptionFetcher.execute()
                .stream()
                .map(GeneratorOption::map)
                .toList();
        model.setGeneratorOptions(generatorOptions);
    }

    @Override
    public void onNetworkSelectionChanged(StressNetwork selectedNetwork) {
        mapStressNetworkToModel(selectedNetwork);
    }

    private void mapStressNetworkToModel(StressNetwork network) {
        model.networkNameProperty().set(network.name());
        model.suConsumedProperty().set(network.calculateSUConsumed());
        model.suProducedProperty().set(network.calculateSUProduced());
        model.suBalanceProperty().set(network.calculateSUBalance());
    }
}
