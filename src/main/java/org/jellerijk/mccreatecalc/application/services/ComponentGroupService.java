package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public interface ComponentGroupService {
    Optional<ComponentGroup> getById(String groupId);

    void subscribe(Subscription<ComponentGroupDTO, String> subscription);

    /**
     * @param groupId The group to look up the network id for.
     * @return An optional containing the network's id.
     */
    Optional<String> getNetworkIdForGroup(String groupId);

    void delete(String groupId);
}
