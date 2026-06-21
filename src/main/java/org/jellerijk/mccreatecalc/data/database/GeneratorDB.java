package org.jellerijk.mccreatecalc.data.database;

import org.jellerijk.mccreatecalc.data.dao.GeneratorDAO;
import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;
import java.util.Optional;

public class GeneratorDB implements GeneratorDAO {
    @Override
    public Optional<Generator> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Generator> getAll() {
        return List.of();
    }
}
