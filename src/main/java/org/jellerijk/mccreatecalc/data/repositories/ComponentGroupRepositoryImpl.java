package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public class ComponentGroupRepositoryImpl implements ComponentGroupRepository {
    private final ComponentGroupDAO cgDAO;

    public ComponentGroupRepositoryImpl(ComponentGroupDAO cgDAO) {
        this.cgDAO = cgDAO;
    }

    @Override
    public Optional<ComponentGroup> getById(String id) {
        return cgDAO.get(id);
    }

    @Override
    public Optional<String> getNetworkId(String groupId) {
        return cgDAO.getNetworkId(groupId);
    }

    @Override
    public void delete(String groupId) {
        cgDAO.deleteGroup(groupId);
    }
}
