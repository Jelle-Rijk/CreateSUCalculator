package org.jellerijk.minecraft.viewModel;

import javafx.beans.property.StringProperty;

public interface ComponentViewModel {
    /**
     * @return Path to this component's image.
     */
    String getImagePath();

    /**
     * @return StringProperty containing the Generator's name.
     */
    StringProperty nameProperty();

    /**
     * @return The value of the nameProperty.
     */
    default String getName() {
        return nameProperty().get();
    }


}
