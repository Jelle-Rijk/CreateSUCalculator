package org.jellerijk.mccreatecalc.entities.components;

public class WaterWheel extends BaseComponent implements Generator {

    private final int suGeneration;
    private final int rpm;

    public WaterWheel(WaterWheelType waterWheelType) {
        super(waterWheelType.getName(), waterWheelType.getImg());
        this.rpm = waterWheelType.getRpm();
        this.suGeneration = waterWheelType.getSuProduction();
    }

    public WaterWheel(String name, String img, int suGeneration, int rpm) {
        super(name, img);
        validateSU(suGeneration);
        validateRPM(rpm);
        this.rpm = rpm;
        this.suGeneration = suGeneration;
    }

    private void validateRPM(int rpm) {
        if (rpm < 0)
            throw new IllegalArgumentException("RPM cannot be a negative number.");
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

    public static final class Builder {
        private String img;
        private String name;
        private int suGeneration;
        private int rpm;

        private Builder() {
        }

        public static Builder aConstantGenerator() {
            return new Builder();
        }

        public WaterWheel build() {
            return new WaterWheel(name, img, suGeneration, rpm);
        }

        public Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSuGeneration(int suGeneration) {
            this.suGeneration = suGeneration;
            return this;
        }

        public Builder withRpm(int rpm) {
            this.rpm = rpm;
            return this;
        }
    }
}
