package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.Generator;

public class ConstantGenerator extends StandardComponent implements Generator {
    private final int stressCapacity;

    public ConstantGenerator(String name, String imgPath, int rpm, int stressCapacity) {
        super(name, imgPath, rpm);
        if (stressCapacity < 0)
            throw new IllegalArgumentException("Stress capacity cannot be a negative number.");
        this.stressCapacity = stressCapacity;
    }

    @Override
    public int calculateSU() {
        return stressCapacity * getRPM();
    }

}
