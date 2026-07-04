package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.application.Platform;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.components.GetConsumersUseCase;
import org.jellerijk.mccreatecalc.application.usecases.components.GetGeneratorsUseCase;
import org.jellerijk.mccreatecalc.application.usecases.network.selection.SelectedNetworkObserver;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddComponentGroupRequest;
import org.jellerijk.mccreatecalc.application.usecases.network.update.AddComponentGroupToSelectedNetworkUC;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;

import java.util.List;

public class NetworkDetailsInteractor implements SelectedNetworkObserver {
    private final AddComponentGroupToSelectedNetworkUC addComponentGroupHandler;
    private final NetworkDetailsModel model;
    private final GetGeneratorsUseCase generatorOptionsFetcher;
    private final GetConsumersUseCase consumerOptionsFetcher;


    public NetworkDetailsInteractor(NetworkDetailsModel model, UseCaseFactory factory) {
        this.model = model;
        generatorOptionsFetcher = factory.buildGetGeneratorsUseCase();
        consumerOptionsFetcher = factory.buildGetConsumersUC();
        addComponentGroupHandler = factory.buildAddComponentGroupUC();
        factory.buildObserveSelectedNetworkUC().execute(this);
    }

    public void addComponentGroup(ComponentOption componentOption) {
        AddComponentGroupRequest.Builder request = AddComponentGroupRequest.Builder.anAddComponentGroupRequest()
                .withName(componentOption.name())
                .withType(componentOption.type());
        if (componentOption.type() == ComponentType.WATER_WHEEL) {
            request.withWaterWheelType(WaterWheelType.fromName(componentOption.name()));
        }
        addComponentGroupHandler.execute(request.build());
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

        model.setConsumers(ComponentGroupDTO.map(network.consumers()));
        model.setNetworkSelected(true);
        model.setGenerators(ComponentGroupDTO.map(network.generators()));
        model.networkNameProperty().set(network.name());
        model.suConsumedProperty().set(network.calculateSUConsumed());
        model.suProducedProperty().set(network.calculateSUProduced());
        model.suBalanceProperty().set(network.calculateSUBalance());
    }

    public List<ComponentOption> getGeneratorOptions() {
        return generatorOptionsFetcher.execute();
    }

    public List<ComponentOption> getConsumerOptions() {
        return consumerOptionsFetcher.execute();
    }

    private void clearStressNetworkProperties() {
        model.networkNameProperty().set(null);
        model.setNetworkSelected(false);
        model.suConsumedProperty().set(0);
        model.suProducedProperty().set(0);
        model.suBalanceProperty().set(0);
    }
}
