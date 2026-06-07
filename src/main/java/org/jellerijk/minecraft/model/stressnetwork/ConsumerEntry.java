package org.jellerijk.minecraft.model.stressnetwork;

import org.jellerijk.minecraft.model.component.Consumer;

public class ConsumerEntry extends ComponentEntry<Consumer> {
    private final int rpm;

    public ConsumerEntry(Consumer type, int amount, int rpm) {
        super(type, amount);
        if (rpm < 1) throw new IllegalArgumentException("Consumer rpm cannot be lower than 1");
        this.rpm = rpm;
    }

    @Override
    public int calculateSUPerUnit() {
        return getComponent().calculateSUPerUnit(rpm);
    }
}
