package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

public class GetSelectedNetworkUseCase implements NoArgsUseCase<StressNetwork> {
    private final SelectedNetworkData data;

    public GetSelectedNetworkUseCase(SelectedNetworkData data) {
        this.data = data;
    }

    /**
     * @return The selected stress network or null if no network is selected.
     */
    @Override
    public StressNetwork execute() {
        return data.read().orElse(null);
    }
}
