package org.jellerijk.mccreatecalc.application.usecases.generator;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.Generator;

import java.util.List;

public class GetGeneratorsUseCase implements NoArgsUseCase<List<Generator>> {
    private final GeneratorService generatorService;

    public GetGeneratorsUseCase(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }


    @Override
    public List<Generator> execute() {
        return generatorService.getAll();
    }
}
