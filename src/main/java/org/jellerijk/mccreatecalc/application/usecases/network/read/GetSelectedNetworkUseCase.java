package org.jellerijk.mccreatecalc.application.usecases.network.read;

import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

import java.util.Optional;

public class GetSelectedNetworkUseCase implements NoArgsUseCase<Optional<StressNetwork>> {
    private final NetworkService networkService;

    public GetSelectedNetworkUseCase(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * @return The selected stress network or null if no network is selected.
     */
    @Override
    public Optional<StressNetwork> execute() {
        return networkService.getSelectedNetwork();
    }
}
