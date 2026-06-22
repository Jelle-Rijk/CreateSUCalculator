package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

public class GeneratorSelectorModel {
    private final ObservableList<GeneratorOption> generatorOptions = FXCollections.observableArrayList();
    private final ObjectProperty<GeneratorOption> selectedGeneratorOption = new SimpleObjectProperty<>();
    private final IntegerProperty amount = new SimpleIntegerProperty();

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
