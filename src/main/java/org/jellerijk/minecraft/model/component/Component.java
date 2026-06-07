package org.jellerijk.minecraft.model.component;

import org.jellerijk.minecraft.model.component.data.ComponentData;

public abstract class Component<T extends ComponentData> {
    private final T data;

    public Component(T data) {
        if (data == null)
            throw new IllegalArgumentException("Component data was null");
        this.data = data;
    }

    public int getStressImpact() {
        return getData().getStressImpact();
    }

    protected T getData() {
        return data;
    }
}
