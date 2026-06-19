package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.concurrent.Task;
import org.jellerijk.mccreatecalc.application.usecases.network.GetSelectedNetworkUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.concurrent.ExecutionException;

public class NetworkDetailsInteractor implements SelectedNetworkObserver {
    private final NetworkDetailsModel model;
    private final GetSelectedNetworkUseCase selectedNetworkFetcher;

    public NetworkDetailsInteractor(NetworkDetailsModel model, NetworkUseCaseFactory factory) {
        this.model = model;
        selectedNetworkFetcher = factory.buildGetSelectedNetworkUseCase();
        factory.buildObserveSelectedNetworkUseCase().execute(this);
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

    @Override
    public void onNetworkSelectionChanged(StressNetwork selectedNetwork) {
        mapStressNetworkToModel(selectedNetwork);
    }

    private void mapStressNetworkToModel(StressNetwork network) {
        model.networkNameProperty().set(network.name());
    }
}
