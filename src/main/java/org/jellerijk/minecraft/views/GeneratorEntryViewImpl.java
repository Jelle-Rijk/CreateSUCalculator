package org.jellerijk.minecraft.views;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.jellerijk.minecraft.viewModel.GeneratorEntryViewModel;
import org.jellerijk.minecraft.util.fxlib.HBoxes;
import org.jellerijk.minecraft.util.fxlib.Labels;
import org.jellerijk.minecraft.util.fxlib.TextFields;

public class GeneratorEntryViewImpl extends HBox {
    private final GeneratorEntryViewModel viewModel;

    public GeneratorEntryViewImpl(GeneratorEntryViewModel viewModel) {
        this.viewModel = viewModel;
        buildLayout();
    }

    private void buildLayout() {
        getChildren().add(new ComponentView(viewModel));
        getChildren().add(buildStats());
        getChildren().add(buildAmountInput());
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);
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
