package org.jellerijk.mccreatecalc.presentation.componentgroup;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.Labels;
import org.jellerijk.mccreatecalc.util.fxlib.TextFields;

public class ComponentGroupViewBuilder {
    private static final double INPUT_FIELD_WIDTH = 30;

    private final ComponentGroupModel model;
    private final Runnable onDelete;
    private final Runnable onSubmit;

    public ComponentGroupViewBuilder(ComponentGroupModel model, Runnable onDelete, Runnable onSubmit) {
        this.model = model;
        this.onDelete = onDelete;
        this.onSubmit = onSubmit;
    }

    public Node build() {
        HBox inputs = HBoxes.aligned(Pos.CENTER_LEFT, 8, buildAmountField(), buildSailsField(), buildRpmField());
        Label nameLabel = Labels.boundLabel(model.componentNameProperty());
        Label suLabel = Labels.balanceLabel(model.suProperty());
        HBox header = HBoxes.aligned(Pos.CENTER_LEFT, 10, buildIcon(), nameLabel, suLabel, buildDeleteButton());
        return new VBox(8, header, inputs);
    }

    private Node buildIcon() {
        ImageView iv = new ImageView();
        iv.imageProperty()
                .bind(Bindings.createObjectBinding(() -> model.getImage() == null ? null : new Image("/assets/icons/components/" + model.getImage()), model.imageProperty()));
        iv.setPreserveRatio(true);
        iv.setFitHeight(24);
        return iv;
    }

    private Node buildAmountField() {
        return buildNumericalField("Amount", model.componentAmountProperty(), null);
    }

    private Node buildRpmField() {
        return buildNumericalField("RPM", model.rpmProperty(), model.needsRpmProperty());
    }

    private Node buildSailsField() {
        return buildNumericalField("Sails", model.sailsProperty(), model.needsSailsProperty());
    }

    private Button buildDeleteButton() {
        Button btnDelete = new Button("X");
        btnDelete.setOnAction((_) -> onDelete.run());
        return btnDelete;
    }

    private Node buildNumericalField(String lblText, IntegerProperty intProperty, BooleanProperty visibleProperty) {
        Label lbl = new Label(lblText);
        lbl.getStyleClass().add("component-card__field-label__default");
        TextField input = TextFields.numericalField(intProperty);
        input.setOnAction((_) -> onSubmit.run());
        input.focusedProperty().addListener((_, _, focused) -> {
            if (!focused) onSubmit.run();
        });
        input.setMaxWidth(INPUT_FIELD_WIDTH);
        VBox container = new VBox(lbl, input);
        if (visibleProperty != null) {
            container.visibleProperty().bind(visibleProperty);
            container.managedProperty().bind(container.visibleProperty());
        }
        return container;
    }
}
