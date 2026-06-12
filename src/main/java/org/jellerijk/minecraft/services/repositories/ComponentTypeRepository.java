package org.jellerijk.minecraft.services.repositories;

import org.jellerijk.minecraft.model.components.ComponentType;
import org.jellerijk.minecraft.services.dataaccess.ComponentDatabaseAccess;

import java.util.NoSuchElementException;

public class ComponentTypeRepository {
    private final ComponentDatabaseAccess<ComponentType> compDAO;

    public ComponentTypeRepository(ComponentDatabaseAccess<ComponentType> compDAO) {
        this.compDAO = compDAO;
    }

    public void save(ComponentType componentType) {
        compDAO.save(componentType);
    }

    public ComponentType load(String name) throws NoSuchElementException {
        return compDAO.load(name).orElseThrow();
    }

    public void delete(String name) {
        compDAO.delete(name);
    }
}
