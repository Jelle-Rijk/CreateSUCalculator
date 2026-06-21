package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.application.usecases.UseCase;
import org.jellerijk.mccreatecalc.entities.StressNetwork;

public class AddGeneratorToNetworkUseCase implements UseCase<AddGeneratorToNetworkRequest, StressNetwork> {
    /**
     * Adds a generator entry to a stress network. The request will always override existing generator entries such that the amount set in <code>addGeneratorToNetworkRequest</code> is always the total amount for this entry upon finishing the execution of the use case.
     *
     * @param addGeneratorToNetworkRequest The request containing the data for this use case.
     * @return The updated StressNetwork.
     */
    @Override
    public StressNetwork execute(AddGeneratorToNetworkRequest addGeneratorToNetworkRequest) {
        throw new UnsupportedOperationException();
    }

}
