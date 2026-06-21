package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;
import java.util.Optional;

public class GeneratorServiceImpl implements GeneratorService {
    private final GeneratorRepository generatorRepo;

    public GeneratorServiceImpl(GeneratorRepository generatorRepo) {
        this.generatorRepo = generatorRepo;
    }

    @Override
    public Optional<Generator> getByName(String name) {
        return generatorRepo.getByName(name);
    }

    @Override
    public List<Generator> getAll() {
        return generatorRepo.getAll();
    }
}
