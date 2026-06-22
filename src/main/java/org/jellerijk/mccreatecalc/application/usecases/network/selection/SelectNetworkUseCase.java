package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.gateways.NetworkRepository;
import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.services.SelectedNetworkPublisher;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.NetworkNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SelectNetworkUseCase implements VoidUseCase<String> {
    private final SelectedNetworkData data;
    private final NetworkRepository networkRepo;
    private final List<SelectedNetworkObserver> observers;

    public SelectNetworkUseCase(NetworkRepository networkRepo, SelectedNetworkData selectedData, SelectedNetworkPublisher publisher) {
        this.networkRepo = networkRepo;
        this.data = selectedData;
        this.observers = new ArrayList<>();
    }

    /**
     * Selects a network.
     *
     * @param networkId The network to be selected.
     */
    @Override
    public void execute(String networkId) {
        StressNetwork network = networkRepo.getById(networkId)
                .orElseThrow(() -> new NetworkNotFoundException(networkId));
        data.write(network);
        publish(network);
    }

    private void publish(StressNetwork network) {
        observers.forEach(o -> o.onNetworkSelectionChanged(network));
    }

    public void subscribe(SelectedNetworkObserver observer) {
        observers.add(observer);
    }
}
