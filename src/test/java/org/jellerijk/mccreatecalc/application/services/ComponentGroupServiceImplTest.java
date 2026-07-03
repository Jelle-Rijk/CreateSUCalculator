package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupPublisher;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ComponentGroupServiceImplTest {
    private ComponentGroupRepository cgRepo;
    private ComponentGroupService service;

    @BeforeEach
    void setUp() {
        cgRepo = mock();
        ComponentGroupPublisher cgPublisher = mock();
        service = new ComponentGroupServiceImpl(cgRepo, cgPublisher);
    }

    @Test
    void getById_InRepo_ReturnsOptional() {
        ComponentGroup cg = mock();
        when(cgRepo.getById("id")).thenReturn(Optional.of(cg));
        assertTrue(service.getById("id").isPresent());
    }

    @Test
    void getById_NotInRepo_ReturnsEmptyOptional() {
        when(cgRepo.getById("id")).thenReturn(Optional.empty());
        assertTrue(service.getById("id").isEmpty());
    }
}