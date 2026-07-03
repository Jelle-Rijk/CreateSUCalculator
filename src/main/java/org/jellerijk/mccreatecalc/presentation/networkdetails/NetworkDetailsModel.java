package org.jellerijk.mccreatecalc.presentation.networkdetails;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

import java.util.Collection;

public class NetworkDetailsModel {
    private final ObservableList<ComponentGroupDTO> consumers = FXCollections.observableArrayList();
    private final ObservableList<ComponentGroupDTO> generators = FXCollections.observableArrayList();
    private final StringProperty networkName = new SimpleStringProperty();
    private final BooleanProperty overstressed = new SimpleBooleanProperty();
    private final IntegerProperty suBalance = new SimpleIntegerProperty();
    private final IntegerProperty suConsumed = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();

    public ObservableList<ComponentGroupDTO> getConsumers() {
        return consumers;
    }

    public ObservableList<ComponentGroupDTO> getGenerators() {
        return generators;
    }

    public void setConsumers(Collection<ComponentGroupDTO> consumers) {
        this.consumers.setAll(consumers);
    }

    public void addConsumer(ComponentGroupDTO consumer) {
        if (consumer.type() != ComponentType.CONSUMER)
            throw new IllegalArgumentException("The added component group was a generator, only consumers are allowed.");
        consumers.add(consumer);
    }

    public void removeConsumer(ComponentGroupDTO consumer) {
        consumers.remove(consumer);
    }

    public void setGenerators(Collection<ComponentGroupDTO> generators) {
        this.generators.setAll(generators);
    }

    public void addGenerator(ComponentGroupDTO generator) {
        if (generator.type() == ComponentType.CONSUMER)
            throw new IllegalArgumentException("The added component group was a consumer, not a generator.");
        this.generators.add(generator);
    }

    public void removeGenerator(ComponentGroupDTO generator) {
        this.generators.remove(generator);
    }

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
