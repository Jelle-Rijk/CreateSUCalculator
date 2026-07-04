package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.NetworkService;

public class DeleteNetworkUC implements VoidUseCase<String> {

    private final NetworkService networkService;

    public DeleteNetworkUC(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * Deletes the network with the provided <code>networkId.</code>
     *
     * @param networkId The network to delete.
     */
    @Override
    public void execute(String networkId) {
        validateId(networkId);
        networkService.delete(networkId);
    }

    private void validateId(String networkId) {
        if (networkId == null || networkId.isBlank())
            throw new IllegalArgumentException();
    }
}
