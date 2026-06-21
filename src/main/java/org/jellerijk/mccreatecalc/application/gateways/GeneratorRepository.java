package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;
import java.util.Optional;

public interface GeneratorRepository {
    Optional<Generator> getByName(String name);

    List<Generator> getAll();
}
