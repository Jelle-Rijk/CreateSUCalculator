package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;

public class UpdateComponentGroupUC implements VoidUseCase<UpdateComponentGroupRequest> {

    private final ComponentGroupService componentGroupService;

    public UpdateComponentGroupUC(ComponentGroupService componentGroupService) {
        this.componentGroupService = componentGroupService;
    }

    /**
     * @param request The request containing the group's id and its data fields to update.
     */
    @Override
    public void execute(UpdateComponentGroupRequest request) {
        validateRequest(request);
        componentGroupService.update(request);
    }

    private void validateRequest(UpdateComponentGroupRequest request) {
        if (request.groupId() == null)
            throw new IllegalArgumentException("Request did not have a group id.");
        if (request.amount() == null && request.rpm() == null && request.sails() == null)
            throw new IllegalArgumentException("Request did not contain any fields to set");
    }
}
