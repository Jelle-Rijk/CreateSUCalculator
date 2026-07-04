package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class UpdateComponentGroupUCTest {
    @Mock
    private ComponentGroupService cgService;
    @Mock
    private NetworkService networkService;
    private UpdateComponentGroupUC uc;
    private UpdateComponentGroupRequest.Builder requestBuilder;
    private static final String VALID_ID = "test";

    @BeforeEach
    void setUp() {
        uc = new UpdateComponentGroupUC(cgService, networkService);
        requestBuilder = UpdateComponentGroupRequest.Builder.aRequest().withGroupId(VALID_ID).withAmount(5);
    }

    @Test
    void validateRequest_ValidRequest_DoesNotThrow() {
        assertDoesNotThrow(() -> uc.execute(requestBuilder.build()));
    }

    @Test
    void validateRequest_NoGroupId_ThrowsIllegalArgumentException() {
        UpdateComponentGroupRequest request = UpdateComponentGroupRequest.Builder.aRequest().withAmount(5).build();
        assertThrows(IllegalArgumentException.class, () -> uc.execute(request));
    }

    @Test
    void validateRequest_NoFieldsToSet_ThrowsIAE() {
        UpdateComponentGroupRequest request = UpdateComponentGroupRequest.Builder.aRequest()
                .withGroupId(VALID_ID)
                .build();
        assertThrows(IllegalArgumentException.class, () -> uc.execute(request));
    }
}