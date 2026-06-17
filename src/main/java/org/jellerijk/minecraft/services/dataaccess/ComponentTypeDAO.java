package org.jellerijk.minecraft.services.dataaccess;

import org.jellerijk.minecraft.model.component.type.ComponentType;

import java.util.List;
import java.util.Optional;

public interface ComponentTypeDAO {
    Optional<ComponentType> load(String name);

    List<ComponentType> loadAll();

    List<ComponentType> getGenerators();

    List<ComponentType> getConsumers();
}
