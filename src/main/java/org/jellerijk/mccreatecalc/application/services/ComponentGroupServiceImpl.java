package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupDTOPublisher;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.usecases.network.update.UpdateComponentGroupRequest;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public class ComponentGroupServiceImpl implements ComponentGroupService {
    private final ComponentGroupRepository cgRepo;
    private final ComponentGroupDTOPublisher publisher;

    public ComponentGroupServiceImpl(ComponentGroupRepository cgRepo, ComponentGroupDTOPublisher publisher) {
        this.cgRepo = cgRepo;
        this.publisher = publisher;
    }

    public Optional<ComponentGroup> getById(String groupId) {
        return cgRepo.getById(groupId);
    }

    @Override
    public void subscribe(Subscription<ComponentGroupDTO, String> subscription) {
        publisher.subscribe(subscription);
    }

    @Override
    public Optional<String> getNetworkIdForGroup(String groupId) {
        return cgRepo.getNetworkId(groupId);
    }

    @Override
    public void delete(String groupId) {
        cgRepo.delete(groupId);
    }

    @Override
    public void update(UpdateComponentGroupRequest request) {
        cgRepo.update(request);
    }
}
