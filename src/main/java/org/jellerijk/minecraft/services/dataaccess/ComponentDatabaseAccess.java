package org.jellerijk.minecraft.services.dataaccess;

import org.jellerijk.minecraft.model.components.ComponentType;

import java.util.List;
import java.util.Optional;

public interface ComponentDatabaseAccess<T extends ComponentType> {
    void save(T component);

    Optional<T> load(String name);

    List<T> loadAll();

    void delete(String name);
}
