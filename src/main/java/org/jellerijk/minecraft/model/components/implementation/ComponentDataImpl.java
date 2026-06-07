package org.jellerijk.minecraft.model.components.implementation;

import org.jellerijk.minecraft.model.components.ComponentData;

import java.util.Objects;

public class ComponentDataImpl implements ComponentData {
    private final String imgPath;
    private final String name;

    public ComponentDataImpl(String name, String imgPath) {
        validateName(name);
        validateImgPath(imgPath);
        this.name = name;
        this.imgPath = imgPath;
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
    public int hashCode() {
        return Objects.hash(name, imgPath);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ComponentDataImpl that = (ComponentDataImpl) o;
        return Objects.equals(name, that.name) && Objects.equals(imgPath, that.imgPath);
    }

    private void validateImgPath(String imgPath) {
        if (imgPath == null || imgPath.isBlank()) throw new IllegalArgumentException("Image path was null or blank");
        if (!imgPath.matches("^.*\\.[A-Za-z]+$")) throw new IllegalArgumentException("Invalid image path.");
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Every component needs a name.");
    }
}
