package domain.component;

import domain.component.data.ComponentData;

public class ConsumerFacade extends ComponentFacade<ComponentData> {
    public ConsumerFacade(ComponentData data) {
        super(data);
    }

    /**
     * Calculates the consumed stress units based on the supplied <code>rpm</code>.
     * @param rpm The RPM value to calculate the stress units for.
     * @return Amount of stress units consumed by a single consumer rotating at <code>rpm</code>.
     */
    public int calculateSUPerUnit(int rpm) {
        return getData().getStressImpact() * rpm;
    }
}
