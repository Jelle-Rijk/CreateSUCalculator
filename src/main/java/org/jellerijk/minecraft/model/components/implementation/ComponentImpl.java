package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.Component;
import org.jellerijk.minecraft.model.components.ComponentType;

public class ComponentImpl implements Component {
    private final ComponentType type;
    private final int rpm;

    /**
     * This constructor can be used to instantiate Components that have an RPM constant.
     *
     * @param type The component's type.
     */
    public ComponentImpl(ComponentType type) {
        this(type, type.getRpmConstant().orElseThrow());
    }

    /**
     * This constructor can be used to instantiate Components that do not have an RPM constant.
     *
     * @param type The component's type.
     * @param rpm  The rpm to set the component to.
     */
    public ComponentImpl(ComponentType type, int rpm) {
        if (type == null)
            throw new IllegalArgumentException("Every component needs a type.");
        if (rpm < 0)
            throw new IllegalArgumentException("Rpm cannot be a negative number.");
        this.type = type;
        this.rpm = rpm;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public String getImgPath() {
        return type.getImgPath();
    }

    @Override
    public int getStressImpact() {
        return type.getStressImpact();
    }

    @Override
    public boolean isGenerator() {
        return type.isGenerator();
    }

    @Override
    public int getMinRpm() {
        return type.getMinRpm();
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    @Override
    public int calculateSU() {
        return getRpm() * type.getStressImpact();
    }

    @Override
    public boolean hasMinRequiredRpm() {
        return getRpm() >= type.getMinRpm();
    }
}
