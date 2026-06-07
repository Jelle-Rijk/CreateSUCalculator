package org.jellerijk.minecraft.services;

import org.jellerijk.minecraft.model.components.Component;

import java.util.List;
import java.util.Optional;

public interface ComponentDatabaseAccess<T extends Component> {
    void save(T component);

    Optional<T> load(String name);

    List<T> loadAll();

    void delete(String name);
}
