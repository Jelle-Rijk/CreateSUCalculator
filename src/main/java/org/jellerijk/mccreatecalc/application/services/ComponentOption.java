package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.Component;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public record ComponentOption(ComponentType type, String name, String img) {
    public static ComponentOption map(Component c) {
        return new ComponentOption(ComponentType.of(c), c.getName(), c.getImg());
    }
}
