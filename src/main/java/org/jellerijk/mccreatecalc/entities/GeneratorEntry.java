package org.jellerijk.mccreatecalc.entities;

public class GeneratorEntry implements SUProducer {
    private final Generator generator;
    private final int amount;

    public GeneratorEntry(Generator generator, int amount) {
        if (generator == null) throw new IllegalArgumentException("Generator was null");
        if (amount < 1) throw new IllegalArgumentException("Amount needs to be at least 1");
        this.generator = generator;
        this.amount = amount;
    }

    @Override
    public int calculateSUProduced() {
        return getAmount() * generator.getSuGeneration();
    }

    public Generator getGenerator() {
        return generator;
    }

    public int getAmount() {
        return amount;
    }
}
