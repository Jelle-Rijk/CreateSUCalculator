package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.ComponentService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.*;

import java.util.ArrayList;
import java.util.List;

// TODO: implement
// This use case should add a new component group to the selected network. Before we can do this, we need to know which component this group will represent. The amount, level, sails, rpm, etc. can all be set to their defaults.
public class AddComponentGroupToSelectedNetworkUC implements UseCase<AddComponentGroupRequest, ComponentGroupDTO> {

    private final ComponentService componentService;
    private final NetworkService networkService;

    public AddComponentGroupToSelectedNetworkUC(NetworkService networkService, ComponentService componentService) {
        this.networkService = networkService;
        this.componentService = componentService;
    }

    /**
     * Adds a component group to the selected network.
     *
     * @param addComponentGroupRequest The request containing data for the component group to add.
     * @return A DTO representing the newly added component group.
     */
    @Override
    public ComponentGroupDTO execute(AddComponentGroupRequest addComponentGroupRequest) {
        StressNetwork network = getNetwork();
        ComponentGroup componentGroup = createComponentGroup(addComponentGroupRequest);
        network = addGroupToNetwork(network, componentGroup);
        saveNetwork(network);
        return ComponentGroupDTO.map(componentGroup);
    }

    /**
     * Adds the <code>componentGroup</code> to a <code>network</code>. The network will be added in the correct generators/consumers list within the newly created network.
     *
     * @param network        The network to add the group to.
     * @param componentGroup The component group to add.
     * @return Copy of <code>network</code> with the added component group.
     */
    private StressNetwork addGroupToNetwork(StressNetwork network, ComponentGroup componentGroup) {
        StressNetwork.Builder builder = new StressNetwork.Builder(network);
        List<ComponentGroup> components = new ArrayList<>(componentGroup.isConsumer() ? network.consumers() : network.generators());
        components.add(componentGroup);
        return componentGroup.isConsumer() ? builder.withConsumers(components)
                .build() : builder.withGenerators(components).build();
    }

    private StressNetwork getNetwork() {
        return networkService.getSelectedNetwork().orElseThrow();
    }

    private void saveNetwork(StressNetwork network) {
        networkService.save(network);
        networkService.setSelectedNetwork(network);
    }

    private ComponentGroup createComponentGroup(AddComponentGroupRequest request) {
        return new ComponentGroup(ComponentGroup.generateId(), createComponent(request), 0);
    }

    private Component createComponent(AddComponentGroupRequest request) {
        return switch (request.type()) {
            case WATER_WHEEL -> new WaterWheel(request.waterWheelType());
            case WINDMILL -> new WindmillImpl(0);
            case CONSUMER -> componentService.getConsumerByName(request.name()).orElseThrow();
            case STEAM_ENGINE -> throw new UnsupportedOperationException();
        };
    }
}
