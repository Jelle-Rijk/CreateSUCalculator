package org.jellerijk.mccreatecalc.application.usecases.network.read;

import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public class GetSelectedNetworkUseCase implements NoArgsUseCase<Optional<StressNetwork>> {
    private final SelectedNetworkData data;

    public GetSelectedNetworkUseCase(SelectedNetworkData data) {
        this.data = data;
    }

    /**
     * @return The selected stress network or null if no network is selected.
     */
    @Override
    public Optional<StressNetwork> execute() {
        return data.read();
    }
}
