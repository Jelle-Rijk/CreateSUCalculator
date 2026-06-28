package org.jellerijk.mccreatecalc.entities;

import java.util.List;

public record StressNetwork(String id, String name, List<GeneratorEntry> generators, List<ComponentGroup> consumers) implements SUProducer {

    public StressNetwork {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
        generators = List.copyOf(generators);
    }

    private void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Id cannot be null or blank");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
    }

    private void validateGenerators(List<GeneratorEntry> generators) {
        if (generators == null)
            throw new IllegalArgumentException("StressNetwork needs a list of generator entries.");
    }

    public int calculateSUConsumed() {
        return 0; // TODO implement SU consumers
    }

    public int calculateSUProduced() {
        return generators.stream().mapToInt(GeneratorEntry::calculateSUProduced).reduce(0, Integer::sum);
    }

    public int calculateSUBalance() {
        return calculateSUProduced() - calculateSUConsumed();
    }


    public static final class Builder {
        private List<ComponentGroup> consumers;
        private List<GeneratorEntry> generators;
        private String id;
        private String name;

        public Builder() {
        }

        public Builder(StressNetwork other) {
            this.consumers = other.consumers();
            this.generators = other.generators();
            this.id = other.id();
            this.name = other.name();
        }

        public static Builder aStressNetwork() {
            return new Builder();
        }

        public StressNetwork build() {
            return new StressNetwork(id, name, generators, consumers);
        }

        public Builder withConsumers(List<ComponentGroup> consumers) {
            this.consumers = consumers;
            return this;
        }

        public Builder withGenerators(List<GeneratorEntry> generators) {
            this.generators = generators;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
    }
}
