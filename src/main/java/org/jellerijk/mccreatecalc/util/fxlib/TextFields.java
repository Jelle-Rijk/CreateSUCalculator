package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;

public abstract class TextFields {
    public static TextField bidirectionallyBound(StringProperty property, String... styleClasses) {
        TextField txf = new TextField();
        txf.textProperty().bindBidirectional(property);
        txf.getStyleClass().addAll(styleClasses);
        return txf;
    }

    public static TextField numericalField(IntegerProperty property, String... styleClasses) {
        TextField txf = new TextField();
        txf.textProperty().bindBidirectional(property, new NumberStringConverter());
        txf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[0-9]*"))
                return change;
            return null;
        }));
        txf.getStyleClass().addAll(styleClasses);
        return txf;
    }
}
