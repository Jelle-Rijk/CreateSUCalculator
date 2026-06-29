package org.jellerijk.mccreatecalc.presentation.networklist;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.usecases.network.creation.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;

import java.util.List;

public class NetworkListInteractor {
    private final NetworkListModel model;
    private final CreateNetworkUseCase networkCreator;
    private final FetchNetworksInfoUseCase networkFetcher;
    private final SelectNetworkUseCase networkSelector;

    public NetworkListInteractor(NetworkListModel model, UseCaseFactory factory) {
        this.model = model;
        this.networkFetcher = factory.buildFetchNetworksInfoUseCase();
        this.networkCreator = factory.buildCreateNetworkUseCase();
        networkSelector = factory.buildSelectNetworkUseCase();
        fetchNetworks();
    }

    public List<NetworkInfo> fetchNetworks() {
        return networkFetcher.execute();
    }

    public void updateNetworks(List<NetworkInfo> networks) {
        model.setNetworks(networks);
    }

    public void createNetwork() {
        String name = model.getUserInput();
        model.setUserInputEnabled(false);
        networkCreator.execute(name);
        model.setUserInput("");
        model.setUserInputEnabled(true);
    }

    public void selectNetwork(String networkId) {
        networkSelector.execute(networkId);
    }

}
