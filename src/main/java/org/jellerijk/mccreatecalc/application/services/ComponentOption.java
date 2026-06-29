package org.jellerijk.mccreatecalc.application.services;

import org.jellerijk.mccreatecalc.entities.components.Component;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ComponentOption(ComponentType type, String name, String img) {
    public static ComponentOption map(Component c) {
        return new ComponentOption(ComponentType.of(c), c.getName(), c.getImg());
    }

    public static <T extends Component> List<ComponentOption> map(List<T> components) {
        return components.stream().map(ComponentOption::map).collect(Collectors.toCollection(ArrayList::new));
    }
}
