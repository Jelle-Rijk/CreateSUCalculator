package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

public record GeneratorOption(String name, String img, int su) {
    public static GeneratorOption map(ConstantGenerator g) {
        return new GeneratorOption(g.getName(), g.getImg().orElse("default.png"), g.getSuGeneration());
    }
}
