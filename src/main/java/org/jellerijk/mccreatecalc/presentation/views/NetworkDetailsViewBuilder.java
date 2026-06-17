package org.jellerijk.mccreatecalc.presentation.views;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.util.Builder;
import org.jellerijk.mccreatecalc.presentation.model.NetworkDetailsModel;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;

public class NetworkDetailsViewBuilder implements Builder<Region> {
    private final NetworkDetailsModel model;

    public NetworkDetailsViewBuilder(NetworkDetailsModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        BorderPane bp = new BorderPane();
        bp.setTop(buildHeader());
        return bp;
    }

    private Node buildHeader() {
        Label networkName = Labels.boundLabel(model.networkNameProperty());
        Label suBalance = Labels.balanceLabel(model.suBalanceProperty());
        return HBoxes.aligned(Pos.CENTER_LEFT, 5, networkName, suBalance);
    }
}
