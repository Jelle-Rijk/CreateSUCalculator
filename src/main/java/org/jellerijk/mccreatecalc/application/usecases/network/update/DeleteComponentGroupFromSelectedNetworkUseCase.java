package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

public class DeleteComponentGroupFromSelectedNetworkUseCase implements VoidUseCase<String> {
    private final ComponentGroupService componentGroupService;
    private final NetworkService networkService;

    public DeleteComponentGroupFromSelectedNetworkUseCase(ComponentGroupService componentGroupService, NetworkService networkService) {
        this.componentGroupService = componentGroupService;
        this.networkService = networkService;
    }

    @Override
    public void execute(String groupId) {
        String networkId = getNetworkId(groupId);
        componentGroupService.delete(groupId);
        StressNetwork updatedNetwork = getNetwork(networkId);
        networkService.setSelectedNetwork(updatedNetwork);
    }

    private String getNetworkId(String groupId) {
        return componentGroupService.getNetworkIdForGroup(groupId).orElseThrow();
    }

    private StressNetwork getNetwork(String networkId) {
        return networkService.getById(networkId).orElseThrow();
    }
}
