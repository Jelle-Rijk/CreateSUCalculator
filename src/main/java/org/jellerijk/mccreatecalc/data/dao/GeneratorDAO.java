package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

import java.util.List;
import java.util.Optional;

public interface GeneratorDAO {
    Optional<ConstantGenerator> getByName(String name);

    List<ConstantGenerator> getAll();
}
