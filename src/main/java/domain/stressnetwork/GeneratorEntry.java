package domain.system;

import domain.component.GeneratorFacade;

public class GeneratorEntry extends ComponentEntry<GeneratorFacade> {

    public GeneratorEntry(GeneratorFacade type, int amount) {
        super(type, amount);
    }

    @Override
    public int calculateSUPerUnit() {
        return getComponent().calculateSUPerUnit();
    }
}
