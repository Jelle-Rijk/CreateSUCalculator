package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.Generator;

import java.util.List;
import java.util.Optional;

public interface ComponentRepository {
    List<Consumer> getAllConsumers();
    List<Generator> getAllGenerators();

    Optional<Consumer> getConsumerByName(String name);
}
