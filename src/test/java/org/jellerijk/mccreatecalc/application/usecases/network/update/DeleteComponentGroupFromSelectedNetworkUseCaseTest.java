package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.services.NetworkServiceImpl;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteComponentGroupFromSelectedNetworkUseCaseTest {
    @Mock
    private StressNetwork network;
    private NetworkService networkService;
    @Mock
    ComponentGroupService cgService;
    private DeleteComponentGroupFromSelectedNetworkUseCase useCase;

    @BeforeEach
    void setUp() {
        SelectedNetworkData selectedNetwork = new SelectedNetwork();
        networkService = new NetworkServiceImpl(mock(), selectedNetwork, mock());
        useCase = new DeleteComponentGroupFromSelectedNetworkUseCase(cgService, networkService);
    }

    @Test
    void execute_groupIdExists_removesComponent() {
        when(cgService.getNetworkIdForGroup("test")).thenReturn(Optional.of("test-net"));
        when(networkService.getById("test-net")).thenReturn(Optional.of(network));
        useCase.execute("test");
        verify(cgService).delete("test");
    }

    @Test
    void execute_groupIdExists_updatesSelectedNetwork() {
        when(cgService.getNetworkIdForGroup("test")).thenReturn(Optional.of("test-net"));
        when(networkService.getById("test-net")).thenReturn(Optional.of(network));
        useCase.execute("test");
        assertEquals(network, networkService.getSelectedNetwork().orElseThrow());
    }
}