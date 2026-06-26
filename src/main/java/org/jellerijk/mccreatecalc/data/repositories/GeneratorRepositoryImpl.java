package org.jellerijk.mccreatecalc.data.repositories;

import org.jellerijk.mccreatecalc.application.gateways.GeneratorRepository;
import org.jellerijk.mccreatecalc.data.dao.GeneratorDAO;
import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

import java.util.List;
import java.util.Optional;

public class GeneratorRepositoryImpl implements GeneratorRepository {
    private final GeneratorDAO genDAO;

    public GeneratorRepositoryImpl(GeneratorDAO genDAO) {
        this.genDAO = genDAO;
    }

    @Override
    public Optional<ConstantGenerator> getByName(String name) {
        return genDAO.getByName(name);
    }

    @Override
    public List<ConstantGenerator> getAll() {
        return genDAO.getAll();
    }
}
