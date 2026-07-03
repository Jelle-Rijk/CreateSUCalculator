package org.jellerijk.mccreatecalc.application.usecases.components;

import org.jellerijk.mccreatecalc.application.gateways.ComponentRepository;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.application.usecases.NoArgsUseCase;
import org.jellerijk.mccreatecalc.entities.components.Consumer;

import java.util.List;

public class GetConsumersUseCase implements NoArgsUseCase<List<ComponentOption>> {
    private final ComponentRepository componentRepo;

    public GetConsumersUseCase(ComponentRepository componentRepo) {
        this.componentRepo = componentRepo;
    }

    @Override
    public List<ComponentOption> execute() {
        List<Consumer> consumers = componentRepo.getAllConsumers();
        return ComponentOption.map(consumers);
    }
}
