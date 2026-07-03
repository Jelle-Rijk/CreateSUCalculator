package org.jellerijk.mccreatecalc.application.usecases.components;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.services.ComponentService;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;

import java.util.List;

public class GetConsumersUseCase implements NoArgsUseCase<List<ComponentOption>> {
    private final ComponentService compService;

    public GetConsumersUseCase(ComponentService compService) {
        this.compService = compService;
    }

    @Override
    public List<ComponentOption> execute() {
        return compService.getAllConsumerOptions();
    }
}
