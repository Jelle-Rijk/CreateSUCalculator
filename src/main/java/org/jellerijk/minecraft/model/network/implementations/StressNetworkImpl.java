package org.jellerijk.minecraft.model.network.implementations;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.network.StressNetwork;

import java.util.Map;

public class StressNetworkImpl implements StressNetwork {
    private final String id;
    private final String name;
    private final Map<Component, Integer> generators;
    private final Map<Component, Integer> consumers;


    public StressNetworkImpl(String id, String name, Map<Component, Integer> generators, Map<Component, Integer> consumers) {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
        validateConsumers(consumers);
        this.id = id;
        this.name = name;
        this.generators = Map.copyOf(generators);
        this.consumers = Map.copyOf(consumers);
    }


    private void validateGenerators(Map<Component, Integer> generators) {
        if (generators == null) throw new IllegalArgumentException("Cannot set generators to null");
        if (generators.entrySet().stream().anyMatch(g -> !g.getKey().isGenerator()))
            throw new IllegalArgumentException("Generators contained non-generator component.");
    }

    private void validateConsumers(Map<Component, Integer> consumers) {
        if (consumers == null) throw new IllegalArgumentException("Cannot set consumers to null");
        if (consumers.entrySet().stream().anyMatch(c -> c.getKey().isGenerator()))
            throw new IllegalArgumentException("Consumers contained a generator.");
    }

    private void validateId(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Every stress network needs an id.");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Every stress network needs a name");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isOverstressed() {
        return calculateSUBalance() < 0;
    }

    @Override
    public int calculateTotalSUProduced() {
        return calculateSUFromEntries(generators);
    }

    @Override
    public int calculateTotalSUConsumed() {
        return calculateSUFromEntries(consumers);
    }

    @Override
    public int calculateSUBalance() {
        return calculateTotalSUProduced() - calculateTotalSUConsumed();
    }

    @Override
    public Map<Component, Integer> getGenerators() {
        return generators;
    }

    @Override
    public Map<Component, Integer> getConsumers() {
        return consumers;
    }

    private int calculateSUFromEntries(Map<Component, Integer> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculateSU() * entry.getValue())
                .reduce(0, Integer::sum);
    }
}
