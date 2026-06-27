package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.entities.components.WaterWheel;

import java.util.List;
import java.util.Optional;

public interface GeneratorDAO {
    Optional<WaterWheel> getByName(String name);

    List<WaterWheel> getAll();
}
