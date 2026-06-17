package org.jellerijk.minecraft.model.network.implementations;

import org.jellerijk.minecraft.model.component.NetworkComponent;
import org.jellerijk.minecraft.model.network.Network;

import java.util.List;
import java.util.Map;

public record NetworkImpl(String id, String name, Map<NetworkComponent, Integer> generators,
                          Map<NetworkComponent, Integer> consumers) implements Network {
    public NetworkImpl {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
        validateConsumers(consumers);
    }

    private void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Id cannot be null or blank");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
    }

    private void validateGenerators(Map<NetworkComponent, Integer> generators) {
        if (generators == null)
            throw new IllegalArgumentException("Generators cannot be null");
        if (generators.entrySet().stream().anyMatch(e -> !e.getKey().isGenerator()))
            throw new IllegalArgumentException("Generators contained non-generator");
    }

    private void validateConsumers(Map<NetworkComponent, Integer> consumers) {
        if (consumers == null)
            throw new IllegalArgumentException("Consumers cannot be null");
        if (consumers.entrySet().stream().anyMatch(e -> e.getKey().isGenerator()))
            throw new IllegalArgumentException("Consumers contained generators");
    }

    @Override
    public int calculateProducedSU() {
        return calculateTotalSUFromMap(generators());
    }

    @Override
    public int calculateConsumedSU() {
        return calculateTotalSUFromMap(consumers());
    }

    private int calculateTotalSUFromMap(Map<NetworkComponent, Integer> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculateSU() * entry.getValue())
                .reduce(0, Integer::sum);
    }

    @Override
    public int calculateSUBalance() {
        return calculateProducedSU() - calculateConsumedSU();
    }

    @Override
    public boolean isOverstressed() {
        return calculateSUBalance() < 0;
    }
}
