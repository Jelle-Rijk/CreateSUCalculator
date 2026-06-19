package org.jellerijk.mccreatecalc.entities;

public record StressNetwork(String id, String name) {
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
}
