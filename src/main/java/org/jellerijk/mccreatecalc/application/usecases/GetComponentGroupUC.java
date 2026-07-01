package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;

public class GetComponentGroupUC implements UseCase<String, ComponentGroupDTO> {
    private final ComponentGroupRepository componentGroupRepo;

    public GetComponentGroupUC(ComponentGroupRepository componentGroupRepo) {
        this.componentGroupRepo = componentGroupRepo;
    }

    /**
     * @param id The id of the component group to fetch.
     * @return The fetched component group.
     */
    @Override
    public ComponentGroupDTO execute(String id) {
        return ComponentGroupDTO.map(componentGroupRepo.getById(id).orElseThrow());
    }
}
