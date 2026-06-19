package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.concurrent.Task;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkRequest;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkUseCaseFactory;

public class NetworkListInteractor {
    private final NetworkListModel model;
    private final CreateNetworkUseCase networkCreator;
    private final FetchNetworksInfoUseCase networkFetcher;

    public NetworkListInteractor(NetworkListModel model, NetworkUseCaseFactory factory) {
        this.model = model;
        this.networkFetcher = factory.buildFetchNetworksInfoUseCase();
        this.networkCreator = factory.buildCreateNetworkUseCase();
        createModelBindings();
        fetchNetworks();
    }

    public void fetchNetworks() {
        Task<Void> fetchTask = new Task<>() {
            @Override
            protected Void call() {
                model.setNetworks(networkFetcher.execute());
                return null;
            }
        };
        fetchTask.run();
    }

    private void createModelBindings() {
        model.selectedNetworkProperty().addListener((_, _, networkInfo) -> System.out.println(networkInfo.name()));
    }

    public void createNetwork() {
        CreateNetworkRequest request = new CreateNetworkRequest(model.getUserInput());
        System.out.println("Handler called");
        model.setUserInputEnabled(false);
        Task<Void> create = new Task<>() {
            @Override
            protected Void call() {
                networkCreator.execute(request);
                return null;
            }
        };
        create.setOnSucceeded((_) -> {
            fetchNetworks();
            model.setUserInput("");
            model.setUserInputEnabled(true);
        });
        create.run();

    }

}
