package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.ComponentGroupRepository;
import org.jellerijk.mccreatecalc.application.usecases.network.update.UpdateComponentGroupRequest;
import org.jellerijk.mccreatecalc.data.dao.ComponentGroupDAO;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.Windmill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ComponentGroupRepositoryImplTest {
    private ComponentGroupDAO cgDAO;
    private ComponentGroupRepository cgRepo;
    private UpdateComponentGroupRequest.Builder requestBuilder;
    private static final String VALID_ID = "test";

    @BeforeEach
    void setUp() {
        requestBuilder = UpdateComponentGroupRequest.Builder.aRequest().withGroupId(VALID_ID);
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

    @Test
    void update_GroupDoesNotExist_ThrowsIAE() {
        when(cgDAO.getNetworkId(VALID_ID)).thenReturn(Optional.empty());
        UpdateComponentGroupRequest request = UpdateComponentGroupRequest.Builder.aRequest()
                .withGroupId(VALID_ID)
                .withAmount(5)
                .build();
        assertThrows(IllegalArgumentException.class, () -> cgRepo.update(request));
    }

    @Test
    void update_ValidRequestWithAmountSet_UpdatesAmount() {
        ComponentGroup oldData = mock();
        when(oldData.getAmount()).thenReturn(5);
        when(cgDAO.get(VALID_ID)).thenReturn(Optional.of(oldData));
        UpdateComponentGroupRequest request = requestBuilder.withAmount(9).build();

        cgRepo.update(request);
        verify(cgDAO).update(request.groupId(), 9);
    }

    @Test
    void update_ValidRequestWithSailsSet_UpdatesSails() {
        ComponentGroup oldData = mock();
        when(oldData.getComponentType()).thenReturn(ComponentType.WINDMILL);
        when(cgDAO.get(VALID_ID)).thenReturn(Optional.of(oldData));
        UpdateComponentGroupRequest request = requestBuilder.withSails(32).build();

        cgRepo.update(request);
        verify(cgDAO).updateWindmill(request.groupId(), 32);
    }

    @Test
    void update_ValidRequestWithRPMSet_UpdatesRPM() {
        ComponentGroup oldData = mock();
        when(oldData.getComponentType()).thenReturn(ComponentType.CONSUMER);
        when(cgDAO.get(VALID_ID)).thenReturn(Optional.of(oldData));
        UpdateComponentGroupRequest request = requestBuilder.withRpm(56).build();

        cgRepo.update(request);
        verify(cgDAO).updateConsumer(request.groupId(), 56);
    }
}