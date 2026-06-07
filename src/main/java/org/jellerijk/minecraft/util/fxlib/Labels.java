package org.jellerijk.minecraft.util.fxlib;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Labels {
    public static Label boundLabel(ObservableStringValue text, String... styleClasses) {
        Label lbl = new Label();
        lbl.textProperty().bind(text);
        lbl.getStyleClass().addAll(styleClasses);
        return lbl;
    }

    public static Label balanceLabel(IntegerProperty number, String... styleClasses) {
        Label lbl = new Label();
        lbl.textProperty().bind(number.asString());
        lbl.textFillProperty()
                .bind(Bindings.createObjectBinding(() -> number.get() == 0 ? Color.BLACK : number.get() < 0 ? Color.RED : Color.GREEN, number));
        number.addListener((_, _, newNumber) -> {
            int value = newNumber.intValue();
            lbl.setTextFill(Paint.valueOf(value == 0 ? "Black" : value < 0 ? "Red" : "Green"));
        });
        lbl.getStyleClass().addAll(styleClasses);
        return lbl;
    }
}
