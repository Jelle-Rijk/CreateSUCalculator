package domain.component;

import domain.component.data.ComponentData;

public abstract class ComponentFacade<T extends ComponentData> {
    private final T data;

    public ComponentFacade(T data) {
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
