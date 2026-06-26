package org.jellerijk.mccreatecalc.entities.components;

public enum WaterWheel {
    SMALL("Water Wheel", "create_water_wheel.png", 256, 8), LARGE("Large Water Wheel", "create_large_water_wheel.png", 512, 4);

    private final String name;
    private final String img;
    private final int suProduction;
    private final int rpm;

    WaterWheel(String name, String img, int suProduction, int rpm) {
        this.name = name;
        this.img = img;
        this.suProduction = suProduction;
        this.rpm = rpm;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getSuProduction() {
        return suProduction;
    }

    public int getRpm() {
        return rpm;
    }

}
