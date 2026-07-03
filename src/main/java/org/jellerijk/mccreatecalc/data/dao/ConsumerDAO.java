package org.jellerijk.mccreatecalc.data.dao;

import org.jellerijk.mccreatecalc.entities.components.Consumer;

import java.util.List;
import java.util.Optional;

public interface ConsumerDAO {
    List<Consumer> getAll();

    Optional<Consumer> getByName(String name);
}
