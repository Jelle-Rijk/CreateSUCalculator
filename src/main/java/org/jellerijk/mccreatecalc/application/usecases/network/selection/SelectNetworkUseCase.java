package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.NetworkNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SelectNetworkUseCase implements VoidUseCase<String> {
    private final NetworkService networkService;
    private final List<SelectedNetworkObserver> observers;

    public SelectNetworkUseCase(NetworkService networkService) {
        this.networkService = networkService;
        this.observers = new ArrayList<>();
    }

    /**
     * Selects a network.
     *
     * @param networkId The network to be selected.
     */
    @Override
    public void execute(String networkId) {
        StressNetwork network = networkService.getById(networkId)
                .orElseThrow(() -> new NetworkNotFoundException(networkId));
        networkService.setSelectedNetwork(network);
        publish(network);
    }

    private void publish(StressNetwork network) {
        observers.forEach(o -> o.onNetworkSelectionChanged(network));
    }

    public void subscribe(SelectedNetworkObserver observer) {
        observers.add(observer);
    }
}
