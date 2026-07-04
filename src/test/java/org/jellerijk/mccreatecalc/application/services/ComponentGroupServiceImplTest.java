package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.publishers.ComponentGroupDTOPublisher;
import org.jellerijk.mccreatecalc.application.usecases.network.update.UpdateComponentGroupRequest;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ComponentGroupServiceImplTest {
    private ComponentGroupRepository cgRepo;
    private ComponentGroupService service;
    private ComponentGroupDTOPublisher cgPublisher;

    @BeforeEach
    void setUp() {
        cgRepo = mock();
        cgPublisher = mock();
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

    @Test
    void getNetworkIdForGroup_GroupDoesNotExist_ReturnsOptional() {
        when(cgRepo.getNetworkId("a")).thenReturn(Optional.empty());
        assertTrue(service.getNetworkIdForGroup("a").isEmpty());
    }

    @Test
    void getNetworkIdForGroup_GroupExists_ReturnsNetworkId() {
        when(cgRepo.getNetworkId("a")).thenReturn(Optional.of("b"));
        assertTrue(service.getNetworkIdForGroup("a").isPresent());
    }

    @Test
    void delete_DelegatesCall() {
        service.delete("test");
        verify(cgRepo).delete("test");
    }

    @Test
    void update_DelegatesCall() {
        UpdateComponentGroupRequest request = mock();
        when(request.groupId()).thenReturn("id");
        ComponentGroup cg = mock();
        when(service.getById("id")).thenReturn(Optional.of(cg));
        service.update(request);
        verify(cgRepo).update(request);
    }

    @Test
    void update_publishesUpdate() {
        UpdateComponentGroupRequest request = mock();
        when(request.groupId()).thenReturn("id");
        ComponentGroup cg = mock();
        when(service.getById("id")).thenReturn(Optional.of(cg));
        service.update(request);
        verify(cgPublisher).publish(cg);
    }
}