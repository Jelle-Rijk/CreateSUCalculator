package org.jellerijk.mccreatecalc.presentation.networkdetails;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

public class NetworkDetailsInteractor implements SelectedNetworkObserver {
    private final NetworkDetailsModel model;

    public NetworkDetailsInteractor(NetworkDetailsModel model, UseCaseFactory factory) {
        this.model = model;
        factory.buildObserveSelectedNetworkUseCase().execute(this);
    }

    @Override
    public void onNetworkSelectionChanged(StressNetwork selectedNetwork) {
        setStressNetworkProperties(selectedNetwork);
    }

    private void setStressNetworkProperties(StressNetwork network) {
        if (network == null) {
            clearStressNetworkProperties();
            return;
        }

        model.networkNameProperty().set(network.name());
        model.suConsumedProperty().set(network.calculateSUConsumed());
        model.suProducedProperty().set(network.calculateSUProduced());
        model.suBalanceProperty().set(network.calculateSUBalance());
    }

    private void clearStressNetworkProperties() {
        model.networkNameProperty().set(null);
        model.suConsumedProperty().set(0);
        model.suProducedProperty().set(0);
        model.suBalanceProperty().set(0);
    }
}
