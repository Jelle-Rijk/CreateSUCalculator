package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.application.Platform;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.presentation.componentselector.GeneratorOption;

public class NetworkDetailsInteractor implements SelectedNetworkObserver {
    private final NetworkDetailsModel model;

    public NetworkDetailsInteractor(NetworkDetailsModel model, UseCaseFactory factory) {
        this.model = model;
        factory.buildObserveSelectedNetworkUC().execute(this);
    }

    public void addGeneratorGroup(GeneratorOption generatorOption) {
        System.out.println("NetworkDetailsInteractor: added generator group");
    }

    @Override
    public void onNetworkSelectionChanged(StressNetwork selectedNetwork) {
        Platform.runLater(() -> setStressNetworkProperties(selectedNetwork));
    }

    private void setStressNetworkProperties(StressNetwork network) {
        if (network == null) {
            clearStressNetworkProperties();
            return;
        }

        model.networkNameProperty().set(network.name());
        model.suConsumedProperty().set(network.calculateSUConsumed());
        model.suProducedProperty().set(network.calculateSUProduced());
//        model.suBalanceProperty().set(network.calculateSUBalance()); //TODO needs to implement bidirectional binding first.
    }

    private void clearStressNetworkProperties() {
        model.networkNameProperty().set(null);
        model.suConsumedProperty().set(0);
        model.suProducedProperty().set(0);
        model.suBalanceProperty().set(0);
    }
}
