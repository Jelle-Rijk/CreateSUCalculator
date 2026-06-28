package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.entities.components.Windmill;
import org.jellerijk.mccreatecalc.entities.components.WaterWheel;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public record GeneratorOption(ComponentType type, String name, String img) {
    public static GeneratorOption map(WaterWheel w) {
        return new GeneratorOption(ComponentType.WATER_WHEEL, w.getName(), w.getImg());
    }

    public static GeneratorOption map(Windmill w) {
        return new GeneratorOption(ComponentType.WINDMILL, w.getName(), w.getImg());
    }
}
