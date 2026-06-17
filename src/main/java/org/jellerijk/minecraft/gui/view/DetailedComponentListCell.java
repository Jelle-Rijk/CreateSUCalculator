package org.jellerijk.minecraft.gui.view;

import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jellerijk.minecraft.gui.viewModel.StressNetworkComponentViewModel;
import org.jellerijk.minecraft.util.fxlib.Labels;

public class DetailedComponentListCell extends ListCell<StressNetworkComponentViewModel> {
    private final Node graphic;

    public DetailedComponentListCell() {
        graphic = buildGraphic();
        graphicProperty().bind(Bindings.createObjectBinding(() -> isEmpty() ? null : graphic, emptyProperty()));
        }

    private Node buildGraphic() {
        return buildNameLabel();
    }

    private Node buildNameLabel() {
        Label lbl = Labels.boundLabel(Bindings.createStringBinding(() -> itemProperty().get()
                .getName(), itemProperty()));
        ImageView iv = new ImageView();
        iv.setFitWidth(32);
        iv.setPreserveRatio(true);
        iv.imageProperty()
                .bind(Bindings.createObjectBinding(() -> new Image(itemProperty().get().getImgPath()), itemProperty()));
        lbl.setGraphic(iv);
        return lbl;
    }
}
