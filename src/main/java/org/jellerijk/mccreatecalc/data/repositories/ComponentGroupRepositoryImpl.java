package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.update.UpdateComponentGroupRequest;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.Windmill;

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

    @Override
    public void update(UpdateComponentGroupRequest request) {
        ComponentGroup oldData = getById(request.groupId()).orElseThrow(() -> new IllegalArgumentException("The requested group to update does not exist."));
        if (request.amount() != null)
            cgDAO.update(request.groupId(), request.amount());
        ComponentType type = oldData.getComponentType();
        if (type == ComponentType.WINDMILL && request.sails() != null)
            cgDAO.updateWindmill(request.groupId(), request.sails());
        if (type == ComponentType.CONSUMER && request.rpm() != null)
            cgDAO.updateConsumer(request.groupId(), request.rpm());
    }
}
