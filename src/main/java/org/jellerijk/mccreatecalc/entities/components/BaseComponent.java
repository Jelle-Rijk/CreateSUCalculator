package org.jellerijk.mccreatecalc.entities.components;

import org.jellerijk.mccreatecalc.entities.Component;

import java.util.Optional;

public abstract class BaseComponent implements Component {
    private final String img;
    private final String name;
    private static final String DEFAULT_IMG = "create_cuckoo_clock.png";

    public BaseComponent(String name, String img) {
        this.name = name;
        this.img = img;
        validateName(name);
        validateImg(img);
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        if (img == null)
            return DEFAULT_IMG;
        return img;
    }

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

}
