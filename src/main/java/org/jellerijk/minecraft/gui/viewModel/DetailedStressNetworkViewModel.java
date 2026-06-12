package org.jellerijk.minecraft.gui.viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.minecraft.dtos.StressNetworkDTO;

public class DetailedStressNetworkViewModel {
    private final ObservableList<StressNetworkComponentViewModel> consumers = FXCollections.observableArrayList();
    private final ObservableList<StressNetworkComponentViewModel> generators = FXCollections.observableArrayList();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final BooleanProperty overstressed = new SimpleBooleanProperty();
    private final IntegerProperty suBalance = new SimpleIntegerProperty();
    private final IntegerProperty suConsumed = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();

    //===== Public methods =====
    public ObservableList<StressNetworkComponentViewModel> getConsumers() {
        return consumers;
    }

    public ObservableList<StressNetworkComponentViewModel> getGenerators() {
        return generators;
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
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

    public StringProperty idProperty() {
        return id;
    }

    public boolean isOverstressed() {
        return overstressed.get();
    }

    public StringProperty nameProperty() {
        return name;
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

//===== Private methods =====

    /**
     * Sets the properties for the viewmodel based on a supplied <code>dto</code>.
     *
     * @param dto StressNetworkDTO containing the data for the properties.
     */
    private void setProperties(StressNetworkDTO dto) {
        id.set(dto.id());
        name.set(dto.name());
        generators.setAll(dto.generators()
                .entrySet()
                .stream()
                .map(e -> new StressNetworkComponentViewModel(e.getKey(), e.getValue()))
                .toList());
        consumers.setAll(dto.consumers()
                .entrySet()
                .stream()
                .map(e -> new StressNetworkComponentViewModel(e.getKey(), e.getValue()))
                .toList());
        suConsumed.set(dto.suConsumed());
        suProduced.set(dto.suProduced());
        suBalance.set(dto.suBalance());
        overstressed.set(dto.overstressed());
    }
}
