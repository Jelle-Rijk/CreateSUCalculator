package org.jellerijk.mccreatecalc.entities;

import java.util.List;

public record StressNetwork(String id, String name, List<GeneratorEntry> generators) implements SUProducer {

    public StressNetwork {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
        generators = List.copyOf(generators);
        System.out.println(generators);
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
}
