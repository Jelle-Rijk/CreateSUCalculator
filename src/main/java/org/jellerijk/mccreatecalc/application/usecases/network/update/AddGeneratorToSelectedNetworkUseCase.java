package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.Generator;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.GeneratorEntry;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;
import org.jellerijk.mccreatecalc.entities.components.WindmillImpl;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class AddGeneratorToSelectedNetworkUseCase implements UseCase<AddGeneratorToSelectedNetworkRequest, StressNetwork> {

    private final NetworkService networkService;

    public AddGeneratorToSelectedNetworkUseCase(NetworkService networkService, GeneratorService generatorService) {
        this.networkService = networkService;
    }

    /**
     * Adds a generator entry to a stress network. The request will always override existing generator entries such that the amount set in <code>addGeneratorToNetworkRequest</code> is always the total amount for this entry upon finishing the execution of the use case.
     *
     * @param request The request containing the data for this use case.
     * @return The updated StressNetwork.
     */
    @Override
    public StressNetwork execute(AddGeneratorToSelectedNetworkRequest request) {
        throw new UnsupportedOperationException();
    }

}
