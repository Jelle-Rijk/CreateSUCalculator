package org.jellerijk.mccreatecalc.entities.components;

import org.jellerijk.mccreatecalc.entities.Generator;

public class ConstantGenerator extends BaseComponent implements Generator {

    private final int suGeneration;
    private final int rpm;

    public ConstantGenerator(String name, String img, int suGeneration, int rpm) {
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
    public int calculateSuProduced() {
        return getSuGeneration();
    }

    @Override
    public int getRPM() {
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

        public ConstantGenerator build() {
            return new ConstantGenerator(name, img, suGeneration, rpm);
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
