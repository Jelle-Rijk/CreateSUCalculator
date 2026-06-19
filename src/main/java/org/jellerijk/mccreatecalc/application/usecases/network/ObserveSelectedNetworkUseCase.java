package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.VoidUseCase;
import org.jellerijk.mccreatecalc.application.services.SelectedNetworkPublisher;

public class ObserveSelectedNetworkUseCase implements VoidUseCase<SelectedNetworkObserver> {
    private final SelectedNetworkPublisher publisher;

    public ObserveSelectedNetworkUseCase(SelectedNetworkPublisher publisher) {
        this.publisher = publisher;
    }
    @Override
    public void execute(SelectedNetworkObserver selectedNetworkObserver) {
        publisher.subscribe(selectedNetworkObserver);
    }
}
