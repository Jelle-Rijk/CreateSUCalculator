package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

import java.util.List;
import java.util.Optional;

public interface GeneratorService {
    /**
     * @param name The generator's unique name.
     * @return The generator that has the <code>name</code>.
     */
    Optional<ConstantGenerator> getByName(String name);

    List<ConstantGenerator> getAll();
}
