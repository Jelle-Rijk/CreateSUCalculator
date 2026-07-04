package org.jellerijk.mccreatecalc.util.fxlib;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public abstract class Labels {
    public static Label boundLabel(ObservableStringValue text, String... styleClasses) {
        Label lbl = new Label();
        lbl.textProperty().bind(text);
        lbl.getStyleClass().addAll(styleClasses);
        return lbl;
    }

    public static Label integerDisplay(ObservableIntegerValue integerValue, String... styleClasses) {
        Label lbl = new Label();
        lbl.textProperty().bind(Bindings.createStringBinding(() -> integerValue.getValue().toString(), integerValue));
        lbl.getStyleClass().addAll(styleClasses);
        return lbl;
    }

    public static Label balanceLabel(IntegerProperty number, String... styleClasses) {
        Label lbl = new Label();
        lbl.textProperty().bind(number.asString());
        lbl.textFillProperty()
                .bind(Bindings.createObjectBinding(() -> number.get() == 0 ? Color.BLACK : number.get() < 0 ? Color.RED : Color.GREEN, number));
        lbl.getStyleClass().addAll(styleClasses);
        return lbl;
    }
}
