package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public abstract class TextFields {
    public static TextField bidirectionallyBound(StringProperty property, String... styleClasses) {
        TextField txf = new TextField();
        txf.textProperty().bindBidirectional(property);
        txf.getStyleClass().addAll(styleClasses);
        return txf;
    }

    public static TextField numericalField(IntegerProperty intProperty, String... styleClasses) {
        StringProperty intPropertyAsString = new SimpleStringProperty();
        intProperty.addListener((_, _, integer) -> intPropertyAsString.set(integer.toString()));
        intPropertyAsString.addListener((_, _, input) -> intProperty.set(Integer.parseInt(input)));

        TextField txf = bidirectionallyBound(intPropertyAsString, styleClasses);
        txf.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("[0-9]*"))
                return change;
            return null;
        }));
        return txf;
    }
}
