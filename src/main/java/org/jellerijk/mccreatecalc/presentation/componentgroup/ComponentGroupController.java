package org.jellerijk.mccreatecalc.presentation.componentgroup;

import javafx.concurrent.Task;
import javafx.scene.Node;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.Controller;

public class ComponentGroupController extends Controller {
    private final ComponentGroupInteractor interactor;
    private final ComponentGroupViewBuilder viewBuilder;
    private final ComponentGroupModel model;

    public ComponentGroupController(UseCaseFactory factory) {
        model = new ComponentGroupModel();
        interactor = new ComponentGroupInteractor(model, factory);
        viewBuilder = new ComponentGroupViewBuilder(model, this::delete, this::submitChanges);
    }

    public void setGroupId(String id) {
        model.setGroupId(id);
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
