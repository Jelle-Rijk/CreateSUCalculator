package org.jellerijk.minecraft.views;

import javafx.scene.control.Label;
import org.jellerijk.minecraft.viewModel.ComponentViewModel;
import org.jellerijk.minecraft.util.fxlib.ImageViews;

public class ComponentView extends Label {
    private static final double DEFAULT_ICON_SIZE = 16;

    public ComponentView(ComponentViewModel viewModel) {
        this(viewModel, DEFAULT_ICON_SIZE);
    }

    public ComponentView(ComponentViewModel viewModel, double iconSize) {
        getStyleClass().add("component-name");
        textProperty().bind(viewModel.nameProperty());
        setGraphic(ImageViews.squareIcon(viewModel.getImagePath(), iconSize));
    }
}
