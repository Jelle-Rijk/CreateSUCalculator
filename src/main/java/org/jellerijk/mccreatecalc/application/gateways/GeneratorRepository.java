package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

import java.util.List;
import java.util.Optional;

public interface GeneratorRepository {
    Optional<ConstantGenerator> getByName(String name);

    List<ConstantGenerator> getAll();
}
