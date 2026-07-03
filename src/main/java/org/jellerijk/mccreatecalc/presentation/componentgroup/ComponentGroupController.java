package org.jellerijk.mccreatecalc.presentation.componentgroup;

import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;

public class ComponentGroupController extends Controller {
    private final ComponentGroupInteractor interactor;
    private final ComponentGroupViewBuilder viewBuilder;

    public ComponentGroupController(UseCaseFactory factory, String groupId) {
        ComponentGroupModel model = new ComponentGroupModel();
        model.setGroupId(groupId);
        interactor = new ComponentGroupInteractor(model, factory);
        viewBuilder = new ComponentGroupViewBuilder(model, this::delete, this::submitChanges);
    }

    @Override
    public Node getView() {
        return viewBuilder.build();
    }

    private void submitChanges() {
        Task<Void> changeSubmit = new Task<>() {
            @Override
            protected Void call() {
                interactor.submitChanges();
                return null;
            }
        };
        startTaskOnNewThread(changeSubmit);
    }

    private void delete() {
        Task<Void> deleteTask = new Task<>() {
            @Override
            protected Void call() {
                interactor.deleteGroup();
                return null;
            }
        };
        //TODO : implement removing the card from the list.
        startTaskOnNewThread(deleteTask);
    }
}
