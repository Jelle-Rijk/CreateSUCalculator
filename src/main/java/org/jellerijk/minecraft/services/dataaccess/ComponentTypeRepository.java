package org.jellerijk.minecraft.services.dataaccess;

import org.jellerijk.minecraft.model.component.type.ComponentType;

import java.util.List;
import java.util.NoSuchElementException;

public class ComponentTypeRepository {
    private final ComponentTypeDAO compDAO;

    public ComponentTypeRepository(ComponentTypeDAO compDAO) {
        this.compDAO = compDAO;
    }

    public ComponentType load(String name) throws NoSuchElementException {
        return compDAO.load(name).orElseThrow();
    }

    public List<ComponentType> loadAll() {
        return compDAO.loadAll();
    }

    public List<ComponentType> loadGenerators() {
        return compDAO.getGenerators();
    }

    public List<ComponentType> loadConsumers() {
        return compDAO.getConsumers();
    }
}
