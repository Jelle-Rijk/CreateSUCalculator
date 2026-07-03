package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public interface ComponentGroupRepository {
    Optional<ComponentGroup> getById(String id);

    /**
     * @param groupId The group to look up the network for.
     * @return The network id associated with the <code>groupId</code> or an empty optional if the group does not exist.
     */
    Optional<String> getNetworkId(String groupId);

    void delete(String groupId);
}
