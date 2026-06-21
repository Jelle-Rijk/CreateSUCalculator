package org.jellerijk.mccreatecalc.presentation.networkdetails;

import org.jellerijk.mccreatecalc.entities.Generator;

public record GeneratorOption(String name, String img, int su) {
    public static GeneratorOption map(Generator g) {
        return new GeneratorOption(g.getName(), g.getImg().orElse("default.png"), g.getSuGeneration());
    }
}
