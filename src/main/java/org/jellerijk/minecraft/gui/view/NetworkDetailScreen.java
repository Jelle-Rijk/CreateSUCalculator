package org.jellerijk.minecraft.gui.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.jellerijk.minecraft.gui.viewModel.DetailedStressNetworkViewModel;
import org.jellerijk.minecraft.gui.viewModel.StressNetworkComponentViewModel;
import org.jellerijk.minecraft.util.fxlib.Labels;

public class NetworkDetailScreen extends BorderPane {
    private final DetailedStressNetworkViewModel viewModel;

    public NetworkDetailScreen(DetailedStressNetworkViewModel viewModel) {
        this.viewModel = viewModel;
        buildLayout();
    }

    private void buildLayout() {
        setCenter(buildComponentLists());
        setBottom(buildNetworkDetails());
    }

    private Node buildComponentLists() {
        HBox lists = new HBox(10);
        lists.getChildren().add(buildComponentListView(viewModel.getGenerators()));
        lists.getChildren().add(buildComponentListView(viewModel.getConsumers()));
        return lists;
    }

    private ListView<StressNetworkComponentViewModel> buildComponentListView(ObservableList<StressNetworkComponentViewModel> components) {
        ListView<StressNetworkComponentViewModel> lv = new ListView<>();
        lv.setItems(components);
        lv.setCellFactory(_ -> new DetailedComponentListCell());
        return lv;
    }

    private Node buildNetworkDetails() {
        Label lblBalance = new Label("Balance: ");
        Label lblBalanceValue = Labels.balanceLabel(viewModel.suBalanceProperty());
        return new HBox(lblBalance, lblBalanceValue);
    }


}
