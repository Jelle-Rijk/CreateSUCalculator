package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.components.Consumer;
import org.jellerijk.mccreatecalc.entities.components.Generator;

import java.util.List;

public interface ComponentRepository {
    List<Consumer> getAllConsumers();
    List<Generator> getAllGenerators();
}
