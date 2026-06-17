package org.jellerijk.mccreatecalc.presentation.interactor;

import javafx.concurrent.Task;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkRequest;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.presentation.NetworkUseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.model.NetworkListModel;

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
        networkFetcher.execute();
    }

    private void createModelBindings() {
        model.selectedNetworkProperty().addListener((_, _, networkInfo) -> System.out.println(networkInfo.name()));
    }

    public void createNetwork() {
        CreateNetworkRequest request = new CreateNetworkRequest(model.getUserInput());
        model.setUserInputEnabled(false);
        Task<Void> create = new Task<>() {
            @Override
            protected Void call() throws Exception {
                networkCreator.execute(request);
                Thread.sleep(2000);
                return null;
            }
        };
        create.run();
        create.setOnSucceeded((_) -> {
            fetchNetworks();
            model.setUserInput("");
            model.setUserInputEnabled(true);
        });

    }

}
