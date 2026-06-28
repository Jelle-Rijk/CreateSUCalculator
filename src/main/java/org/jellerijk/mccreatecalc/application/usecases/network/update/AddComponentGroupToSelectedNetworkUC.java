package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;

// TODO: implement
// This use case should add a new component group to the selected network. Before we can do this, we need to know which component this group will represent. The amount, level, sails, rpm, etc. can all be set to their defaults.
public class AddComponentGroupToSelectedNetworkUC implements UseCase<AddComponentGroupRequest, ComponentGroupDTO> {

    public AddComponentGroupToSelectedNetworkUC(NetworkService networkService) {

    }

    @Override
    public ComponentGroupDTO execute(AddComponentGroupRequest addComponentGroupRequest) {
        return null;
    }
}
