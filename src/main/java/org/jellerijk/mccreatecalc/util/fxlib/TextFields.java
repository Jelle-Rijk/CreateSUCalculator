package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public abstract class TextFields {
    public static TextField numericalField(IntegerProperty intProperty, String... styleClasses) {
        TextField txf = styledTxf(styleClasses);

        StringConverter<Integer> blankIsZero = new StringConverter<>() {
            @Override
            public String toString(Integer integer) {
                return integer == null ? "0" : Integer.toString(integer);
            }

            @Override
            public Integer fromString(String s) {
                return s == null || s.isBlank() ? 0 : Integer.parseInt(s);
            }
        };
        TextFormatter<Integer> formatter = new TextFormatter<>(blankIsZero, intProperty.get(), change -> {
            if (change.getControlNewText().matches("[0-9]*"))
                return change;
            return null;
        });
        txf.setTextFormatter(formatter);

        ObjectProperty<Integer> intPropertyAsObject = intProperty.asObject();
        formatter.valueProperty().bindBidirectional(intPropertyAsObject);
        txf.getProperties()
                .put("intPropertyAsObjectRef", intPropertyAsObject); // Needed so that the garbage collector does not remove the property.

        return txf;
    }

    private static TextField styledTxf(String... styleClasses) {
        TextField txf = new TextField();
        txf.getStyleClass().addAll(styleClasses);
        return txf;
    }
}
