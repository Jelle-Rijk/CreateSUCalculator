package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;

import java.util.List;

public class ComponentServiceImpl implements ComponentService {
    private final GeneratorRepository generatorRepo;

    public ComponentServiceImpl(GeneratorRepository generatorRepo) {
        this.generatorRepo = generatorRepo;
    }

    @Override
    public List<ComponentOption> getAllGeneratorOptions() {
        return ComponentOption.map(generatorRepo.getAll());
    }

    @Override
    public List<ComponentOption> getAllConsumerOptions() {
        return List.of();
    }
}
