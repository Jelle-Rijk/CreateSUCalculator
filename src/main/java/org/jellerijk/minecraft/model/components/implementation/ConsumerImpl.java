package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.Consumer;

public class ConsumerImpl extends StandardComponent implements Consumer {
    private final int stressImpact;
    private final int minRPM;

    public ConsumerImpl(String name, String imgPath, int rpm, int stressImpact) {
        this(name, imgPath, rpm, stressImpact, 0);
    }

    public ConsumerImpl(String name, String imgPath, int rpm, int stressImpact, int minRPM) {
        super(name, imgPath, rpm);
        validateRPM(minRPM);
        if (stressImpact < 0)
            throw new IllegalArgumentException("Stress impact of a consumer cannot be negative.");
        this.stressImpact = stressImpact;
        this.minRPM = minRPM;
    }

    @Override
    public int calculateSU() {
        return getStressImpact() * getRPM();
    }

    @Override
    public int getStressImpact() {
        return stressImpact;
    }

    @Override
    public int getMinRPM() {
        return minRPM;
    }

    @Override
    public boolean hasMinRPMRequired() {
        return getRPM() >= getMinRPM();
    }
}
