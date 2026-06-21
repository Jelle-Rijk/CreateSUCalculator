package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.Generator;
import org.jellerijk.mccreatecalc.entities.GeneratorTestBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockGeneratorService implements GeneratorService {
    @Override
    public Optional<Generator> getByName(String name) {
        return Optional.of(GeneratorTestBuilder.defaultGenerator().withName(name).build());
    }

    @Override
    public List<Generator> getAll() {
        return new ArrayList<>();
    }
}
