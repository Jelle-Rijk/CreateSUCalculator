package org.jellerijk.minecraft.presenters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MockGeneratorVM implements GeneratorViewModel {
    private final StringProperty name = new SimpleStringProperty("Small water wheel");
    private final IntegerProperty amount = new SimpleIntegerProperty(3);
    private final IntegerProperty suPerUnit = new SimpleIntegerProperty(256);
    private final IntegerProperty totalSu = new SimpleIntegerProperty(3 * 256);
    private final StringProperty imagePath = new SimpleStringProperty("/assets/icons/generators/Dirt.png");

    @Override
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public IntegerProperty amountProperty() {
        return amount;
    }

    @Override
    public IntegerProperty suPerUnitProperty() {
        return suPerUnit;
    }

    @Override
    public IntegerProperty totalSuProperty() {
        return totalSu;
    }

    @Override
    public String getImagePath() {
        return imagePath.getValue();
    }


}
