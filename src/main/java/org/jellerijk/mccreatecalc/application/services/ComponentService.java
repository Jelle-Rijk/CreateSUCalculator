package org.jellerijk.mccreatecalc.application.services;

import java.util.List;

public interface ComponentService {
    List<ComponentOption> getAllGeneratorOptions();

    List<ComponentOption> getAllConsumerOptions();
}
