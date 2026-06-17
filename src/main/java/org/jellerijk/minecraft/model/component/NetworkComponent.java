package org.jellerijk.minecraft.model.component;

public interface NetworkComponent {
    String getName();

    String getImgPath();

    int getStressImpact();

    boolean isGenerator();

    int getMinRpm();

    int getRpm();

    int calculateSU();

    boolean hasMinRequiredRpm();
}
