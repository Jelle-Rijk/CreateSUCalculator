package org.jellerijk.mccreatecalc.presentation.generatorEntry;

import org.jellerijk.mccreatecalc.entities.GeneratorEntry;

public class GeneratorEntryInteractor {
    private final GeneratorEntryModel model;

    public GeneratorEntryInteractor(GeneratorEntryModel model) {
        this.model = model;
    }

    public void setEntry(GeneratorEntry entry) {
        model.setImagePath(entry.getGenerator().getImg().orElseThrow());
        model.setAmount(entry.getAmount());
        model.suProducedProperty().set(entry.calculateSUProduced());
    }
}
