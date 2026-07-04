package org.jellerijk.mccreatecalc.application.usecases.network;

import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteNetworkUCTest {
    @Mock
    private NetworkService networkService;
    private DeleteNetworkUC uc;

    @BeforeEach
    void setUp() {
        uc = new DeleteNetworkUC(networkService);
    }

    @Test
    void execute_ValidRequest_DeletesNetwork() {
        uc.execute("test-id");
        verify(networkService).delete("test-id");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void execute_InvalidNetworkId_ThrowsIAE(String invalidId) {
        assertThrows(IllegalArgumentException.class, () -> uc.execute(invalidId));
    }
}