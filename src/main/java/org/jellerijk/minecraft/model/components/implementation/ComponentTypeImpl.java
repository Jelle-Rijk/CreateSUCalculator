package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.ComponentType;

import java.util.Objects;
import java.util.Optional;

public class ComponentTypeImpl implements ComponentType {
    private final String imgPath;
    private final String name;
    private final int stressImpact;
    private final boolean generator;
    private final int minRpm;
    private final Integer rpmConstant;

    public ComponentTypeImpl(String name, String imgPath, int stressImpact, boolean generator, int minRpm, Integer rpmConstant) {
        validateName(name);
        validateImgPath(imgPath);
        validateRPM(minRpm);
        validateStressImpact(stressImpact);
        validateConstant(rpmConstant);
        this.name = name;
        this.imgPath = imgPath;
        this.minRpm = minRpm;
        this.stressImpact = stressImpact;
        this.generator = generator;
        this.rpmConstant = rpmConstant;
    }

    private void validateConstant(Integer rpmConstant) {
        if (rpmConstant != null)
            validateRPM(rpmConstant);
    }

    private void validateStressImpact(int stressImpact) {
        if (stressImpact < 0)
            throw new IllegalArgumentException("Stress impact cannot be a negative number.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
    public int getStressImpact() {
        return stressImpact;
    }

    @Override
    public boolean isGenerator() {
        return generator;
    }

    @Override
    public int getMinRpm() {
        return minRpm;
    }

    @Override
    public Optional<Integer> getRpmConstant() {
        return Optional.ofNullable(rpmConstant);
    }

    private void validateImgPath(String imgPath) {
        if (imgPath == null || imgPath.isBlank()) throw new IllegalArgumentException("Image path was null or blank");
        if (!imgPath.matches("^.*\\.[A-Za-z]+$")) throw new IllegalArgumentException("Invalid image path.");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Every component needs a name.");
    }

    protected void validateRPM(int rpm) {
        if (rpm < 0)
            throw new IllegalArgumentException("RPM cannot be a negative number.");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ComponentTypeImpl that = (ComponentTypeImpl) o;
        return getStressImpact() == that.getStressImpact() && isGenerator() == that.isGenerator() && getMinRpm() == that.getMinRpm() && Objects.equals(getImgPath(), that.getImgPath()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImgPath(), getName(), getStressImpact(), isGenerator(), getMinRpm());
    }
}
