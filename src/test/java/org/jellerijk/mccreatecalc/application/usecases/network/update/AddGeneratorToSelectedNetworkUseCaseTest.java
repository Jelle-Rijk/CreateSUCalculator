package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.MockGeneratorService;
import org.jellerijk.mccreatecalc.application.services.MockNetworkService;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddGeneratorToSelectedNetworkUseCaseTest {
    private AddGeneratorToSelectedNetworkUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AddGeneratorToSelectedNetworkUseCase(new MockNetworkService(), new MockGeneratorService());
    }

    @Test
    void execute_returnsUpdatedGenerators() {
        String generatorName = "Test-Generator";
        AddGeneratorToSelectedNetworkRequest request = new AddGeneratorToSelectedNetworkRequest(MockNetworkService.MOCK_NETWORK_ID, generatorName, 5);
        StressNetwork network = useCase.execute(request);
        assertEquals(generatorName, network.generators().getLast().getGenerator().getName());
    }
}