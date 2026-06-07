package org.jellerijk.minecraft.views;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import org.jellerijk.minecraft.viewModel.ComponentViewModel;

import java.util.HashMap;
import java.util.Map;

public class ComponentSelector extends ComboBox<ComponentViewModel> {
    private Map<String, ComponentView> componentViews;


    public ComponentSelector(ObservableList<ComponentViewModel> viewModels) {
        componentViews = new HashMap<>();
        for (ComponentViewModel component : viewModels)
            componentViews.put(component.getName(), new ComponentView(component, 24));

        setItems(viewModels);
        setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(ComponentViewModel item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(getComponentView(item.getName()));
                }
            }
        });
    }

    private ComponentView getComponentView(String componentName) {
        return componentViews.get(componentName);
    }
}
