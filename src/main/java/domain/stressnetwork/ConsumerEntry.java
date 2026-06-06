package domain.system;

import domain.component.ComponentFacade;
import domain.component.ConsumerFacade;

public class ConsumerEntry extends ComponentEntry<ConsumerFacade> {
    private final int rpm;

    public ConsumerEntry(ConsumerFacade type, int amount, int rpm) {
        super(type, amount);
        if (rpm < 1) throw new IllegalArgumentException("Consumer rpm cannot be lower than 1");
        this.rpm = rpm;
    }

    @Override
    public int calculateSUPerUnit() {
        return getComponent().calculateSUPerUnit(rpm);
    }
}
