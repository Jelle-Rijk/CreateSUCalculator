package org.jellerijk.minecraft.model.components;

import java.util.Optional;

public interface ComponentType {
    String getName();

    String getImgPath();

    int getStressImpact();

    boolean isGenerator();

    int getMinRpm();

    Optional<Integer> getRpmConstant();
}
