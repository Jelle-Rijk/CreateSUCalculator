package org.jellerijk.mccreatecalc.presentation.componentgroup;

import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.GetComponentGroupUC;
import org.jellerijk.mccreatecalc.entities.ComponentGroup;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public class ComponentGroupInteractor {
    private final ComponentGroupModel model;
    private final GetComponentGroupUC componentGroupFetcher;

    public ComponentGroupInteractor(ComponentGroupModel model, UseCaseFactory factory) {
        this.model = model;
        this.componentGroupFetcher = factory.buildGetComponentGroupUC();
        updateComponentGroupData();
    }

    /**
     * Fetches and sets the data for the component group that is associated with this interactor's {@link ComponentGroupModel}.
     */
    public void updateComponentGroupData() {
        ComponentGroup componentGroup = componentGroupFetcher.execute(model.getGroupId());
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
        model.imageProperty().set(componentGroup.img());
        model.componentNameProperty().set(componentGroup.name());
        model.componentAmountProperty().set(componentGroup.amount());
        model.suProperty().set(componentGroup.su());
    }

    public void submitChanges() {
        System.out.println("ComponentGroupInteractor: Changes submitted.");
    }

    public void deleteGroup() {
        System.out.println("ComponentGroupInteractor: Group deleted");
    }
}
