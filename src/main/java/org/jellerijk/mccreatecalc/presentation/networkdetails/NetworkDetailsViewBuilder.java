package org.jellerijk.mccreatecalc.presentation.networkdetails;


import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;

public class NetworkDetailsViewBuilder implements Builder<Region> {
    private final NetworkDetailsModel model;
    private final Node generatorSelectorContent;

    public NetworkDetailsViewBuilder(NetworkDetailsModel model,
                                     Node generatorSelectorContent) {
        this.model = model;
        this.generatorSelectorContent = generatorSelectorContent;
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
        Node generators = buildComponentOverview("Generators", model.suProducedProperty(), generatorSelectorContent);
        Node consumers = buildComponentOverview("Consumers", model.suConsumedProperty(),
                new Label("Consumer selector here"));
        return HBoxes.aligned(Pos.TOP_CENTER, 5, generators, consumers);
    }

    private Node buildComponentOverview(String title, IntegerProperty su, Node selector) {
        BorderPane container = new BorderPane();
        container.setTop(buildComponentOverviewHeader(title, selector));
        container.setCenter(buildComponentOverviewCenter());
        container.setBottom(buildComponentOverviewFooter(su));
        return container;
    }


    private Node buildComponentOverviewCenter() {
        return new ListView<>();
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
