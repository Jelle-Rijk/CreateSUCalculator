package org.jellerijk.mccreatecalc.presentation.generatorEntry;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;
import org.jellerijk.mccreatecalc.util.fxlib.TextFields;


public class GeneratorEntryViewBuilder {
    private static final String ICON_DIRECTORY = "assets/icons/components/";
    private final GeneratorEntryModel model;

    public GeneratorEntryViewBuilder(GeneratorEntryModel model) {
        this.model = model;
    }

    public Node getView() {
        return HBoxes.aligned(Pos.CENTER_LEFT, 5, buildAmountInput(), buildSUDisplay());
    }

    public Node buildIcon() {
        ImageView iv = new ImageView();
        iv.setPreserveRatio(true);
        iv.setFitHeight(32);
        iv.imageProperty()
                .bind(Bindings.createObjectBinding(() -> model.getImagePath() == null ? null : new Image(ICON_DIRECTORY + model.getImagePath()), model.imagePathProperty()));
        return iv;
    }

    public Node buildAmountInput() {
        Label lblMultiply = new Label("X");
        TextField amountInput = TextFields.numericalField(model.amountProperty());
        amountInput.setMaxWidth(40);
        amountInput.setAlignment(Pos.CENTER);
        return HBoxes.aligned(Pos.CENTER_LEFT, 3, buildIcon(), lblMultiply, amountInput);
    }

    public Node buildSUDisplay() {
        return Labels.integerDisplay(model.suProducedProperty(), "green-text");
    }
}
