package org.jellerijk.mccreatecalc.presentation.networklist;

import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.application.usecases.network.CreateNetworkUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.DeleteNetworkUC;
import org.jellerijk.mccreatecalc.application.usecases.network.read.FetchNetworksInfoUseCase;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectNetworkUseCase;

import java.util.List;

public class NetworkListInteractor {
    private final NetworkListModel model;
    private final CreateNetworkUseCase networkCreator;
    private final FetchNetworksInfoUseCase networkFetcher;
    private final SelectNetworkUseCase networkSelector;
    private final DeleteNetworkUC networkDeleter;

    public NetworkListInteractor(NetworkListModel model, UseCaseFactory factory) {
        this.model = model;
        this.networkFetcher = factory.buildFetchNetworksInfoUseCase();
        this.networkCreator = factory.buildCreateNetworkUseCase();
        this.networkDeleter = factory.buildDeleteNetworkUC();
        networkSelector = factory.buildSelectNetworkUseCase();
        fetchNetworks();
    }

    public void deleteNetwork(String networkId) {
        networkDeleter.execute(networkId);
        model.setNetworks(model.getNetworks().stream().filter(info -> !info.id().equals(networkId)).toList());
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
        String id = networkCreator.execute(name);
        model.setUserInput("");
        model.setUserInputEnabled(true);
        selectNetwork(id);
    }

    public void selectNetwork(String networkId) {
        networkSelector.execute(networkId);
    }

}
