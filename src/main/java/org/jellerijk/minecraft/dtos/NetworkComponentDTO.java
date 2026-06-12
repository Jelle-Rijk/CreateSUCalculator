package org.jellerijk.minecraft.dtos;

import org.jellerijk.minecraft.model.components.Component;

public record NetworkComponentDTO(String type, String name, String imgPath, int rpm, int stressImpact, int su,
                                  int minRpm, boolean hasMinimumRequiredRpm) {
    public static NetworkComponentDTO from(Component c) {
        String type = c.isGenerator() ? "generator" : "consumer";
        return new NetworkComponentDTO(type, c.getName(), c.getImgPath(), c.getRpm(), c.getStressImpact(), c.calculateSU(), c.getMinRpm(), c.hasMinRequiredRpm());
    }
}
