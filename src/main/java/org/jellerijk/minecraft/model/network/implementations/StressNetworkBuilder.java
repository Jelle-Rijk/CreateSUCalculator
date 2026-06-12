package org.jellerijk.minecraft.model.network.implementations;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.network.StressNetwork;

import java.util.Map;

public final class StressNetworkBuilder {
    private Map<Component, Integer> consumers;
    private Map<Component, Integer> generators;
    private String id;
    private String name;

    public StressNetworkBuilder() {
    }

    public StressNetworkBuilder(StressNetwork other) {
        this.consumers = other.getConsumers();
        this.generators = other.getGenerators();
        this.id = other.getId();
        this.name = other.getName();
    }

    public static StressNetworkBuilder aStressNetworkImpl() {
        return new StressNetworkBuilder();
    }

    public StressNetworkImpl build() {
        return new StressNetworkImpl(id, name, generators, consumers);
    }

    public StressNetworkBuilder withConsumers(Map<Component, Integer> consumers) {
        this.consumers = consumers;
        return this;
    }

    public StressNetworkBuilder withGenerators(Map<Component, Integer> generators) {
        this.generators = generators;
        return this;
    }

    public StressNetworkBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public StressNetworkBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
