package org.jellerijk.mccreatecalc.entities;

import java.util.List;

public record StressNetwork(String id, String name, List<GeneratorEntry> generators) implements SUProducer {

    public StressNetwork {
        validateId(id);
        validateName(name);
        validateGenerators(generators);
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
        throw new UnsupportedOperationException();
    }

    public int calculateSUProduced() {
        throw new UnsupportedOperationException();
    }

    public int calculateSUBalance() {
        return calculateSUProduced() - calculateSUConsumed();
    }
}
