package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupPublisher;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

import java.util.Optional;

public class ComponentGroupServiceImpl implements ComponentGroupService {
    private final ComponentGroupRepository cgRepo;
    private final ComponentGroupPublisher publisher;

    public ComponentGroupServiceImpl(ComponentGroupRepository cgRepo, ComponentGroupPublisher publisher) {
        this.cgRepo = cgRepo;
        this.publisher = publisher;
    }

    public Optional<ComponentGroup> getById(String groupId) {
        return cgRepo.getById(groupId);
    }

    @Override
    public void subscribe(Subscription<ComponentGroup, String> subscription) {
        publisher.subscribe(subscription);
    }
}
