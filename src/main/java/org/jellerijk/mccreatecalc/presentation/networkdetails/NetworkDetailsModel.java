package org.jellerijk.mccreatecalc.presentation.networkdetails;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;

import java.util.Collection;

public class NetworkDetailsModel {
    private final ObservableList<ComponentGroupDTO> consumers = FXCollections.observableArrayList();
    private final ObservableList<ComponentGroupDTO> generators = FXCollections.observableArrayList();
    private final StringProperty networkName = new SimpleStringProperty();
    private final BooleanProperty networkSelected = new SimpleBooleanProperty();
    private final IntegerProperty suBalance = new SimpleIntegerProperty();
    private final IntegerProperty suConsumed = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();

//===== Public methods =====
    public ObservableList<ComponentGroupDTO> getConsumers() {
        return consumers;
    }

    public ObservableList<ComponentGroupDTO> getGenerators() {
        return generators;
    }

    public boolean isNetworkSelected() {
        return networkSelected.get();
    }

    public StringProperty networkNameProperty() {
        return networkName;
    }

    public BooleanProperty networkSelectedProperty() {
        return networkSelected;
    }

    public void setConsumers(Collection<ComponentGroupDTO> consumers) {
        this.consumers.setAll(consumers);
    }

    public void setGenerators(Collection<ComponentGroupDTO> generators) {
        this.generators.setAll(generators);
    }

    public void setNetworkSelected(boolean selected) {
        networkSelected.set(selected);
    }

    public IntegerProperty suBalanceProperty() {
        return suBalance;
    }

    public IntegerProperty suConsumedProperty() {
        return suConsumed;
    }

    public IntegerProperty suProducedProperty() {
        return suProduced;
    }

}
