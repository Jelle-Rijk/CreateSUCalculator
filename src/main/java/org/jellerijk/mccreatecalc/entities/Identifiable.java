package org.jellerijk.mccreatecalc.entities;

public interface Identifiable {
    String getId();

    /**
     * Validates the id of this identifiable object. Throws an IllegalArgumentException if <code>id</code> is null or blank.
     *
     * @param id The id to validate.
     */
    default void validateId(String id) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Id cannot be null or blank.");
    }


}
