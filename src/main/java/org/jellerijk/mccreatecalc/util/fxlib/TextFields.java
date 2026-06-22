package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public abstract class TextFields {
    public static TextField numericalField(IntegerProperty property, String... styleClasses) {
        TextField txf = new TextField();
        txf.setTextFormatter(createPositiveIntegerFormatter(property));
        txf.getStyleClass().addAll(styleClasses);
        return txf;
    }

    public static TextFormatter<Integer> createPositiveIntegerFormatter(IntegerProperty valueProperty) {
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
            if (change.getControlNewText().matches("[0-9]*"))
                return change;
            return null;
        });
        textFormatter.valueProperty().bindBidirectional(valueProperty.asObject());
        return textFormatter;
    }
}
