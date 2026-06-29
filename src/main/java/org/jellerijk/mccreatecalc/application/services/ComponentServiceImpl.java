package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.entities.components.Consumer;

import java.util.List;
import java.util.Optional;

public class ComponentServiceImpl implements ComponentService {
    private final ComponentRepository componentRepo;
    private final GeneratorRepository generatorRepo;

    public ComponentServiceImpl(GeneratorRepository generatorRepo, ComponentRepository componentRepo) {
        this.generatorRepo = generatorRepo;
        this.componentRepo = componentRepo;
    }

    @Override
    public Optional<Consumer> getConsumerByName(String name) {
        return componentRepo.getConsumerByName(name);
    }

    @Override
    public List<ComponentOption> getAllGeneratorOptions() {
        return ComponentOption.map(componentRepo.getAllGenerators());
    }

    @Override
    public List<ComponentOption> getAllConsumerOptions() {
        return ComponentOption.map(componentRepo.getAllConsumers());
    }
}
