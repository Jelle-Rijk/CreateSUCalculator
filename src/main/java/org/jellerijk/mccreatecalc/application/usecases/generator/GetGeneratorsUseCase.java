package org.jellerijk.mccreatecalc.application.usecases.generator;

import org.jellerijk.mccreatecalc.application.services.ComponentService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;

public class GetGeneratorsUseCase implements NoArgsUseCase<List<WaterWheel>> {
    private final ComponentService componentService;

    public GetGeneratorsUseCase(ComponentService componentService) {
        this.componentService = componentService;
    }


    @Override
    public List<WaterWheel> execute() {
        return componentService.getAllGeneratorOptions();
    }
}
