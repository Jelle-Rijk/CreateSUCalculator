package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentGroupRepositoryImplTest {
    private ComponentGroupDAO cgDAO;
    private ComponentGroupRepository cgRepo;

    @BeforeEach
    void setUp() {
        cgDAO = mock();
        cgRepo = new ComponentGroupRepositoryImpl(cgDAO);
    }

    @Test
    void getById_IdExists_ReturnsFilledOptional() {
        ComponentGroup cg = mock();
        when(cgDAO.get("id")).thenReturn(Optional.of(cg));
        assertTrue(cgRepo.getById("id").isPresent());
    }

    @Test
    void getById_IdDoesNotExist_ReturnsEmptyOptional() {
        when(cgDAO.get("id")).thenReturn(Optional.empty());
        assertTrue(cgRepo.getById("id").isEmpty());
    }
}