package org.jellerijk.minecraft.services;

import org.jellerijk.minecraft.model.components.Generator;

import java.util.Optional;

public class GeneratorRepository {
    private final ComponentDatabaseAccess<Generator> genDAO;

    public GeneratorRepository() {
        genDAO = new MockGeneratorDB();
    }

    public void save(Generator generator) {
        genDAO.save(generator);
    }

    public Optional<Generator> load(String name) {
        return genDAO.load(name);
    }

    public void delete(String name) {
        genDAO.delete(name);
    }
}
