package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class GeneratorSelectorModel {
    private final ObservableList<GeneratorOption> generatorOptions = FXCollections.observableArrayList();
    private final ObjectProperty<GeneratorOption> selectedGeneratorOption = new SimpleObjectProperty<>();
    private final BooleanProperty selected = new SimpleBooleanProperty();
    private final IntegerProperty amount = new SimpleIntegerProperty();
    private final BooleanProperty addingDisabled = new SimpleBooleanProperty();

    public GeneratorOption getSelectedGeneratorOption() {
        return selectedGeneratorOption.get();
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAddingDisabled(boolean enabled) {
        addingDisabled.set(enabled);
    }

    public BooleanProperty addingDisabledProperty() {
        return addingDisabled;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public ObservableList<GeneratorOption> getGeneratorOptions() {
        return generatorOptions;
    }

    public void setGeneratorOptions(Collection<GeneratorOption> generatorOptions) {
        this.generatorOptions.setAll(generatorOptions);
    }

    public ObjectProperty<GeneratorOption> selectedGeneratorOptionProperty() {
        return selectedGeneratorOption;
    }
}
