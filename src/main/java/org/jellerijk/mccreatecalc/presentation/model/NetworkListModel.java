package org.jellerijk.mccreatecalc.presentation.model;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;

import java.util.Collection;

public class NetworkListModel {
    private final ObservableList<NetworkInfo> networks = FXCollections.observableArrayList();
    private final ObjectProperty<NetworkInfo> selectedNetwork = new SimpleObjectProperty<>();

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
}
