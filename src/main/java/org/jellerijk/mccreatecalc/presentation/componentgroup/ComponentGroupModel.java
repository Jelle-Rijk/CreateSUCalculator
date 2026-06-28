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
    private final StringProperty image = new SimpleStringProperty();

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setGroupId(String groupId) {
        this.groupId.set(groupId);
    }

    public void setNeedsSails(boolean needsSails) {
        this.needsSails.set(needsSails);
    }

    public void setNeedsRpm(boolean needsRpm) {
        this.needsRpm.set(needsRpm);
    }

    public void setNeedsLevel(boolean needsLevel) {
        this.needsLevel.set(needsLevel);
    }

    public BooleanProperty needsSailsProperty() {
        return needsSails;
    }

    public BooleanProperty needsRpmProperty() {
        return needsRpm;
    }

    public StringProperty componentNameProperty() {
        return componentName;
    }

    public IntegerProperty componentAmountProperty() {
        return componentAmount;
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

    public StringProperty levelProperty() {
        return level;
    }

    public IntegerProperty suProperty() {
        return su;
    }

    public String getGroupId() {
        return groupId.get();
    }

}
