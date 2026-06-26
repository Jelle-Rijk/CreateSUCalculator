package org.jellerijk.mccreatecalc.entities;

import org.jellerijk.mccreatecalc.entities.components.ConstantGenerator;

public class GeneratorEntry implements SUProducer {
    private final ConstantGenerator generator;
    private final int amount;

    public GeneratorEntry(ConstantGenerator generator, int amount) {
        if (generator == null) throw new IllegalArgumentException("ConstantGenerator was null");
        if (amount < 1) throw new IllegalArgumentException("Amount needs to be at least 1");
        this.generator = generator;
        this.amount = amount;
    }

    @Override
    public int calculateSUProduced() {
        return getAmount() * generator.getSuGeneration();
    }

    public ConstantGenerator getGenerator() {
        return generator;
    }

    public int getAmount() {
        return amount;
    }
}
