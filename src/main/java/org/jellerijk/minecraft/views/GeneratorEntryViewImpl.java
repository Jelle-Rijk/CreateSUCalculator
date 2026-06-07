package org.jellerijk.minecraft.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.jellerijk.minecraft.presenters.GeneratorViewModel;
import org.jellerijk.minecraft.util.fxlib.HBoxes;
import org.jellerijk.minecraft.util.fxlib.ImageViews;
import org.jellerijk.minecraft.util.fxlib.Labels;
import org.jellerijk.minecraft.util.fxlib.TextFields;

public class GeneratorEntryViewImpl extends HBox {
    private final GeneratorViewModel viewModel;

    public GeneratorEntryViewImpl(GeneratorViewModel viewModel) {
        this.viewModel = viewModel;
        buildLayout();
    }

    private void buildLayout() {
        getChildren().add(buildName());
        getChildren().add(buildStats());
        getChildren().add(buildAmountInput());
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);
    }

    private Node buildName() {
        Label lbl = Labels.boundLabel(viewModel.nameProperty(), "bold");
        lbl.setGraphic(ImageViews.squareIcon(viewModel.getImagePath()));
        return lbl;
    }

    private Node buildStats() {
        Label lbl1 = new Label("Total output: ");
        Label lbl2 = Labels.balanceLabel(viewModel.totalSuProperty(), "bold");
        return HBoxes.aligned(Pos.CENTER_LEFT, 0, lbl1, lbl2);
    }

    private Node buildAmountInput() {
        return TextFields.numericalField(viewModel.amountProperty());
    }


}
