package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void delete_delegatesToDAO() {
        cgRepo.delete("test");
        verify(cgDAO).deleteGroup("test");
    }

    @Test
    void getNetworkId_GroupExists_ReturnsNetworkId() {
        when(cgDAO.getNetworkId("test")).thenReturn(Optional.of("Test-Network"));
        assertTrue(cgRepo.getNetworkId("test").isPresent());
    }

    @Test
    void getNetworkId_GroupDoesNotExist_ReturnsEmptyOptional() {
        when(cgDAO.getNetworkId("test")).thenReturn(Optional.empty());
        assertTrue(cgRepo.getNetworkId("test").isEmpty());
    }
}