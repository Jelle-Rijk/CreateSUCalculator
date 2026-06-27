package org.jellerijk.mccreatecalc.presentation.componentgroup;

import javafx.beans.property.*;

public class ComponentGroupModel {
    private final StringProperty componentName = new SimpleStringProperty();
    private final IntegerProperty componentAmount = new SimpleIntegerProperty();
    private final IntegerProperty sails = new SimpleIntegerProperty();
    private final IntegerProperty rpm = new SimpleIntegerProperty();
    private final StringProperty level = new SimpleStringProperty();
    private final IntegerProperty su = new SimpleIntegerProperty();
    private final StringProperty groupId = new SimpleStringProperty();
    private final BooleanProperty needsSails = new SimpleBooleanProperty();
    private final BooleanProperty needsRpm = new SimpleBooleanProperty();
    private final BooleanProperty needsLevel = new SimpleBooleanProperty();

    public void setNeedsSails(boolean needsSails) {
        this.needsSails.set(needsSails);
    }

    public void setNeedsRpm(boolean needsRpm) {
        this.needsRpm.set(needsRpm);
    }

    public void setNeedsLevel(boolean needsLevel) {
        this.needsLevel.set(needsLevel);
    }

    public boolean isNeedsSails() {
        return needsSails.get();
    }

    public BooleanProperty needsSailsProperty() {
        return needsSails;
    }

    public boolean isNeedsRpm() {
        return needsRpm.get();
    }

    public BooleanProperty needsRpmProperty() {
        return needsRpm;
    }

    public boolean isNeedsLevel() {
        return needsLevel.get();
    }

    public BooleanProperty needsLevelProperty() {
        return needsLevel;
    }

    public String getComponentName() {
        return componentName.get();
    }

    public StringProperty componentNameProperty() {
        return componentName;
    }

    public int getComponentAmount() {
        return componentAmount.get();
    }

    public IntegerProperty componentAmountProperty() {
        return componentAmount;
    }

    public int getSails() {
        return sails.get();
    }

    public IntegerProperty sailsProperty() {
        return sails;
    }

    public int getRpm() {
        return rpm.get();
    }

    public IntegerProperty rpmProperty() {
        return rpm;
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public int getSu() {
        return su.get();
    }

    public IntegerProperty suProperty() {
        return su;
    }

    public String getGroupId() {
        return groupId.get();
    }

    public StringProperty groupIdProperty() {
        return groupId;
    }
}
