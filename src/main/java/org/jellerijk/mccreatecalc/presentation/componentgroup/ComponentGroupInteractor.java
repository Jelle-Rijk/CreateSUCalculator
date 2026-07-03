package org.jellerijk.mccreatecalc.presentation.componentgroup;

import org.jellerijk.mccreatecalc.application.publishers.Observer;
import org.jellerijk.mccreatecalc.application.publishers.Subscription;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.application.usecases.ObserveComponentGroupUC;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.entities.components.ComponentType;

public class ComponentGroupInteractor implements Observer<ComponentGroupDTO> {
    private final ComponentGroupModel model;
    private final ObserveComponentGroupUC observeComponentGroupUC;

    public ComponentGroupInteractor(ComponentGroupModel model, UseCaseFactory factory) {
        this.model = model;
        model.groupIdProperty().addListener((_, oldId, newId) -> handleGroupIdChange(oldId, newId));
        observeComponentGroupUC = factory.buildObserveComponentGroupUC();
    }

    private void handleGroupIdChange(String oldId, String newId) {
        System.out.printf("ComponentGroupInteractor: Unsubscribing from %s (not implemented yet)%n", oldId);
        observeComponentGroupUC.execute(new Subscription<>(this, newId));

    }

    @Override
    public void update(ComponentGroupDTO message) {
        updateComponentGroupData(message);
    }

    /**
     * Fetches and sets the data for the component group that is associated with this interactor's {@link ComponentGroupModel}.
     */
    public void updateComponentGroupData(ComponentGroupDTO componentGroupDTO) {
        ComponentType type = componentGroupDTO.type();
        switch (type) {
            case CONSUMER -> {
                model.setNeedsRpm(true);
                model.rpmProperty().set(componentGroupDTO.rpm());
            }
            case WINDMILL -> {
                model.setNeedsSails(true);
                model.sailsProperty().set(componentGroupDTO.sails());
            }
            case STEAM_ENGINE -> {
                model.setNeedsLevel(true);
                model.levelProperty().set(componentGroupDTO.level());
            }
        }
        model.imageProperty().set(componentGroupDTO.img());
        model.componentNameProperty().set(componentGroupDTO.name());
        model.componentAmountProperty().set(componentGroupDTO.amount());
        model.suProperty().set(componentGroupDTO.su());
    }

    public void submitChanges() {
        System.out.println("ComponentGroupInteractor: Changes submitted.");
    }

    public void deleteGroup() {
        System.out.println("ComponentGroupInteractor: Group deleted");
    }
}
