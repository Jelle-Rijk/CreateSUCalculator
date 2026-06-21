package org.jellerijk.mccreatecalc.entities;

import java.util.Optional;

public class Generator implements SUProducer {
    private final String img;
    private final String name;
    private final int suGeneration;

    public Generator(String name, String img, int suGeneration) {
        validateName(name);
        validateImg(img);
        validateSU(suGeneration);
        this.name = name;
        this.img = img;
        this.suGeneration = suGeneration;
    }

    //===== Public methods =====
    public Optional<String> getImg() {
        return Optional.ofNullable(img);
    }

    @Override
    public int calculateSUProduced() {
        return getSuGeneration();
    }

    public String getName() {
        return name;
    }

    public int getSuGeneration() {
        return suGeneration;
    }

    //===== Private methods =====
    private void validateImg(String img) {
        if (img == null)
            return;
        if (!img.matches("^\\S+\\.\\w+$"))
            throw new IllegalArgumentException(String.format("Invalid img name: %s", img));
    }

    private void validateName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");
    }

    private void validateSU(int suGeneration) {
        if (suGeneration < 0)
            throw new IllegalArgumentException("Stress unit generation needs to be a positive integer.");
    }

}
