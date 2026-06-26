package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddGeneratorToSelectedNetworkUseCaseTest {
    private AddGeneratorToSelectedNetworkUseCase useCase;
    private NetworkService networkService;
    private GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        networkService = mock();
        generatorService = mock();
        useCase = new AddGeneratorToSelectedNetworkUseCase(networkService, generatorService);
    }

    @Test
    void execute_returnsUpdatedGenerators() {
        String generatorName = "Test-ConstantGenerator";
        StressNetwork network = new StressNetwork("1234", generatorName, new ArrayList<>());
        ConstantGenerator generator = mock();
        when(networkService.getSelectedNetwork()).thenReturn(Optional.of(network));
        when(generatorService.getByName(generatorName)).thenReturn(Optional.of(generator));

        AddGeneratorToSelectedNetworkRequest request = new AddGeneratorToSelectedNetworkRequest(generatorName, 5);
        StressNetwork result = useCase.execute(request);
        assertEquals(generator, result.generators().getFirst().getGenerator());
    }
}