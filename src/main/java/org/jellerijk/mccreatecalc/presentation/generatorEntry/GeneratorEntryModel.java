package org.jellerijk.mccreatecalc.presentation.generatorEntry;

import javafx.beans.property.*;

public class GeneratorEntryModel {
    private final StringProperty imagePath = new SimpleStringProperty();
    private final IntegerProperty amount = new SimpleIntegerProperty();
    private final IntegerProperty suProduced = new SimpleIntegerProperty();

    public IntegerProperty suProducedProperty() {
        return suProduced;
    }

    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }
}
