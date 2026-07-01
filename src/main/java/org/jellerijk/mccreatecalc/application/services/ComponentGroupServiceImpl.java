package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public class ComponentGroupServiceImpl implements ComponentGroupService {
    private final ComponentGroupRepository cgRepo;

    public ComponentGroupServiceImpl(ComponentGroupRepository cgRepo) {
        this.cgRepo = cgRepo;
    }

    public Optional<ComponentGroup> getById(String groupId) {
        return cgRepo.getById(groupId);
    }
}
