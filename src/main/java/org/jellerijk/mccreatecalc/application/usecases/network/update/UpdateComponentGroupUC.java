package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;

public class UpdateComponentGroupUC implements VoidUseCase<UpdateComponentGroupRequest> {

    private final ComponentGroupService componentGroupService;
    private final NetworkService networkService;

    public UpdateComponentGroupUC(ComponentGroupService componentGroupService, NetworkService networkService) {
        this.componentGroupService = componentGroupService;
        this.networkService = networkService;
    }

    /**
     * @param request The request containing the group's id and its data fields to update.
     */
    @Override
    public void execute(UpdateComponentGroupRequest request) {
        validateRequest(request);
        componentGroupService.update(request);
        String groupId = componentGroupService.getNetworkIdForGroup(request.groupId()).orElseThrow();
        networkService.setSelectedNetwork(networkService.getById(groupId).orElseThrow());
    }

    private void validateRequest(UpdateComponentGroupRequest request) {
        if (request.groupId() == null)
            throw new IllegalArgumentException("Request did not have a group id.");
        if (request.amount() == null && request.rpm() == null && request.sails() == null)
            throw new IllegalArgumentException("Request did not contain any fields to set");
    }
}
