package org.jellerijk.minecraft.model.component;

import org.jellerijk.minecraft.model.component.data.GeneratorData;

public class Generator extends Component<GeneratorData> {
    public Generator(GeneratorData data) {
        super(data);
    }

    public int calculateSUPerUnit() {
        GeneratorData data = getData();
        return data.getStressImpact() * data.getRpm();
    }
}
