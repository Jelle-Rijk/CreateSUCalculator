package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;

import java.util.Collection;

public class GeneratorSelectorModel {
    private final ObservableList<ComponentOption> componentOptions = FXCollections.observableArrayList();
    private final ObjectProperty<ComponentOption> selectedComponentOption = new SimpleObjectProperty<>();
    private final BooleanProperty selected = new SimpleBooleanProperty();
    private final IntegerProperty amount = new SimpleIntegerProperty();
    private final BooleanProperty addingDisabled = new SimpleBooleanProperty();

    public ComponentOption getSelectedComponentOption() {
        return selectedComponentOption.get();
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

    public ObservableList<ComponentOption> getComponentOptions() {
        return componentOptions;
    }

    public void setComponentOptions(Collection<ComponentOption> componentOptions) {
        this.componentOptions.setAll(componentOptions);
    }

    public ObjectProperty<ComponentOption> selectedComponentOptionProperty() {
        return selectedComponentOption;
    }
}
