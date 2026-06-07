package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.Component;

import java.util.Objects;

public abstract class StandardComponent extends ComponentDataImpl implements Component {
    private final int rpm;

    public StandardComponent(String name, String imgPath, int rpm) {
        super(name, imgPath);
        validateRPM(rpm);
        this.rpm = rpm;
    }

    protected void validateRPM(int rpm) {
        if (rpm < 0)
            throw new IllegalArgumentException("RPM cannot be a negative number.");
    }

    @Override
    public int getRPM() {
        return rpm;
    }

    @Override
    public abstract int calculateSU();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StandardComponent that = (StandardComponent) o;
        return rpm == that.rpm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rpm);
    }
}
