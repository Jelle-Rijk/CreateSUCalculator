package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;

public class DeleteComponentGroupFromSelectedNetworkUseCase implements VoidUseCase<String> {
    private final ComponentGroupService componentGroupService;
    private final NetworkService networkService;

    public DeleteComponentGroupFromSelectedNetworkUseCase(ComponentGroupService componentGroupService, NetworkService networkService) {
        this.componentGroupService = componentGroupService;
        this.networkService = networkService;
    }

    @Override
    public void execute(String groupId) {
        componentGroupService.getNetworkIdForGroup(groupId);
        componentGroupService.delete(groupId);
        // verwijder de groep
        // updatet het netwerk
    }
}
