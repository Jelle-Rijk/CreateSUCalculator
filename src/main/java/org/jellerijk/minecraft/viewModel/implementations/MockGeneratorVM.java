package org.jellerijk.minecraft.viewModel.implementations;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jellerijk.minecraft.viewModel.GeneratorEntryViewModel;

public class MockGeneratorVM implements GeneratorEntryViewModel {
    private final StringProperty name = new SimpleStringProperty("Small water wheel");
    private final IntegerProperty amount = new SimpleIntegerProperty(3);
    private final IntegerProperty suPerUnit = new SimpleIntegerProperty(256);
    private final IntegerProperty totalSu = new SimpleIntegerProperty(3 * 256);
    private final StringProperty imagePath = new SimpleStringProperty("/assets/icons/components/create_large_water_wheel.png");

    public MockGeneratorVM() {
    }

    public MockGeneratorVM(String name) {
        this.name.set(name);
        String baseURI = "/assets/icons/components/create_";
        this.imagePath.set(baseURI + name.toLowerCase().replace(" ", "_") + ".png");
    }

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
