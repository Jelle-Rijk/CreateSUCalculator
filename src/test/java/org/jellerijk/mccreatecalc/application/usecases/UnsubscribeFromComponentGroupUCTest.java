package org.jellerijk.mccreatecalc.application.usecases;

import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UnsubscribeFromComponentGroupUCTest {
    @Mock
    private ComponentGroupService componentGroupService;
    private static final String VALID_GROUP_ID = "test-group";
    private UnsubscribeFromComponentGroupUC uc;

    @BeforeEach
    void setUp() {
        uc = new UnsubscribeFromComponentGroupUC(componentGroupService);
    }

    @Test
    void execute_ValidRequest_executes() {
        Subscription<ComponentGroupDTO, String> request = new Subscription<>(mock(), VALID_GROUP_ID);
        assertDoesNotThrow(() -> uc.execute(request));
    }

    @Test
    void execute_InvalidObserver_throwsIAE() {
        Subscription<ComponentGroupDTO, String> request = new Subscription<>(null, VALID_GROUP_ID);
        assertThrows(IllegalArgumentException.class, () -> uc.execute(request));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void execute_InvalidGroupId_throwsIAE(String invalidGroupId) {
        Subscription<ComponentGroupDTO, String> request = new Subscription<>(mock(), invalidGroupId);
        assertThrows(IllegalArgumentException.class, () -> uc.execute(request));
    }
}