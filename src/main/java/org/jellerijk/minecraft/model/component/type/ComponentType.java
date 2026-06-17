package org.jellerijk.minecraft.model.component.type;

import java.util.Optional;

public interface ComponentType {
    String getName();

    String getImgPath();

    int getStressImpact();

    boolean isGenerator();

    int getMinRpm();
}
