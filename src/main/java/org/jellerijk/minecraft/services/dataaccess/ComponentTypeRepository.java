package org.jellerijk.minecraft.services.dataaccess;

import org.jellerijk.minecraft.model.components.ComponentType;

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
}
