package org.jellerijk.mccreatecalc.presentation.componentgroup;

import javafx.beans.property.*;

public class ComponentGroupModel {
    private final IntegerProperty componentAmount = new SimpleIntegerProperty();
    private final StringProperty componentName = new SimpleStringProperty();
    private final StringProperty groupId = new SimpleStringProperty();
    private final StringProperty image = new SimpleStringProperty();
    private final StringProperty level = new SimpleStringProperty();
    private final BooleanProperty needsLevel = new SimpleBooleanProperty();
    private final BooleanProperty needsRpm = new SimpleBooleanProperty();
    private final BooleanProperty needsSails = new SimpleBooleanProperty();
    private final IntegerProperty rpm = new SimpleIntegerProperty();
    private final IntegerProperty sails = new SimpleIntegerProperty();
    private final IntegerProperty su = new SimpleIntegerProperty();

    //===== Public methods =====
    public IntegerProperty componentAmountProperty() {
        return componentAmount;
    }

    public StringProperty componentNameProperty() {
        return componentName;
    }

    public String getImage() {
        return image.get();
    }

    public int getRpm() {
        return rpm.get();
    }

    public StringProperty groupIdProperty() {
        return groupId;
    }

    public StringProperty imageProperty() {
        return image;
    }

    public StringProperty levelProperty() {
        return level;
    }

    public BooleanProperty needsRpmProperty() {
        return needsRpm;
    }

    public BooleanProperty needsSailsProperty() {
        return needsSails;
    }

    public IntegerProperty rpmProperty() {
        return rpm;
    }

    public IntegerProperty sailsProperty() {
        return sails;
    }

    public void setGroupId(String groupId) {
        this.groupId.set(groupId);
    }

    public void setNeedsLevel(boolean needsLevel) {
        this.needsLevel.set(needsLevel);
    }

    public void setNeedsRpm(boolean needsRpm) {
        this.needsRpm.set(needsRpm);
    }

    public void setNeedsSails(boolean needsSails) {
        this.needsSails.set(needsSails);
    }

    public IntegerProperty suProperty() {
        return su;
    }

}
