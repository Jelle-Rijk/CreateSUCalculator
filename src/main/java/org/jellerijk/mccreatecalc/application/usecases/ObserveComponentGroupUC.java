package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;

public class ObserveComponentGroupUC implements VoidUseCase<Subscription<ComponentGroup, String>> {
    private final ComponentGroupService cgService;

    public ObserveComponentGroupUC(ComponentGroupService cgService) {
        this.cgService = cgService;
    }

    @Override
    public void execute(Subscription<ComponentGroup, String> subscription) {
        cgService.subscribe(subscription);
        subscription.getObserver().update(cgService.getById(subscription.getEventIdentifier()).orElseThrow());
    }
}
