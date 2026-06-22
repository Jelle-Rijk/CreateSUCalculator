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
        TextField amount = TextFields.numericalField(model.amountProperty());
        Button btnAdd = new Button("+");
        btnAdd.setOnAction(_ -> addGeneratorHandler.run());
        return HBoxes.aligned(Pos.CENTER_LEFT, 3, buildGeneratorSelector(), amount, btnAdd);
    }
}
