package org.jellerijk.mccreatecalc.application.usecases.network.update;

import org.jellerijk.mccreatecalc.entities.components.ComponentType;
import org.jellerijk.mccreatecalc.entities.components.WaterWheelType;

public record AddGeneratorToSelectedNetworkRequest(ComponentType type, WaterWheelType waterWheelType, String generatorName,
                                                   int amount, int sails) {
}
