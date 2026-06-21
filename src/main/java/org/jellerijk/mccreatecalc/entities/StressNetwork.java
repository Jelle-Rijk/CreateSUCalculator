package org.jellerijk.mccreatecalc.entities;

public record StressNetwork(String id, String name) implements SUProducer {

    public StressNetwork {
        validateId(id);
        validateName(name);
    }

    private void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Id cannot be null or blank");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
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
