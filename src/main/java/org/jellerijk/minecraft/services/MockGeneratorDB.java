package org.jellerijk.minecraft.services;

import org.jellerijk.minecraft.model.components.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MockGeneratorDB implements ComponentDatabaseAccess<Generator> {
    List<Generator> generators = new ArrayList<>();

    @Override
    public void save(Generator component) {
        generators.add(component);
    }

    @Override
    public Optional<Generator> load(String name) {
        return generators.stream().filter(g -> g.getName().equals(name)).findAny();
    }

    @Override
    public List<Generator> loadAll() {
        return new ArrayList<>(generators);
    }

    @Override
    public void delete(String name) {
        generators = generators.stream()
                .filter(g -> !g.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
