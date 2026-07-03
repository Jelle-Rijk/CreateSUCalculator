package org.jellerijk.mccreatecalc.presentation.networkdetails;

import javafx.scene.control.ListCell;
import org.jellerijk.mccreatecalc.application.dto.ComponentGroupDTO;
import org.jellerijk.mccreatecalc.application.services.UseCaseFactory;
import org.jellerijk.mccreatecalc.presentation.componentgroup.ComponentGroupController;

public class ComponentGroupListCell extends ListCell<ComponentGroupDTO> {
    private final ComponentGroupController controller;

    public ComponentGroupListCell(UseCaseFactory factory) {
        controller = new ComponentGroupController(factory);
    }

    @Override
    protected void updateItem(ComponentGroupDTO data, boolean empty) {
        super.updateItem(data, empty);
        if (empty || data == null) {
            setGraphic(null);
            setText(null);
        } else {
            controller.setGroupId(data.id());
            setGraphic(controller.getView());
        }
    }
}
