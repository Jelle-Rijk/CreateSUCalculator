package org.jellerijk.mccreatecalc.application.usecases.network.update;

public record AddGeneratorToNetworkRequest(String networkId, String generatorName, int amount) {
}
