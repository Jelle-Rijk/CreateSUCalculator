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

    /**
     * Updates the componentGroups for a network. Any component groups associated with the network, but not in the supplied list, will be deleted.
     *
     * @param networkId       The network to sync entries for.
     * @param componentGroups The component groups to sync.
     */
    void syncComponentGroups(String networkId, List<ComponentGroup> componentGroups);

    Optional<String> getNetworkId(String groupId);

    void update(String id, int amount);

    void updateConsumer(String id, int rpm);

    void updateWindmill(String id, int sails);
}
