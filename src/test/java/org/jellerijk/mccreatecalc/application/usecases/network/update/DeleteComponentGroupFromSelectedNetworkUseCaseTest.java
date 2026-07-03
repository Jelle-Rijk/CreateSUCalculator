package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.gateways.SelectedNetworkData;
import org.jellerijk.mccreatecalc.application.services.ComponentGroupService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.services.NetworkServiceImpl;
import org.jellerijk.mccreatecalc.application.services.SelectedNetworkPublisher;
import org.jellerijk.mccreatecalc.data.local.SelectedNetwork;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteComponentGroupFromSelectedNetworkUseCaseTest {
    @Mock
    StressNetwork network;
    private NetworkService networkService;
    private SelectedNetworkData selectedNetwork;
    @Mock
    ComponentGroupService cgService;
    private DeleteComponentGroupFromSelectedNetworkUseCase useCase;

    @BeforeEach
    void setUp() {
        selectedNetwork = new SelectedNetwork();

        networkService = new NetworkServiceImpl(mock(), selectedNetwork, mock());
        useCase = new DeleteComponentGroupFromSelectedNetworkUseCase(cgService, networkService);
    }

    @Test
    void execute_groupIdExists_removesComponent() {
    }
}