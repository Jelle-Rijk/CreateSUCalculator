package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public interface ComponentGroupService {
    Optional<ComponentGroup> getById(String groupId);
}
