package org.jellerijk.mccreatecalc.entities;

import java.util.List;

public record StressNetwork(String id, String name, List<ComponentGroup> generators,
                            List<ComponentGroup> consumers) implements SUProducer, Identifiable {

    public StressNetwork {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
        validateConsumers(consumers);
        generators = List.copyOf(generators);
        consumers = List.copyOf(consumers);
    }

    //===== Public methods =====
    public int calculateSUBalance() {
        return calculateSUProduced() - calculateSUConsumed();
    }

    public int calculateSUConsumed() {
        return calculateTotalSuFromComponentGroups(consumers);
    }

    public int calculateSUProduced() {
        return calculateTotalSuFromComponentGroups(generators);
    }

//===== Private methods =====

    /**
     * Calculates the total su produced or consumed for a list of component groups.
     *
     * @param componentGroupDTOS The list of component groups to calculate the total su consumption or production of.
     * @return The total stress units consumed or produced by the component groups.
     */
    private int calculateTotalSuFromComponentGroups(List<ComponentGroup> componentGroupDTOS) {
        return componentGroupDTOS.stream().mapToInt(ComponentGroup::calculateSu).reduce(0, Integer::sum);
    }

    private void validateConsumers(List<ComponentGroup> consumers) {
        if (consumers == null)
            throw new IllegalArgumentException("Consumers cannot be null");
        if (consumers.stream().anyMatch(componentGroup -> !componentGroup.isConsumer()))
            throw new IllegalArgumentException("Consumers contained non-consumer.");
    }

    private void validateGenerators(List<ComponentGroup> generators) {
        if (generators == null)
            throw new IllegalArgumentException("StressNetwork needs a list of components entries.");
        if (generators.stream().anyMatch(ComponentGroup::isConsumer))
            throw new IllegalArgumentException("Generators contained a consumer");
    }

    @Override
    public String getId() {
        return id;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
    }

    public static final class Builder {
        private List<ComponentGroup> consumers;
        private List<ComponentGroup> generators;
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

        //===== Static methods =====
        public static Builder aStressNetwork() {
            return new Builder();
        }

        //===== Public methods =====
        public StressNetwork build() {
            return new StressNetwork(id, name, generators, consumers);
        }

        public Builder withConsumers(List<ComponentGroup> consumers) {
            this.consumers = consumers;
            return this;
        }

        public Builder withGenerators(List<ComponentGroup> generators) {
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
