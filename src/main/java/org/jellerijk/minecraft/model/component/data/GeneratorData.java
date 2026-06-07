package org.jellerijk.minecraft.model.component.data;

public class GeneratorData extends ComponentData {
    private final int rpm;

    public GeneratorData(String name, int stressImpact, int rpm) {
        super(name, stressImpact);
        if (rpm < 1)
            throw new IllegalArgumentException("RPM cannot be lower than 1");
        this.rpm = rpm;
    }

    public int getRpm() {
        return rpm;
    }
}
