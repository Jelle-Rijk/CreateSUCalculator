package org.jellerijk.mccreatecalc.entities.components;

public class BaseConsumer extends BaseComponent implements Consumer {
    private final int rpm;
    public BaseConsumer(String name, String img, int rpm) {
        super(name, img);
        validateRPM
    }

    @Override
    public int getSuConsumption() {
        return 0;
    }
}