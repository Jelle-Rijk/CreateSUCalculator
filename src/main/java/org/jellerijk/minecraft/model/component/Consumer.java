package org.jellerijk.minecraft.model.component;

import org.jellerijk.minecraft.model.component.data.ComponentData;

public class Consumer extends Component<ComponentData> {
    public Consumer(ComponentData data) {
        super(data);
    }

    /**
     * Calculates the consumed stress units based on the supplied <code>rpm</code>.
     *
     * @param rpm The RPM value to calculate the stress units for.
     * @return Amount of stress units consumed by a single consumer rotating at <code>rpm</code>.
     */
    public int calculateSUPerUnit(int rpm) {
        return getData().getStressImpact() * rpm;
    }
}
