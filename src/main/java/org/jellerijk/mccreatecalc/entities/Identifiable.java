package org.jellerijk.mccreatecalc.entities;

public interface Identifiable {
    String getId();

    default void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Id cannot be null or blank.");
    }
}
