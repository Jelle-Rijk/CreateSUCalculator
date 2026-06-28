package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;

public class ComponentOptionListCell extends ListCell<ComponentOption> {
    private final Node graphic;

    public ComponentOptionListCell() {
        ImageView icon = new ImageView();
        icon.setFitWidth(18);
        icon.setPreserveRatio(true);
        icon.imageProperty()
                .bind(Bindings.createObjectBinding(() -> getItem() == null ? null : new Image("assets/icons/components/" + getItem().img()), itemProperty()));
        Label name = Labels.boundLabel(Bindings.createStringBinding(() -> getItem() == null ? null : getItem().name(), itemProperty()));
        graphic = HBoxes.aligned(Pos.CENTER_LEFT, 2, icon, name);
    }

    @Override
    protected void updateItem(ComponentOption item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null)
            setGraphic(null);
        else
            setGraphic(graphic);
    }
}
