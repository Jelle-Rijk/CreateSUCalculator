package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.services.NetworkService;
import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.Generator;
import org.jellerijk.mccreatecalc.entities.GeneratorEntry;
import org.jellerijk.mccreatecalc.entities.StressNetwork;
import org.jellerijk.mccreatecalc.entities.StressNetworkBuilder;

import java.util.ArrayList;
import java.util.List;

public class AddGeneratorToNetworkUseCase implements UseCase<AddGeneratorToNetworkRequest, StressNetwork> {

    private final GeneratorService generatorService;
    private final NetworkService networkService;

    public AddGeneratorToNetworkUseCase(NetworkService networkService, GeneratorService generatorService) {
        this.networkService = networkService;
        this.generatorService = generatorService;
    }

    /**
     * Adds a generator entry to a stress network. The request will always override existing generator entries such that the amount set in <code>addGeneratorToNetworkRequest</code> is always the total amount for this entry upon finishing the execution of the use case.
     *
     * @param request The request containing the data for this use case.
     * @return The updated StressNetwork.
     */
    @Override
    public StressNetwork execute(AddGeneratorToNetworkRequest request) {
        StressNetwork network = getNetwork(request);
        GeneratorEntry newEntry = new GeneratorEntry(getGenerator(request), request.amount());
        List<GeneratorEntry> generators = updateGenerators(network, newEntry);
        network = new StressNetworkBuilder(network).withGenerators(generators).build();
        networkService.save(network);
        return network;
    }

    private Generator getGenerator(AddGeneratorToNetworkRequest request) {
        return generatorService.getByName(request.generatorName()).orElseThrow();
    }

    private StressNetwork getNetwork(AddGeneratorToNetworkRequest request) {
        return networkService.getById(request.networkId()).orElseThrow();
    }

    private List<GeneratorEntry> updateGenerators(StressNetwork network, GeneratorEntry newEntry) {
        List<GeneratorEntry> generators = new ArrayList<>(network.generators());
        generators.add(newEntry);
        return generators;
    }

}
