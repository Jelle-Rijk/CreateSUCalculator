package org.jellerijk.mccreatecalc.entities;

public class GeneratorTestBuilder {
    public static final String DEFAULT_IMG = "test.png";
    public static final String DEFAULT_NAME = "Test-Generator";
    public static final int DEFAULT_SU = 512;

    private String name;
    private String img;
    private int suGeneration;

    //===== Static methods =====
    public static GeneratorTestBuilder defaultGenerator() {
        return new GeneratorTestBuilder().withName(DEFAULT_NAME).withImg(DEFAULT_IMG).withSuGeneration(DEFAULT_SU);
    }

    //===== Public methods =====
    public Generator build() {
        return new Generator(name, img, suGeneration);
    }

    public GeneratorTestBuilder withImg(String img) {
        this.img = img;
        return this;
    }

    public GeneratorTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GeneratorTestBuilder withSuGeneration(int suGeneration) {
        this.suGeneration = suGeneration;
        return this;
    }
}
