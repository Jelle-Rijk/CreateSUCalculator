package org.jellerijk.mccreatecalc.application.gateways;

import org.jellerijk.mccreatecalc.entities.components.Component;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;
import java.util.Optional;

public interface GeneratorRepository {
    Optional<WaterWheel> getByName(String name);

    List<Component> getAll();
}
