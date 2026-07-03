package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.Consumer;

import java.util.List;
import java.util.Optional;

public interface ComponentService {
    Optional<Consumer> getConsumerByName(String name);

    List<ComponentOption> getAllGeneratorOptions();

    List<ComponentOption> getAllConsumerOptions();
}
