package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.List;
import java.util.Optional;

public interface ComponentGroupDAO {
    /**
     * @param networkId The network to look up component groups for.
     * @return A list of component groups in the network.
     */
    List<ComponentGroup> getGroupsForNetwork(String networkId);

    Optional<ComponentGroup> get(String groupId);

    void deleteGroup(String groupId);
}
