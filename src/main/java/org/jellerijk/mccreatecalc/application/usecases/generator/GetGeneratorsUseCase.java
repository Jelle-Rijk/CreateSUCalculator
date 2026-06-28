package org.jellerijk.mccreatecalc.application.usecases.generator;

import org.jellerijk.mccreatecalc.application.services.GeneratorService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;

public class GetGeneratorsUseCase implements NoArgsUseCase<List<WaterWheel>> {
    private final GeneratorService generatorService;

    public GetGeneratorsUseCase(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }


    @Override
    public List<WaterWheel> execute() {
        return generatorService.getAll();
    }
}
