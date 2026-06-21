package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.ArrayList;
import java.util.List;

public class SelectedNetworkPublisher {
    private final List<SelectedNetworkObserver> observers;

    public SelectedNetworkPublisher() {
        observers = new ArrayList<>();
    }

    public void subscribe(SelectedNetworkObserver observer) {
        observers.add(observer);
    }

    public void publish(StressNetwork selectedNetwork) {
        observers.forEach(o -> o.onNetworkSelectionChanged(selectedNetwork));
    }
}
