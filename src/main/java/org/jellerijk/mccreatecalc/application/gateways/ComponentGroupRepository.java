package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public interface ComponentGroupRepository {
    Optional<ComponentGroup> getById(String id);
}
