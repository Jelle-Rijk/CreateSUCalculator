package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;

import java.util.Collection;

public class NetworkListModel {
    private final ObservableList<NetworkInfo> networks = FXCollections.observableArrayList();
    private final ObjectProperty<NetworkInfo> selectedNetwork = new SimpleObjectProperty<>();
    private final StringProperty userInput = new SimpleStringProperty();
    private final BooleanProperty userInputEnabled = new SimpleBooleanProperty(true);

    public boolean isUserInputEnabled() {
        return userInputEnabled.get();
    }

    public BooleanProperty userInputEnabledProperty() {
        return userInputEnabled;
    }

    public void setUserInputEnabled(Boolean userInputEnabled) {
        this.userInputEnabled.set(userInputEnabled);
    }

    public ObservableList<NetworkInfo> getNetworks() {
        return networks;
    }

    public NetworkInfo getSelectedNetwork() {
        return selectedNetwork.get();
    }

    public ObjectProperty<NetworkInfo> selectedNetworkProperty() {
        return selectedNetwork;
    }

    public void setSelectedNetwork(NetworkInfo selectedNetwork) {
        this.selectedNetwork.set(selectedNetwork);
    }

    public final void setNetworks(
            Collection<NetworkInfo> networks) {
        this.networks.setAll(networks);
    }

    public void bindSelectedNetwork(ObjectBinding<NetworkInfo> binding) {
        selectedNetwork.bind(binding);
    }

    public String getUserInput() {
        return userInput.get();
    }

    public StringProperty userInputProperty() {
        return userInput;
    }

    public void setUserInput(String input) {
        userInput.set(input);
    }
}
