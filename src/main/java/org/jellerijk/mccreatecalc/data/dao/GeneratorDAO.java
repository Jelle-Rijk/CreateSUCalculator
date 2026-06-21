package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;
import java.util.Optional;

public interface GeneratorDAO {
    Optional<Generator> getByName(String name);

    List<Generator> getAll();
}
