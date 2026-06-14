package org.jellerijk.minecraft.services.usecases.addComponent;

import org.jellerijk.minecraft.dtos.StressNetworkDTO;
import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.components.ComponentType;
import org.jellerijk.minecraft.model.components.implementation.ComponentImpl;
import org.jellerijk.minecraft.model.components.implementation.Windmill;
import org.jellerijk.minecraft.model.network.StressNetwork;
import org.jellerijk.minecraft.model.network.implementations.StressNetworkBuilder;
import org.jellerijk.minecraft.services.dataaccess.ComponentTypeRepository;
import org.jellerijk.minecraft.services.repositories.StressNetworkRepository;
import org.jellerijk.minecraft.services.usecases.UseCase;
import org.jellerijk.minecraft.services.usecases.viewNetwork.NetworkNotFoundException;

import java.util.Map;

public class AddComponentUseCase implements UseCase<AddComponentRequest, StressNetworkDTO> {
    private final StressNetworkRepository networkRepo;
    private final ComponentTypeRepository componentRepo;

    public AddComponentUseCase(StressNetworkRepository networkRepo, ComponentTypeRepository componentRepo) {
        this.networkRepo = networkRepo;
        this.componentRepo = componentRepo;
    }

    @Override
    public StressNetworkDTO execute(AddComponentRequest request) {
        StressNetwork network = fetchNetwork(request);
        StressNetworkBuilder builder = new StressNetworkBuilder(network);
        Map<Component, Integer> updatedComponents = updateComponents(request.isGenerator() ? network.getGenerators() : network.getConsumers(), createComponent(request), request.getAmount());
        StressNetwork updatedNetwork = request.isGenerator() ? builder.withGenerators(updatedComponents)
                .build() : builder.withConsumers(updatedComponents).build();
        networkRepo.save(updatedNetwork);
        return StressNetworkDTO.from(updatedNetwork);
    }

    private StressNetwork fetchNetwork(AddComponentRequest request) {
        String id = request.getNetworkId();
        return networkRepo.load(id).orElseThrow(() -> new NetworkNotFoundException(id));
    }

    private Map<Component, Integer> updateComponents(Map<Component, Integer> oldComponents, Component component, int amount) {
        oldComponents.put(component, amount);
        return oldComponents;
    }

    private Component createComponent(AddComponentRequest request) {
        ComponentType type = componentRepo.load(request.getComponentName());
        if (type.getRpmConstant().isPresent()) return new ComponentImpl(type);
        if (request.isWindmill()) return new Windmill(type, request.getSails());
        return new ComponentImpl(type, request.getRpm());
    }


}
