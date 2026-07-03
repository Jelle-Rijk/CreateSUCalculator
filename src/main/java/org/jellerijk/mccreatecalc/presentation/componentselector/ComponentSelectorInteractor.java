package org.jellerijk.mccreatecalc.presentation.componentselector;

import org.jellerijk.mccreatecalc.application.services.ComponentOption;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ComponentSelectorInteractor {
    private final Consumer<ComponentOption> addGeneratorGroup;
    private final ComponentSelectorModel model;
    private final Supplier<List<ComponentOption>> optionsSupplier;

    public ComponentSelectorInteractor(ComponentSelectorModel model, Consumer<ComponentOption> addGeneratorGroup, Supplier<List<ComponentOption>> optionsSupplier) {
        this.model = model;
        this.addGeneratorGroup = addGeneratorGroup;
        this.optionsSupplier = optionsSupplier;
        bindModelProperties();
    }

    public void addGenerator() {
        addGeneratorGroup.accept(model.getSelectedComponentOption());
    }

    public void loadComponentOptions() {
        model.setComponentOptions(optionsSupplier.get());
    }

    private void bindModelProperties() {
        model.selectedProperty().bind(model.selectedComponentOptionProperty().isNotNull());
    }
}
