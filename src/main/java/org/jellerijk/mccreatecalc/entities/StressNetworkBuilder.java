package org.jellerijk.mccreatecalc.entities;

import java.util.List;

public final class StressNetworkBuilder {
    private List<GeneratorEntry> generators;
    private String id;
    private String name;

    public StressNetworkBuilder() {
    }

    public StressNetworkBuilder(StressNetwork other) {
        this.generators = other.generators();
        this.id = other.id();
        this.name = other.name();
    }

    public static StressNetworkBuilder aStressNetwork() {
        return new StressNetworkBuilder();
    }

    public StressNetwork build() {
        return new StressNetwork(id, name, generators);
    }

    public StressNetworkBuilder withGenerators(List<GeneratorEntry> generators) {
        this.generators = List.copyOf(generators);
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
