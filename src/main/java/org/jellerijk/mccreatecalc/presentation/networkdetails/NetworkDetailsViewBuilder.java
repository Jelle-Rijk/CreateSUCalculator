package org.jellerijk.mccreatecalc.presentation.networkdetails;


import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;

public class NetworkDetailsViewBuilder implements Builder<Region> {
    private final Node consumerSelectorContent;
    private final UseCaseFactory factory;
    private final NetworkDetailsModel model;
    private final Node generatorSelectorContent;

    public NetworkDetailsViewBuilder(NetworkDetailsModel model,
                                     Node generatorSelectorContent, Node consumerSelectorContent, UseCaseFactory factory) {
        this.model = model;
        this.generatorSelectorContent = generatorSelectorContent;
        this.consumerSelectorContent = consumerSelectorContent;
        this.factory = factory;
    }

    @Override
    public Region build() {
        BorderPane bp = new BorderPane();
        bp.setTop(buildHeader());
        return bp;
    }

    private Node buildHeader() {
        Label networkName = Labels.boundLabel(model.networkNameProperty(), "network-details__header__name");
        return new VBox(5, networkName, buildSUOverview(), buildComponentLists());
    }

    private Node buildSUOverview() {
        Node suBalance = HBoxes.aligned(Pos.CENTER_LEFT, 1,
                new Label("Balance:", Labels.balanceLabel(model.suBalanceProperty())));
        return HBoxes.aligned(Pos.CENTER_LEFT, 5, suBalance);
    }

    private Node buildComponentLists() {
        Node generators = buildComponentOverview("Generators", model.suProducedProperty(), generatorSelectorContent, model.getGenerators());
        Node consumers = buildComponentOverview("Consumers", model.suConsumedProperty(),
                consumerSelectorContent, model.getConsumers());
        return HBoxes.aligned(Pos.TOP_CENTER, 5, generators, consumers);
    }

    private Node buildComponentOverview(String title, IntegerProperty su, Node selector, ObservableList<ComponentGroupDTO> componentGroupList) {
        BorderPane container = new BorderPane();
        container.setTop(buildComponentOverviewHeader(title, selector));
        container.setCenter(buildComponentOverviewCenter(componentGroupList));
        container.setBottom(buildComponentOverviewFooter(su));
        return container;
    }


    private Node buildComponentOverviewCenter(ObservableList<ComponentGroupDTO> groupList) {
        ListView<ComponentGroupDTO> listView = new ListView<>();
        listView.setCellFactory((_) -> new ComponentGroupListCell(factory));
        listView.setItems(groupList);
        return listView;
    }

    private Node buildComponentOverviewHeader(String title, Node selector) {
        Label lblTitle = new Label(title);
        return new VBox(3, lblTitle, selector);
    }

    private Node buildComponentOverviewFooter(IntegerProperty su) {
        Label lbl = Labels.integerDisplay(su);
        return HBoxes.aligned(Pos.CENTER_RIGHT, 5, lbl);
    }
}
