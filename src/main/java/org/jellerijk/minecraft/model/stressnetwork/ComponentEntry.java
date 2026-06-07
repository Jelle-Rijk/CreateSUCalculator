package org.jellerijk.minecraft.model.stressnetwork;

import org.jellerijk.minecraft.model.component.Component;

public abstract class ComponentEntry<T extends Component<?>> {
    private final T component;
    private final int amount;

    public ComponentEntry(T component, int amount) {
        if (component == null)
            throw new IllegalArgumentException("Type in component entry cannot be null");
        if (amount < 0)
            throw new IllegalArgumentException("Cannot have less than zero components in a system.");
        this.component = component;
        this.amount = amount;
    }

    /**
     * @return The component associated with this entry.
     */
    protected T getComponent() {
        return component;
    }

    /**
     * Calculates the total SU usage or production in this component entry.
     *
     * @return The calculated SU produced or consumed.
     */
    public int calculateTotalSU() {
        return calculateSUPerUnit() * amount;
    }

    public abstract int calculateSUPerUnit();


}
