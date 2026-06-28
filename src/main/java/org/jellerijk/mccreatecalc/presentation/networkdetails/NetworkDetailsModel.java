package org.jellerijk.mccreatecalc.presentation.networkdetails;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetworkDetailsModel {
    private final ObservableList<String> consumers = FXCollections.observableArrayList(); // TODO implement
    private final ObservableList<String> generators = FXCollections.observableArrayList(); // TODO implement
    private final StringProperty networkName = new SimpleStringProperty();
    private final BooleanProperty overstressed = new SimpleBooleanProperty();
    private final IntegerProperty suBalance = new SimpleIntegerProperty();
    private final IntegerProperty suConsumed = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();

    public String getNetworkName() {
        return networkName.get();
    }

    public int getSuBalance() {
        return suBalance.get();
    }

    public int getSuConsumed() {
        return suConsumed.get();
    }

    public int getSuProduced() {
        return suProduced.get();
    }

    public boolean isOverstressed() {
        return overstressed.get();
    }

    public StringProperty networkNameProperty() {
        return networkName;
    }

    public BooleanProperty overstressedProperty() {
        return overstressed;
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
