package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;

public class ObserveComponentGroupUC implements VoidUseCase<Subscription<ComponentGroupDTO, String>> {
    private final ComponentGroupService cgService;

    public ObserveComponentGroupUC(ComponentGroupService cgService) {
        this.cgService = cgService;
    }

    @Override
    public void execute(Subscription<ComponentGroupDTO, String> subscription) {
        cgService.subscribe(subscription);
        ComponentGroupDTO observedCG = ComponentGroupDTO.map(cgService.getById(subscription.getEventIdentifier())
                .orElseThrow());
        subscription.getObserver().update(observedCG);
    }
}
