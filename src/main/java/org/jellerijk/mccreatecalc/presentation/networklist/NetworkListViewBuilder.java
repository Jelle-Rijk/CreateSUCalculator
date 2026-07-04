package org.jellerijk.mccreatecalc.presentation.networklist;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.application.dto.NetworkInfo;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

import java.util.function.Consumer;

public class NetworkListViewBuilder implements Builder<Region> {
    private final NetworkListModel model;
    private final Runnable createNetworkHandler;
    private final Consumer<String> selectNetworkHandler;
    private final Consumer<String> deleteNetworkHandler;

    public NetworkListViewBuilder(NetworkListModel model, Runnable createNetworkHandler, Consumer<String> selectNetworkHandler, Consumer<String> deleteNetworkHandler) {
        this.model = model;
        this.createNetworkHandler = createNetworkHandler;
        this.selectNetworkHandler = selectNetworkHandler;
        this.deleteNetworkHandler = deleteNetworkHandler;
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
        lv.setCellFactory(_ -> new NetworkListViewCell(deleteNetworkHandler));
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
        private final Node graphic;
        private final Button btnDelete;
        private final Consumer<String> deleteNetworkHandler;

        public NetworkListViewCell(Consumer<String> deleteNetworkHandler) {
            this.deleteNetworkHandler = deleteNetworkHandler;
            Label lblName = new Label();
            btnDelete = new Button("X");
            graphic = HBoxes.aligned(Pos.CENTER_LEFT, 5, lblName, btnDelete);
            lblName.textProperty()
                    .bind(Bindings.createStringBinding(() -> itemProperty().isNull().get() ? null : getItem().name(),
                            itemProperty()));
        }

        @Override
        protected void updateItem(NetworkInfo item, boolean empty) {
            super.updateItem(item, empty);
            if (empty)
                setGraphic(null);
            else {
                btnDelete.setOnAction((_) -> deleteNetworkHandler.accept(item.id()));
                setGraphic(graphic);
            }
        }
    }
}
