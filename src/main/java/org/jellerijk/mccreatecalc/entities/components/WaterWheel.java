package org.jellerijk.mccreatecalc.entities.components;

public class WaterWheel extends BaseComponent implements Generator {

    private final int suGeneration;
    private final int rpm;
    private final WaterWheelType size;

    public WaterWheel(WaterWheelType waterWheelType) {
        super(waterWheelType.getName(), waterWheelType.getImg());
        super.validateRpm(waterWheelType.getRpm());
        validateSU(waterWheelType.getSuProduction());
        this.rpm = waterWheelType.getRpm();
        this.suGeneration = waterWheelType.getSuProduction();
        this.size = waterWheelType;
    }

    //===== Public methods =====
    @Override
    public int getSuProduction() {
        return getSuGeneration();
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    public int getSuGeneration() {
        return suGeneration;
    }

    //===== Private methods =====


    private void validateSU(int suGeneration) {
        if (suGeneration < 0)
            throw new IllegalArgumentException("Stress unit generation needs to be a positive integer.");
    }


    public WaterWheelType getSize() {
        return size;
    }
}
