package org.jellerijk.mccreatecalc.application.usecases.components;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.ComponentService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;

import java.util.List;

public class GetGeneratorsUseCase implements NoArgsUseCase<List<ComponentOption>> {
    private final ComponentService componentService;

    public GetGeneratorsUseCase(ComponentService componentService) {
        this.componentService = componentService;
    }


    @Override
    public List<ComponentOption> execute() {
        return componentService.getAllGeneratorOptions();
    }
}
