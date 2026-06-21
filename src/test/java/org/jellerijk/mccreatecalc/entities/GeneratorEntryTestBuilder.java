package org.jellerijk.mccreatecalc.entities;

class GeneratorEntryTestBuilder {
    public static final Generator DEFAULT_GENERATOR = GeneratorTestBuilder.defaultGenerator().build();
    public static final int DEFAULT_AMOUNT = 4;

    private Generator generator;
    private int amount;

    public static GeneratorEntryTestBuilder defaultGeneratorEntry() {
        return new GeneratorEntryTestBuilder().withGenerator(DEFAULT_GENERATOR).withAmount(DEFAULT_AMOUNT);
    }

    public GeneratorEntryTestBuilder withGenerator(Generator generator) {
        this.generator = generator;
        return this;
    }

    public GeneratorEntryTestBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public GeneratorEntry build() {
        return new GeneratorEntry(generator, amount);
    }
}
