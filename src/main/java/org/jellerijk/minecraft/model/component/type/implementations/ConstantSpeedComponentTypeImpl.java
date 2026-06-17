package org.jellerijk.minecraft.model.component.type.implementations;

import org.jellerijk.minecraft.model.component.type.ConstantSpeedComponentType;

public class ConstantSpeedComponentTypeImpl extends ComponentTypeImpl implements ConstantSpeedComponentType {
    private final int rpm;

    public ConstantSpeedComponentTypeImpl(String name, String imgPath, int stressImpact, boolean generator, int minRpm, int rpm) {
        super(name, imgPath, stressImpact, generator, minRpm);
        validateRPM(rpm);
        this.rpm = rpm;
    }

    @Override
    public int getRpm() {
        return rpm;
    }
}
