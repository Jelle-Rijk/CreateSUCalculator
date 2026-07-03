package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.*;

import java.util.UUID;

public class ComponentGroup implements Identifiable {
    private final int amount;
    private final Component component;
    private final String id;

    public ComponentGroup(String id, Component component, int amount) {
        validateId(id);
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be a negative number");

        this.id = id;
        this.component = component;
        this.amount = amount;
    }

    //===== Public methods =====
    public int calculateSu() {
        if (component instanceof Consumer c)
            return c.getSuConsumption() * amount;
        if (component instanceof Generator g)
            return g.getSuProduction() * amount;
        throw new IllegalArgumentException("Unknown component type");
    }

    public int getAmount() {
        return amount;
    }

    public Component getComponent() {
        return component;
    }

    public ComponentType getComponentType() {
        return ComponentType.of(component);
    }

    @Override
    public String getId() {
        return id;
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public boolean isConsumer() {
        return component instanceof Consumer;
    }

    public static final class Builder {
        private int amount;
        private Component component;
        private String id;

        public Builder() {
        }

        public Builder(ComponentGroup other) {
            this.amount = other.amount;
            this.component = other.component;
            this.id = other.id;
        }

        //===== Static methods =====
        public static Builder aComponentGroup() {
            return new Builder();
        }

        //===== Public methods =====
        public ComponentGroup build() {
            return new ComponentGroup(id, component, amount);
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder withComponent(Component component) {
            this.component = component;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }
    }
}
