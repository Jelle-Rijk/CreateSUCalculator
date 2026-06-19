package org.jellerijk.mccreatecalc.data.local;

import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public class SelectedNetwork implements SelectedNetworkData {
    private StressNetwork selected;

    @Override
    public void write(StressNetwork network) {
        selected = network;
    }

    @Override
    public Optional<StressNetwork> read() {
        return Optional.ofNullable(selected);
    }
}
