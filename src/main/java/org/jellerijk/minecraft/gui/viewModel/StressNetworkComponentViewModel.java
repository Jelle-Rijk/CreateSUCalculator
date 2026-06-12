package org.jellerijk.minecraft.gui.viewModel;

import javafx.beans.property.*;
import org.jellerijk.minecraft.dtos.NetworkComponentDTO;

public class StressNetworkComponentViewModel {
    private final IntegerProperty amount = new SimpleIntegerProperty();
    private final BooleanProperty hasMinRPM = new SimpleBooleanProperty();
    private final StringProperty imgPath = new SimpleStringProperty();
    private final IntegerProperty minRpm = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty rpm = new SimpleIntegerProperty();
    private final IntegerProperty stressImpact = new SimpleIntegerProperty();
    private final IntegerProperty totalStressUnits = new SimpleIntegerProperty();
    private final StringProperty type = new SimpleStringProperty();

    public StressNetworkComponentViewModel(NetworkComponentDTO dto, int amount) {
    }

    //===== Public methods =====
    public IntegerProperty amountProperty() {
        return amount;
    }

    public int getAmount() {
        return amount.get();
    }

    public String getImgPath() {
        return imgPath.get();
    }

    public int getMinRpm() {
        return minRpm.get();
    }

    public String getName() {
        return name.get();
    }

    public int getRpm() {
        return rpm.get();
    }

    public int getStressImpact() {
        return stressImpact.get();
    }

    public int getTotalStressUnits() {
        return totalStressUnits.get();
    }

    public String getType() {
        return type.get();
    }

    public BooleanProperty hasMinRPMProperty() {
        return hasMinRPM;
    }

    public StringProperty imgPathProperty() {
        return imgPath;
    }

    public boolean isHasMinRPM() {
        return hasMinRPM.get();
    }

    public IntegerProperty minRpmProperty() {
        return minRpm;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty rpmProperty() {
        return rpm;
    }

    public IntegerProperty stressImpactProperty() {
        return stressImpact;
    }

    public IntegerProperty totalStressUnitsProperty() {
        return totalStressUnits;
    }

    public StringProperty typeProperty() {
        return type;
    }

    //===== Private methods =====
    private void setProperties(NetworkComponentDTO dto, int amount) {
        type.set(dto.type());
        name.set(dto.name());
        imgPath.set(dto.imgPath());
        rpm.set(dto.rpm());
        stressImpact.set(dto.stressImpact());
        totalStressUnits.set(dto.su());
        minRpm.set(dto.minRpm());
        hasMinRPM.set(dto.hasMinimumRequiredRpm());
    }
}
