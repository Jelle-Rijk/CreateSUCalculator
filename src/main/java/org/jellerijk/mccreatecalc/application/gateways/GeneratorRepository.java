package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;

public interface GeneratorRepository {
    Generator getByName(String name);

    List<Generator> getAll();
}
