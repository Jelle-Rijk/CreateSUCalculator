package org.jellerijk.minecraft.model.network.implementations;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.components.Consumer;
import org.jellerijk.minecraft.model.components.Generator;
import org.jellerijk.minecraft.model.network.StressNetwork;

import java.util.Map;

public class StressNetworkImpl implements StressNetwork {
    private final String id;
    private final String name;
    private final Map<Generator, Integer> generators;
    private final Map<Consumer, Integer> consumers;


    public StressNetworkImpl(String id, String name, Map<Generator, Integer> generators,
                             Map<Consumer, Integer> consumers) {
        validateId(id);
        validateName(name);
        validateComponents(generators);
        validateComponents(consumers);
        this.id = id;
        this.name = name;
        this.generators = Map.copyOf(generators);
        this.consumers = Map.copyOf(consumers);
    }

    private <T extends Component> void validateComponents(Map<T, Integer> components) {
        if (components == null)
            throw new IllegalArgumentException("Cannot set generators or consumers to null");
    }

    private void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Every stress network needs an id.");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Every stress network needs a name");
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
    public Map<Generator, Integer> getGenerators() {
        return generators;
    }

    @Override
    public Map<Consumer, Integer> getConsumers() {
        return consumers;
    }

    private <T extends Component> int calculateSUFromEntries(Map<T, Integer> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey().calculateSU() * entry.getValue())
                .reduce(0, Integer::sum);
    }
}
