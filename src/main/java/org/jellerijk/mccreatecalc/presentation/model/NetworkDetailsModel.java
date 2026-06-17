package org.jellerijk.mccreatecalc.presentation.model;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetworkDetailsModel {
    private final StringProperty networkName = new SimpleStringProperty();
    private final IntegerProperty suConsumed = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();
    private final IntegerProperty suBalance = new SimpleIntegerProperty();
    private final ObservableList<String> generators = FXCollections.observableArrayList(); // TODO implement
    private final ObservableList<String> consumers = FXCollections.observableArrayList(); // TODO implement
    private final BooleanProperty overstressed = new SimpleBooleanProperty();

    public String getNetworkName() {
        return networkName.get();
    }

    public StringProperty networkNameProperty() {
        return networkName;
    }

    public int getSuConsumed() {
        return suConsumed.get();
    }

    public IntegerProperty suConsumedProperty() {
        return suConsumed;
    }

    public int getSuProduced() {
        return suProduced.get();
    }

    public IntegerProperty suProducedProperty() {
        return suProduced;
    }

    public int getSuBalance() {
        return suBalance.get();
    }

    public IntegerProperty suBalanceProperty() {
        return suBalance;
    }

    public boolean isOverstressed() {
        return overstressed.get();
    }

    public BooleanProperty overstressedProperty() {
        return overstressed;
    }
}
