package org.jellerijk.mccreatecalc.entities.components;

public enum ComponentType {
    WATER_WHEEL, WINDMILL, STEAM_ENGINE, CONSUMER;

    public static ComponentType of(Component c) {
        return switch (c) {
            case Consumer _ -> ComponentType.CONSUMER;
            case WaterWheel _ -> ComponentType.WATER_WHEEL;
            case Windmill _ -> ComponentType.WINDMILL;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
