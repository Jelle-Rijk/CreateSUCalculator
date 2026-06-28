package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;
import org.jellerijk.mccreatecalc.util.fxlib.TextFields;

public class GeneratorSelectorViewBuilder {

    private final GeneratorSelectorModel model;
    private final Runnable addGeneratorHandler;

    public GeneratorSelectorViewBuilder(GeneratorSelectorModel model, Runnable addGeneratorHandler) {
        this.model = model;
        this.addGeneratorHandler = addGeneratorHandler;
    }

    public Node build() {
        return buildAddComponentPane();
    }

    private ComboBox<GeneratorOption> buildGeneratorSelector() {
        ComboBox<GeneratorOption> optionComboBox = new ComboBox<>();
        optionComboBox.setCellFactory(_ -> new GeneratorOptionListCell());
        optionComboBox.setButtonCell(new GeneratorOptionListCell());
        optionComboBox.setItems(model.getGeneratorOptions());
        optionComboBox.valueProperty().bindBidirectional(model.selectedGeneratorOptionProperty());
        return optionComboBox;
    }

    private Node buildAddComponentPane() {
        Node amount = buildAmountField();
        Node btnAdd = buildAddButton();
        return HBoxes.aligned(Pos.CENTER_LEFT, 3, buildGeneratorSelector(), amount, btnAdd);
    }

    private Node buildAmountField() {
        TextField amount = TextFields.numericalField(model.amountProperty());
        amount.editableProperty().bind(model.addingDisabledProperty().not().and(model.selectedProperty()));
        amount.disableProperty().bind(amount.editableProperty().not());
        return amount;
    }

    private Node buildAddButton() {
        Button btnAdd = new Button("+");
        btnAdd.setOnAction(_ -> addGeneratorHandler.run());
        btnAdd.disableProperty().bind(model.addingDisabledProperty().or(model.selectedProperty().not()));
        return btnAdd;
    }
}
