package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;
import java.util.Optional;

public class ComponentServiceImpl implements ComponentService {
    private final GeneratorRepository generatorRepo;

    public ComponentServiceImpl(GeneratorRepository generatorRepo) {
        this.generatorRepo = generatorRepo;
    }

    @Override
    public Optional<WaterWheel> getByName(String name) {
        return generatorRepo.getByName(name);
    }

    @Override
    public List<WaterWheel> getAllGeneratorOptions() {
        return generatorRepo.getAll();
    }

    @Override
    public List<ComponentOption> getAllConsumerOptions() {
        return List.of();
    }
}
