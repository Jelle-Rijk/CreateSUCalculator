package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;

import java.util.List;

public class ComponentServiceImpl implements ComponentService {
    private final ComponentRepository componentRepo;
    private final GeneratorRepository generatorRepo;

    public ComponentServiceImpl(GeneratorRepository generatorRepo, ComponentRepository componentRepo) {
        this.generatorRepo = generatorRepo;
        this.componentRepo = componentRepo;
    }

    @Override
    public List<ComponentOption> getAllGeneratorOptions() {
        return ComponentOption.map(generatorRepo.getAll());
    }

    @Override
    public List<ComponentOption> getAllConsumerOptions() {
        return ComponentOption.map(componentRepo.getAllConsumers());
    }
}
