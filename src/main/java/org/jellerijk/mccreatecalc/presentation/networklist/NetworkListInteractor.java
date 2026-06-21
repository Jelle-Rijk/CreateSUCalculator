package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.concurrent.Task;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkRequest;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;

public class NetworkListInteractor {
    private final NetworkListModel model;
    private final CreateNetworkUseCase networkCreator;
    private final FetchNetworksInfoUseCase networkFetcher;
    private final SelectNetworkUseCase networkSelector;

    public NetworkListInteractor(NetworkListModel model, NetworkUseCaseFactory factory) {
        this.model = model;
        this.networkFetcher = factory.buildFetchNetworksInfoUseCase();
        this.networkCreator = factory.buildCreateNetworkUseCase();
        networkSelector = factory.buildSelectNetworkUseCase();
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

    public void createNetwork() {
        CreateNetworkRequest request = new CreateNetworkRequest(model.getUserInput());
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

    public void selectNetwork(String networkId) {
        networkSelector.execute(networkId);
    }

}
