package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.application.usecases.network.read.NetworkInfo;

import java.util.function.Consumer;

public class NetworkListViewBuilder implements Builder<Region> {
    private final NetworkListModel model;
    private final Runnable createNetworkHandler;
    private final Consumer<String> selectNetworkHandler;

    public NetworkListViewBuilder(NetworkListModel model, Runnable createNetworkHandler, Consumer<String> selectNetworkHandler) {
        this.model = model;
        this.createNetworkHandler = createNetworkHandler;
        this.selectNetworkHandler = selectNetworkHandler;
    }

    @Override
    public Region build() {
        VBox container = new VBox();
        container.getChildren().add(buildNetworkListView());
        container.getChildren().add(buildInputField());
        return container;
    }

    private ListView<NetworkInfo> buildNetworkListView() {
        ListView<NetworkInfo> lv = new ListView<>();
        lv.setItems(model.getNetworks());
        lv.setCellFactory(_ -> new NetworkListViewCell());
        model.bindSelectedNetwork(Bindings.createObjectBinding(() -> lv.getSelectionModel().getSelectedItem(),
                lv.getSelectionModel().selectedItemProperty()));
        lv.getSelectionModel()
                .selectedItemProperty()
                .addListener((_, _, selected) -> selectNetworkHandler.accept(selected.id()));
        return lv;
    }

    private Node buildInputField() {
        TextField txf = new TextField();
        txf.textProperty().bindBidirectional(model.userInputProperty());
        txf.editableProperty().bind(model.userInputEnabledProperty());
        txf.disableProperty().bind(model.userInputEnabledProperty().not());
        txf.setOnAction((_) -> createNetworkHandler.run());
        return txf;
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
