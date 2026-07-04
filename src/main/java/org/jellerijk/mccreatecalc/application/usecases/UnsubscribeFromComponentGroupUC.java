package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;

public class UnsubscribeFromComponentGroupUC implements VoidUseCase<Subscription<ComponentGroupDTO, String>> {
    private final ComponentGroupService cgService;

    public UnsubscribeFromComponentGroupUC(ComponentGroupService cgService) {
        this.cgService = cgService;
    }

    /**
     * Ends the supplied <code>subscription</code>.
     *
     * @param subscription A subscription containing the observer and the groupId to unsubscribe from.
     */
    @Override
    public void execute(Subscription<ComponentGroupDTO, String> subscription) {
        validateRequest(subscription);
        cgService.unsubscribe(subscription);
    }

    private void validateRequest(Subscription<ComponentGroupDTO, String> subscription) {
        if (subscription.getObserver() == null)
            throw new IllegalArgumentException("Invalid observer.");
        if (subscription.getEventIdentifier() == null || subscription.getEventIdentifier().isBlank())
            throw new IllegalArgumentException("Invalid groupId");
    }
}
