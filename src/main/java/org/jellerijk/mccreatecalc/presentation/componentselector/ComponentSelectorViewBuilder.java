package org.jellerijk.mccreatecalc.presentation.componentselector;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.jellerijk.mccreatecalc.application.services.ComponentOption;
import org.jellerijk.mccreatecalc.util.fxlib.HBoxes;

public class ComponentSelectorViewBuilder {

    private final ComponentSelectorModel model;
    private final Runnable addGeneratorHandler;

    public ComponentSelectorViewBuilder(ComponentSelectorModel model, Runnable addGeneratorHandler) {
        this.model = model;
        this.addGeneratorHandler = addGeneratorHandler;
    }

    public Node build() {
        return buildAddComponentPane();
    }

    private ComboBox<ComponentOption> buildGeneratorSelector() {
        ComboBox<ComponentOption> optionComboBox = new ComboBox<>();
        optionComboBox.setCellFactory(_ -> new ComponentOptionListCell());
        optionComboBox.setButtonCell(new ComponentOptionListCell());
        optionComboBox.setItems(model.getComponentOptions());
        optionComboBox.valueProperty().bindBidirectional(model.selectedComponentOptionProperty());
        return optionComboBox;
    }

    private Node buildAddComponentPane() {
        Node generatorSelector = buildGeneratorSelector();
        HBox.setHgrow(generatorSelector, Priority.ALWAYS);
        return HBoxes.aligned(Pos.CENTER_LEFT, 3, buildGeneratorSelector(), buildAddButton());
    }

    private Node buildAddButton() {
        Button btnAdd = new Button("+");
        btnAdd.setOnAction(_ -> addGeneratorHandler.run());
        btnAdd.disableProperty().bind(model.addingDisabledProperty().or(model.selectedProperty().not()));
        return btnAdd;
    }
}
