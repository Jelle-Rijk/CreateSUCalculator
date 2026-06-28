package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;
import java.util.Optional;

public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorRepository generatorRepo;

    public GeneratorServiceImpl(GeneratorRepository generatorRepo) {
        this.generatorRepo = generatorRepo;
    }

    @Override
    public Optional<WaterWheel> getByName(String name) {
        return generatorRepo.getByName(name);
    }

    @Override
    public List<WaterWheel> getAll() {
        return generatorRepo.getAll();
    }
}
