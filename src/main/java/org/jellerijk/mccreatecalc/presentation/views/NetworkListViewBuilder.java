package org.jellerijk.mccreatecalc.presentation.views;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.application.usecases.network.NetworkInfo;
import org.jellerijk.mccreatecalc.presentation.model.NetworkListModel;

public class NetworkListViewBuilder implements Builder<Region> {
    private final NetworkListModel model;

    public NetworkListViewBuilder(NetworkListModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        VBox container = new VBox();
        container.getChildren().add(buildNetworkListView());
        return container;
    }

    private ListView<NetworkInfo> buildNetworkListView() {
        ListView<NetworkInfo> lv = new ListView<>();
        lv.setItems(model.getNetworks());
        lv.setCellFactory(_ -> new NetworkListViewCell());
        model.bindSelectedNetwork(Bindings.createObjectBinding(() -> lv.getSelectionModel().getSelectedItem(),
                lv.getSelectionModel().selectedItemProperty()));
        return lv;
    }

    private static class NetworkListViewCell extends ListCell<NetworkInfo> {
        public NetworkListViewCell() {
            Label graphic = new Label();
            graphic.textProperty()
                    .bind(Bindings.createStringBinding(() -> itemProperty().isNull().get() ? null : getItem().name(),
                            itemProperty()));
            graphicProperty().bind(Bindings.createObjectBinding(() -> isEmpty() ? null : graphic, emptyProperty()));
            setText(null);
        }
    }
}
