package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.entities.GeneratorEntry;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
        String generatorName = "Test-WaterWheel";
        StressNetwork network = new StressNetwork("1234", generatorName, new ArrayList<>());
        WaterWheel generator = mock();
        when(networkService.getSelectedNetwork()).thenReturn(Optional.of(network));
        when(generatorService.getByName(generatorName)).thenReturn(Optional.of(generator));

        AddGeneratorToSelectedNetworkRequest request = new AddGeneratorToSelectedNetworkRequest(ComponentType.WATER_WHEEL, WaterWheelType.SMALL, generatorName, 5, 0);
        StressNetwork result = useCase.execute(request);
        assertEquals(generator, result.generators().getFirst().getGenerator());
    }

    //    === Adding different types ===
    @Test
    void execute_WaterWheel_addsConstantGenerator() {
        List<GeneratorEntry> generators = new ArrayList<>();
        StressNetwork n = mock();
        when(n.generators()).thenReturn(generators);
        when(networkService.getSelectedNetwork()).thenReturn(Optional.of(n));

        AddGeneratorToSelectedNetworkRequest request = new AddGeneratorToSelectedNetworkRequest(ComponentType.WATER_WHEEL, WaterWheelType.SMALL, null, 5, 0);
        StressNetwork result = useCase.execute(request);

        assertEquals(WaterWheel.class, result.generators().getFirst().getGenerator().getClass());
    }

    @Test
    void execute_Windmill_addsWindmill() {

    }
}