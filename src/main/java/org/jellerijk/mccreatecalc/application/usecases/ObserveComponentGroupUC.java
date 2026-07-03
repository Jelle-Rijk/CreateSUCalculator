package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

// TODO: implement use case
public class ObserveComponentGroupUC implements UseCase<Subscription<ComponentGroup, String>, ComponentGroup> {
    private final ComponentGroupService cgService;

    public ObserveComponentGroupUC(ComponentGroupService cgService) {
        this.cgService = cgService;
    }

    @Override
    public ComponentGroup execute(Subscription<ComponentGroup, String> subscription) {
        cgService.subscribe(subscription);
        return cgService.getById(subscription.getEventIdentifier()).orElseThrow();
    }
}
