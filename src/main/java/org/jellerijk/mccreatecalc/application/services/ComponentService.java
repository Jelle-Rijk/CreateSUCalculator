package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;
import java.util.Optional;

public interface ComponentService {
    /**
     * @param name The generator's unique name.
     * @return The generator that has the <code>name</code>.
     */
    Optional<WaterWheel> getByName(String name);

    List<WaterWheel> getAllGeneratorOptions();

    List<ComponentOption> getAllConsumerOptions();
}
