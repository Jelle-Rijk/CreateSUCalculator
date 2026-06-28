package org.jellerijk.mccreatecalc.entities.components;

public class BaseConsumer extends BaseComponent implements Consumer {
    private final int rpm;
    private final int stressImpact;

    public BaseConsumer(String name, String img, int rpm, int stressImpact) {
        super(name, img);
        validateRpm(rpm);
        validateStressImpact(stressImpact);
        this.rpm = rpm;
        this.stressImpact = stressImpact;

    }

    private void validateStressImpact(int stressImpact) {
        if (stressImpact < 0)
            throw new IllegalArgumentException("Stress impact cannot be negative");
    }

    @Override
    public int getRpm() {
        return rpm;
    }

    @Override
    public int getSuConsumption() {
        return rpm * stressImpact;
    }

    @Override
    public int getStressImpact() {
        return stressImpact;
    }


    public static final class Builder {
        private String img;
        private String name;
        private int rpm;
        private int stressImpact;

        private Builder() {
        }

        public static Builder aBaseConsumer() {
            return new Builder();
        }

        public BaseConsumer build() {
            return new BaseConsumer(name, img, rpm, stressImpact);
        }

        public Builder withImg(String img) {
            this.img = img;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRpm(int rpm) {
            this.rpm = rpm;
            return this;
        }

        public Builder withStressImpact(int stressImpact) {
            this.stressImpact = stressImpact;
            return this;
        }
    }
}