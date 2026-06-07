package org.jellerijk.minecraft.model.stressnetwork;

import org.jellerijk.minecraft.model.component.Generator;

public class GeneratorEntry extends ComponentEntry<Generator> {

    public GeneratorEntry(Generator type, int amount) {
        super(type, amount);
    }

    @Override
    public int calculateSUPerUnit() {
        return getComponent().calculateSUPerUnit();
    }
}
