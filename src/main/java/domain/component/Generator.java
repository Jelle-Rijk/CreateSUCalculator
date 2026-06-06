package domain.component;

import domain.component.data.GeneratorData;

public class GeneratorFacade extends ComponentFacade<GeneratorData> {
    public GeneratorFacade(GeneratorData data) {
        super(data);
    }

    public int calculateSUPerUnit() {
        GeneratorData data = getData();
        return data.getStressImpact() * data.getRpm();
    }
}
