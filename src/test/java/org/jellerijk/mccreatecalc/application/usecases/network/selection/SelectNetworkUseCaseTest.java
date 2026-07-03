package org.jellerijk.mccreatecalc.application.usecases.network.selection;

import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.exceptions.NetworkNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectNetworkUseCaseTest {
    @Mock
    private NetworkService networkService;
    private SelectNetworkUseCase usecase;

    @BeforeEach
    void setUp() {
        usecase = new SelectNetworkUseCase(networkService);
    }

    @Test
    void execute_IdFound_SetsSelectedNetwork() {
        StressNetwork n = new StressNetwork("id", "Test-Network", new ArrayList<>(), new ArrayList<>());
        when(networkService.getById("id")).thenReturn(Optional.of(n));
        usecase.execute("id");
        verify(networkService).setSelectedNetwork(n);
    }

    @Test
    void execute_IdNotFound_ThrowsNetworkNotFoundException() {
        when(networkService.getById("id")).thenReturn(Optional.empty());
        assertThrows(NetworkNotFoundException.class, () -> usecase.execute("id"));
    }
}