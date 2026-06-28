package org.jellerijk.mccreatecalc.presentation.componentgroup;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public class ComponentGroupInteractor {
    private final ComponentGroupModel model;

    public ComponentGroupInteractor(ComponentGroupModel model, UseCaseFactory factory, ComponentGroup componentGroup) {
        this.model = model;
        setModelProperties(componentGroup);
    }

    private void setModelProperties(ComponentGroup componentGroup) {
        ComponentType type = componentGroup.type();
        switch (type) {
            case CONSUMER -> {
                model.setNeedsRpm(true);
                model.rpmProperty().set(componentGroup.rpm());
            }
            case WINDMILL -> {
                model.setNeedsSails(true);
                model.sailsProperty().set(componentGroup.sails());
            }
            case STEAM_ENGINE -> {
                model.setNeedsLevel(true);
                model.levelProperty().set(componentGroup.level());
            }
        }
        model.componentAmountProperty().set(componentGroup.amount());
        model.suProperty().set(componentGroup.su());
    }
}
